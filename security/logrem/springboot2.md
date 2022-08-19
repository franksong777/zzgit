### ################  utils
## windows port was taken
    in terminal (CMD) 
    # check port
    netstat -ano
    # check port determinedly
    netstat -ano | findstr "80"
    # check the PID
    tasklist
    # kill depend on the PID
    taskkill -f -pid "9480"
    # kill depend on progress name
    taskKill -f -t -im "name"

### ########### Linux ------------------------

          [root@node1]# ip addr            // check the virtual server ip address
                    in --- inet 192.168.88.100

#  ## mySQL change password --- after login : set password for root@localhost = password('root')      // mySQL change password             

### run the project in linux enviroment on the backend runing 
		in linux
		nohup java -jar project.jar > server.log 2>&1 &                // start the project

		ps -ef | grep "java -jar" /--- get the pid 
		kill -9 pid                                                    // stop the project

        cat server.log                           // check the log , log will generate as the same file with the project.jar
-----   ----------------------------------------------------------------------------------------

### mysql table copy methods ;

### need create a new table with a fully same constructor as the old table,
            select * from user into outfile "C:/softee/table/user.txt";

            load data infile "C:/softee/table/account.txt" into table tb_account;

  ---------   --------------------------------------------------------------  ------------------        
### ### 
=== springboot2 maintainance 55
### ###### in IDE , configuration as in Terminal to make sure wrether or not can use in Terminal 
                the row in the top , "Bootstrap" --- "Edit Configurations" --- the second row below "Build and Run"
                in "Program arguments - hint"  ---  like "--server.port=8081"

                == change application.yml name 
                     a,   --spring.config.name=ebank
                     b,   --spring.config.location=classpath:/ebank.yml
                     c,   --spring.config.location=classpath:/ebank.yml,classpath:/ebank-server.yml   // lastest using
                     == developing rule : program do not error do not change , only change use overlay!!!

            == when use spring cloud , server will not set configuration file , will be manange by registry center,
                   upload configuration info by dynamic from config center .  // dynamically

## can set maybe for can not be changed property , maybe safely more as below :
            public static void main(String[] args){
                SpringApplication.run(ssmpApplication.class);   //  no "args"
                           }
## in developing enviroment  under "resources" mkdir a folder "config" and in the folder make a file "application.yml"
             this "config" file inside the ".yml- will cover the ../ application.yml when confilct 
             that means , the two file will cooperation and make sure in "config/application.yml"
             application.yml  --- develop// config/application.yml  --- project manager
             application.yml as same file with -jar -- client 
             config/application.yml as same file with -jar  --- client manager

### ###
    multi enviroment developing config in .yml
    spring:
       profiles:
          active: pro
    ---
    spring:
       profiles: pro
    server:
       port: 80
    ---
    spring:
       profiles: dev
    server:
       port: 81
    ---
    spring:
       profiles: test
    server:
       port: 82
 == above commonly using to split into 4 .yml file !
##  application .yml  multi congfig file write method :
      ==  spring:
             profiles:
                active: dev
                include: devDB,devMVC          // if have conflict , lastest overlay the front springboot2-maintain-61
## after springboot 2.4 version
                    spring:
                       profiles:
                          active: dev
                          group:
                             "dev": devDB,devRedis,devMVC
                             "pro": proDB,proRedis,proMVC
                             "test":

## multi enviroment control botn in maven and soringboot , maven will be the primary, notice should do compile,
       check in springboot2 maintain -62   // doc - springboot2-IDE-10

# ###
# Logging  -----------------------------------------------------------------

##  logging set in springboot2   --- doc_springboot2 -  11_log
set a, logging level in .yml
        == logging:
              level:
                 root: info
                 ebank: debug                  // set a group logging level
                 com.eagle.controller: warn    //  set a package's logging level

              group: 
                 ebank: com.eagle.controller    // set a group fpr prepare define logging level


set b, in the Controller.class

       @Slf4j              // for logging  after dependency lombok in pom.xml , straight use log.info(); below

        //  private static final Logger log = LoggerFactory.getLogger(ControllerName.class);

          and then , can use "log" methods to logging, like :
                    log.debug("debug...");
                    log.info("info...");
                    log.warn("warn...");
                    log.error("erroe...");


