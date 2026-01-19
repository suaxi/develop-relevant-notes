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



#### 四、网络

##### 1. 简介

Podman 管理的容器在进行网络连接时，非特权用户无法在主机上创建网络接口，默认是 `slirp4netns`，相比于特权用户而言缺少一部分功能，如：无法为容器分配 IP 地址；特权用户运行的容器默认网络模式是 `netavark`。



**防火墙**

当容器执行端口映射时，防火墙也会同步打开对应的端口，但当重新加载防火墙规则时，会删除 netavark iptables，导致容器丢失网络连接，Podman v3 提供了 `podman network reload` 命令来恢复对应设置，且无需重启容器。



##### 2. 桥接

![1.bridge](static/4.网络/1.bridge.png)

桥接指创建一个内部网络，容器和主机都连接到该网络，以此实现容器与外部主机通信

Podman 默认使用桥接模式，同时也提供了一个默认的桥接网络，从 4.0 版本开始，非特权用户运行的容器也可以使用 `netavark`（但不提供默认配置）从旧版本 CNI 切换到 netavark 时，需执行 `podman system reset --force` 命令，该命令会删除所有的镜像、容器和自定义网络

rootless 容器的网络操作是在一个额外的命名空间内执行的，可以通过 `podman unshare --rootless-netns` 命令加入



##### 3. 默认

Netavark 是纯内存网络，由于需要向后兼容 Docker，因此不支持 DNS 解析，可通过以下命令修改对应的配置文件

```bash
# 特权
podman network inspect podman | jq .[] > /etc/containers/networks/podman.json

# 非特权
podman network inspect podman | jq .[] > ~/.local/share/containers/storage/networks/podman.json
```

以特权和非特权为例，将服务暴露至主机外

```bash
# 特权
sudo podman run -d --name webserver -p 8080:80 quay.io/libpod/banner
00f3440c7576aae2d5b193c40513c29c7964e96bf797cf0cc352c2b68ccbe66a

# 非特权
podman run -d --name webserver --network podman1 -p 8081:80 quay.io/libpod/banner
269fd0d6b2c8ed60f2ca41d7beceec2471d72fb9a33aa8ca45b81dc9a0abbb12
```

外部客户端可通过8080 或 8081 端口访问到对应的服务



##### 4. Macvlan

![2.macvlan](static/4.网络/2.macvlan.png)

Podman 必须以 root 身份才能操作 macvlan

```bash
# 创建 macvlan，eth0 为主机的网络接口（与 Docker 的创建方式一致）
sudo podman network create -d macvlan -o parent=eth0 macvlan_test
macvlan_test

# --subnet指定子网，不使用 DHCP（可选）
sudo podman network create -d macvlan  \
     --subnet 192.168.123.0/24  \
     --gateway 192.168.123.1 \
     -o parent=eth0  \
     macvlan_test

# 使用 DHCP
# 查看当前使用的网络后端
sudo podman info --format {{.Host.NetworkBackend}}

# NetAvark 启用 DHCP（以systemd为例）
sudo systemctl enable --now netavark-dhcp-proxy.socket

# CNI 启用 DHCP（以systemd为例）
sudo systemctl enable --now cni-dhcp.socket
```

```bash
# 容器使用 macvlan
sudo podman run -d --name webserver --network macvlan_test quay.io/libpod/banner
03d82083c434d7e937fc0b87c25401f46ab5050007df403bf988e25e52c5cc40

# 使用外部客户端访问
(outside_host): $ curl http://<容器分配到的ip>
   ___           __
  / _ \___  ___/ /_ _  ___ ____
 / ___/ _ \/ _  /  ' \/ _ `/ _ \
/_/   \___/\_,_/_/_/_/\_,_/_//_/
```



##### 5. Slirp4netns

Slirp4netns 是 rootless 容器和 Pod 的默认网络配置，它的出现是因为非特权用户无法在主机上创建网络接口。Slirp4netns 会在容器的网络命名空间中创建一个 TAP 设备，并连接到用户模式 TCP/IP 协议栈

![3.rootless_default](static/4.网络/3.rootless_default.png)

非特权用户必须使用 1024 到 65535 端口，因为更低的端口需要 root 权限，可以使用以下方法调整默认的设置 `sysctl net.ipv4.ip_unprivileged_port_start`

slirp4netns 网络模式下，容器与容器之间完全隔离，且没有虚拟网络，容器之间要进行通信时，需要使用与宿主机的端口映射，或者将它们放入同一个 Pod 中（即共享同一个网络命名空间）

以两个 rootless 容器通信为例

```bash
# 假设主机 ip 为 192.168.123.123

# 运行 rootless 容器
podman run -d --name webserver -p 8080:80 quay.io/libpod/banner
17ea33ccd7f55ff45766b3ec596b990a5f2ba66eb9159cb89748a85dc3cebfe0

# 运行另一个 rootless 容器，并执行 curl
podman run -it quay.io/libpod/banner curl http://192.168.123.123:8080

# 外部客户端访问
(outside_host): $ curl http://192.168.123.123:8080
   ___           __
  / _ \___  ___/ /_ _  ___ ____
 / ___/ _ \/ _  /  ' \/ _ `/ _ \
/_/   \___/\_,_/_/_/_/\_,_/_//_/
```



##### 6. 容器与 Pod 之间的通信

Podman Pod 中的所有容器共享同一个网络命名空间（即它们将具有相同的 IP 地址、MAC 地址和端口映射），Pod 中的容器之间可以使用 localhost 方便地进行通信

![4.pod](static/4.网络/4.pod.png)

如上图所示，该 Pod 中的 DB Container 和 Web Container 所属同一个网络命名空间下，他们之间可以通过 localhost:[port] 进行通信，也可以通过分配给 Pod 的 ip 进行寻址，在 DNS 服务可用时，也可以使用 dns name 进行通信（类似于 K8s 中的 Core DNS）。