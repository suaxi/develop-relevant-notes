## K8s集群搭建与应用部署

*使用 kubeadm（官方推荐） 方式搭建一主一从测试环境，操作系统使用  Debian 12.15*



### 一、K8s 1.23.17 版本

#### 1. 基础环境

**操作系统：**Debian 12.15

**主节点：**192.168.123.100

**从节点：**192.168.123.101

**文件目录结构：**

```bash
home
└── workspace
    ├── kubernetes # K8s 基础文件夹
    └── Project     # 项目文件夹
```



#### 2. 基础环境准备

```bash
# 以下命令在所有节点都需要执行

# 更新软件源
apt update

# 安装基础工具
apt install -y curl wget nano net-tools

# 设置各节点 hostname（以主节点为例）
hostnamectl set-hostname master.k8s

# 查看修改结果
hostnamectl status

# 设置 hostname 解析
echo "127.0.0.1   $(hostname)" >> /etc/hosts
```



#### 3. 关闭swap

```bash
# 所有节点都需执行
swapoff -a
sed -i '/swap/d' /etc/fstab

# 检查挂载状态
mount -a
```



#### 4. 关闭防火墙（可选）、SELinux

```bash
# ufw（可选）
systemctl disable ufw

# SELinux
setenforce 0
sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config
```



#### 5. 配置内核参数

注：安装 `docker` 时会自动配置该参数，**安装 `containerd` 时需手动配置**

```bash
# 允许iptables检查桥接流量
echo "net.bridge.bridge-nf-call-ip6tables = 1" >> /etc/sysctl.conf
echo "net.bridge.bridge-nf-call-iptables = 1" >> /etc/sysctl.conf
# 开启转发
echo "net.ipv4.ip_forward = 1" >> /etc/sysctl.conf

# 加载模块
modprobe br_netfilter

# 使内核参数生效
sysctl -p
```



#### 6. 安装 Docker

```bash
# 在所有节点执行
apt install -y docker
systemctl enable docker

# 配置 docker 的 cgroups 使用 systemd，编辑 /etc/docker/daemon.json 文件，并加入以下内容
"exec-opts": ["native.cgroupdriver=systemd"]

# 重载环境变量
systemctl daemon-reload

# 重启 docker
systemctl restart docker
```



#### 7. 安装 kubeadm、kubelet、kubectl

##### （1）添加 K8s 源

```bash
# 以阿里源为例
apt update && apt install -y apt-transport-https

curl https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg | apt-key add - 

cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
deb https://mirrors.aliyun.com/kubernetes/apt/ kubernetes-xenial main
EOF
```



##### （2）安装

```bash
# 更新软件源
apt update

apt install -y kubelet-1.23.17 kubeadm-1.23.17 kubectl-1.23.17

systemctl enable kubelet
```



#### 8. 初始化 master 节点

```bash
# cidr 网段使用 K8s kubeadm 默认的（不建议自定义）
kubeadm init \
      --apiserver-advertise-address=192.168.123.100 \
      --image-repository registry.aliyuncs.com/google_containers \
      --kubernetes-version v1.23.17 \
      --service-cidr=10.96.0.0/12 \
      --pod-network-cidr=10.244.0.0/16
```



初始化完成后根据界面上的提示配置 `kubelet`

```bash
mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config
```



#### 9. 从节点加入集群

```bash
# 该命令在主节点初始化完成后会自动生成并输出到终端界面，复制到从节点执行
kubeadm join 192.168.123.100:6443 --token cf3ym3.8h78hkp3z6sm1prk --discovery-token-ca-cert-hash sha256:ce120c883a37a6c93b88aa9de6799c2c1246a11c7d13e81d15102acf52131163

# 没保存 token 时，可以手动生成（默认有效期24小时）
kubeadm token create --print-join-command
```



#### 10. 安装 calico 网络插件

以 calico 3.25.0 版本为例

```bash
# 获取 calico 配置文件
cd /home/workspace/kubernetes/calico
curl https://calico-v3-25.netlify.app/archive/v3.25/manifests/calico.yaml -O

# 部署
kubectl apply -f calico.yaml
```

**注：在部署之前需准备好 `calico/node` , `calico/cni` , `calico/kube-controllers` docker 镜像并导入每一个节点**



#### 11. 补全 kubectl shell 命令

```bash
# 编辑~/.bashrc
nano ~/.bashrc

# 在末尾添加以下行
source  <(kubectl  completion  bash)

# 最后运行
source ~/.bashrc
source /etc/profile
```



#### 12. 给节点添加标签

```bash
# 查看节点标签详细信息
kubectl get nodes --show-labels

# 设置标签
kubectl label nodes master.k8s "test/role"=master
kubectl label nodes node1.k8s "test/role"=node1
```



#### 13. 测试

```bash
# 在主节点执行以下命令即可查看集群节点情况
kubectl get node -o wide
```



### 二、K8s 1.25.16 版本

*基础环境准备同理 1.23.17 版本的 1 - 5 节的步骤和内容*