## Logging output style control -----------------

   2021-11-02 12:25:06.334 info   2336 ---main com.eagle.springboot2Application : started springboot2LogApplication ...
     time                  level  PID     thread        class/interface                  logging info   

 == logging:
       pattern:
          console: "%d %clr(%5p) --- [%16t] %clr(%-40.40c){cyan} : %m %n"      // customize logging output pattern
            %d --- date   /    %m --- message    /   %n --- ln   

== logging:
      file:
         name: server.log
      logback:
         rollingpolicy:
            max-file-size: 100KB
            file-name-pattern: server.%d{yyyy-MM-dd}.%i.log    // set the looging write in the file 
                                                                // server.log in the same file with the project   

### ###
  ============================ springboot2 =====================
## hot deploy
    in pom.xml       <artifactId>spring-boot-devtools      <groupId>org.springframework.boot                                                                 

  ==  in application.yml   // set the hot deploy
    devtools:
       restart:
          enable: false   // stop the hot deploy

## very higher level to set for control the system method as below :
  in @SpringBootApplication  class (bootstrap) 

     in main() method {
        System.setProperty("spring.devtools.restart.enabled","false");   // higher level control method
     }          

## in spring boot  , set a config class , related annotation as below :
      
      @Data
      @Component
      @ConfigurationProperties(prefix="servers") //  in application.yml, there defined servers and the properties values
      public class ServerConfig{
        private String ipAddress;
        private int port;
        private long timeout;
      }

## @EnableConfigurationProperties put @ConfigurationProperties class  into spring container and auto make it be a bean
   @EnableConfigurationProperties  can not use with @Component

## a annotation for define the DurationUnit as below :
       @DurationUnit(ChronoUnit.HOURS) 
       private Duration serverTimeOut;

### ###################
system define  static class        / serverTimeOut  / dataSize --- @DataSizeUnit

### ### Bean property validation 
         a, in pom.xml , add two dependency in pom.xml
                <artifact>validation-api  <group>javax.validation
                <artifact>Hibernate-validator  <group>hibernate.validator

         b, at the class which should be validated add @Validated above the class,
               and add like : {@Max,@Min, }   above the property 

## a case for write String in .yml  --- springboot2-75
        notice : maybe some problem solve in  .yml , when write a String , need use in " string "                        

## when use @springBootTest class to test ,there have two annotation for properties and args as below :
        a, @SpringBootTest(properties={"test.prop=testValue1"})
        b, @SpringBootTest(args={"--test.prop=testValue2"})          //  springboot2-76

### springboot test controller
   @SpringBootTest(webEnviroment=SpringBootTest.WebEnviroment.RANDOM_PORT) 
   @AutoConfigureMockMvc          // virtual MockMVC for test controller 
   public class WebTest{
   @Test
   void testRandomPort(){

   }

   @Test
   void testWeb(@Autowired MockMVC mvc) throws Exception {
            MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
            mvc.perform(builder);
   }

   @Test
   void testStatus(@Autowired MockMVC mvc) throws Exception {
            MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
            ResultActions action = mvc.perform(builder);

            StatusResultMatchers status = MockMVCResultMatchers.status();      //
            ResultMatcher ok = status.isOK();                                  //  two lines for assert 
            action.andExpect(ok);                              // check the result whether or not as expect 
   }

} 

 ===================================  above check assert test fro controller --- springboot2-83

### use @Transactional in unit test , that means the test data will not put into database, as below :
    
             @SpringBootTest
             @Transactional
               // @Rollback(true)    //  this @Rollback include @Transactional , not need add here , but when"false" ,  @Transactiobnal  also "false"
             public class ServiceTest {

             } 


### for unit test , can write data in "application-test.yml"  and maping a domain class in test package, as below :
       ----------    springboot2-85 /  doc-springboot_14_test
      a, === in application-test.yml
         testcase:
            book:
               id: ${random.int}    // random.int(10)  random.int(5,10) // expend --- random.int() should good using
               name: eagle${random.value}           // "eagle" will must appear in the String prefix
               uuid: ${random.uuid}

      b, === in test package domain

           @Component                                       // to be a bean will be control by spring container
           @Data
           @ConfigurationProperties(prefix="tastcase.book")    // invoke the data from application-test.yml
           public class BookCase {

                private int id;
                private String name;
                private String uuid;
           }


