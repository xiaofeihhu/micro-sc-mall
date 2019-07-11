**项目说明**  
该项目基于开源spring-cloud搭建的微服务工程脚手架  
使用版本：  
springboot：2.0.1.RELEASE  
springcloud：Finchley.RELEASE

附上各组件版本的对应关系   

|Spring Boot  | Spring Cloud  | Spring Cloud Alibaba|
|:-------------|:--------|:-------|
|2.1.x  | Greenwich  | 0.9.x|  
|2.0.x  | Finchley  | 0.2.x|  
|1.5.x  | Edgware  | 0.1.x|  
|1.5.x  | Dalston  | 0.1.x|  

**服务说明**  

服务组件 | 服务名 | 端口号
:------|:------|:-------
配置中心|spring cloud config |   8100  
服务注册与发现 |eureka | 8761  
熔断机制|hystrix turbine |  8023  
网关|zuul  |   8080  
负载均衡|open feign  | 
服务监控|springboot-admin |  8022  
链路追踪 |sleuth+zipkin |9411  
消息总线|spring cloud bus  |
MQ|rabbitmq |5672  
数据库|mysql  |
鉴权|auth-server| 8020  
oauth2授权|auth-authorization-server | 8000  
测试用-生产者服务|producer|8082  
测试用-消费者服务|consumer|8081  
用户服务|user|8085
商品服务|product|8086
订单服务|order|8087
支付服务|pay|8088
物流服务|logistics|8089  


**TODO List**

No | 内容 | 状态 |备注
:------|:------|:-------|:-------
1| 采用JWT方式开发鉴权服务 |**`done`** |
2| 集成docker和docker-compose部署 |**`done`** |
3| 实际服务器部署并实现jenkins持续集成 | |
4| feign拦截请求头信息  |**`done`** |
5| 统一接口文档管理swagger |**`done`** |
6|日志管理集成logback |**`done`** |
7| feign 不支持传参POJO对象  |**`done`** |
8| 集成spring session  | **`done`** |
9| 集成kafka  | **`done`** |
10|集成spring-session  | **`done`** |
11| 集成 spring-kafka 需安装kafka2.1.0+版本  | **`done`** |
12|集成redis 定时任务生成订单id |**`done`** |
13| 集成 mongodb | |
14| 分布式id生成器 | **`done`**|
15|完成一个简单的完整线上流程| 


**启动方式**  
  * docker方式  
    需预先准备docker环境并安装docker-compose组件
    修改pom文件中的docker_host
    通过maven命令mvn package 生成docker镜像，  
    再cd到docker-compose.yml文件目录执行启动命令:docker-compose up -d
  * 正常方式  
    通过maven命令mvn package 打包，
    将target文件夹下的tar文件解压目录，执行bin目录下的启动脚本
 

**最后**  
    前端工程由于技术和时间原因没有着手，希望有这方面想法和技术的大神一起加入  
    联系方式：
        Tel 13851936496     Email 401263564@qq.com
        ![image](https://github.com/xiaofeihhu/micro-sc-mall/blob/master/WeChat.png)