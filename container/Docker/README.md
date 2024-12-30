# Docker

### 一、基本组成

**镜像（image）：**

docker镜像就好比是一个模板，可以通过这个模板来创建容器服务，tomcat镜像-->run-->tomcat01容器（提供服务），通过这个镜像可以创建多个容器（最终服务或者项目就是运行在容器中）

**容器（container）：**

docker利用容器技术，独立运行一个或一组应用，通过镜像创建（一个简易的Linux系统）

**仓库（repository）：**

存放镜像，分为公有和私有



### 二、安装

```bash
# 卸载原有的docker
yum remove docker \
                   docker-client \
                   docker-client-latest \
                   docker-common \
                   docker-latest \
                   docker-latest-logrotate \
                   docker-logrotate \
                   docker-engine

# 安装工具包
yum install -y yum-utils

# 更换镜像源
yum-config-manager \
     --add-repo \
     http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
     
yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
     
# 更新软件源
yum makecache fast

# 安装
yum install docker-ce docker-ce-cli containerd.io

# 卸载
yum remove docker-ce docker-ce-cli containerd.io
rm -rf /var/lib/docker
rm -rf /var/lib/containerd

# 阿里云镜像加速
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://9i6lbyl6.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```



### 三、docker常用命令

#### 1.镜像命令：

**docker images**

```bash
[root@suaxi ~]# docker images
REPOSITORY    TAG       IMAGE ID       CREATED       SIZE
hello-world   latest    d1165f221234   12 days ago   13.3kB

REPOSITORY --仓库源
TAG --标签
IMAGE ID --镜像id
CREATED --创建时间
SIZE --大小

-a, --all         	  # 显示所有镜像
-f, --filter filter   # Filter output based on conditions provided
--format string   	  # Pretty-print images using a Go template
--no-trunc            # Don't truncate output
-q, --quiet           # 只显示id
```



**docker search** 搜索

```bash
[root@suaxi ~]# docker search mysql
NAME                              DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
mysql                             MySQL is a widely used, open-source relation…   10624     [OK]       
mariadb                           MariaDB Server is a high performing open sou…   3982      [OK]       
mysql/mysql-server                Optimized MySQL Server Docker images. Create…   779                  [OK]

# 可选项 通过搜索来过滤
# 过滤STARS大于3000的镜像
docker search mysql --filter=STARS=3000
```



**docker pull** 拉取（下载）

```bash
# 下载镜像
docker pull 镜像名[:tag]

[root@suaxi ~]# docker pull mysql
Using default tag: latest 			# 不写tag，默认拉取最新版
latest: Pulling from library/mysql
a076a628af6f: Pull complete 		# 分层下载
f6c208f3f991: Pull complete 
88a9455a9165: Pull complete 
406c9b8427c6: Pull complete 
7c88599c0b25: Pull complete 
25b5c6debdaf: Pull complete 
43a5816f1617: Pull complete 
1a8c919e89bf: Pull complete 
9f3cf4bd1a07: Pull complete 
80539cea118d: Pull complete 
201b3cad54ce: Pull complete 
944ba37e1c06: Pull complete 
Digest: sha256:feada149cb8ff54eade1336da7c1d080c4a1c7ed82b5e320efb5beebed85ae8c # 签名
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest # 真实地址

docker pull mysql 等价于 docker.io/library/mysql:latest

# 指定版本下载
[root@suaxi ~]# docker pull mysql:5.7
5.7: Pulling from library/mysql
a076a628af6f: Already exists 			# 分层文件系统，已经存在的版本不重复下载
f6c208f3f991: Already exists 
88a9455a9165: Already exists 
406c9b8427c6: Already exists 
7c88599c0b25: Already exists 
25b5c6debdaf: Already exists 
43a5816f1617: Already exists 
1831ac1245f4: Pull complete 
37677b8c1f79: Pull complete 
27e4ac3b0f6e: Pull complete 
7227baa8c445: Pull complete 
Digest: sha256:b3d1eff023f698cd433695c9506171f0d08a8f92a0c8063c1a4d9db9a55808df
Status: Downloaded newer image for mysql:5.7
docker.io/library/mysql:5.7

```



**docker rmi 删除镜像**