### random ------------------
   can use anywhere
      random.int
      random.int(10)  // number from 0-10
      random.int(10,20) // number between 10 , 20
      random.uuid         // ?
      random.value      // random String , MD5 String , 32 bit
      random.long   // using like "publishTime"  , random integer (long) 


### spring boot default integration    ====================
                                        === dataSource --- hikari
                                        === JPA (java persistance access) --- jdbcTemplate   // springboot2-87
  (use above JDBCTemplate should add dependency in pom.xml)  <artifact>spring-boot-starter-jdbc
                                                             <group>org.springframework.boot

                                        === embedded default databases by springboot
                                            - H2
                                            - HSQL
                                            - Derby
                                    (above 3 database all can start with springboot in memory , for sure , they also can install and in using )   // all 3 are very light database  
   
    === description H2  
    == in pom.xml need add 2 dependency
           <artifact>h2       <group>com.h2database
           <artifact>spring-boot-starter-data-jpa   
           <group>org.springframework.boot                                                         
         the set in application.yml  (see doc-springboot_15_sql)
         notice : only using in developing stage for test , must "enabled: false" when online

### ################# Redis -------------------
    Redis 是一款key-value 存储机构的内存级NoSQL数据库
    - 支持多种数据存储格式
    - 支持持久化
    - 支持集群
