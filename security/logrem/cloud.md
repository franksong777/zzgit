###### ######
###     Spring Cloud =======

# day1  input the mysql data have problem , mysql>set names gbk;   //  SET NAMES utf8mb4;  ??


# day1- 06  ==> use RestTemplate invoke the "users/id" ;

1-A
### ###
### RestTemplate for distance invoke === ===

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){     // define a bean for @Autowired in Service , must be public !
		return new RestTemplate();
	}
}

### ###
### Eureka
           a, <artifact>spring-cloud-starter-netflix-eureka-server  <group>org.springframework.cloud
           b, @EnableEurekaServer    
           c, in application.yml: 
                  server:
                     port: 10086
                  spring:
                     application: 
                        name: eurekaserver
                  eureka:
                     client:
                        service-url:
                           defaultZone: http://127.0.0.1:10086/eureka/         


# Eureka --- client 
               a, a, <artifact>spring-cloud-starter-netflix-eureka-client  <group>org.springframework.cloud
               b, in application.yml     // same as above - only name changes



### Service - Copy Configuration - "make a new Name" - enviroment - VM options - "-Dserver.port=8082"  
                 // copy a new start

# use the dervice  , in "OrderServiceImpl"     a, String url = "http://userservice/user/"+order.getUserId();
                                               b, @Bean
                                                  @LoadBalanced
                                                  public RestTemplate restTemplate(){

                                                  }

# ## 
# Ribbon  - LoadBalanced
               === change loadbalance rule
                   a, (whole scope)     @Bean
                                        public IRule randomRule(){
                                        	return new RandomRule();
                                        }
                   b, change for one service --- 
              userservice:
                 ribbon:
                    NFLoadBalanceRuleClassName: com.netflix.loadbalancer.RandomRule

#      ribbon --- eager-load  ---     
                                     ribbon:
                                        eager-load:
                                           enabled: true
                                           clients:
                                              - userservice


# ##
# Nacos          // alibaba nacos --- alibaba cloud    // port: 8848   // username/password: nacos

      >startup.cmd -m standalone          // start                                               

 === why     ---   <parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.9.RELEASE</version>                          // can not analysis <version>2.5.4</version>   ? ?
		<relativePath/> <!-- lookup parent from repository -->
	</parent> 

#      nacos set cluster property :  a, cloud:
                                           nacos:
                                              server-addr: localhost:8848
                                              discovery:
                                                 cluster-name: HZ   // set the cluster

            b, in order-service application.yml
               userservice:
                  ribbon:
                      NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule                              


# in Nacos center, 设置实例的权重值， “操作”-“编辑”---编辑实例-“权重”
# ### 
   设置实例的权重值 --- 这个非常重要，可以权重值为 “0” ， 达到 "CI/CD"   ? ?

# namespace  ---  Nacos 环境隔离
        1） namespace用来做环境隔离
        2） 每个namespace都有唯一id
        3)  不同namespace下的服务不可见， 不可以相互调用 。

# Nacos注册中心  ---  临时实例和非临时实例
                          spring:
                             cloud:
                                nacos:
                                   discovery:
                                      ephemeral: false   // false == 非临时实例        


# ###
# Nacos配置管理 （关于配置热更新 --- 又是关于CI/CD ？？）
        在Nacos中添加配置信息：
        “配置列表”  - “+” --- 在弹出form中， 填写“Data ID”  ---服务名称-profile.yaml
                   配置内容---只填写核心，可能有变化的配置

# 统一配置管理  
               a, in pom.xml:  <artifact>spring-cloud-starter-alibaba-nacos-config    <group>aom.alibaba.cloud
               b, "bootstarp.yml"  优先级最高， 优于"application.yml" 
                    in bootstrap.yml :
                                  spring:
                                     apllication:
                                        name: userservise
                                     profiles:
                                        active: dev
                                     cloud:
                                        nacos:
                                           server-addr: localhost:8848
                                           config:
                                              file-extension: yaml
                        Nacos配置管理中，写的就是: userservise-dev.yaml     // 等同于上面配置内容

## 通过@Value 加载配置中的数据名称的“值”   例如：in UserController
                  ===  public class UserController{
                    @Value("${pattern.dateformat}")     // 拿到 pattern.dateformat 的值
                    private String dateformat;

                    @GetMapping("now")
                    public String now(){
                      return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
                    }

                  }
     这是上面要读取的配置在nacos配置管理-配置详情-配置内容： 
                  pattern:
                     dateformat: yyyy-MM-dd HH:mm:ss  


## Nacos配置管理 --- 配置自动刷新
   Nacos中的配置文件变更后，微服务无需重启就可以感知。不过需要通过下面的两种配置实现：
   方式一： 在@Value注入的变量所在的类上添加注解 @RefreshScope

   方式二： 使用 @ConfigurationProperties 
            a,
                 @Component
                 @Data
                 @ConfigurationProperties(prefix = "pattern")    // 采用“约定大于配置的方式”， 
                                                    //只要前缀名和变量名两者拼接与配置文件一致，就能完成属性的注入。
                 public class PatternProperties{    // 前缀名 "prefix="  :  变量名"dateformat"
                     private String dateformat;
                 }
             b, 
                  in the "UserController"   // 第一种方式 注入@Value 的类， 使用如下：
                   @Autowired
                   private PatternProperties patternProperties;

                   @GetMapping("now")
                   public String now(){
                    return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
                   } 
   