```bash
[root@suaxi ~]# docker rmi -f 容器id					#删除指定容器id的镜像
[root@suaxi ~]# docker rmi -f 容器id 容器id 容器id	  #删除多个指定容器id的镜像
[root@suaxi ~]# docker rmi -f $(docker images -aq) 	  #删除全部镜像

```



#### 2.容器命令

```bash
docker run [可选参数] image

# 参数说明
--name="name"		容器名字
-d					后台方式运行
-it					交互方式运行，进入容器查看内容
-p					端口号 -p 8088:8088
	-p ip:主机端口:容器端口
	-p 主机端口:容器端口 (常用)
	-p 容器端口
	容器端口
-p					随机指定端口
```



**测试**

```bash
# 启动并进入容器
[root@suaxi ~]# docker run -it centos /bin/bash
[root@1c4af5d6c9fd /]# ls #查看容器中的centos（基础版本，功能不完善）
bin  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
[root@1c4af5d6c9fd /]# exit
exit
[root@suaxi /]# ls # 退出容器，回到主机（完整的centos）
bin  boot  CloudResetPwdUpdateAgent  CloudrResetPwdAgent  dev  etc  home  lib  lib64  lost+found  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var


```



**列出所有运行中的容器**

```bash
# 参数说明
		# docker ps 列出当前正在运行的容器
-a		# 当前正在运行的容器 + 历史运行的容器
-n=?	# 显示最近创建的容器
-q		# 只显示容器编号

[root@suaxi ~]# docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
[root@suaxi ~]# docker ps -a
CONTAINER ID   IMAGE          COMMAND       CREATED             STATUS                         PORTS     NAMES
1c4af5d6c9fd   centos         "/bin/bash"   8 minutes ago       Exited (0) 5 minutes ago                 bold_pascal
705a3df22c9b   hello-world    "/hello"      About an hour ago   Exited (0) About an hour ago             sharp_jepsen
d978003a153b   d1165f221234   "/hello"      22 hours ago        Exited (0) 22 hours ago                  beautiful_engelbart
45a55574058f   d1165f221234   "/hello"      27 hours ago        Exited (0) 27 hours ago                  charming_cerf
938e1fed5677   d1165f221234   "/hello"      27 hours ago        Exited (0) 27 hours ago                  compassionate_matsumoto
[root@suaxi ~]# docker ps -a -n=1
CONTAINER ID   IMAGE     COMMAND       CREATED         STATUS                     PORTS     NAMES
1c4af5d6c9fd   centos    "/bin/bash"   8 minutes ago   Exited (0) 6 minutes ago             bold_pascal
[root@suaxi ~]# docker ps -aq
1c4af5d6c9fd
705a3df22c9b
d978003a153b
45a55574058f
938e1fed5677

```



**退出容器**

```bash
exit			# 停止并退出
ctrl + P + Q	# 退出，但容器不停止
```



**删除容器**

```bash
docker rm 容器id					# 删除指定的容器（不能删除正在运行的，强制删除运行中的容器，需加docker rm -f）
docker rm -f $(docker ps -aq) 	 # 删除所有的容器
docker ps -a -q|xargs docker rm  # 删除所有的容器
```



**启动和停止**

```bash
docker start 容器id		# 启动
docker restart 容器id		# 重启
docker stop 容器id		# 停止当前正在运行的容器
docker kill 容器id		# 强制停止
```



#### 3.常用其他命令

**后台启动容器**

```bash
# docker run -d 镜像名
[root@suaxi ~]# docker run -d centos
```

注：docker容器使用后台运行，必须要有一个前台进程，如果docker发现没有运行的应用，就会自动停止；

同理nginx容器启动后，发现没有提供服务，就会立刻停止



**查看日志**

```bash
docker logs -tf --tail [number]
# 参数说明
-tf					# 带时间戳、行数显示日志
--tail [number]		# 查看几行日志

[root@suaxi ~]# docker run -d centos /bin/sh -c "while true;do echo hello;sleep 2;done"
bb0ac965992f3a0d14c7198a79ada368bbb3331da7bfca88d4e235eb4482a3b5
[root@suaxi ~]# docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED         STATUS         PORTS     NAMES
bb0ac965992f   centos    "/bin/sh -c 'while t…"   3 seconds ago   Up 2 seconds             nice_hermann
[root@suaxi ~]# docker logs -tf --tail 5 bb0ac965992f
2021-03-20T15:15:58.658886157Z hello
2021-03-20T15:16:00.660610164Z hello
2021-03-20T15:16:02.662305052Z hello
2021-03-20T15:16:04.663980021Z hello
2021-03-20T15:16:06.666010717Z hello

```