#### 1. 安装 Containerd

```bash
# 安装
apt install -y containerd
mkdir -p /etc/containerd
containerd config default > /etc/containerd/config.toml

# 设置 Systemd cgroup 驱动
sed -i 's#SystemdCgroup = false#SystemdCgroup = true#g' /etc/containerd/config.toml

# 重启
systemctl restart container
```



#### 2. 安装 kubeadm、kubelet、kubectl

##### （1）添加 K8s 源

```bash
# 以阿里源为例
apt update && apt install -y apt-transport-https

curl https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg | apt-key add - 

cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
deb https://mirrors.aliyun.com/kubernetes/apt/ kubernetes-xenial main
EOF
```



##### （2）安装

```bash
apt install -y kubelet-1.25.16 kubeadm-1.25.16 kubectl-1.25.16
systemctl enable kubelet
```



#### 3. 初始化 master 节点

```bash
kubeadm init \
      --apiserver-advertise-address=192.168.123.100 \
      --image-repository registry.aliyuncs.com/google_containers \
      --kubernetes-version v1.23.17 \
      --service-cidr=10.96.0.0/12 \
      --pod-network-cidr=10.244.0.0/16
	  --cri-socket unix:///var/run/cri-dockerd.sock
```



初始化完成后根据安装完成后界面上的提示配置 `kubelet`

```bash
mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config
```



后续步骤同理 1.23.17 版本



### 三、应用部署

#### 1. 命名空间

为了方便不同项目，业务的管理，可以以 `namespace` 来划分不同的业务，即不同项目下的容器统一划分各自在对应的命名空间下

```bash
# 创建
kubectl create ns project

# 删除
kubectl delete ns project
```



#### 2. pvc 存储

K8s 集群不会单独为用户处理业务层应用的数据存储，这时可根据不同项目及命名空间声明各自对应的持久化存储（如：hostPath(测试环境使用)、nfs、云存储等）

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: project-data-pv
  namespace: project
spec:
  # 声明使用的存储空间大小
  capacity:
    storage: 10Gi
  # 读写策略
  accessModes:
    - ReadWriteMany
  storageClassName: manual
  # 本地挂载路径
  hostPath:
    path: /home/workspace/project/data

---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: project-data-pvc
  namespace: project
spec:
  # 读写策略
  accessModes:
    - ReadWriteMany
  storageClassName: manual
  # 资源使用策略
  resources:
    requests:
      storage: 10Gi
```

补充：

- PersistentVolume（pv）- 描述卷的具体信息
- PersistentVolumeClaim（pvc）- 对存储需求的申明，可以理解为一个申请单，系统根据这个申请单去找一个合适的 pv



#### 3. 部署应用

##### （1）处以 nginx 为例

- nginx-test.yaml：

  ```yaml
  ---
  apiVersion: apps/v1
  kind: Deployment
  # 元数据（声明当前 pod 在哪个命名空间下，labels 标签信息等）
  metadata:
    namespace: project
    name: nginx-test
    labels:
      web: nginx-test
  spec:
    # pod 副本数量
    replicas: 1
    # 用来查找关联的 pod，所有标签都匹配才行（即对应元数据中所提到的 labels）
    selector:
      matchLabels:
        web: nginx-test
    # 定义 pod 相关的数据
    template:
      metadata:
        labels:
          web: nginx-test
      spec:
        restartPolicy: Always
        containers:
          # 镜像名称
          - image: 'nginx:latest'
            # 镜像拉取策略
            imagePullPolicy: IfNotPresent
            name: project
            # 挂载容器日志
            volumeMounts:
              - name: nginx-test
                mountPath: /var/log/nginx
                subPath: nginx-test-log
            # 资源使用策略
            resources:
              limits:
                memory: 1Gi
              requests:
            # 环境变量
            env:
              - name: TZ
                value: Asia/Shanghai
            # 容器端口
            ports:
              - containerPort: 80
                protocol: TCP
        # 挂载 pvc
        volumes:
          - name: nginx-test
            persistentVolumeClaim:
              claimName: project-data-pvc
        # dns解析（使用默认配置即可）
        dnsPolicy: ClusterFirst
        dnsConfig: {}
        schedulerName: default-scheduler
        terminationGracePeriodSeconds: 30
    progressDeadlineSeconds: 600
    # 扩缩容策略
    strategy:
      type: RollingUpdate
      rollingUpdate:
        maxUnavailable: 25%
        maxSurge: 25%
  
  ---
  apiVersion: v1
  kind: Service
  metadata:
    namespace: project
    name: nginx-test
    labels:
      web: nginx-test
  spec:
    # service类型
    type: NodePort
    ports:
      - port: 80
        targetPort: 80
        nodePort: 30080
        protocol: TCP
    selector:
      web: nginx-test
  ```

```bash
# 启动
kubectl apply -f nginx-test.yaml

# 删除
kubectl delete -f nginx-test.yaml