## Nacos 多环境配置共享      // 关注拿值的方式和理念

    === in UserController 

                      @Autowired 
                      private PatternPreoperties properties;

                      @GetMapping("prop")
                      public Patternproperties properties(){
                        return properties;
                      }                         // day01 - 05


# "userservice-dev.yaml" --- only in dev enviroment  vs "userservice.yaml"  --- shared 


# ##
# Nacos cluster setup  --- C:\workteams\cloud\doc\dbb      nacos-cluster.md


### ###
### Feign --- http客户端， feign是一个声明式的http客户端，其作用就是帮助我们优雅的实现http请求的发送。
#  Feign makes writing java http clients easier
        a, in pom.xml:  <artifact>spring-cloud-starter-openfeign     <group>org.springframework.cloud
        b, in "bootstarp"  above  @EnableFeignClients
        c, 编写Feign客户端：
        @FeignClient("userservice")
        public interface UserClient{
          @GetMapping("/user/{id}")
          User findById(@PathVariable("id") Long id); 
        }

      主要是基于SpringMVC的注解来声明远程调用的信息，比如：
      - 服务名称： userservice
      - 请求方式： GET
      - 请求路径： /user/{id}
      - 请求参数： Long id 
      - 返回值类型： User


# Feign     自定义配置  feign.logger.level    //  日志级别： NONE , BASIC , HEADERS , FULL

Feign 日志方式一：
Feign config set (whole scope)
       feign:
          client:
             config:
                default:      //  userservice --- config for userservice  (part scope)
                   loggerLevel: FULL


Feign 日志方式二：       

            a, create a class --- public class FeignClientConfiguration{
              @Bean
              public Logger.Level feignLogLevel(){
                return Logger.Level.BASIC;
              }
            }
            b, (whole scope)  in "bootstrap" above 
                        @EnableFeignClients(defaultConfiguration=FeignClientConfiguration.class)
            c, (part scope)   
                        @FeignClient(value="userservice",configuration=FeignClientConfiguration.class)


# Feign的性能优化-连接池配置

Feign添加HttpClient的支持：
a, in pom.xml:   <artifact>feign-httpclient   <group>io.github.openfeign

b, in application.yml:  
                feign:
                   client:
                      config:
                         default:
                            loggerLevel: BASIC
                   httpclient:
                      enabled: true
                      max-connections: 200
                      max-connections-per-route: 50

## Feign 的最佳实践：
 1） 让controller 和FeignClient继承同一接口
 2） 将FeignClient, POJO, Feign的默认配置都定义到一个项目中，供所有消费者使用

 实现方式二： 抽取Feignclient
               a, create module , named feign-api, put in dependency in pom.xml
          <artifact>spring-cloud-starter-openfeign   <group>org.springframework.cloud
   b, remove "order-service" UserClient , User, DefaultFeignConfiguration to "feign-api"                                 
   c, "order-service" dependency "feign-api" in pom.xml
   4,the remove from the "feign-api", need ---re-import--- to define
   === problem is when start , can not found "UserClient" bean ,   Exception "could not be found"
     这里语法没报错，但是运行报错， 肯定是spring容器管理的bean不能创建实例，说明注入有问题，
    ---解决 ： 当定义的FeignClient不在SpringBootApplication的扫描包范围时（指order-service）,
               这些FeignClient无法使用， 两种方式解决：   //day02-11

              
### ###
### Docker
            --- Docker and DockerHub(Docker Registry) , DOckerHub 是Docker镜像(image)的托管平台。
       DockerHub的公开服务， 比如： 网易云镜像服务，阿里云镜像库 等       

            --- Docker is a CS programing , s- DockerServer / c - Client 
            客户端client： 通过命令（本地，本机）或RestAPI(远程)向DockerServer发送指令，
                           client 指令：
                            - docker build
                            - docker pull          // 从公开的云服务镜像Registry 拉取需要的镜像到DockerServer上使用
                            - docker run


      Docker install in CentOS : C:\workteams\cloud\doc\dcc

# Docker install met problem : find that the "python --version "  have problem , centOS default python2 , 
                               should change python version in "usr/bin/yum-config-manager"    

                               and then , need re-construct the "--add-repo" as below :

yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo                // check "Log1" 01 ,02 

# Docker CMD: 
        # systemctl stop firewalld
        # systemctl disable firewalld
        # systemctl status firewalld
        -------------------------------- above firewall close and check status -------------
        # systemctl start docker
        # systemctl status docker
        # docker -v                      // check docker version

# Docker 基本操作
            镜像名称：  [repository]:[tag]
                              mysql:5.7      
     如果没有指定tag , default is the latest version
#     === image CMD:
           docker pull / docker images  // docker rmi --- delete image
           docker puch --- push docker to private repository
           docker save --- save docker to be copy from "u 盘"    // docker load  --- from copy 
           docker build   // from Dockerfile
#      # docker --help // 查询docker命令 
       # docker images --help    // check  all images CMD

## execise CMD :
                 # docker pull nginx    
                 # docker images
                 # clear //  clear screen
                 --------------------------------
#                # docker save --help
                 # docker save -o nginx.tar nginx:latest
                 # docker rmi nginx:latset
#                # docker load --help  
                 # docker load -i nginx.tar