**查看容器中的进程信息**

```bash
[root@suaxi ~]# docker top bb0ac965992f
UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
root                21507               21487               0                   23:15 
root                21901               21507               0                   23:20 

```

**查看镜像的元数据**

```bash
docker inspect 容器id

```

![](static\3.基本命令\1.查看镜像的元数据.png)



**进入当前正在运行的容器**

```bash
# 方式一：
docker exec -it 容器id bashshell

[root@suaxi ~]# docker exec -it bb0ac965992f bin/bash
[root@bb0ac965992f /]# uname -a
Linux bb0ac965992f 3.10.0-1160.15.2.el7.x86_64 #1 SMP Wed Feb 3 15:06:38 UTC 2021 x86_64 x86_64 x86_64 GNU/Linux
[root@bb0ac965992f /]# uptime
 15:28:08 up 3 days, 14:33,  0 users,  load average: 0.00, 0.02, 0.05


# 方式二：
docker attach 容器id

# docker exec		# 进入容器后开启一个新的终端（常用）
# docker attach		# 进入容器正在执行的终端，不会启动新的进程
```



**从容器中拷贝文件到主机**

```bash
docker cp 容器id:容器内路径 主机路径

# 启动容器
[root@suaxi ~]# docker run -it centos
[root@112a084e0bd3 /]# cd /home
[root@112a084e0bd3 home]# ls
# 创建一个新的文件
[root@112a084e0bd3 home]# touch sunxiaochuan.txt
[root@112a084e0bd3 home]# exit
exit
[root@suaxi ~]# docker ps -a
CONTAINER ID   IMAGE     COMMAND       CREATED          STATUS                     PORTS     NAMES
112a084e0bd3   centos    "/bin/bash"   36 seconds ago   Exited (0) 8 seconds ago             sleepy_tereshkova

# 拷贝文件到主机
[root@suaxi ~]# docker cp 112a084e0bd3:/home/sunxiaochuan.txt /home
[root@suaxi ~]# cd /home
[root@suaxi home]# ls
sunxiaochuan.txt  test  www
[root@suaxi home]# 

```



#### 4.docker常用命令小结

```bash
attach    Attach to a running container  #当前shell下attach连接指定运行镜像
build     Build an image from a Dockerfile  #通过Dockerfile定制镜像
commit    Create a new image from a container's changes  #提交当前容器为新的镜像
cp    Copy files/folders from a container to a HOSTDIR or to STDOUT  #从容器中拷贝指定文件或者目录到宿主机中
create    Create a new container  #创建一个新的容器，同run 但不启动容器
diff    Inspect changes on a container's filesystem  #查看docker容器变化
events    Get real time events from the server#从docker服务获取容器实时事件
exec    Run a command in a running container#在已存在的容器上运行命令
export    Export a container's filesystem as a tar archive  #导出容器的内容流作为一个tar归档文件(对应import)
history    Show the history of an image  #展示一个镜像形成历史
images    List images  #列出系统当前镜像
import    Import the contents from a tarball to create a filesystem image  #从tar包中的内容创建一个新的文件系统映像(对应export)
info    Display system-wide information  #显示系统相关信息
inspect    Return low-level information on a container or image  #查看容器详细信息
kill    Kill a running container  #kill指定docker容器
load    Load an image from a tar archive or STDIN  #从一个tar包中加载一个镜像(对应save)
login    Register or log in to a Docker registry#注册或者登陆一个docker源服务器
logout    Log out from a Docker registry  #从当前Docker registry退出
logs    Fetch the logs of a container  #输出当前容器日志信息
pause    Pause all processes within a container#暂停容器
port    List port mappings or a specific mapping for the CONTAINER  #查看映射端口对应的容器内部源端口
ps    List containers  #列出容器列表
pull    Pull an image or a repository from a registry  #从docker镜像源服务器拉取指定镜像或者库镜像
push    Push an image or a repository to a registry  #推送指定镜像或者库镜像至docker源服务器
rename    Rename a container  #重命名容器
restart    Restart a running container  #重启运行的容器
rm    Remove one or more containers  #移除一个或者多个容器
rmi    Remove one or more images  #移除一个或多个镜像(无容器使用该镜像才可以删除，否则需要删除相关容器才可以继续或者-f强制删除)
run    Run a command in a new container  #创建一个新的容器并运行一个命令
save    Save an image(s) to a tar archive#保存一个镜像为一个tar包(对应load)
search    Search the Docker Hub for images  #在docker
hub中搜索镜像
start    Start one or more stopped containers#启动容器
stats    Display a live stream of container(s) resource usage statistics  #统计容器使用资源
stop    Stop a running container  #停止容器
tag         Tag an image into a repository  #给源中镜像打标签
top       Display the running processes of a container #查看容器中运行的进程信息
unpause    Unpause all processes within a container  #取消暂停容器
version    Show the Docker version information#查看容器版本号
wait         Block until a container stops, then print its exit code  #截取容器停止时的退出状态值
```