## Redis on Windows system -----------
  --- (clear the screen // windows : >cls   //  Redis : >clear) 
  --- start the "Redis server"   Redis>redis-server.exe redis.windows.conf  ( a bug when start need operate in cli) 
  --- start the "redis client"   Redis>redis-cli
             (for the Redis server start bug , should in operate in redis-cli) as below :
             Redis>redis-cli
             6379 >exit
             Redis>redis-cli
             6379 >shutdown
             not connected>exit

## Redis  --- command lines -----------------   
      Redis constractor is key-value
      sample key-value :  6379>set a1 aaa         /   6379>get a1         /   6379>keys *
      array  key-value :  6379>hset haa ha1 qqq   /   6379>hget haa ha1

# ############ springboot integration Redis 
                  a, in pom.xml
                  b, in apllication.yml (default)      //   client-type: lettuce (default)  / jedis           
                  c, in class  @Autowired
                               private RedisTemplate redisTemplate;
                 @Test               
                 void set(){
                     ValueOperations vops = redisTemplate.opsForValue();
                     vops.set("age",41);
                 }              

                 @Test
                 void hset(){
                      HashOperations hops = redisTemplate.opsForHash();
                      hops.put("info","a","aa");
                 } 

        === notice : StringRedisTemplate  can operate same as the "redis-cli"    

### ################   MongoDB  -------------------
  MongoDB 是一个开源、高性能、无模式的文档型数据库。NoSQL数据库产品中的一种，是最像关系型数据库的非关系型数据库。


## start mongoDB :  bin>mongod --dbpath=..\data\db  //   (monggoDB server)            
                    bin>mongo                       //    (client)
        show databases / show tables / use db1 //   command as same as mySQL but not ";" in the last 
## robo3 --- is the vision app of mongoDB client --------         

## mongoDB command line ------
            db.book.find({})
            db.book.save({})
            db.book.deleteOne({})       /    db.book.remove({})
            db.book.update({name:'computer'},{$set:{name:'777'}})   //  more complicated

            db.book.save({name:'computer2',type:'springboot55'})

            db.book.deleteOne({type:'springboot55'})

            db.book.remove({type:'springMVC'})

            db.book.update({name:'computer'},{$set:{name:'777'}})

## spring boot integration mongoDB   ------------------
             a, in pom.xml
             b, in application.yml 
                     spring:
                        data:
                           mongodb:
                              uri: mongodb://localhost/test

             c, @SpringBootTest to confirm is ok 
             

### #####################   ElasticSearch (ES)  -------------------------------
      ElasticSearch  是一个分布式全文搜索引擎

      概念 倒排索引 : 从数据到索引
           创建文档 :
            使用文档 :

# ElasticSearch total support RESTful operate , so we should use "postman" to handle the ElasticSearch    

 === start elastic search had problem , change JAVA to version 1.8 , after install jdk1.8 , solve the problem .
#     (shift + mouse right key ===> open "PowerShell") 
=== start in "bin" :  (double click) ElasticSearch.bat     

 === "ik" file in plugins is build for split word search 

 ## ElasticSearch command lines --------------------

"put"  http://localhost:9200/books    --- create a "index" books  // same as create a "database"  books in mySQL

"put"  for add split word search in index as below :
{
    "mappings":{
        "properties":{
            "id":{
                "type":"keyword",
                "index":false                  // this is for sometimes to stop use it as "keyword"
            }
        }
    }
}

===  -------------------------

 "put" for split word ---complete as below :
 {
    "mappings":{
        "properties":{
            "id":{
                "type":"keyword"
            },
            "name":{
                "type":"text",
                "analyzer":"ik_max_word",
                "copy_to":"all"
            },
            "type":{
                "type":"keyword"
            },
            "description":{
                "type":"text",
                "analyzer":"ik_max_word",
                "copy_to":"all"
            },
            "all":{
                "type":"text",
                "analyzer":"ik_max_word"
            }
        }
    }
} 

=== -----------------------

create data in "index" , two ways 
                   a,  "post"   --- http://localhost:9200/books/"underdash"doc  // generate random id

                       "post"   --- http://localhost:9200/books/_doc/3  // generate defined id

                   b,   "post"  --- http://localhost:9200/books/_create/1   // use _create should add "id"
=== -----------------------
select all  :   "get"     http://localhost:9200/books/"underdash"search     // select all  --- search

=== -----------------------

select use "keyword"  "get"  http://localhost:9200/books/"Udash"search?q=name:springboot  // springboot 小写不能分词

=== -----------------------

delete by id  ---  "delete"   http://localhost:9200/books/"Udash"doc/5

=== ----------------------

update by Entity ---  "put" (use "post" as same result as "put")   http://localhost:9200/books/"Udash"doc/6

update by row   ---   "post"   http://localhost:9200/books/"Udash"update/3   // "doc":{} 
       {
        "doc":{
        "id": 3,
        "name": "springboot678"
        }
    }

### ######################   spring boot integration ElasticSearch -------

## for spring boot offical only support "low-level-client" with ElasticSearch , therefore we manually integration ES,
      === in pom.xml   <artifact>elasticsearch-rest-high-level-client   <group>org.elasticsearch.client
      === in class , because we munual create the "client " , therefore there not "@Autowired" to use
      === springboot2-101  /    doc - springboot_18_es

      === in test class :

      private RestHighLevelClient client;

      @Test
      void testCreateClient() throws IOException {

        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);

        client.close();

    }


      @Test
      void testCreateIndex() throws IOException {

        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);

        CreateIndexRequest request = new CreateIndexRequest("orders");
        client.indices().create(request, RequestOptions.DEFAULT);

        client.close();

    }
# === === @BeforeEach            @AfterEach -------------------------------
     @BeforeEach
     void beforeRun(){
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);

    }

     @AfterEach
     void afterRun() throws IOException{
        client.close();
    }

# ### ###   doc-springboot2-102-103   bulkRequest upload data from mySQL to ElasticSearch



### #############################################################################
     cache 缓存 是一种介于数据永久存储介质与数据应用之间的 数据临时存储介质 
                使用缓存可以有效的减少低速数据读取过程的次数（例如磁盘IO），提高系统性能
                缓存不仅可以用于提高永久性存储介质的数据读取效率，还可以提供临时的数据存储空间
    === start Cache in springboot  
    a, in pom.xml :  <artifact>spring-boot-starter-cache</artifact>  <group>org.springframework.boot
    b, on the "bootstarp" class above : @EnableCaching 
    c, in the class which operate , like "BookServiceImpl" , on the method above :
           @Override
           @Cacheable(value="cacheSpace",key="#id")
           public Book getById(integer id){
                return bookDao.selectById(id);
           }

