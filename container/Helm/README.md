## Helm

*参照 b站王晓春老师 helm 课程*

### 一、安装

1. 官网：https://helm.sh/

2. 此处以二进制包安装为例：

   ```bash
   # K8s 管理节点
   wget https://github.com/helm/helm/releases/download/v3.18.3/helm-v3.18.3-linux-amd64.tar.gz.asc
   
   tar -zxvf helm-v3.18.3-linux-amd64.tar.gz.asc -C /usr/local/bin
   
   ln -s /usr/local/bin/linuc-amd64/helm /usr/bin/helm
   
   # 查看版本信息
   helm version
   ```



### 二、常用命令

#### 1. 管理类

- Repository 管理

  add、list、remove、upgrade、index子命令等

- Chart 管理

  create、package、pull、push、dependency、search、show、verify等

- Release 管理
- instll、upgrade、get、list、history、status、rollback、uninstall等



#### 2. 常用命令示例

```bash
# repo
helm repo list # 列出已添加的仓库
helm repo add [REPO_NAME] [URL] # 添加远程仓库并指定名称
helm repo remove [ROPE_NAME] # 删除仓库
helm repo update # 更新仓库，类似于 apt update
helm search hub [xxx] # 从 artifacthub 搜索，类似于 docker search
helm search repo [xxx] # 从本地仓库搜索
helm show chart [CHART] # 查看 chart 包的信息
helm show values [CHART] # 查看 chart 包下 values.yaml 的文件内容

# 拉取
helm pull repo/chartname # 拉取最新版本的 chart.tgz 到当前目录下
helm pull chart_URL # 从 url 拉取
helm pull repo/app --version 1.0 --untar # 拉取指定版本并解压

# 安装
helm install [NAME] [CHART] [--version <string>] # 安装指定版本的 chart
helm install [CHART] --generate-name # 安装时自动生成 RELEASE_NAME
helm install --set key1=value1,key2=value2 [RELEASE_NAME] [CHART] # 动态设置参数
helm install -f values.yaml [RELEASE_NAME] [CHART] # 引用指定文件配置
helm install --debug --dry-run [RELEASE_NAME] [CHART] # 调试但不实际运行，输出调试的结果

# 删除
helm uninstall [RELEASE_NAME] # 删除 release

# 查看
helm list # 列出已安装的 release
helm status [RELEASE_NAME] # 查看 release 状态
helm get notes [RELEASE_NAME] # 查看 release 的说明
helm get values [RELEASE_NAME] -n [NAMESPACE] > values.yaml # 查看 release 生成的 values 并导出至指定文件
helm get mainfest [RELEASE_NAME] -n [NAMESPACE] # 查看 release 生成的资源清单文件

# 升级和回滚
helm upgrade [RELEASE_NAME] [CHART] --set key=value # 更新 release，同时动态设置参数
helm upgrade [RELEASE_NAME] [CHART] -f chartname/values.yaml # 同上
helm rollback [RELEASE_NAME] [REVISION] # 回滚至指定版本，不指定版本时默认回滚至上一个版本
helm history [RELEASE_NAME] # 查看历史记录

# 打包
helm package chartname/ # 将指定目录下的 chart 打包为 chartname.tgz 包至当前目录下
```



#### 3. 安装 chart 的六种方式

- By chart reference: 

  ```bash
  helm install mysql xxx/mysql
  ```

- By path to a packaged chart:

  ```bash
  helm install mysql ./mysql-5.7.35.tgz
  ```

- By path to an unpacked chart directory:

  ```bash
  helm install mysql ./mysql
  ```

- By absolute URL:

  ```bash
  helm install mysql https://xxx.com/charts /mysql-5.7.35.tgz
  ```

- By chart reference and repo url:

  ```bash
  helm install --repo https://xxx .com/charts/mysql mysql
  ```

- By oci registries:

  ```bash
  helm install mysql --version 5.7.35 oci://xxx.com/charts /mysql
  ```