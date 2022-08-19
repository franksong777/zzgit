### ############ mySQl statements ------------------
## java -jar project.jar --server.port=8080
##                                          --spring.datasource.druid.password=123


### ############## #
		mysql> show variables like "%secure%";   // load data fail problem
# notice : this is under the linux ! !   // should create a file : my.cnf ! !  not the "my.ini"	
   vim my.cnf     [mysqld]
                  secure_file_priv=''	
             == exit and service mysql restart
             == mysql -uroot -p 
                 (password) root 
                 mysql> show variables like "%secure%";    // successfully ! ! !
# ######
                 >service mysql restart
                 >set names GBK;


### upload and download 
    mysql> load data infile "/export/server/mysql-5.7.29/data/db1/account.txt" into table tb_account;

    mysql> select * from tb_account into outfile "/export/server/mysql-5.7.29/data/db2/account.txt";   // in linux , permit there , file should be under db 



     