## spring boot integration cache techniques as below :
 Generic /JCache  / Hazelcast / infinispan  / Couchbase  / 
 simple (default)  /  Ehcache  /   Redis  /   memcached   /  Caffenine

## ###
## Ehcache   a, in pom.xml  <artifact>ehcache         <group>net,sf.ehcache
             b, in application.yml       
                                         spring:
                                            cache:
                                               type: ehcache
                                               ehcache:
                                                  config: ehcache.xml
              c, in "resources" :  ehcache.xml   
                                    (in ehcache.xml  <ehcache 
                                                        <cache
                                                              name="smsCode"  
                                                                                />
                                                      </ehcache>         )
                               //   springboot2-108                                    

## ###
## Redis     a, in pom.xml :  <artifact>spring-boot-starter-data-redis  <group>org.springframework.boot
             b, in application.yml
                          redis:
                             host: localhost
                             port: 6379
                          cache:
                             type: redis
                             redis:
                                use-key-prefix: true
                                key-prefix: sms_
                                cache-null-value: false
                                time-to-live: 10s
             (>flushdb )  // empty db

## ###
## memcached     --- need download and install    --- port :  11211      //  springboot2 - 111
                    a, admin identity login the "CMD" terminal
                    b, >cd memcached 
                       >memcached.exe -d install
                       >memcached.exe -d start      /   -d stop                              

## ###
## jetCache     --- alibaba ---  local cache scheme :  LinkedHashMap / Caffeine
                                 remote cache scheme : Redis  /  Tair
           a, in pom.xml :  <artifact>jetcache-starter-redis  <group>com.alicp.jetcache   <version>2.6.2
           b, in application.yml : 
                                   jetcache:
                                      local:
                                         default:
                                            type: linkedhashmap
                                            keyConvertor: fastjson   // coordinate have "fastjson" --- object=>json

                                      remote:
                                         default:                // --A  
                                            type: redis
                                            host: localhost
                                            port: 6379
                                            poolConfig:
                                               maxTotal: 50

                                         sms:                // --B
                                            type: redis
                                            host: localhost
                                            port: 6379
                                            poolConfig:
                                               maxTotal: 50
      

c, in "bootstrap" class above: @EnableCreateCacheAnnotation     //jetcache start  using's switch
d, in SMSCodeServiceImpl : 
               @CreateCache(name="jetCache_",expire=3600,timeUnit=TimeUnit.SECONDS)    // (area="sms")    --B
                                  not solid                default second              // area="default"  --A   
               private Cache<String,String> jetcache;   //  doc-springboot_20_jetcache


#  config in application.yml for instance : 
            
            jstcache:
               statIntervalMinutes: 15            //  statistics form
               areaInCacheName: false
               local:
                  default:
                     type: linkedhashmap
                     keyConvertor: fastjson
                     limit: 100
               remote:
                  default:
                     host: localhost
                     port: 6379
                     type: redis
                     keyConvertor: fastjson
                     valueEncode: java
                     valueDecode: java
                     poolConfig:
                        minIdle: 5
                        maxIdle: 20
                        maxTotal: 50       

# for domain.entity may not put in the redis cache because it is a object , therefore should make the 
    entity class " implements Serializable "  for guarantee can put in redis cache,
     public class Book implements Serializable {

     } 
  and in application.yml  must add two lines :    valueEncode: java
                                                  valueDecode: java


    and also have two annotations 
 a, @EnableMethodCache(basePackages="com.eagle")   // on "bootstrap" above , must with @EnableCreateCacheAnnotation
 b, in "BookServiceImpl" class on the "method" above : 
                   @Override
                   @Cached(name="book",key="#id",expire=3600)   // (cacheType = CacheType.REMOTE  / .BOTH / .LOCAL)
                   public Book getById(Integer id){
                     return bookDao.selectById(id);      
                   } 

# CRUD  details : doc-jetcache /  114


## ###
## j2cache   是一个缓存整合框架，可以提供缓存的整合方案，使各种缓存搭配使用，自身不提供缓存功能

 === ehcache L1 / redis L2



