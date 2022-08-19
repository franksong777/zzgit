# create a .git file 
git init
# check the log
git log
# add new updating
git add test.txt / git add .
# commit the new updating
git commit -m "second change test.txt"
# commit write rule for team 
git commit -m "fix(test.txt):change content 3 updating"
# back to the version in front 
git reset --hard 7a942f6aba43f6e1adf66d2569c09661905160ee
# create a branch 
git branch 0.2 / git branch 0.3 
# check all of the branches we have
git branch -a
# switch to a branch 
git checkout 0.2
# integration the difference branch into main branch-master 1 git checkout master 2 below :
git merge 0.3


##################################
…or create a new repository on the command line
echo "# zzgit" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/franksong777/zzgit.git
git push -u origin main
-------------------------------------------------
…or push an existing repository from the command line
git remote add origin https://github.com/franksong777/zzgit.git
git branch -M main
git push -u origin main
----------------------------------------------------
…or import code from another repository
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.

############################################

# check the remote origin address 
git remote -v

# Clone from the repository from remote --- as below :
# have a empty directory open it from Visual Studio Code , trust it , 
# use "Clone Git Repository... "
# copy the https:// address to the opened input row , --- or open a new Terminal , write as below :
git clone https://github.com/***/***.git .

# cd filename --- step in the clone file , you can use "git command operators" here ,
# related with others repository
git remote add upstream https://github.com/midorg-com/re01.git

# create and switch into a new branch 
git checkout -b kwc

# use the Visual Studio Code open the directory and clone from the remote repository , seems like at the same time ,
# this directory is binding with the remote repository , when after "add . and commit " , only use git push can complete 
# upload to the remote repository,
git push

################################################
## zzgit1 was the directory for establish the my new remote repository "zzgit", --- zagit was binding with this repository
## workteams/sourceaaa/ was binding with the others stars remote repository "xkcoding/spring-boot-demo" 

################################################

