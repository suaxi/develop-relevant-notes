## Podman

### 一、简介

#### 1. 概念

Podman 是一个无守护进程、开源、Linux原生的工具，遵循 OCI 开放容器倡议的容器合容器镜像，可以轻松的查找、运行、构建、共享和部署应用程序。与其他常见的容器（Docker、CRI-O、Containerd）类似，Podman 依赖于一个符合 OCI 规范的容器运行时（如 runc、crun、runv 等）来与操作系统交互并创建运行中的容器。



#### 2. 特性

Podman 容器支持 root / rootless 模式，其使用 libpod 库来管理整个容器生态系统，包括 Pod、容器、容器镜像和容器卷。

Linux 环境下提供管理容器的 RESTful API



### 二、安装和基础命令

#### 1. 安装

*以 Debian 12 为例*

```bash
apt install podman

root@debian:~# podman version
Client:       Podman Engine
Version:      4.3.1
API Version:  4.3.1
Go Version:   go1.19.8
Built:        Thu Jan  1 08:00:00 1970
OS/Arch:      linux/amd64

```



#### 2. 基础命令

##### 运行示例容器

```bash
# 以基础的 httpd 服务为例
podman run --name nginx-test -d -p 8080:80/tcp docker.io/nginx
```

注：该命令下容器以分离模式运行（-d），Podman 会在运行结束后打印容器ID



##### 列出正在运行的容器

```bash
podman ps

# 列出所有容器
podman ps -a
```



##### 检查运行中的容器

```bash
podman inspect <container>
```

注：由于容器以 rootless 模式运行，因此不会分配 IP 地址，inspect 信息中该项的值为 none



##### 测试 httpd 服务

```bash
root@debian:~# curl http://localhost:8080
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
html { color-scheme: light dark; }
body { width: 35em; margin: 0 auto;
font-family: Tahoma, Verdana, Arial, sans-serif; }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>

```



##### 查看容器日志

```bash
podman logs -f <container_id>
```

```bash
root@debian:~# podman logs nginx-test
/docker-entrypoint.sh: /docker-entrypoint.d/ is not empty, will attempt to perform configuration
/docker-entrypoint.sh: Looking for shell scripts in /docker-entrypoint.d/
/docker-entrypoint.sh: Launching /docker-entrypoint.d/10-listen-on-ipv6-by-default.sh
10-listen-on-ipv6-by-default.sh: info: Getting the checksum of /etc/nginx/conf.d/default.conf
10-listen-on-ipv6-by-default.sh: info: Enabled listen on IPv6 in /etc/nginx/conf.d/default.conf
/docker-entrypoint.sh: Sourcing /docker-entrypoint.d/15-local-resolvers.envsh
/docker-entrypoint.sh: Launching /docker-entrypoint.d/20-envsubst-on-templates.sh
/docker-entrypoint.sh: Launching /docker-entrypoint.d/30-tune-worker-processes.sh
/docker-entrypoint.sh: Configuration complete; ready for start up
2026/01/14 14:05:20 [notice] 1#1: using the "epoll" event method
2026/01/14 14:05:20 [notice] 1#1: nginx/1.29.4
2026/01/14 14:05:20 [notice] 1#1: built by gcc 14.2.0 (Debian 14.2.0-19) 
2026/01/14 14:05:20 [notice] 1#1: OS: Linux 6.1.0-41-amd64
2026/01/14 14:05:20 [notice] 1#1: getrlimit(RLIMIT_NOFILE): 1048576:1048576
2026/01/14 14:05:20 [notice] 1#1: start worker processes
2026/01/14 14:05:20 [notice] 1#1: start worker process 24
2026/01/14 14:05:20 [notice] 1#1: start worker process 25
2026/01/14 14:05:20 [notice] 1#1: start worker process 26
2026/01/14 14:05:20 [notice] 1#1: start worker process 27
10.88.0.1 - - [14/Jan/2026:14:05:38 +0000] "GET / HTTP/1.1" 200 615 "-" "curl/7.88.1" "-"

```



##### 查看容器信息和进程

```bash
root@debian:~# podman top nginx-test 
USER        PID         PPID        %CPU        ELAPSED          TTY         TIME        COMMAND
root        1           0           0.000       2m26.588290297s  ?           0s          nginx: master process nginx -g daemon off; 
nginx       24          1           0.000       2m26.588345391s  ?           0s          nginx: worker process 
nginx       25          1           0.000       2m26.588369296s  ?           0s          nginx: worker process 
nginx       26          1           0.000       2m26.588388167s  ?           0s          nginx: worker process 
nginx       27          1           0.000       2m26.588406535s  ?           0s          nginx: worker process 

```