### #######################################
### 任务  - 定时任务是企业级应用中的常见操作
                   - 年度报表 - 缓存统计报告 - 定时活动 ...
           === Quartz
                - Job
                - JobDetail
                - Trigger
                - Scheduler

      a, in pom.xml  <artifact>spring-boot-starter-quartz   <group>org.springframework.boot
      b, define a Job class                      //  (normal with no annotation)
           public class QuartzTaskBean extends QuartzJobBean {
               @Override
               protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
                        sout("quartz job run...");
               }
           }
       c, define a config class for binding the "JobDetail" with the  "Trigger"
            @Configuration
            public class QuartzConfig {
                @Bean
                public JobDetail printJobDetail(){
                    return JobBuilder.newJob(QuartztaskBean.class).storeDurably().build();
                }
                @Bean
                public Trigger printJobTrigger(){
                    CronScheduleBuilder cronScheduleBuilder= CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
                                                                                            //  s  m H d M weekday
return  TriggerBuilder.newTrigger().forJob(printJobDetail()).withSchedule(cronScheduleBuilder).build();                                                                      
                }   
            }
# ## ###  ------ spring task integration Quartz -------
           === spring task do it so easily ,
           a, in "bootstrap" above,   @EnableScheduling
           b, in the class include the "Job" , above @Component
                   @Component
                   public class MyJobBean{

                       @Scheduled(cron = "0/5 * * * * ?")
                       public void print(){
                          sout("spring task run ...");
                       }
                   } 

                //  more config can set in application.yml --- springboot2 - 118

## ####### 
## spring boot integration JavaMail 
  SMTP (Simple Mail Transfer Protocal)       // post out email
  POP3 (Post Office Protocal -Version 3)     // receive email
  IMAP (Internet Mail Access protocal)       // instead of POP3

  a, in pom.xml   <artifact>spring-boot-starter-mail    <group>org.springframework.boot
  b, in application.yml :    
                             spring:
                                mail:
                                   host: stmp.qq.com
                                   username: ---@qq.com
                                   password: ------           // need get from in mail setting - smtp/pop3

  c, in "service" create a interface class : SendMailService
  d, in "service.impl" create a class : sendMailServiceImpl
              @Service
              public class SendMailServiceImpl implements SendMailService {

                   @Autowired
                   private JavaMailSender javaMailSender;

                   private String from = "---@qq.com";

                   @Override
                   public void sendMail(){
                      SimpleMailMessage message = new SimpleMailMessage();
                      message.setFrom(from+"(xiaott)");
                      message.setTo();
                      message.setSubject();
                      message.setText();
                      
                      javaMailSender.send(message);
                   }
              }
         //    complicated Mail  use :             // in SendMailServiceImpl2.java
                      MimeMessage message = javaMailSender.createMimeMessage();
                      MimeMessageHelper helper = new MimeMessageHelper(message,true);  // true = multi parts, attaching
                      helper.setFrom();
                      helper.setText(context,true);    // support html set in --- private String context = "" ;

### ######
### MQ message queue
      --- JMS (Java Message Service) : 一个规范，等同于JDBC规范，提供了与消息服务相关的API接口
       -- JMS消息模型
              - peer-2-peer: 点对点模型，消息发送到一个队列中，队列保存消息。队列的消息只能被一个消费者消费，或超时
              - publish-subscribe: 发布订阅模型，消息可以被多个消费者消费，生产者和消费者完全独立，不需要感知对方的存在

      --- AMQP (advanced message queuing protocal): 一种协议（高级消息队列协议，也是消息代理规范），规范了网络交换的数据格式，兼容JMS
           优点： 具有跨平台性 
#        AMQP message model:      // ---one type :   byte[]
              - direct exchange     //  *
              - fanout exchange
              - topic exchange      //  ***
              - headers exchange
              - system exchange 

#          instance : RabbitMQ , RocketMQ , StormMQ                     

## Kafka 一种高吞吐量的分布式发布订阅消息系统，提供实时消息功能。