### 四、Docker练习

> 安装Nginx

```bash
#1、搜索镜像
[root@suaxi ~]# docker search nginx

#2、拉取
[root@suaxi ~]# docker pull nginx

#3、运行
#参数说明
-d 后台运行
--name 给容器取名字
-p 容器端口:主机端口


[root@suaxi ~]# docker run -d --name nginx01 -p 8088:80 nginx
fd78aade29f79d332f6ec1becfeb7ea7400790195682784b0be576fc9e004cf6
[root@suaxi ~]# docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED         STATUS         PORTS                  NAMES
fd78aade29f7   nginx     "/docker-entrypoint.…"   4 seconds ago   Up 3 seconds   0.0.0.0:8088->80/tcp   nginx01
[root@suaxi ~]# curl localhost:80

# 进入容器
[root@suaxi ~]# docker exec -it nginx01 /bin/bash
root@fd78aade29f7:/# ls
bin   dev		   docker-entrypoint.sh  home  lib64  mnt  proc  run   srv  tmp  var
boot  docker-entrypoint.d  etc			 lib   media  opt  root  sbin  sys  usr
root@fd78aade29f7:/# whereis nginx
nginx: /usr/sbin/nginx /usr/lib/nginx /etc/nginx /usr/share/nginx
root@fd78aade29f7:/# 

```



> 安装tomcat

```bash
# 官方文档中建议使用的命令（测试环境下）
docker run -it --rm tomcat:9.0

# 参数说明：加了 --rm 表名容器用完即删

# 常规测试
docker run -d -p 8088:8080 --name tomcat01 tomcat

```

注：阿里云docker镜像是默认的最小镜像，剔除了不必要的功能，只保证最小可运行的环境（阉割了部分功能）



> 部署ElasticSearch + Kibana

```bash
# 启动
--net somenetwork  # 网络配置

docker run -d --name elasticsearch01 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.6.2

# es 启动时添加内存限制
docker run -d --name elasticsearch02 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms64m -Xmx256m" elasticsearch:7.6.2
```

![1.ElasticSearch添加内存限制](static\4.docker练习\1.ElasticSearch添加内存限制.png)

```bash
# 测试
[root@suaxi ~]# curl localhost:9200
{
  "name" : "d9efc9b99508",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "Q3F3eZv_TouzzDW-_JZyTA",
  "version" : {
    "number" : "7.6.2",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "ef48eb35cf30adf4db14086e8aabd07ef6fb113f",
    "build_date" : "2020-03-26T06:34:37.794943Z",
    "build_snapshot" : false,
    "lucene_version" : "8.4.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}

```

![2.Kibana连接ES](static\4.docker练习\2.Kibana连接ES.png)

图片来源：狂神说Java



### 五、Docker镜像

#### 1.UnionFS 联合文件系统

UnionFS（联合文件系统）：是一种分层、轻量级并且高性能的文件系统，它支持对文件系统的修改作为一次提交来一层层的叠加，同时可以将不同目录挂载到同一个虚拟文件系统下（unite several directories into a single vritual filesystem）。Union文件系统是docker镜像的基础，镜像可以通过分层来进行继承，基于基础镜像（没有父镜像），可以制作各种具体的应用镜像。