##### 检查容器

对容器进行检查点操作会停止容器运行，并将容器内所有进程的状态写入磁盘，在有需要的时候可以恢复，并从检查点创建时的状态继续运行（此功能需要系统上安装 CRIU 3.11 或更高版本，**且不支持 rootless**）

```bash
sudo podman container checkpoint <container_id>
```



##### 恢复容器

创建过检查点的容器才支持快照

```bash
sudo podman container restore <container_id>
```



##### 迁移容器

创建检查点 ---> 将对应文件传输至目标主机 ---> 目标主机恢复容器

```bash
# 原主机
sudo podman container checkpoint <container_id> -e /tmp/checkpoint.tar.gz
scp /tmp/checkpoint.tar.gz <destination_host>:/tmp

# 目标主机
sudo podman container restore -i /tmp/checkpoint.tar.gz

```



#####  停止容器

```bash
podman stop <container_id>
```



##### 移除容器

```bash
podman rm <container_id>
```



### 三、对镜像进行签名和分发

*以 GNU Privacy Guard（GPG）密钥为例*

如果使用 Podman 在将镜像推送到镜像仓库前对其进行了签名，则拉取时的客户端必须配置为从远程服务器获取签名，未签名的镜像都将被拒绝

#### 1. 生成 GPG密钥

```bash
gpg --full-gen-key

# 验证密钥
gpg --list-keys xxx@gmail.com
```



#### 2. 拉取镜像

```bash
# 拉取镜像
podman pull docker://docker.io/alpine:latest

# 查看
podman images alpine

REPOSITORY                TAG         IMAGE ID      CREATED      SIZE
docker.io/library/alpine  latest      e7b39c54cdec  4 weeks ago  8.74 MB

# 自定义 tag（localhost:5000 为本地镜像仓库）
sudo podman tag alpine localhost:5000/alpine
```



#### 3. 修改配置

```bash
/etc/containers/registries.d/default.yaml

default-docker:
  sigstore: http://localhost:8000 # 引用网络服务器进行签名读取
  sigstore-staging: file:///var/lib/containers/sigstore # 引用文件路径进行签名写入
```



#### 4. 签名并推送镜像

```bash
sudo -E GNUPGHOME=$HOME/.gnupg \
    podman push \
    --tls-verify=false \
    --sign-by sgrunert@suse.com \
    localhost:5000/alpine
…
Storing signatures
```

推送镜像后，可以看到产生的新的可用签名

```bash
ls /var/lib/containers/sigstore
'alpine@sha256=e9b65ef660a3ff91d28cc50eba84f21798a6c5c39b4dd165047db49e84ae1fb9'
```



#### 5. 自定义本地8000端口服务

```bash
bash -c 'cd /var/lib/containers/sigstore && python3 -m http.server'
Serving HTTP on 0.0.0.0 port 8000 (http://0.0.0.0:8000/) ...
```



#### 6. 强制签名要求配置

```bash
nano /etc/containers/policy.json

{
  "default": [{ "type": "insecureAcceptAnything" }],
  "transports": {
    "docker": {
      "localhost:5000": [
        {
          "type": "signedBy",
          "keyType": "GPGKeys",
          "keyPath": "/tmp/key.gpg"
        }
      ]
    }
  }
}
```

```bash
# 将 GPG 密钥放置到 keyPath 目录下
gpg --output /tmp/key.gpg --armor --export xxx@gmail.com
```



#### 7. 拉取测试

```bash
# 先删除本地已经拉取的 alpine 镜像
podman rmi docker.io/alpine localhost:5000/alpine

# 重新拉取
podman pull --tls-verify=false localhost:5000/alpine
```

此时在8000端口的服务日志中可以看到访问签名的日志



#### 8. 错误配置示例

`keyPath` 路径配置错误，在拉取镜像时会报错

```bash
sudo podman pull --tls-verify=false localhost:5000/alpine
Trying to pull localhost:5000/alpine...
Error: pulling image "localhost:5000/alpine": unable to pull localhost:5000/alpine: unable to pull image: Source image rejected: Invalid GPG signature:
......
```