--- MQTT (Message Queueing Telemety Transport) 消息队列遥测传输，专为小设备设计，是物联网（IOT）生态系统中主要成分之一

### ### ActiveMQ , RabbitMQ , RocketMQ , Kafka

## ###
## ActiveMQ    path : C:\softee\soft\activemq\bin\win64   
               start :  double click "activemq.bat"
               port: http://127.0.0.1:8161/                 //     service port: 61616
               username: admin   /  password: admin
       a, in pom.xml:  <artifact>spring-boot-starter-activemq    <group>org.springframework.boot
       b, in application.yml:  
                                spring:
                                   activemq:
                                      broker-url: tcp://localhost:61616
                                   jms:
                                      pub-sub-domain: true  // this is start the pub-sub model , not must be write
                                      template:
                                         default-destination: eagle

       c, in MessageServiceActivemqImpl : 

                     @Autowired
                     private JmsMessagingTemplate messagingTemplate;


                     1) messageTemplate.convertAndSend(id);

                     2) String id = messageTemplate.receiveAndConvert(String.class);

       d, in actual online : not person there waiting and listening , make listener class as below : 
                          @Component
                          public class Messagelistener{

                            @JmsListener(destination = "order.queue.id")
                            @SendTo("order.other.queue.id")   // after consume and continuously to next step
                            public void receive(String id){    // at this way , we can continously many steps

                            }
                          }     // instead of above  2)  method

## ###
## RabbitMQ  --- erlang language --- need install "erlang" and restart computer       // springboot2 - 125
              RabbitMQ   ---port: 15672


# ## command line :
          >rabbitmq-service.bat start   /  stop    
          >rabbitmqctl status

          服务管理可视化（插件形式）
          >rabbitmq-plugins.bat list
          >rabbitmq-plugins.bat enable rabbitmq_management  // start manage plugin
          http://localhost:15672
          service port: 5672   / backend manage port: 15672
          username/password: guest

#          re-learn from 126


## ###  
## RocketMQ    port: 9876              // Asynchronized message
            path enviroment config:
                  ROCKETMQ_HOME
                  PATH
                  NAMESRV_ADDR: 127.0.0.1:9876

             
             a, in pom.xml: <artifact>rocketmq-spring-boot-starter   <group>org.apache.rocketmq   <version>2.2.1
             b, in application.yml
                                      rocketmq:
                                         name-server: localhost:9876
                                         producer:
                                            group: group_rocketmq
                 @Autowired
              c, private RocketMQTemplate rocketMQTemplate;
              d, in Listener class :  @RocketMQMessageListener(topic="order_sm_id",consumerGroup="group_rocketmq")
                                      public class RocketmqMessageListener implements RocketMQListener<String>{

                                      }


## ###
## Kafka    kafka use zookeeper to be the registry center , therefore need start zookeeper first.
      start zookeeper: >zookeeper-server-start.bat ..\..\config\zookeeper.properties
       default port: 2181
      start kafka: >kafka-server-start.bat ..\..\config\server.properties
       default port: 9092

       need test after install : 130

       a, in pom.xml: <artifact>spring-kafka      <group>org.springframework.kafka
       b, in application.yml:   
                               spring:
                                  kafka:
                                     bootstrap-servers: localhost:9092
                                     consumer:
                                        group-id: order

        c, private KafkaTemplate<K,V> kafkaTemplate;   /  (kafakaTemplate.send();)
        d, Listener class:
            @COmponent
            public class KafkaMessageListener{
                    @KafkaListener(topics = {"kafka_topic"})
                    public void onMessage(ConsumerRecord<K,V> record){
                        sout("id==>"+record.value());
                    }
            }                                 
 
### ####### 
###  Ops - Spring Boot Actuator  ------------------------

## ###
## SpringBootAdmin  
#     === in server
             a, in pom.xml: <artifact>spring-boot-admin-starter-server  <group>de.codecentric   <version>same as boot
             b, in application.yml:    server:
                                          port: 8080
             c, in "bootstarp" above class :  @EnableAdminServer
             d, web to be the vision app (web also in pom.xml)

