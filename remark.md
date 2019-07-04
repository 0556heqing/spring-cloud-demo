# SpringCloud

### 服务治理：Eureka
服务端步骤
  1. 增加eureka-server的依赖
  ```
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-eureka-server</artifactId>
     </dependency>
  ```
  2. 增加默认配置
  ```
     eureka.instance.hostname = localhost
     eureka.client.register-with-eureka = false
     eureka.client.fetch-registry = false
  ```
  3. 注册服务中心(启动类增加EnableEurekaServer注解)
  ```
     @EnableEurekaServer
     @SpringBootApplication
  ```

客户端步骤
  1. 增加eureka的依赖
  ```
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-eureka</artifactId>
     </dependency> 
  ```
  2. 增加默认配置
  ```
     spring.application.name = eureka-client-response
     eureka.client.serviceUrl.defaultZone = http://localhost:10001/eureka/
  ```
  3. 注册服务中心(启动类增加EnableEurekaClient注解)
  ```
     @EnableEurekaClient
     @SpringBootApplication
  ```
  
测试步骤
   1. 增加ribbon的依赖
   ```
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-ribbon</artifactId>
     </dependency> 
   ```
   2. 启动类增加restTemplate
   ```
     @Bean
     @LoadBalanced
     RestTemplate restTemplate() {
         return new RestTemplate(); 
     }
   ```
   3. 依次重启service,client-resp,client-req。
      观察http://localhost:10001/
   4. 依次请求http://localhost:10002/test，http://localhost:10003/test观察结果
   
### 服务容错保护：Hystrix
客户端步骤
  1. 增加hystrix的依赖
  ```
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-hystrix</artifactId>
     </dependency>
  ```
  2.开启容错保护(启动类增加EnableCircuitBreaker注解)   
  ```
     @EnableCircuitBreaker
  ```
### 配置中心：config
客户端步骤
    注：https://blog.csdn.net/hubo_88/article/details/80726901
    
    
    当你配置的配置中心的 server.port 不是 8888 的时候，其他服务就起不来了，从日志中可以发现，服务启动的时候，Fetching config from server at: http://localhost:8888，说明其他服务还是去 8888 这个默认端口取配置中心的文件，而不去你在 application.yaml 文件中配置的配置中心读取配置文件，这是什么原因呢？
    其实这就是一个配置文件的优先级的问题，当服务启动的时候，它需要加载一些配置才能启动成功，而当你把配置信息放至配置中心的时候，启动服务首先就要去配置中心获取配置信息，然后加载这些信息才能成功启动。
    当你的服务配置文件使用 application.yaml 文件时，服务启动还没到加载 application.yaml 文件那一步，所以并不会去你配置的注册中心里的配置中心读取所需要的配置信息，因为application.yaml 的优先级不高，而此时又需要一些配置信息（例如数据库的信息），系统才能继续往下执行启动，这个时候就需要使用 bootstrap.yaml 引导配置文件，使用这个配置文件时，服务在启动的时候就会先加载 bootstrap.yaml 配置文件，这样就会去你配置的注册中心里的配置中心读取配置文件信息，然后加载信息进行启动。
    