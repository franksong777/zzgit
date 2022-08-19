### ####################### spring02

### IDE maybe sometime need a "clean" for solve some bugs from the IDE part 

### ################### Vue.js ###############
### in Vue.js seems like the feature same as Java8 , lambda meaning allow using the method to be the arguments as below :
			getAll(){
				axios.get("/books").then((res)=>{
					console.log(res.data);						// res --- response --- it take the data when "then"
					});
			}
### ElementUI   have a row data   "scope.row" --- "row" take the row data in it 

### Vue.js     ---   this.$message.success("");   / info  / error 
### Vue.js     ---     have a row data   "scope.row" --- "row" take the row data in it        
     
     handleDelete(row){ 
     
       this.$confirm("really continuous do ?","prompting",{
     			type:'info'
     	}).then(()=>{
     		axios.delete("/books/"+row.id).then((res)=>{
     					if(res.data.flag){
     						this.$message.success("delete success");
     					} else {
     						this.$message.error("delete fail");
     					}

     			}).finally(()=>{
     				this.getAll();
     				});
     		}).catch(()=>{
     			this.$message.info("cancel opperation");
     			});

     	}


### Exception Handler related
    all of the exception , all will except to the controller layer ,despite occur in DAO layer or Service layer
    along with in controller layer, then , we must have a Exception Handler in project !
      should in  utils package

      @RestControllerAdvice
      public class ProjectExceptionAdvice{

        @ExceptionHandler
      	public Front doException(Exception ex){
      			// apart the Exception msg to front end
      			// to maintain end 
      			// to developer

      		ex.printStackTrace(); 
      		return new Front("server blocked , try again later .")              // message ---msg
      	}
      } 
# msg unify manage in controller
unify deal with the Exception msg can put all mag content in controller , in html , only write down the "res.data.msg"

# in actual developing enviroment , there maybe occu currentPage > page.getPages , deal with it coding as below :
                
		@GetMapping("/{currentpage}/{pageSize}")
		public R getPage(@Pathvariable int currentpage, @PathVariable int pageSize){
			IPage<Book> page = bookService.getPage(currentPage,pageSize);
                 if(currentPage > page.getPages()){
                 	page = bookService.getPage((int)page.getPages(),pageSize); 
                 }

                 return new R(true,page); 

               }  

### for check in mypan

### ############## for spring boot maintainance related
## put the project into server , before package should click "lightning" in maven scope's top row,
		this can skip "test" and get the .jar , for safy , can first with "test" and then skip "test"
			
### Logging output style control -----------------

   2021-11-02 12:25:06.334 info   2336 ---main com.eagle.springboot2Application : started springboot2LogApplication ...
     time                  level  PID     thread		class/interface                  logging info	

###### =====================    springboot --- mybatis-plus    // C:\wangpanaa\vspringboot2\aabase\SpringBoot- from 25 -50