特性：一次同时加载多个文件系统，但从外面看起来，只能看到一个文件系统，联合加载会把各层文件系统叠加起来，这样最终的文件系统会包含所有底层的文件和目录。



#### 2.docker镜像加载原理

docker镜像本质由一层一层的文件系统组成——UnionFS。

bootfs（boot file system）主要包含bootloader和kernel，bootloader主要引导加载kernel，linux刚启动时会加载bootfs文件系统，在docker镜像的最底层是bootfs。这一层与Linux/Unix是一样的，包含boot加载器和内核。当boot加载完成之后整个内核就都在内存中了，此时内存的使用权已由bootfs转交给内核，同时系统也会卸载bootfs。

rootfs（root file system）在bootfs之上，包含的就是Linux系统中的/dev，/proc，/bin，/etc等标准目录和文件。rootfs就是各种不同的Linux发行版（Centos、Ubuntu、RealHat...）

![](static\5.联合文件系统\1.UnionFS联合文件系统.png)

图片来源：狂神说Java



Docker镜像为什么这么小？

<font color='red'>对于一个精简的OS，rootfs可以很小，只需要包含基本的命令、工具和程序库，其底层本质上是调用主机的Kernel，本身只需提供rootfs。</font>



#### 3.分层理解

**redis镜像拉取举例：**

![](static\5.联合文件系统\2.redis拉取举例.png)

```bash
# 查看镜像分层
docker image inspect 镜像名

"RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:cb42413394c4059335228c137fe884ff3ab8946a014014309676c25e3ac86864",
                "sha256:8e14cb7841faede6e42ab797f915c329c22f3b39026f8338c4c75de26e5d4e82",
                "sha256:1450b8f0019c829e638ab5c1f3c2674d117517669e41dd2d0409a668e0807e96",
                "sha256:f927192cc30cb53065dc266f78ff12dc06651d6eb84088e82be2d98ac47d42a0",
                "sha256:a24a292d018421783c491bc72f6601908cb844b17427bac92f0a22f5fd809665",
                "sha256:3480f9cdd491225670e9899786128ffe47054b0a5d54c48f6b10623d2f340632"
            ]
        },

```



所有的docker镜像都起始于一个基础镜像层，当进行修改或增加新的内容时，就会在当前镜像层之上，创建新的镜像层。例：基于Ubuntu16.04创建一个新的镜像，这就是新镜像的第一层，如果在该镜像中添加Python包，就会在基础镜像层之上创建第二个镜像层；继续添加新的软件同理，

![](static\5.联合文件系统\3.1分层理解.png)

在添加额外镜像层的同时，镜像始终保持当前所有的镜像组合，例：每个镜像层包含3个文件，而总镜像包含来自两个镜像层的6个文件，

![](static\5.联合文件系统\3.2分层理解.png)

下图中的例子在外部看来整个镜像只包含6个文件，这是因为最上层的文件7是文件5的迭代更新版本

![](static\5.联合文件系统\3.3分层理解.png)

在这个过程中，上层镜像层中的文件覆盖了底层镜像层中的文件，这使得文件的更新版本作为一个新的镜像层添加到镜像中。

docker通过存储引擎（新版使用快照方式）的方式来实现镜像层堆栈，同时保证多层镜像层对外展示为一个统一的文件系统。

Linux上可用的存储引擎有AUFS、Overlay2、Device Mapper、Btrfs及ZFS。

在Windows上仅支持windowsfilter一种存储引擎，该引擎基于NTFS文件系统实现分层和CoW。

![](static\5.联合文件系统\3.4分层理解.png)

所有镜像层堆叠并合并，对外展示为统一的文件系统。



> 补充

docker镜像都是只读的，当容器启动时，一个新的可写层被加载到镜像的顶部，也就是通常所说的容器，容器之下的都叫镜像层。

![](static\5.联合文件系统\6.容器层.png)

图片来源：狂神说Java



#### 5.commit镜像

```bash
# 命令
docker commit -m="注释信息" -a="作者" 容器id 目标镜像名:[TAG]
```

例：给精简版的tomcat添加一点东西

![7.commit镜像](static\5.联合文件系统\7.commit镜像.png)



### 六、容器数据卷

*容器数据卷：将应用和环境打包成一个镜像*

目的：将容器中的目录，挂在的Linux本地（容器的持久化和同步操作、容器间数据共享）