#       === in client
                 a, in pom.xml: <artifact>spring-boot-admin-starter-client   <group>de.codecentric  <version>same as boot
                 b, in application.yml:   
                                        server:
                                           port: 80     // notice: different client use different port (like: 81) under admined in server
                                        spring:
                                           boot:
                                              admin:
                                                 client:
                                                    url: http://localhost:8080
                                        management:
                                           endpoint:
                                              health:
                                                 show-details: always    // default-never
                                           endpoints:
                                              web:
                                                 exposure:
                                                    include: "*"     // showing all in server // default-health          
                 c, with web (need not add anything above "bootstrap")

# ### Actuator 监控原理 Actuator提供了springboot生产就绪功能，通过端点(endpoint)的配置与访问，获取端点信息
                       端点描述了一组监控信息，springboot提供了多个内置端点，也可以根据需要自定义端点信息
                       访问当前应用所有端点信息：  /actuator
                       访问端点详细信息: /actuator/endpoint name


                      === in application.yml: 
                             management:
                                endpoint:
                                   health:
                                      show-details: always
                                   info:
                                      enabled: true   // set using info endpoint
                                   beans:
                                      enable: true   // start using appointed endpoint

                                                          //       management:
                                endpoints:
                                   enabled-by-default: true   // start using all endpoints

           mainly monitoring: health / loggers / metrics  / info

## windows-"CMD" Terminal - >jconsole           // windows support monitoring applet

#               ===  "info" setup  : 
                                      info:
                                         appName: @project.artifactId@      // read from pom.xml <project><artifactId>
                                         author: eagle

#        === details setup "info" should in actuator.InfoConfig class (create package actuator under InfoConfig.class)
                 @Component
                 public class InfoConfig implements InfoContributor{
                    @Override
                    public void contribute(Info.Builder builder){
                        builder.withDetail("runTime",System.currentTimeMillis());
                        Map infoMap = new HashMap();
                        infoMap.put("buildTime","2006");
                        builder.withDetails(infoMap);
                    }
                 }

#    details set in health endpoint:    (same as set InfoConfig in "info" )
         === set a HealthConfig.class in the actuator package :
                public class HealthConfig extends AbstractHealthIndicator{
                    @Override
                    protected void doHealthCheck(Health.Builder builder) throws Exception{
                        "  Can take the whole contents from 'InfoConfig'"
                        builder.status(Status.UP);    // builder.up();

                    }
                }

# ### 
          Metrics              endpoint control
          in the method class , create a constructor as below :

                     private Counter counter;

                     public BookServiceImpl(MeterRegistry meterRegistry){
                        counter = meterRegistry.counter("user payment count:");
                     }
                
                @Override
                Public boolean delete(Integer id){
                    counter.increment();
                    return booDao.deleteById(id) > 0;
                }
                        // springboot2 - 137


## return HashMap == Json !!

# ###
      ===  self define "Endpoint" 
      PayEndpoint class in actuator package as below :

       @Component
       @Endpoint(id="pay",enableByDefault = true)
       public class PayEndpoint{

           @ReadOperation
           public Object getPay(){
            Map payMap = new HashMap();
            payMap.put("level 1","300");
            payMap.put("level 2","291");
            payMap.put("level 3","777");
            return payMap;                //  pattern get same as Json    
           }
       }


# ######################
# Spring Boot --- MyBatis-plus   =================
# add mybatis-plus  
                    a, "Custom:"  https://start.aliyun.com 
                    b,  find "mybatis plus"  in  mvnrepository.com



# ######


    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid-spring-boot-starter</artifactId>
      <version>1.2.6</version>
    </dependency>


两个主要的错误： 1， 上面的依赖写错成“<artifact>druid</artifact>”    2，"OrderDao" 忘了写@Autowired  : "NullPointerException"

# ######

# "in controller  --- getAll == list();"    // MyBatis-plus

### ###

mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true
  global-config:
    db-config:
      table-prefix: tb_
      id-type: auto
  type-aliases-package: com.eagle.user.domain  // this solve invoke User from another module ? ?  
                                               //  seems like remain sovle because add dependency ! !

### ###
another question is  user_id   but userId  ? ? ?  and from 2 row to the last row ? ?




