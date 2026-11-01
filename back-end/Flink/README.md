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



##### （1）环境准备

Yarn 模式需要 Hadoop 和 HDFS，配置 Hadoop 环境变量

```bash
nano /etc/profile

HADOOP_HOME=/home/workspace/hadoop-3.3.4
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
export HADOOP_CONF_DIR=${HADOOP_HOME}/etc/hadoop
export HADOOP_CLASSPATH=`hadoop classpath`
```



##### （2）会话模式

执行 `bin/yarn-session.sh -nm [jobName]` 启动，该模式下 Yarn 会动态分配集群资源

脚本参数说明：

- -d：分离模式，即后台运行，与 docker run -d 类似
- -jm（--jobManagerMemory）：指定 JobManager 使用的内容，单位默认 MB
- -nm（--name）：指定 job name
- -qu（--queue）：指定 Yarn 队列名
- -tm（--taskManager）：指定 TaskManager 使用的内容



##### （3）单作业模式

执行 `bin/flink run -d -t yarn-per-job -c [fullyQualifiedClassName] xxx.jar` 启动，启动过程遇到类加载器报错是，可通过修改配置文件解决

```bash
nano conf/flink-conf.yaml

classloader.check-leaked-classloader: false
```



##### （4）应用模式

与单作业模式类似，执行 `bin/flink run-application -t yarn-application -c [fullyQualifiedClassName] xxx.jar` 启动



#### 3. K8s 集群部署

部署原理与 Yarn 类似，可参照官网 Operator 文档：https://nightlies.apache.org/flink/flink-kubernetes-operator-docs-stable/



### 三、运行时架构

#### 1. 系统架构

![3.1系统架构](static/3.运行时架构/3.1系统架构.png)



##### （1）JobManager 作业管理器

概念：Flink 集群中任务管理、调度的核心，控制应用执行的主进程，即：每个任务都应该被唯一的 JobManager 控制执行。

包含以下3个组件：

- JobMaster

  负责处理单独的作业（Job），它与具体的作业是一一对应的。当作业提交时，JobMaster 会先接收要执行的应用，将其转换为 StreamGraph 后，再转换为 JobGraph（ExecutionGraph 执行图），其包含了所有所有可以并发执行的任务，之后 JobMaster 向资源管理器（ResourceManager）申请执行任务所需的资源（Slot），并将执行图分发到执行具体任务的 TaskManager

- ResourceManager（资源管理器）

  主要负责资源分配和管理，资源指 TaskManager 的任务槽（Task Slots），Flink 集群中的资源调配单元，包含用于执行计算的一组 CPU 和内存资源，一个任务对应一个 Slot

- Dispatcher（分发器）

  提供 REST 接口和 Web UI 管理界面，用于提交应用，并且为每一个新提交的作业启动一个新的 JobMaster 组件，该组件随部署模式的不同而不同



##### （2）TaskManager 任务管理器

任务管理器是 Flink 的工作进程，负责计算数据流（执行具体的作业），每个任务管理器都包含一定数量的 Task Slot，Slot 是资源调度的最小单位，其数量决定了任务管理器能并行处理的任务数。不同 TaskManager 之间可以进行数据交换。