#### 方式一：直接使用 -v 命令挂载

```bash
docker run -it -v 主机目录:容器内目录

# 测试
[root@suaxi ~]# docker run -it -v /home/test:/home centos /bin/bash

```

![1.-v直接挂载](static\6.容器数据卷\1.1-v直接挂载.png)

从容器同步到主机：

![1.2同步过程（双向绑定）](static\6.容器数据卷\1.2同步过程（双向绑定）.png)

从主机同步到容器：（在容器停止后，主机修改了绑定路径下的文件，容器再次启动后，双向绑定依然生效）

![](static\6.容器数据卷\1.3同步过程（双向绑定）.png)



#### 1.安装MySQl

```bash
# 拉取镜像
[root@suaxi ~]# docker pull mysql

# 官方文档命令
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag

# 启动（注意：docker启动mysql需使用-e命令配置密码）
-d 后台运行
-p 端口映射
-v 数据卷挂载
-e 环境配置
--name 给容器起个别名
[root@suaxi ~]# docker run -d -p 8088:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql


# 后续再使用navicat等工具连接docker中的mysql实际测试一下配置是否生效
```

![2.安装mysql测试](static\6.容器数据卷\2.安装mysql测试.png)

<font color='red'>删除容器之后，同步到本地的数据依然存在，即实现数据的持久化。</font>



#### 2.具名挂载、匿名挂载

```bash
# 匿名挂载
-v 容器内的路径
-P 大写的P，随机映射端口
[root@suaxi ~]# docker run -d -P --name nginx01 -v /etc/nginx nginx

```

![](static\6.容器数据卷\3.匿名挂载.png)



```bash
# 具名挂载
-v 卷名:容器内路径
[root@suaxi ~]# docker run -d -P --name nginx02 -v juming-nginx:/etc/nginx nginx

# 查看刚才挂载的卷
[root@suaxi ~]# docker volume inspect juming-nginx

```

![](static\6.容器数据卷\4.具名挂载.png)

所有docker容器内的卷，在没有指定目录的情况下都在 `/var/lib/docker/volumes/xxx/`

<font color='red'>通过具名挂载，可以方便的找到挂载的卷及相关数据、信息</font>



```bash
# 区分匿名/具名挂载、指定路径挂载
-v 容器内路径			# 匿名挂载
-v 卷名:容器内路径		   # 具名挂载
-v /主机路径:容器内路径	  # 指定路径挂载
```



```bash
# 通过 -v 容器内路径:ro/rw 改变读写权限
ro read only	# 只读
rw read/write	# 可读可写

[root@suaxi ~]# docker run -d -P --name nginx02 -v juming-nginx:/etc/nginx:ro nginx
[root@suaxi ~]# docker run -d -P --name nginx02 -v juming-nginx:/etc/nginx:rw nginx

# ro 只能通过主机来操作，容器内只具有读的权限
```



#### 方式二：Dockerfile

通过脚本生成镜像

```bash
# 1.创建dockerfile文件
# 2.编写脚本，命令（都是大写）参数
FROM centos

VOLUME ["volume01","volume02"]

CMD echo "----create complete----"
CMD /bin/bash
```

![5.dockerfile脚本](static\6.容器数据卷\5.1.dockerfile脚本.png)

启动并进入容器：

![5.2.dockerfile启动容器](static\6.容器数据卷\5.2.dockerfile启动容器.png)



脚本命令中挂载的目录是匿名挂载：

![5.3.dockerfile匿名挂载](static\6.容器数据卷\5.3.dockerfile匿名挂载.png)



测试挂载目录下的双向绑定是否成功:

![5.4.dockerfile双向绑定](static\6.容器数据卷\5.4.dockerfile双向绑定.png)



#### 1.数据卷容器

多个mysql同步数据

![6.数据卷容器](static\6.容器数据卷\6.数据卷容器.png)

图片来源：狂神说Java



```bash
# 通过刚才的脚本依次启动3个容器
[root@suaxi docker-test-volume]# docker run -it --name docker01 b0bf8a6507a3

[root@suaxi docker-test-volume]# docker run -it --name docker02 --volumes-from docker01 b0bf8a6507a3

[root@suaxi docker-test-volume]# docker run -it --name docker03 --volumes-from docker01 b0bf8a6507a3

```

