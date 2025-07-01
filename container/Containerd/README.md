## Containerd

### 一、简介

#### 1. 前言

- 早在2016年3月，Docker 1.11 的 Docker Engine 里就包含了 containerd，而现在则是把 containerd 从 Docker Engine 里彻底剥离出来，作为一个独立的开源项目发展，目标是提供一个更加开放、稳定的容器运行基础设施。和原先包含在 Docker Engine 里 containerd 相比，独立的 containerd 将具有更多的功能，可以涵盖整个容器运行时管理的所有需求。
- containerd 并不直接面向用户，而是主要用于集成到更上层的系统里，比如 Swarm, Kubernetes , Mesos 等容器编排系统。
- containerd 以 Daemon 的形式运行在系统上，通过暴露底层的 gRPC API，上层系统可以通过这些API管理对应的容器。
- 每个 containerd 只负责一台机器，Pull 镜像，对容器的启动、停止等操作，网络，存储都是由 containerd 完成。具体运行容器由 runC 负责，实际上只要是符合OCI规范的容器都可以支持。
- 对于容器编排服务来说，运行时只需要使用 containerd + runC，更加轻量，容易管理。
- 独立之后 containerd 的特性演进可以和 Docker Engine 分开，更加专注于专注容器运行时管理。



#### 2. 架构

![1.架构图](static/1.架构图/1.架构图.png)



简要版

![2.架构图-简要版](static/1.架构图/2.架构图-简要版.png)



### 二、安装

*以 Ubuntu 20.04 LTS 为例*

#### 1. 安装依赖

```bash
sudo apt update
sudo apt install -y ca-certificates curl gnupg lsb-release
```



#### 2. 添加 Docker 官方 GPG 密钥

```bash
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
```



#### 3. 添加仓库

```bash
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```



#### 4. 安装

```bash
sudo apt update
sudo apt install -y containerd.io

# 查看版本
containerd -V
```



### 三、镜像管理

*containerd 使用 ctr 命令，即：containerd  CLI*

#### 1. ctr images help

```bash
root@suaxi:~# ctr images -h
NAME:
   ctr images - Manage images

USAGE:
   ctr images command [command options] [arguments...]

COMMANDS:
   check                    Check existing images to ensure all content is available locally
   export                   Export images
   import                   Import images
   list, ls                 List images known to containerd
   mount                    Mount an image to a target path
   unmount                  Unmount the image from the target
   pull                     Pull an image from a remote
   push                     Push an image to a remote
   prune                    Remove unused images
   delete, del, remove, rm  Remove one or more images by reference
   tag                      Tag an image
   label                    Set and clear labels for an image
   convert                  Convert an image
   usage                    Display usage of snapshots for a given image ref

OPTIONS:
   --help, -h  show help
   
```



#### 2. 查看镜像

```bash
ctr images ls
```



#### 3. 拉取镜像

containerd 支持 oci 标准的镜像，所以可以直接使用 docker 官方或 dockerfile 构建的镜像。

```bash
# 拉取时镜像名需要写全量名称（源 + 镜像名:TAG）
ctr images pull --all-platforms docker.io/library/nginx:alpine

# 拉取指定平台的镜像（x86）
ctr images pull --platform linux/amd64 docker.io/library/nginx:alpine
```



#### 4. 删除镜像

```bash
ctr image rm docker.io/library/nginx:alpine
```



#### 5. 导出镜像

```bash
ctr i export --all-platforms nginx.img docker.io/library/nginx:alpine
```



#### 6. 导入镜像

```bash
# 以上一步导出的 nginx.img 镜像为例
ctr images import nginx.img unpacking docker.io/library/nginx:alpine
```



#### 7. 修改镜像 tag

```bash
# 将 docker.io/library/nginx:alpine 修改为 nginx:alpine-test
ctr images tag docker.io/library/nginx:alpine nginx:alpine-test
```

修改后检查镜像

```bash
ctr images check
```



### 四、容器管理

#### 1. ctr container help

```bash
root@suaxi:~# ctr container help
NAME:
   ctr containers - Manage containers

USAGE:
   ctr containers [global options] command [command options] [arguments...]

VERSION:
   1.7.27

COMMANDS:
   create                   Create container
   delete, del, remove, rm  Delete one or more existing containers
   info                     Get info about a container
   list, ls                 List containers
   label                    Set and clear labels for a container
   checkpoint               Checkpoint a container
   restore                  Restore a container from checkpoint

GLOBAL OPTIONS:
   --help, -h  show help

```



#### 2. 查看容器

```bash
ctr container ls
```



查看容器中正在跑着的进程

```bash
ctr task ls

# 简写
ctr t ls
```



#### 3. 创建静态容器

```bash
# 同上，c 为 container 的简写
ctr c create docker.io/library/nginx:alpine nginx-test

# 查看已创建的容器
ctr container ls
CONTAINER    IMAGE                             RUNTIME
nginx-test       docker.io/library/nginx:alpine    io.containerd.runc.v2

# 查看容器信息
ctr container info nginx-test
```



#### 4. 启动容器

```bash
# 启动task，即表时在容器中运行了进程（动态容器）
ctr task start -d nginx-test
```



#### 5. 进入容器

```bash
# --exec-id 表示为 exec 进程设置一个id（可以使用随机数，保证该id唯一即可）
ctr task exec --exec-id 1 nginx-test /bin/sh
```



#### 6. 直接运行动态容器

```bash
# --net-host 网络模式为 host
ctr run -d --net-host docker.io/library/nginx:alpine nginx-test
```



#### 7. 暂停容器

```bash
# 查看容器状态
ctr tasks ls
TASK        PID     STATUS
nginx-test  5687    RUNNING

# 暂停
ctr tasks pause nginx-test

# 再次查看状态
ctr tasks ls
TASK        PID     STATUS
nginx-test  5687    PAUSED
```



#### 8. 恢复容器

```bash
ctr tasks resume nginx-test

# 查看容器状态
ctr tasks ls
TASK        PID     STATUS
nginx-test  5687    RUNNING
```



#### 9. 停止容器

```bash
# 使用 kill 命令，在 ctr task -h 帮助文档中可查看详细信息
ctr tasks kill nginx-test

# 查看容器状态
ctr tasks ls
TASK        PID     STATUS
nginx-test  5687    STOPPED
```



#### 10. 删除容器

```bash
# 停止/删除 task
ctr tasks delete nginx-test

# 删除容器（需先完成上述步骤）
ctr container delete nginx-test
```

