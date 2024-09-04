# 第02章 MySQL的数据目录

## 1. MySQL8的主要目录结构

```shell
find / -name mysql
```

### 1.1 数据库文件的存放路径

**MySQL数据库文件的存放路径：/var/lib/mysql**

`数据目录`对应系统变量`datadir`

```mysql
show variables like 'datadir';
+---------------+-----------------+
| Variable_name | Value           |
+---------------+-----------------+
| datadir       | /var/lib/mysql/ |
+---------------+-----------------+
```

### 1.2 相关命令目录

**相关命令目录：/usr/bin 和/usr/sbin。**

`/usb/bin`目录包含 mysqladmin、mysqlbinlog、mysqldump等命令

### 1.3 配置文件目录

**配置文件目录：/usr/share/mysql-8.0（命令及配置文件），/etc/mysql（如my.cnf）**

## 2. 数据库和文件系统的关系

`文件系统`是操作系统用来管理磁盘的结构

**InnoDB**、**MyISAM**等存储引擎将`表存储在文件系统上`，负责数据的读取和写入

本节的内容介绍`InnoDB`、`MyISAM`这两个存储引擎的如何在文件系统中存储数据。

### 2.1 查看默认的数据库

```mysql
SHOW DATABASES;
```

有4个数据库是属于MySQL自带的系统数据库

* `mysql`

  MySQL系统自带的核心数据库，它存储了MySQL的用户账户和权限信息，一些存储过程、事件的定义信息，一些运行过程中产生的日志信息，一些帮助信息以及时区信息等。
  
* `information_schema`

  MySQL系统自带的数据库，这个数据库保存着MySQL服务器`维护的所有其他数据库的信息`，比如有哪些表、哪些视图、哪些触发器、哪些列、哪些索引。这些信息并不是真实的用户数据，而是一些描述性信息，有时候也称之为`元数据`。在系统数据库`information_schema`中提供了一些以`innodb_sys`开头的表，用于表示内部系统表。

* `performance_schema`

  MySQL 系统自带的数据库，这个数据库里主要保存MySQL服务器运行过程中的一些状态信息，可以用来`监控 MySQL 服务的各类性能指标`。包括统计最近执行了哪些语句，在执行过程的每个阶段都花费了多长时间，内存的使用情况等信息。
  
* `sys`

  MySQL 系统自带的数据库，这个数据库主要是通过`视图`的形式把`information_schema`和
  `performance_schema`结合起来，帮助系统管理员和开发人员监控 MySQL 的技术性能。

### 2.2 数据库在文件系统中的表示

每个数据库都对应着数据目录下的一个子目录，或者说一个文件夹。

当使用`CREATE DATABASE 语句`新建数据库时。MySQL会做两件事

* 在数据目录下创建一个和数据库同名的子目录
* 在与该数据库同名的子目录下创建一个名为`db.opt`的文件（仅限MySQL5.7及之前的版本），这个文件中包含了`该数据库的各种属性`，比如该数据库的字符集和比较规则

>除了`information_schema` 系统数据库外，其他的数据库在数据目录下都有对应的子目录。

### 2.3 表在文件系统中的表示

数据是以`记录的形式`插入到表中，每个表的信息可以分为两种

* 表的结构的定义
* 表中的数据

`表结构`就是该表的名称，表里面有多少列，每个列的数据类型，约束条件和索引，使用的字符集和比较规则等信息，这些信息都体现在了建表语句中。

#### InnoDB存储引擎模式

##### 1) 表结构

为了保存表结构，`InnoDB`在`数据目录`下对应的数据库子目录下创建了一个专门用于`描述表结构的文件`

`表名.frm` form表单

`.frm文件`的格式在不同的平台上都是相同的。这个后缀名为.frm是以二进制格式存储的，直接打开是乱码的。

> MySQL8.0中不再单独提供`表名.frm`，而是合并在`表名.ibd`文件中。

##### 2) 表中数据和索引

InnoDB是以页为基本单位来管理存储空间的

为了更好的管理页，InnoDB提出了一个`表空间`或者`文件空间`（英文名：`table space` 或者`file space`）的概念，表空间是一个抽象概念，它可以对应文件系统上一个或多个真实文件（不同表空间对应的文件数可能不同）。每一个`表空间`可以被划分为多个`页`，我们的表数据就存放在某个`表空间`下的某些页里。

表空间的类型

**① 系统表空间（system tablespace）**

默认情况下，InnoDB会在数据目录下创建一个名为`ibdata1`、大小为`12M`的`自拓展`文件，这个文件就是对应的`系统表空间`在文件系统上的表示。

```properties
# 修改系统表空间文件名、文件数量、初始大小
[server]
innodb_data_file_path=data1:512M;data2:512M:autoextend
```

这样在MySQL启动之后，就会创建这两个512M大小的文件作为系统表空间，其中的`autoextend`表示这两个文件如果不够用会自动拓展`data2`文件的大小