![7.数据卷容器文件同步](static\6.容器数据卷\7.1.数据卷容器文件同步.png)



通过volumes实现容器间的数据共享

![7.2.volumes容器间数据共享](static\6.容器数据卷\7.2.volumes容器间数据共享.png)

<font color='red'>注：即使删除父容器docker01，其他容器中的数据依然存在</font>

```bash
# 实现多个mysql间的数据同步
[root@suaxi ~]# docker run -d -p 8088:3306 -v /etc/mysql/conf.d -v /var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql

[root@suaxi ~]# docker run -d -p 8088:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql02 --volumes-from mysql01 mysql
```

结论：

容器之间配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止。



### 七、DockerFile

dockerfile用于构建docker镜像（命令参数脚本）

![1.github dockerfile](static\7.DockerFile\1.github dockerfile.png)



#### 1.dockerfile构建过程：

```bash
# 1.指令关键字必须大写
# 2.从上往下顺序执行
# 3.# 表示注释
# 4.每一个指令都会创建一个新的镜像层，并提交
```

![分层镜像](static\7.DockerFile\分层镜像.png)

DockerFile：构建文件，定义执行的内容

DockerImages：通过DockerFile构建生成的镜像，即最终发布和运行的产品

DockerContainer：运行镜像并提供服务



#### 2.DockerFile指令

```shell
FROM			# 基础镜像
MAINTAINER		# 作者：姓名+邮箱
RUN				# 镜像构建时需要运行的命令
ADD				# 添加需要使用的文件（会自动解压）
WORKDIR			# 镜像工作目录
VOLUME			# 挂载目录
EXPOSE			# 暴露端口
CMD				# 指定容器启动时要运行的命令（只有最后一个才生效，可被替代）
ENTRYPOINT		# 同理CMD，但可命令可以追加，类似于StringUtils.append()
ONBUILD			# 当构建一个被继承的dockerfile时，就会运行onbuild指令（触发指令）
COPY			# 类似于ADD，将需要的文件拷贝到镜像中
ENV				# 构建时设置环境变量
```



#### 3.centos 测试实例

DockerHub中大部分的镜像都是基于scratch这个基础镜像构建而来

```shell
# 1.编写dockerfile配置文件
FROM centos
MAINTAINER suaxi<281463547@qq.com>

ENV MYPATH /usr/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80

CMD echo $MYPATH
CMD echo "-----end-----"
CMD /bin/bash

# 2.构建镜像
docker build -f dockerfile文件路径 -t 镜像名:[TAG] .
[root@suaxi dockerfile]# docker build -f dockerfile-centos -t mycentos:1.0 .

# 3.测试运行
```

![2.测试运行](static\7.DockerFile\2.测试运行.png)

镜像构建历史：`docker history 容器id`

![4.镜像构建历史](static\7.DockerFile\4.镜像构建历史.png)



> CMD和ENTRYPOINT的区别

**测试CMD**

```shell
# 1.编写dockerfile文件
FROM centos
CMD ["ls","-a"]

# 2.构建镜像
[root@suaxi dockerfile]# docker build -f docker-cmd-test -t cmdtest .

# 3.运行，执行了ls -a 命令
[root@suaxi dockerfile]# docker run 92054cb6235d
.
..
.dockerenv
bin
dev
etc
home
lib
lib64
lost+found
media
mnt
opt
proc
root
run
sbin
srv
sys
tmp
usr
var

# 现在项追加一个 -l 命令 ls -al
[root@suaxi dockerfile]# docker run 92054cb6235d -l
docker: Error response from daemon: OCI runtime create failed: container_linux.go:367: starting container process caused: exec: "-l": executable file not found in $PATH: unknown.

# 在使用CMD指令的情况下，后追加的 -l 命令替换了之前的CMD["ls","-a"]命令，所以报错
```



**测试ENTRYPOINT**

