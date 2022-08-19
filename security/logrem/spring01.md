-----------------spring-01-------------------------

###### after from video 06 (include 06)

### some log for spring for recall
## the scope of the bean
1, why the bean been create was default singalton 
2, which can fit ro be managed by spring container 
   a, the object of controller (servlet)
   b, the object of service
   c, the object of DAO
   d, the object of util
3, which should not be managed by spring container---(domain/pojo) the scope object which is encapsulation of entity
	the commonly feature of this object --- inside there are some properties of memories of the veriables.

## create been by the container --- 3 methods (4 methods the 4 is a important change for third method by spring) :
1, constructor  --- only use the default constructor (no parameter) to create bean,
  (do not written any constructor = default constructor // if can not find the default constructor ---exception : "BeanCreationException")
  === in XML <bean id="bookDao" class="com.eagle.dao.impl.BookDaoImpl"/>

2, use static factory to create bean  (when the factory inside need something to run , have to use this factory method)
  ====== public class OrderDaoFactory{
		public static OrderDao getOrderDao(){
			return new OrderDaoImpl();
			}
		}
 	=== in XML <bean id="orderDao" class="com.eagle.factory.OrderDaoFactory" factory-method="getOrderDao"/>
3, use the instance factory to new instance and then invoke the instance's  "get---" method 
  === in XML (1) <bean id="userFactory" class="com.eagle.factory.UserDaoFactoty"/>
  (2) <bean id="userDao" factory-method="getUserDao" factory-bean="userFactory"/>

4, use the instance factory but by implement factoryBean (important change by spring)
    (why important ? becase when you find the spring integration other frameworks they are mainly use this method deal with spring framework) 
====== public class UserDaoFactoryBean implements FactoryBean<UserDao>{
            public UsrDao getObject() throws exception{
              return new UserDaoImpl();
          }

        public Class<?> getObjectType(){
              return UserDao.class;
        }

        // implement the third method is for singleton or not 
}
  === in XML <bean id="userDao" class="com.eagle.factory.UserDaoFactoryBean"/>

#--- bean's life-cycle spring-day1-doc-spring_04_bean_lifecycle---#
## parentclass to new childclass , but there has a method only childclass has it , then change to use childclass to new instance can solve this sort of problems 

## DI dependency injection 1 setter DJ ---a, simple type / b, reference type 
      in class , the simple type and reference type all must have set method 
  === in XML a, <property name="" value=""/>   /  b, <property name="" ref=""/>
   DI dependency injection 2 construtor DJ 
    in the class generate the constructor with the bean to be the parameter //  pros and cons /cons --- tight coupling

## spring autowire --- (also need a set method in class)
===         <bean id="bookDao" class="com.eagle.dao.impl.BookDaoImpl"/> 
          (attention below autowire="byType" point is to the above bean "bookDao") 
=== in XML <bean id="bookService" class="com.eagle.service.impl.BookServiceImpl" autowire="byType"/>

## in spring's test class , there always have a annotation " @RunWith(SpringRunner.class) "



  ###   ################################# REST style ----------- develop---------RESTful
  @RequestBody   @ RequestParam    @pathVariable

  distinguish from above 
  @RequestParam for url or form 
  @RequestBody for json
  @PathVariable for param of path  / --- use {param name} represent the path param

  actual in developing environment
  a, in developing , when the param more than 1 , mainly is Json , ------ @RequestBody mainly using
  b, if is nit a Json , will use @RequestParam recevied the request param
  c, when param less , for example 1 , should use ------ @PathVariable received the path param , usually use in transfer the id

  ################ spring mvc -day1-16- ServletcontainersInitConfig --------related the dispatch for static sources






  ###   ####################  --- Maven enhancement ---
-------------- my local maven file --- /softaa/apache-maven-3.6.1/conf/settings.xml     
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

<localRepository>C:\softaa\apache-maven-3.6.1\mvn_resp</localRepository>
  

  difference module develop depart , any of them should install to the repository after the compile , the one dependent it should 
  a, in pom.XML dependency the module
  b, compile to check it if can build success

# choose the dependency if want to conduction  
  === in XML   1 method:   <optional>true</optional>
               2 method:   <exclusions>
                                <exclusion>
                                    <groupID>
                                    <artifactId>

# in parent pom.XML  a, <packaging>pom</packaging>
                     b, <modules>
                            <module>../
                     c, <dependencies>
                            <dependency>           // must be inheritance  
                     d, <dependencyManagement>     // be choose to inheritance


                     
# in child pom.XML   a, <parent>
                            <groupId>
                            <artifactId>
                            <version>
                            <relativePath>../---parent/pom.xml</relativePath>
                        </parent>                 
                     b, choose the dependency must inherit from the parent pom  --- notice : must be not version needs

# in parent XML  version for unified management 
                        <version>${spring.version}</version>

            from here : <properties>
                             <spring.version>5.2.10.RELEASE</spring.version>
                        </properties>                                  

# in web module pom.XML 
         <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.tomcat.maven</groupId>
                    <artifactId>tomcat7-maven-plugin</artifactId>
                    <version>2.1</version>
                    <configuration>
                      <port>80</port>
                      <path>/</path>
                    </configuration>
                 </plugin>
                 <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-war-plugin</artifactId>
                    <version>3.2.3</version>
                    <configuration>
                        <failOnMissingWebXml>false</failOnmissingwebxml>    //--- maven-08.mp4
                    </configuration>    

# if in the deveoping need to read the value from the maven's sysytem properties,
   in the maven's bin file --- use a CMD teminal : mvn help:system 
   from the needs properties , put the thing in front of the =    in  ${thing in front of} , that is , get the value

# in maven scope , in lifecycle , focus on the "install" and then click in above row "m" ,
   and in the row ---Run Anything in put "mvn install -P env_test" , this is commonly use in working enviroment
          (above --- maven-10-multi enviroment developing) 

#  skip test--- maven scope when "install" without "test" , a, in the above row click "lightning" label 
    b, "mvn install/package -D skipTests"  c, config in pom.xml

### maven private repository ------ maven-12.mp4
      CMD Terminal start the server :  a, nexus.exe /run nexus 
                                       b, (browser) default port : 8081  http://localhost:8081
                                              username: admin / password: admin
=== have already installed in file : /softcc/nexus/
create repository in nexus --- in "maven2(hosted)"  
      demonstration in maven-14.mp4  and  setting.xml --- in maven doc /wangpanaa/ssm/
      <server>  and <mirror>
 
=== and need config in parent pom.xml 
     <distributionManagement>
        <repository>
            <id>itheima-release</id>
            <url>http://localhost:8081/repository/itheima-release/</url>
        </repository>
        <snapshotRepository>
            <id>itheima-snapshot</id>
            <url>http://localhost:8081/repository/itheima-snapshot/</url>
        </snapshotRepository>
      </distributionManagement>      
### upload into private server (in nexus) --- use maven lifecycle-deploy    / CMD --- mvn deploy  
## if want change the Remote storage address --- in (nexus) maven-central below "Proxy" /"Remote storage"
      change this address"https://repo1.maven.org/maven2/" to the new address 


# # # # # # --- --- spring boot ---------------------------------------------
## a quick talent --- hide the file or folder ---  IDE - Settings/ Editor/File Types --- Ignored Files and folders

### why spring boot so easily to developing than spring ? for the spring boot's  " bootstrap "
# "bootstrap" is the core of a spring boot program , beacause it is the entry point for the program 
# "bootstrap" with a "main()" so it also be a config class, 
# "bootstrap" start to initial a spring container and san the package of this "bootstrap" sitting on package along with 
               the sub packages , to find out the " bean " which was defined .

### the theory of embed tomcat in spring boot starter
# embed tomcat also be a "bean" put in the spring container when the "bootstrap" start , 
     that is why we can use the tomcat easily .    

# because of my IDE is community version , so can not prompting if use .yml instead of .properties ,
    but the .properties have so many prompting is should be great welcome  

### plugin a "Spring Boot Assistant" solved the support of prompting in the .properties and .yml
### in spring boot the "application.properties" , every config related the techniques which depends 
      on pom.xml already have it . If do not have the related , the prompting will not appear .  
### the rule of .yml
    String: helloworld     /    String: "hello world"      string's value can straight write do not need use " " if have a space there  use value in "" 
### read out the date from .yml , just define in the controller class use the ---  @Value"${keyname}"
### in .yml also can use the ${keyname} get the value , for example
            baseDir: c:\windows  
            tempDir: c:\windows\temp  ---  tempDir: ${baseDir}\temp                                    
### use spring boot method read out all the data in .yml
          @Autowired
          private Enviroment env;
          env.getProperty("keyname");  // get the data 

### define a class to use read out the segment data
    above the class there have two annotation 
    @Component
    @ConfigurationProperties(prefix="datasource")
    public class DataSource{

    }     // this method , all name should same as in .yml

### in .yml  server:
                port: 80
                servlet:
                   context-path: /test123    // in browser to access the data should change to 
                                                         --- localhost/test123/books           

### ### ### ### ### spring Boot integration myBatis-plus 
       a,   need add dependency manually , in " mvnrepository.com " to find out the dependency
             <groupId>com.baomidou
             <artifactId>mybatis-plus-boot-starter
             <version>3.4.3

       b, do need manually write the sql statement and annotation anymore , just as below --- BaseMapper<User> :
                   @Mapper
                   public interface UserDao extends BaseMapper<User>{
                             }  // check in the parent class to see the methods

       c,  (notice) because myBatis-plus mapping the table name from the entity class name ,
                     if in the database , the table name is not as same as the entity name ,
                     (order / tb_order) 
                     for there do not have the space to write down the sql statement to declare that,
                     then we should config it in the .yml as below :
                     mybatis-plus:
                          global-config:
                                db-config:
                                   table-prefix: tb_                      

# ###############  lombok  / ctrl+F3 ? --- check the inherited members ?
# ###############  myBatis-plus have the difference primary key auto_increment method , 
                        config in application.yml   , as below :
                        mybatis-plus:
                          global-config:
                                db-config:
                                   table-prefix: tb_                      
                                   id-type: auto
                      simple increment : auto   // had already set the primery key auto_increment in db
                      snow algorithm : assign_id

### set to start the mybatis-plus's logging in application.yml
                      mybatis-plus:
                         configuration:
                            log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
                    (but only use in developing stage , because of will generate so many infos)

### log for re-check mybatis-plus  --- springboot2-base-34  ---  pages apart 
              @Test
              void testGetPage(){
                IPage page = new Page(1,5);
                bookDao.selectPage(page,null);
              } 

               @Configuration
               public clss MpConfig{
                  @Bean
                  public MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor(); 
                  mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
                  return mpInterceptor;
               } 

# check in mypan
### mybatis-plus dao.interface  extends BaseMapper<Book>
                   service.interface IBookService extends IService<Book>{

                    }
                                                  /
                   @Service // do not forget
                   BookServiceImpl extends ServiceImpl<BookDao,Book> implements IBookService {

                   }   
                          // save ---save / update --- update / delete --- remove /  getAll --- list  /  
                              /   LambdaQueryWrapper  lqw.like(); 