# 查看 pod 日志
kubectl logs -f nginx-test-1cxb9cc666-1ac46 -n project

# 查看 pod 明细信息
kubectl describe pod nginx-test-1cxb9cc666-1ac46 -n project

# console
kubectl exec -it nginx-test-1cxb9cc666-1ac46 -n project -- /bin/bash

# 重新打包镜像后重启
kubectl rollout restart deployment nginx-test -n l24038
```



##### （3）补充：

- Service：
  - Service 通过 label 关联对应的 pod
  - Servcie 生命周期不跟 pod 绑定，不会因为 pod 重创改变 ip
  - 提供负载均衡功能，自动转发流量到不同 pod
  - 可对集群外部提供访问端口
  - 集群内部可通过服务名字访问
- 工作负载：
  - Deployment：适合无状态应用，所有 pod 等价，可替代
  - StatefulSet：有状态的应用，适合数据库这种类型
  - DaemonSet：在每个节点上跑一个 pod，常用于节点监控、节点日志收集等
  - Job & CronJob：Job 用于一次性的任务，CronJob 会根据其时间规划反复运行



#### 4. ConfigMap 和 Secret

##### （1）ConfigMap

`ConfigMap` 可以理解为字典变量 / 配置文件，即代码中需根据具体业务场景使用的变量（如数据库链接地址，环境变量等）

以 `mssql` 数据库连接地址（键值对的形式）为例：

```yaml
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: project-config
  namespace: project
data:
  a: sunxiaochun
```



执行

```bash
kubectl apply -f project-config.yaml
```



在业务应用中作为环境变量使用：

```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
  namespace: project
  name: nginx-test
  labels:
    web: nginx-test
spec:
  replicas: 1
  selector:
    matchLabels:
      web: nginx-test
  template:
    metadata:
      labels:
        web: nginx-test
    spec:
      restartPolicy: Always
      containers:
        - image: 'nginx:latest'
          imagePullPolicy: IfNotPresent
          name: project
          volumeMounts:
            - name: nginx-test
              mountPath: /var/log/nginx
              subPath: nginx-test-log
          resources:
            limits:
              memory: 1Gi
            requests:
          env:
            - name: TZ
              value: Asia/Shanghai
            # 使用 ConfigMap 中配置的环境变量
            - name: a
              valueFrom:
                configMapKeyRef:
                  name: project-config
                  key: a
          ports:
            - containerPort: 80
              protocol: TCP
      volumes:
        - name: nginx-test
          persistentVolumeClaim:
            claimName: project-data-pvc
      dnsPolicy: ClusterFirst
      dnsConfig: {}
      schedulerName: default-scheduler
      terminationGracePeriodSeconds: 30
  progressDeadlineSeconds: 600
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 25%
      maxSurge: 25%

---
apiVersion: v1
kind: Service
metadata:
  namespace: project
  name: nginx-test
  labels:
    web: nginx-test
spec:
  type: NodePort
  ports:
    - port: 80
      targetPort: 80
      nodePort: 30080
      protocol: TCP
  selector:
    web: nginx-test
```



##### （2）Secret

`Secret` 密文可以理解为存放重要数据的地方，如 ssl 证书配置，token，sdk endpoint、appid等

以 `MySQL` 数据库用户名、密码为例：

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: project-mysql-secret
type: Opaque
data:
  # 键值对中的 value 必须以 base64 编码
  username: xxx
  password: xxx
```



执行

```bash
kubectl apply -f mysql-secret.yaml
```



在后端业务应用中作为环境变量使用：

```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
  namespace: project
  name: backend-app
  labels:
    app: backend-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: backend-app
  template:
    metadata:
      labels:
        app: backend-app
    spec:
      restartPolicy: Always
      containers:
        - image: 'xxx:latest'
          imagePullPolicy: IfNotPresent
          name: backend-app
          volumeMounts:
            - name: backend-app
              mountPath: /app/log/xxx
              subPath: backend-app-log
          resources:
            limits:
              memory: 2Gi
            requests:
          env:
            - name: TZ
              value: Asia/Shanghai
            # 使用 Secret 中配置的环境变量
            - name: USERNAME
              valueFrom:
                secretKeyRef:
                  name: project-mysql-secret
                  key: username
            - name: PASSWORD
              valueFrom:
                secretKeyRef:
                  name: project-mysql-secret
                  key: password
          ports:
            - containerPort: 8080
              protocol: TCP
      volumes:
        - name: backend-app
          persistentVolumeClaim:
            claimName: project-data-pvc
      dnsPolicy: ClusterFirst
      dnsConfig:
        options: []
      schedulerName: default-scheduler
      terminationGracePeriodSeconds: 30
  progressDeadlineSeconds: 600
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 25%
      maxSurge: 25%

---
apiVersion: v1
kind: Service
metadata:
  namespace: l24038
  name: backend-app
  labels:
    app: backend-app
spec:
  ports:
    - port: 8080
      targetPort: 8080
      protocol: TCP
  selector:
    app: backend-app
```