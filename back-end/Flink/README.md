## Flink

*参考b站尚硅谷陈教授 Flink 1.17.0*



### 一、部署模式

#### 1. 会话模式（Session Mode）

概念：启动集群，保持一个会话，在这个会话中通过客户端提交作业，在集群启动时，所有的资源就已确定并加载，所提交的作业会竞争集群中的资源，适用于**单个规模较小、执行时间短的大量作业**。



#### 2. 单作业模式（Pre - Job Mode）

概念：为每个提交的作业启动一个集群，作业完成后，集群就会关闭且释放对应的资源。该模式需要借助资源管理框架（YARN、K8s等）来启动集群，Flink 本身无法以该模式直接运行。



#### 3. 应用模式（Application Mode）

概念：为每一个提交的作业单独启动一个 JobManager（即创建一个集群，由 JobManager 直接执行应用程序），单作业模式下，由客户端解析应用，再提交给 JobManager，资源占用较大。



### 二、运行模式

#### 1. Standalone

独立运行模式不依赖外部资源管理平台，一般用于开发、测试环境。



##### （1）会话模式

提前启动集群（集群资源是固定的），并通过 Web 页面客户端提交作业



##### （2）单作业模式

Flink 的 Standalone 集群不支持单作业模式部署，需借助外部平台。



##### （3）应用模式

通过官方文件的 standalone-job.sh 脚本来创建一个 JobManager。

注：执行的作业/应用需放置于 lib 目录下



#### 2. YARN

客户端将 Flink 应用提交给 Yarn 的 ResourceManager，ResourceManager 会向 Yarn 的 NodeManager 申请容器，Flink 会在申请到的容器上部署 JobManager 和 TaskManager 实例，从而启动集群，同时 Flink 会根据运行在 JobManager 上的作业所需要的 Slot 数量动态分配 TaskManager 资源。