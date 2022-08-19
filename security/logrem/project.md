###### ######
###### Project 03 ====== wanxin ======
 
# ###  
                        === day 01 ===
# ##
# swagger  auto generate a api-document for : path / parameters / return type / request pattern  --- RESTful style 
            for collaboration between front end and back end ,
        in spring :  spring-swagger  == springfox  
                    === springboot integration swagger as below : 
                      a, in pom.xml: <artifact>springfox-swagger2   <group>io.springfox  <version>2.9.2
                                     <artifact>springfox-swagger-ui  <group>io.springfox  <version>2.9.2

                      b, in application.yml: 
                                                  swagger:
                                                     enable: true

                     c, in config.SwaggerConfiguration.class  // 很多配置，以后用到就复制过去即可                              

# swagger show the api-document :  localhost:????/consumers/swagger-ui.html



