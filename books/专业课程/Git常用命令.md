# 常用命令

## Git            

### 1. 回滚到指定版本

```shell
git reset --hard commitId
git push origin head --force
```

### 2.合并提交记录

```shell
git rebase -i commitId
{
   pick -- use commit
   s -- use commit, 第一条不能s
}

git push origin head --  
```

### 3.删除分支

>删除远程分支

```shell
git push origin -d origin/branch_name  #-d --delete的快捷键
```

> 删除本地分支

```shell
git branch -d local_branch_name   #-D --force的快捷键
```



## Maven

### 1.查看依赖树

```shell
mvn dependency:tree
```





