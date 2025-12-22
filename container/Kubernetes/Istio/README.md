## Istio

### 一、服务网格

服务网格（Service Mesh）是一个专用的基础架构层，用于管理分布式应用程序中各个微服务之间的通信。它充当透明且分散的代理网络，并且部署在应用服务旁边，这些代理通常被称为 **Sidercar**，用于处理服务间的网络调用，限流，熔断，负载均衡等。

![1.服务网格](static/1.服务网格/1.服务网格.png)

当微服务（Service）集群扩大到一定规模后，就形成了网格状（Mesh），即 Service Mesh 形态



### 二、Istio

官网：https://istio.io/latest/zh/

Istio 是一种开源服务网格，可透明地分层到现有的分布式应用程序上。 Istio 的强大功能提供了一种统一且更高效的方式来保护、连接和监控服务。 Istio 是实现负载均衡、服务到服务身份验证和监控的途径 - 几乎无需更改服务代码。包含以下功能：

- 使用双向 TLS 加密、强大的基于身份的身份验证和鉴权在集群中保护服务到服务通信
- HTTP、gRPC、WebSocket 和 TCP 流量的自动负载均衡
- 使用丰富的路由规则、重试、故障转移和故障注入对流量行为进行细粒度控制
- 支持访问控制、限流和配额的可插入策略层和配置 API
- 集群内所有流量（包括集群入口和出口）的自动指标、日志和链路追踪

![1.v1.21架构](static/2.Istio/1.v1.21架构.png)

Istio 服务网格从逻辑上划分为**数据平面**和**控制平面**

- 数据平面：由一组被部署为 Sidercar 的智能代理（Envoy）组成，负责协调和控制微服务之间的所有网络通信，同时也收集和报告所有网格流量的遥测数据
- 控制平面：管理、配置代理，进行流量路由



### 三、安装

*以 istioctl 为例*

```bash
# 下载
curl -L https://istio.io/downloadIstio | sh -
cd istio-1.28.1
export PATH=$PWD/bin:$PATH
```

安装目录包含：

- `samples/` 目录下的示例应用
- `bin/` 目录下的 [`istioctl`](https://istio.io/latest/zh/docs/reference/commands/istioctl) 客户端可执行文件。

```bash
# 安装
istioctl install --set profile=demo -y
        |\          
        | \         
        |  \        
        |   \       
      /||    \      
     / ||     \     
    /  ||      \    
   /   ||       \   
  /    ||        \  
 /     ||         \ 
/______||__________\
____________________
  \__       _____/  
     \_____/        

✔ Istio core installed ⛵️                                                                                          
✔ Istiod installed 🧠                                                                                             
✔ Ingress gateways installed 🛬                                                                                   
✔ Egress gateways installed 🛫                                                                                    
✔ Installation complete 
```

istio 提供的几种内置配置，这些配置文件提供了对 Istio 控制平面和 Istio 数据平面 Sidecar 的定制内容:

- **default**：根据 `IstioOperator` API 的默认设置启动组件。 建议用于生产部署和 [Multicluster Mesh](https://istio.io/latest/zh/docs/ops/deployment/deployment-models/#multiple-clusters) 中的 Primary Cluster。

  您可以运行 `istioctl profile dump` 命令来查看默认设置。

- **demo**：这一配置具有适度的资源需求，旨在展示 Istio 的功能。 它适合运行 [Bookinfo](https://istio.io/latest/zh/docs/examples/bookinfo/) 应用程序和相关任务。 

  此配置文件启用了高级别的追踪和访问日志，因此不适合进行性能测试。

- **minimal**：与默认配置文件相同，但只安装了控制平面组件， 它允许您使用 [Separate Profile](https://istio.io/latest/zh/docs/setup/upgrade/gateways/#installation-with-istioctl) 配置控制平面和数据平面组件(例如 Gateway)。

- **remote**：配置 [Multicluster Mesh](https://istio.io/latest/zh/docs/ops/deployment/deployment-models/#multiple-clusters) 的 Remote Cluster。

- **empty**：不部署任何东西。可以作为自定义配置的基本配置文件。

- **preview**：预览文件包含的功能都是实验性。这是为了探索 Istio 的新功能，不确保稳定性、安全性和性能（使用风险需自负）。

|                        | default | demo | minimal | remote | empty | preview |
| ---------------------- | ------- | ---- | ------- | ------ | ----- | ------- |
| 核心组件               |         |      |         |        |       |         |
| `istio-egressgateway`  |         | ✔    |         |        |       |         |
| `istio-ingressgateway` | ✔       | ✔    |         |        |       | ✔       |
| `istiod`               | ✔       | ✔    | ✔       |        |       | ✔       |

```bash
# 给命名空间添加标签，指示 Istio 在部署应用的时候，自动注入 Envoy Sidecar 代理
kubectl label namespace [default] istio-injection=enabled
```



安装 Kubernetes Gateway API CRD

Kubernetes Gateway API CRD 在大多数 Kubernetes 集群上不会默认安装， 在使用 Gateway API 之前需要安装

```bash
$ kubectl get crd gateways.gateway.networking.k8s.io &> /dev/null || \
   { kubectl kustomize "github.com/kubernetes-sigs/gateway-api/config/crd?ref=v1.4.0" | kubectl app
```



### 四、流量管理

#### 1. Gateway

在安装 istio 的时候，同时安装了入口和出口网关，这两个网关都运行了一个 Envoy 代理实例，它们在网格的边缘作为负载均衡器的角色。

![4.1Gateway](static/4.流量管理/4.1Gateway.png)

gateway 资源实例：

```yaml
apiVersion: networking.istio.io/v1alpha3
  kind: Gateway
  metadata:
    name: gateway-demo
    namespace: default
  spec:
    selector:
      istio: ingressgateway
    servers:
    - port:
        number: 80
        name: http
        protocol: HTTP
      hosts:
      - dev.example.com
      - test.example.com

```

上述示例做了哪些事：

- 配置了一个代理，作为负载均衡器
- 服务端口为80
- 应用于 istio 入口网关代理
- hosts 字段作为过滤器，只有以 dev.example.com 和 test.example.com 为目的地的流量才允许通过

为了控制和转发流量到集群内运行的实际实例，还需要配置 VirtualService，并与网关相连接。



（1）简单路由实例

部署 nginx，并通过 istio 网关进行访问

```yaml
---
apiVersion: apps/v1
kind: Deployment
metadata:
  namespace: test
  name: nginx
  labels:
    app: nginx
spec:
  replicas: 1
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      restartPolicy: Always
      containers:
        - image: 'nginx:latest'
          imagePullPolicy: IfNotPresent
          name: nginx
          env:
            - name: TZ
              value: Asia/Shanghai
          ports:
            - containerPort: 80
              protocol: TCP

---
apiVersion: v1
kind: Service
metadata:
  namespace: test
  name: nginx
  labels:
    app: nginx
spec:
  ports:
    - port: 80
      targetPort: 80
      protocol: TCP
  selector:
    app: nginx
```

```yaml
# 网关
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: gateway-nginx
  namespace: test
spec:
  selector:
    istio: ingressgateway
  servers:
  - port:
      number: 80
      name: http
      protocol: HTTP
    hosts:
      - '*'
```

在未绑定 VirtualService 之前，网关还不知道要将流量路由到哪

```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: virtualService-nginx
  namespace: test
spec:
  hosts:
    - '*'
  gateways:
    - gateway-nginx
  http:
    - route:
      - destination:
          host: nginx.test.svc.cluster.local
          port: 80
```

部署完之后，通过 curl -v x.x.x.x 即可测试