```shell
# 1.编写dockerfile文件
FROM centos
ENTRYPOINT ["ls","-a"]

# 第2、3步的build和run与之前的一样

[root@suaxi dockerfile]# docker build -f docker-entrypoint-test -t entrypointtest .
Sending build context to Docker daemon  4.096kB
Step 1/2 : FROM centos
 ---> 300e315adb2f
Step 2/2 : ENTRYPOINT ["ls","-a"]
 ---> Running in bf423c7dc041
Removing intermediate container bf423c7dc041
 ---> e25354d47b76
Successfully built e25354d47b76
Successfully tagged entrypointtest:latest
[root@suaxi dockerfile]# docker run e25354d47b76
.
..
.dockerenv
bin
dev
etc
home
lib
lib64
lost+found
media
mnt
opt
proc
root
run
sbin
srv
sys
tmp
usr
var

# 同理追加 -l 命令
[root@suaxi dockerfile]# docker run e25354d47b76 -l
total 56
drwxr-xr-x   1 root root 4096 Mar 23 14:12 .
drwxr-xr-x   1 root root 4096 Mar 23 14:12 ..
-rwxr-xr-x   1 root root    0 Mar 23 14:12 .dockerenv
lrwxrwxrwx   1 root root    7 Nov  3 15:22 bin -> usr/bin
drwxr-xr-x   5 root root  340 Mar 23 14:12 dev
drwxr-xr-x   1 root root 4096 Mar 23 14:12 etc
drwxr-xr-x   2 root root 4096 Nov  3 15:22 home
lrwxrwxrwx   1 root root    7 Nov  3 15:22 lib -> usr/lib
lrwxrwxrwx   1 root root    9 Nov  3 15:22 lib64 -> usr/lib64
drwx------   2 root root 4096 Dec  4 17:37 lost+found
drwxr-xr-x   2 root root 4096 Nov  3 15:22 media
drwxr-xr-x   2 root root 4096 Nov  3 15:22 mnt
drwxr-xr-x   2 root root 4096 Nov  3 15:22 opt
dr-xr-xr-x 100 root root    0 Mar 23 14:12 proc
dr-xr-x---   2 root root 4096 Dec  4 17:37 root
drwxr-xr-x  11 root root 4096 Dec  4 17:37 run
lrwxrwxrwx   1 root root    8 Nov  3 15:22 sbin -> usr/sbin
drwxr-xr-x   2 root root 4096 Nov  3 15:22 srv
dr-xr-xr-x  13 root root    0 Mar 22 13:57 sys
drwxrwxrwt   7 root root 4096 Dec  4 17:37 tmp
drwxr-xr-x  12 root root 4096 Dec  4 17:37 usr
drwxr-xr-x  20 root root 4096 Dec  4 17:37 var
 # 可以发现，使用ENTRYPOINT指令，-l命令直接追加在了ls -a之后，且正确执行
```



#### 4.Tomcat 测试实例

**1.准备文件：**

![](static\7.DockerFile\5.1准备文件.png)



**2.编写Dockerfile文件**

*注：Dockerfile为官方命名，build时会自动查找该文件，不需要 -f 指定文件路径*

```shell
FROM centos
MAINTAINER suaxi<281463547@qq.com>

COPY readme.txt /usr/local/readme.txt

ADD jdk-8u281-linux-x64.tar.gz /usr/local/
ADD apache-tomcat-9.0.27.tar.gz /usr/local/

RUN yum -y install vim

ENV MYPATH /usr/local
WORKDIR $MYPATH

ENV JAVA_HOME /usr/local/jdk1.8.0_281
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.27
ENV CATALINA_BASH /usr/local/apache-tomcat-9.0.27
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin

EXPOSE 8080

CMD /usr/local/apache-tomcat-9.0.27/bin/startup.sh && tail -f /usr/local/apache-tomcat-9.0.27/logs/catalina.out

```



**3.构建镜像**

```shell
[root@suaxi dockerfile]# docker build -t diycentos .
```



**4.启动镜像**

```shell
[root@suaxi dockerfile]# docker run -d -p 8088:8080 --name tomcattest -v /home/test/tomcat/test:/usr/local/apache-tomcat-9.0.27/webapps/test -v /home/test/build/tomcat/logs:/usr/local/apache-tomcat-9.0.27/logs diycentos

```



**5.访问测试**



**6.发布项目（挂载目录之后在本地编写项目即可）**

在挂在路径下新建WEB-INF/web.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
                             http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">

</web-app>

```

`cd ..`返回上一级，新建index.html文件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Docker</title>
</head>
<body>
<h1>Docker Tomcat diy test</h1>
</body>
</html>

```

外网访问测试成功

![](static\7.DockerFile\5.2访问测试.png)