在`一个MySQL服务器中，系统表空间只有一份`。从MySQL5.5.7到MySQL5.6.6之间的各个版本中，**表中的数据都会被默认存储到这个系统表空间中。**



**② 独立表空间(file-per-table tablespace)** 

在MySQL5.6.6以及之后的版本中，InnoDB并不会默认的把各个表的数据存储到系统表空间中，而是为`每一个表建立一个独立表空间`，即创建了多少个表，就有多少个独立表空间。使用`独立表空间`来存储表数据的话，会在该表所属数据库对应的子目录下创建一个表示该独立表空间的文件，文件名和表名相同，文件后缀`.ibd` 

`表名.ibd` InnoDB Data



**③ 系统表空间与独立表空间的设置**

我们可以自己指定使用`系统表空间`还是`独立表空间`来存储数据，这个功能由启动参数`innodb_file_per_table`控制

```properties
[server] 
innodb_file_per_table=0 # 0：代表使用系统表空间； 1：代表使用独立表空间
```

默认情况

```mysql
mysql> show variables like 'innodb_file_per_table';
+-----------------------+-------+
| Variable_name         | Value |
+-----------------------+-------+
| innodb_file_per_table | ON    |
+-----------------------+-------+
```

`innodb_file_per_table`参数的修改只对新建的表起作用，对于已经分配了表空间的表不起作用。

修改表所属的表空间

```mysql
#把已经存在系统表空间中的表转移到独立表空间
ALTER TABLE 表名 TABLESPACE [=] innodb_file_per_tables;
#把已经存在独立表空间中的表转移到系统表空间
ALTER TABLE 表名 TABLESPACE [=] innodb_system;
#其中等于号=可以省略
```



**④ 其他类型的表空间**

随着MySQL的发展，除了上述两种老牌表空间之外，现在还新提出了一些不同类型的表空间，比如通用表空间（general tablespace）、临时表空间（temporary tablespace）等。



##### 3）.frm文件

.frm文件在MySQL8中不存在，Oracle 官方将frm文件的信息以及更多的信息统称为序列化字典信息（Serialized Dictionary Information，SDI），并将SDI写在ibd文件内部。

Oracle提供了一个应用程序ibd2sdi，可以从IBD文件中提取SDI信息。

**查看表结构**

到存储ibd文件的目录下，执行以下命令

命令执行后，ibd2sdi会将ibd文件里存储的表结构以json的格式保存在txt文件中

```shell
ibd2sdi --dump-file=student.txt student.ibd
more student.txt
```



#### MyISAM存储引擎模式

##### 1）表结构

在存储表结构方面，`MyISAM`和`InnoDB`一样，也是在`数据目录`下对应的数据库子目录下创建了一个专门用于描述表结构的文件

```
表名.frm
```

##### 2）表中数据和索引

在MyISAM中的索引全部都是`二级索引`，该存储引擎的`数据和索引是分开存放`的。所以在文件系统中也是使用不同的文件来存储数据文件和索引文件，同时表数据都存放在对应的数据库子目录下。

假设`test`表使用MyISAM存储引擎，它所在数据库对应的`atguigu`目录下会为`test`表创建这三个文件

```mysql
test.frm 存储表结构 # MySQL8.0 改为了 test_xxx.sdi
test.MYD 存储数据 (MYData) 
test.MYI 存储索引 (MYIndex)
```



举例：创建一个`MyISAM`表，使用`ENGINE`选项显式指引擎。因为`InnoDB`是默认引擎

```mysql
CREATE TABLE `student` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL
) ENGINE=MYISAM DEFAULT CHARSET=utf8 ;
```



```shell
# MySQL5.7.26
student.frm
student.MYD
student.MYI

# MySQL8.0.25
student_361.sdi
student.MYD
student.MYI
```



#### 总结

**1）InnoDB**

* 表结构 `.frm` mysql8.0不存在，合并到`.ibd`文件中
* 表数据和索引
  * 系统表空间 ibdata1
  * 独立表空间 .ibd
* db.opt 数据库相关信息，比如字符集和比较规则 mysql8.0不存在

**2）MyISAM**

* 表结构 
* * MySQL5.7 `.frm`
  * MySQL8.0 `.sdi`
* 表数据信息 .MYD
* 表数据索引 .MYI



### 2.4 视图在文件系统中的表示

`视图`是`虚拟的表`，不存储真实的数据，只存储结构。

和表一样，描述视图结构的文件也会被存储到所属数据库对应的子目录下，只存储一个`视图名.frm `文件。

### 2.5 其他的文件

为了更好的运行程序，除了用户自己存储的数据以外，`数据目录`下还包括一些额外的文件

* 服务器进程文件

  每运行一个MySQL服务器程序，都意味着启动一个进程。MySQL服务器会把自己的进程ID写入到一个文件中。

* 服务器日志文件

  查询日志、错误日志、二进制日志、redo日志等

* 默认/自动生成的SSL和RSA证书和密钥文件

  主要是为了客户端和服务端安全通信而创建的一些文件。