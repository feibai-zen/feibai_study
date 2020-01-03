# 学习笔记
### 琐碎Tips：
1.BLOB和TEXT类型字段执行了大量删除操作后，会影响性能。
  1）空洞-碎片
  2）解决：optimize table收集碎片

2.检索时，char列会删除尾部空格，而varchar会保留这些空格
3.比较运算符，比较结果为真，则返回1，为假则返回0，不确定返回null
4.报错：ambiguous<—歧义
5.MySQL性能注意事项
1）select * from tb_name;# *代表所有字段，查询速度比选择具体的字段要慢 
2）select * from tb_name where id = XXX and name = XXX;#where后面的查询条件都可以添加索引，但也要避免索引滥用（影响增删改、影响内存、影响磁盘容量）
3）加上limit后提高性能
4）不要在sql语句上加上运算 ：… … where pid + 1 = XXX;
5）like模糊查询比较慢的原因是索引失效。但如果不对它进行左模糊查询，那么索引仍然有效。（左模糊查询：’%key'）
6.
create database 数据库名 default character set utf8



### 全局变量
show variables like ‘%char%’;             #查看编码
show variables like ‘%sql_mode%’;     #查看数据库使用的sql_mode
show variables like ‘%commit%’;        #查看auto_commit状态
show variables like ‘%slow%’;             #查看慢查询开启状态及日志文件位置
show variables like ‘%long%’;             #查看多长时间被定义成慢查询
show variables like ‘%profiling%’;      #查看profiles性能分析是否开启
show variables like ‘%cache%’;           #查看MySQL缓存是否开启
show variables like ‘%qcache%’;         #Qcache_hits缓存命中率

### 数据类型
1.字符串类型
char
varchar
text(适用一切text)、tinytext、mediumtext、longtext
blob
enum

char、varchar比较
char：定长、不够补空格多了截断、存储性能快、按块查找。缺点：浪费空间
varchar：变长、多了截断少了不补空格


2.时间日期类型
year
timestamp
time
date
datetime
建议使用int存Unix时间戳

3.数值类型
1)整数型
tinyint（1字节）
smallint（2字节）
mediumint（3字节）
int（4字节）
bigint（8字节）

2)小数型
浮点：float(10,2)4字节、double(10,2)8字节   #不精确
定点：decimal(4,2)  #最大表示99.99，4代表总长度，2代表小数位数

2019.02.19
1.mysql -uroot -p123456 -P3306 -hlocalhost
2.分号或\g结尾，可以通过dilimiter修改。\G显示的更加直观。
3.创建表时，表名称一定不用大写，可以用下划线_分隔，关键字不区分大小写，推荐使用大写。
4.注释：/* 注释内容 */  __ 注释内容，#注释内容
5.退出：exit/quit
6.帮助：help;或\h或？
7安装MySQL后自动创建的表及功能：
   information_schema:存储系统中的一些数据库对象信息，比如：用户表信息、列信息、权限信息、字符集信息、分区信息等
   mysql:存储了系统用户权限信息
   cluster:存储了系统中的集群信息
8.字段修饰关键字
auto_increment:必须制定为primary key
default
comment
not null
unique
index 
key
primary key
9.运算符
=（赋值、判断）
比较运算符
<、<=、=、!=（<>）、>=、>、in、between X and X
逻辑运算符
not(!)、or(||)、and(&&)、is not null、is null、函数ifnull()
eg:
between 5 and 10;
in(1,2,3,4,5);
not in(1,2,3,4,5)

10.两个判断字符串长度的函数
1）length():字节数。utf8编码下，一个汉字算三个字节（其他编码可能不同）。一个数字或字母算一个字节。
2）char_length():字符数。一个多字节字符算一个字符。

11.在数据之外数据库还维护着满足特定查找算法的数据结构，这些数据结构以某种形式引用数据，这样就可以在这些数据结构之上实现高级查找算法，这种数据结构就是索引。

12.MySQL服务：一系列后台进程
    MySQL数据库：一系列数据文件和目录

13.修改完配置文件my.cnf后，一定要重启MySQL服务。
14.MySQL运行日志：/var/log/mysqld.log

2019.02.20
DDL
create、drop、alter
1.库
create database db_name;
use db_name;
drop database db_name;
2.表
1）创建表：
create table tb_name(
    ename varchar(10),
    hiredate date,
        …
);
因为MySQL的表名以目录的形式存在磁盘上，所以表名的字符可以用任何目录名允许的字符。
2）查看表定义：
desc tb_name;  #查看表的定义
show create table tb_name\g #查看表定义，信息更全面
3）删除表：
drop table tb_name;
drop table if exists tb_name;
4）修改表字段定义：
alter table tb_name modify column_name column_definition [first|after column_name2];
alter table tb_name add column_name column_definition [first|after column_name2]; #默认添加到最后一列
alter table tb_name drop column_name;
alter table tb_name change column_name column_name_new column_definition [first|after column_name2];
change和modify都可以修改列的定义，不同的是change后面可以写两次列名，不方便。但change的优点是可以修改列名，modify则不能。
5）修改表名称
alter table tb_name rename [to] tb_name_new;

DML
insert、update、delete
1.insert
insert into tb_name (col_1,col_1,col_3) values(列值1,列值2,列值3);
insert into tb_name values (xxx,xxx,xxx);
insert into tb_name values (xxx,xxx,xxx), (xxx,xxx,xxx), (xxx,xxx,xxx);
注：含可空字段、非空但是含有默认值的字段、自增字段，可以不用再insert后面的列表中出现，values后面只写对应字段名称的value。这些没写的字段可以自动设置为null、默认值、自增的下一位值。
2.update
update tb_name set age=100 where id = 1; #若不加where筛选，则所有记录的age都会被修改
update tb_name set age =10, user_name=‘xxx’ where id = 1;
3.delete
delete from tb_name where…..
delete from tb_name; #清空表，但自增字段接之前的数值
truncate tb_name; #清空表，自增字段也会从头开始

DCL(数据库控制语言)
正常上线项目一个MySQL可能会跑好几个项目，每个项目对应一个数据库，不同的数据库需要对应不同的用户，并且用户权限也不一样。线上是不允许使用root用户连接的。

mysql库存储了系统用户权限信息。
select user, host from mysql.user;#查询出允许连接的ip地址
flush privileges;#刷新权限

1.修改用户密码
方式1：
use mysql;
update user set password = PASSWORD(‘admin') where user = 'root';
flush privileges;

方式2：
mysqladmin -uroot -padmin password 123456;

2.忘记密码(待补充)
1）修改配置文件，设置：[mysqld] skip-grand-tables 
2）重启MySQL服务：service mysqld restart
3）update mysql.user set authentication_string=password('root') where user='root’ ;
4）重启MySQL服务：service mysqld restart

3.创建用户
mysql -uroot -p123456;#使用root用户创建，先登录root用户
create user ’test'@‘192.168.255.255’ identified by ‘123456’;#可以指定为%，意为所有ip地址都可以访问，不安全。指定前缀192.168.%
drop user ’test’@‘ip’;#删除用户
flush privileges;

4.为用户授权/撤销权限
mysql -uroot -p123456;#使用root用户创建，先登录root用户
grant 权限1,权限2,……… on 数据库名.* to 用户名 @IP地址或者%
revoke 权限1,权限2,……… on 数据库名.* from 用户名 @IP地址或者%
注：所有权限使用all或者all privileges。所有库用*.*

grant update,insert,delete on *.* to test@‘127.0.0.1';
grant select,insert,delete on *.* to test@‘127.0.0.1’ identified by ‘123456’;#创建用户并授权
show grants for ’test’ @‘192.168.21.255’;#root用户查看某用户的权限
以上所有操作都需要flush一下才生效

2019.02.20
DQL(数据库查询语言)

1.select distinct(password) from user; #把查出来的密码去重
2.select contact(user_name, email) as user_name_email from user; #拼接两个字段，并取别名
3.select contact_ws(‘==‘,user_name,email) as user_name_email from user;#以==号拼接两个字段，并取别名。
4.select user_name from user where user_name like ‘%liming%’;#模糊查询，%是占位符，可以有多个。查询比较慢，数据少时可以用

5.
select * from user order by 字段名 ASC|DESC; #默认升序
select * from user order by 字段名1 desc, 字段名2 asc;
order by经常和limit配合使用进行记录的分页显示。
limit1,3  —— 从第2条开始，显示3条
limit 3 —— 显示3条

6.聚合函数
查询时间超过0.5s，就视为不可接受
count(*)很耗时
select count(*)|sum(age)|avg(fee)|max(age)|min(age)…

7.分组group by  xxx  having 
有group by才会有having
having子句可以筛选group by成组后的数据，where子句在聚合前先筛选记录，即where作用在group by和having子句前，having子句在聚合后对组记录进行筛选。

eg1:每个地区的总人口和总面积
select region, sum(polulation), sum(area) from bbc group by region;#高版本的MySQL会限制select后的列必须为order by后面的列或其聚合函数。
先以region把返回记录分成多组，这就是group by的作用。分组完成后，用聚合函数对每组中的不同字段（一条或多条记录）做运算。

eg2:显示每个地区的总人口和总面积，仅显示那些面积大于100000000的地区
select region, sum(polulation), sum(area) from bbc group by region having sum(area) > 100000000;
这里不能用where来筛选超过100000000的地区，因为表中不存在这样的记录。相反，having子句可以让我们筛选成组后的各组数据。

having和where的不同之处：
where作用于视图和表，having作用于组
having可以用聚合函数，where在分组和聚合前选取记录，因此不能包含聚合函数。
select count(*), sex from user group by sex;#按性别分组并统计每组总数
select count(*), sex from user group by sex having count(*) > 2;

2019.02.23
1.创建表的示例
create table user(
    id int unsigned not null auto_increament comment ‘用户id’,
    …
    primary key(id)
);#auto_increament必须满足1）主键或者唯一索引；2）not null。auto_increament的字段值，删除数据并重启MySQL，值会改变。

2.连接查询
1）内连接


select S.name, M.mark from student as S inner join mark as M where/on S.id = M.id;
等效于
select S.name, M.mark from student as S, mark as M where S.id = M.id;
2）左连接

select S.name, M.mark from student as S left join mark as M on S.id = M.id;
先把左表中的列全列出来，右边的值添加上去。注意：on的筛选条件作用于mark表。

3）右连接

select S.name, M.mark from student as S right join mark as M on S.id = M.id;
先把右表中的列全列出来，左右满足筛选条件的添加上去。不满足的补空。

4）联合查询

select name, age from student union all/distinct(默认) select mark,  stu_id from mark;
两个select语句筛选的列必须一样多。如:name,age两列，mark,stu_id也是两列

eg:
select * from student where id = 2 or id = 5;# 可以使用in(2,5)，因为能够使用上索引。注意：如果查询条件中有or，即使其中有条件带索引也不会使用。换言之，就是要求所有字段都必须建立索引。尽量避免使用or关键字。
等效于
select * from student where id = 2 union all select * from student where id = 5;
查询效率性能差别很大，下面的联合查询更优。

3.子查询
select id from student where id in (
    select stu_id from mark;
);
1）from型
把内层的查询结果当成临时表供外层sql再次查询。临时表要使用一个别名
eg:
select goods_id, goods_name, cat_id, shop_price from (
    goods_id,goods_name, cat_id, shop_price from goods order by cat_id asc, goods_id desc
) as temp group by cat_id;
2）IN谓词的子查询
eg:
select Sno, Sname, Sdept from student where Sdept IN (
    select Sdept from student where Sname = ‘XXX’
);
3）带有比较运算符的子查询
eg:
select Sno, Cno from SC X where Grade >= (
    select avg(Grade) from SC Y where Y.Sno=X.Sno
);
4）带有ANY(SOME)或ALL谓词的子查询
5）带有EXISTS谓词的子查询
    EXISTS谓词的子查询不返回任何数据集，只返回逻辑true或false。若子查询结果非空，则外层的where子句返回true，否则返回false。
select Sname from student where exists (
    select * from SC where Sno = student.Sno and Con=‘1’
);#返回true|false

4.视图VIEW（使用视图增加数据安全）
create view 视图名[colu_1,colu_2,…] as select 语句；#实际就是当作一个查询表来用。
eg:
create or replace view test_view (name, email) as select name, email from student where id =2 ;#若存在则替换
show create  view test_view\G #查看视图定义语句
drop view test_view;# 一般不修改视图定义，而是直接删除视图，再重新建视图
show table status like ’test_view’\G
information_schema的views表记录了所有的视图信息。
可以把一个复杂的联表查询定义成一个视图，简化后续查询语句。

2019.02.24
1.MySQL三范式的设计原则
范式不是绝对要求，有时候为了数据使用方便，还会故意违反范式，表多了，查询语句就复杂了。
第一范式：1NF（原子性、列不可分解）
    表的列具有原子性，不可再分解。只要数据库是关系型数据库，就自动满足1NF。
第二范式：2NF（每个记录item必须唯一，有主键，非主键字段依赖主键）
    满足第二范式必须先满足1NF，2NF要求数据表中的每个实例或行必须可以被唯一地区分。为实现区分通常设计一个主键来实现（这里的主键不包含业务逻辑）。
第三范式：3NF
    满足3NF必须先满足2NF。3NF要求一个数据库表中不包含已在其他表中已包含的非主键字段。即表的信息如果能够被推导出来，就不应该单独地设计一个字段来存放（尽量使用外键join）。很多时候，为了满足3NF往往把一张表分成多张表。

2.数据库引擎
不同的引擎解决不同的应用场景，引擎影响数据库的查询效率。
MyISAM:                不支持事务，支持全文索引，不支持外键，表级锁，崩溃处理不好
Innodb：               支持事务，5.6后支持全文索引，支持外键，行级锁，崩溃处理更好
BLACKHOLE
CSV：                     处理Excel
MEMORY：          内存
ARCHIVE

推荐使用Innodb，速度快，5.6后成为默认使用的引擎。
show table status\G #查看所有表的引擎
show engines;#查看数据库系统支持的数据库引擎
alter table user engine=mysiam;
default-storage-engine=MyISAM; #在配置文件中修改创建表时默认使用的引擎，修改配置文件后要重启MySQL

3.sql_mode
ANSI：不严格，允许插入时截断。报warning
STRICT_TRANS_TABLES：超出范围不允许存入，且不允许类型自动转换。报：error
查看使用的sql_mode
show variables like ’%sql_mode%’;
修改sql_mode
set sql_mode=‘STRICT_TRANS_TABLES';
set [session|global] sql_mode=‘xxx’;#session只在本次连接中生效；global已开启连接不生效，而对新创建的连接生效。
启动时通过参数设置：—sql_mode=‘XXX’;
注意：所有set操作都是临时的，MySQL服务关闭后仍恢复成原来值。

4.DTL事务控制语言
Innodb支持事务，Myisam不支持事务
1）一个sql语句就是一个事务；事务可以保证一组语句要么成功，要么失败。默认自动提交，可以通过设置参数关闭。
查看自动提交时否自动提交
show variables like ‘%commit%’;#auto_commit—>on
set auto_commit=0;#关闭自动提交，关闭后每次执行语句后需要commit
eg:
start transaction;#开启事务
update user set fee = 5000 where id = 1;
update user set fee = 3500 where id = 2;
rollback;#回滚，以上所有操作都无用，或者commit;提交成功。

总结：
1）rollback:transaction之后，rollback以前的操作都不执行。
2）commit:transaction之后，commit以前的语句都执行成功。
所有set操作都是临时的，MySQL重启后仍恢复原始值；永久改变通过配置文件。

2）事务的4个特性（ACID）
原子性(Atomicity):一个事务中的所有语句应该做到，要么全部执行成功，要么有执行失败就全部失败
一致性(Consistency):让数据保持逻辑上的合理性；比如：一个商品出库时，既让商品库中的商品数量减1，又让对    用户购物车中的该商品加1
隔离性(Isolation):如果多个事务同时并发执行，但每个事务就像各自独立执行一样。
持久性(Durability):一个事务执行成功，则对数据来说应该是一个明确的硬盘数据更改（而不仅仅是内存中的变化）。


2019.02.25
触发器
create trigger trigger_name after|before  insert|update|delete on tb_name for each row
    begin
        sql语句
    end;

每插入一个表的item，就对另外一个表的字段加1。每增加一篇文章，就对另一个表中的文章总数字段加1.
delimiter //
create trigger insert_total_num after insert on article for each row
begin
    update total_num set num=num + 1 where type = 1;
end//
delimiter ;


delimiter //
create trigger delete_total_num after delete on article for each row
begin 
    update total_num set num=num -1 where type = 1;
end;
delimiter ;

show create trigger trigger_name;#查看创建触发器的语句
drop trigger trigger_name;#删除触发器
select * from information_schema.triggers;#查看定义的所有触发器


2019.02.25
1.字符集
utf8(汉子占3字节)  utf8mb4(汉子占4字节)
做开发时所有的编码都要默认用utf8
show create database test\G
create database test character set utf8;
create table test(…) charset=utf8;
alter table test charset=utf8;
show character set; #查看支持的字符集及字符集校对规则及字符最大长度
默认创建的表的字符集与库的字符集一致；允许为库中的表指定新的字符集（与库不一致的）。

2.字符集校对规则
作用：定义了比较字符串的方式，解决排序和字符分组的问题。使用utf8_general_ci不区分大小写。
show table status;#可以查看字符集及字符集校对规则
create table test(…) engine=myisam charset=utf8 collate=utf8_general_ci;# charset与collate两两绑定，指定了utf8，就默认匹配utf8_general_ci;

3.关于字符、编码等的一些思考
1）ASCII()函数返回字符的ASCII值；大小写字母相差32
2）位、字节、字符
ASCII是1字节编码，对应8位，最大值127。汉字需要多字节才能表示，故出现新的编码，如utf8，gbk等
一个字符可能需要多个字节存储
3）int(n)：默认n为11，n代表的不是长度；n只有在指定了zerofill时候才有用，在不到n位数时补0。可以理解成至少n位。超过存储范围会被截断，按照字节算存储范围(4字节)，n不影响存储范围；
eg:
int(7)#指定zerofill时候，在不到7位数时存储为0123456
alter table int_test modify age int(20) zerofill unsigned;

4）char(n)与varchar(n)
varchar(n)中的n必须指定，不足n不需要自动补空格，超过n会截断。
char(n)的n可以不填，默认为1，最大为255；不足n会自动补空格。不管汉字还是数字，字母都是字符长度，’我是谁’是3个字符，utf8时占用9字节。

存有数据的表，在进行修改字段定义时，会改变表中的值，会通过warning进行提示。

char最多只能够存进255个字符，中文也算一个字符，超过255字符用text或blob存。
varchar(n):当n为65535时，字段被默认转成mediumtext，所以当字符长度超过1000时就用text存储。

2019.03.04
数据导入、导出
1）测试能否连接上数据库使用telnet ip port看是否能够登录
2）导出
     navicate：转储sql文件
     命令行：
mysqldump -uroot -padmin test(库)  account(表) > account.sql。mysql使用mysqldump导出数据的文件通常比navicate导出的文件小。

导出数据时会锁库，会影响业务性能。可以使用crontab定时任务，定时对数据库进行备份，一般选择在夜间操作。
3）导入
mysql -uroot -padmin test < account.sql
navicate
mysql>source ./account.sql

4）复制表
create table a like b;#创建表a，其定义同b
insert into a select * from b;
推荐使用命令行的方式执行数据的导入导出，执行速度快。如果数据量非常大，命令行mysqldump执行也比较耗时，推荐使用xtrabackup进行备份。

2019.03.04
索引
作用：1）提高查询性能；2）提高排序性能；3）提高分组统计速度。
理解上可以参照排序好的btree树。
1.索引类型
1）主键索引
primary key(column1) 
主键索引也是唯一索引，一个表只能有一个primary key，不重复，不允许为空

2）普通索引
key(column1, column2,column3, …)
key/index等效

3）唯一索引
unique key(column1, column2, …)
不重复，允许多个null，允许多个唯一索引

4）全文索引
fulltext(column1, column2, …)

5）外键约束
foreign key references tb_name(column1)
比如：一个学生对应成绩，不可能存在[没有学生，但有成绩]这种情况，即成绩表中的学生，必须在学生表中存在。

6）组合索引/联合索引
key(column1, column2,column3, …)

2.全文索引
比模糊查询更快，解决like比较慢，全文索引不支持中文，对英文忽略大小写。主要是针对文本的检索，比如文章。

创建
alter table user add fulltext(column1,column2);#mysql指定了最小字符长度ft_min_word_len，默认为4，必须要匹配大于4的；

使用
select * from account where match(column1, column2) against(‘XXXXX’);#匹配的内容不能使用中文，匹配的列需要与创建索引是指定的列保持一致

select * from account where password like ‘%XXXXX%’;#模糊匹配支持匹配中文，但是速度比较慢

3.外键约束
两个表必须是innodb引擎
有外键约束后，table1不能清空了

table1:
    create table user (
        id int(10) unsigned not null auto_increment,
        user_name varchar(20) not null,
        password char(32) not null,
        email varchar(50) not null,
        primary key(id),
        unique key user_name(user_name),
        key age(age),
        key email(email)
    )engine=innodb auto_increment=3 default charset=utf8;

table2:
    create table mark(
        mark int unsigned not null,
        stu_id int(10) unsigned,#类型要与table1的类型保持一致
        primary key (id),
        foreign key(stu_id) references user(id)
    )engine=innodb;

在定义表指定索引时，index和key可以任意选，但是primary key除外，不存在“primary index”。

这样定义的表：1）如果往mark表中插入stu_id在user表id字段不存在的值时会报错，执行失败。
                            2）若删除user表中的id已经对应mark表中的stu_id的值时会报错，执行失败。

4.创建短索引
只对于字符串类型。使用短索引不仅能够提高查询速度，而且能节省磁盘和I/O操作。
alter table tb_name add index u_index_name(username(10));

5.创建索引
create index index_name on tb_name (column_list);
create unique index index_name on tb_name(column_list);
不能用create index语句创建primary key。create后面只能跟…index
alter table tb_name add primary key(column_1);

6.查看索引
show index/keys from tb_name;

output:
    Table:表名
    Non_unique:索引不能包括重复词，则为0，可以则为1
    Key_name:索引名称
    Seq_in_index:索引中的列序号，从1开始
    Column_name:列名称
    Collation:列以什么方式存储在索引中。MySQL中A代表升序或NULL(无分类)
    Cardinality:索引中唯一值的数目的估计值。通过运行Analyze table或myisamchk -a可以更新
    Sub_part:如果列只是部分地编入索引，则为被编入索引的字符数目，如果整列被编入索引，则为NULL。可以根据这个字段判断是否为短索引。
    Packed:关键字如何被压缩。如果没有被压缩，则为NULL。
    Null:如果列含有NULL，则为YES，如果没有则为NULL。
    Index_type:用过的索引方法(BTREE、FULLTEXT、HASH、RTREE)

7.删除索引
drop index index_name on tb_name;
alter table tb_name drop index index_name;
alter table tb_name drop primary key;#a.只有在删除primary key索引中使用，因为一个表中可能有一个primary key，因此不需要指定索引名。如果没有创建primary key索引，但具有一个或多个unique索引，则将删除第一个unique索引。b.如果从表定义中删除某列，则索引会受到影响；对于组合索引，删除一列，则会删除索引中相应的列，如果组合索引中的列均删除，则整个组合索引将被删除。


2019.03.13
1.布尔全文索引
create table artices(
    id int unsigned auto_increment not null primary key,
    title varchar(200),
    body text,
    FULLTEXT (title,body)
)engine = myisam, default charset=utf8;
1)
select * from articles where MATCH(title, body) AGAINST (‘+apple -banana’ IN BOOLEAN MODE);
+表示AND，即必须包含。-表示NOT，即不包含。

2)
select * from articles where MATCH(title, body) AGAINST (‘apple banana’ IN BOOLEAN MODE);
apple和banana之间是空格，空格表示OR，即至少包含apple、banana中的一个。

3)
select * from articles where MATCH(title, body) AGAINST (‘+apple banana’ IN BOOLEAN MODE);
必须包含apple，如果包含banana则会获得更大权重。

4）
select * from articles where MATCH(title, body) AGAINST (‘+apple ~banana’ IN BOOLEAN MODE);
必须包含apple，如果同时包含banana会降低权重。不同于+apple -banana，因为后者如果包含banana则不返回。
5）
select * from articles where MATCH(title, body) AGAINST (‘+apple +(>banana <orange)’ IN BOOLEAN MODE);
返回同时包含apple和banana或者同时包含apple和orange的记录。但是同时包含apple和banana的权重高于同时包含apple和orange的权重。

6）
select * from articles where MATCH(title, body) AGAINST (“‘apple and banana’” IN BOOLEAN MODE);
匹配一个短句，把它使用双引号引起来。只有在行包含的单词及其顺序都一致时，才会被认为是匹配上了。


### 2.聚簇索引与非聚簇索引
myisam：非聚簇索引，Btree索引，索引指向行所在磁盘上的位置
innodb：聚簇索引，Btree索引，索引指向对主键的引用

innodb的主索引文件上直接存放该行数据，成为聚簇索引，次索引指向对主键的引用。
myisam中主索引与次索引都指向物理磁盘上的数据行。

注意：对innodb来说
1）主键索引既存储索引值，又在叶子中存储数据值
2）如果没有主键，则会用unique key做主键，如果也没有unique key，则系统会生成一个内部的rowid做主键

聚簇索引：
优势：
    根据主键查询条目比较少时，不用回行，数据就在主键节点下。

劣势：
    如果遇到不规则主键数据插入时，会造成频繁的页分裂，插入速度将会变得非常慢

页分裂：聚簇索引如果主键不规律，将发生反复页分裂。聚簇索引叶子节点存放着数据，如果数据节点比较大，通常说这个叶子节点比较重。同时，如果插入的数据的主键没有规律，一会是4一会是10000，本来想在磁盘上有规律的码放。因为插入的没有规律，所有将会频繁的拖着沉重的数据移来移去，这个过程叫做页分裂。
                                                                



### MySQL优化
- 表的设计合理化(符合3NF)
- sql语句优化
- 添加适当索引(index) [四种: 普通索引、主键索引、唯一索引unique、全文索引]、另外组合索引
- 分表技术(水平分割、垂直分割)
- 读写[写: update/delete/add]分离
- 存储过程 [模块化编程，可以提高速度]
- 对mysql配置优化 [配置最大并发数my.ini, 调整缓存大小 ]
- mysql服务器硬件升级
- 定时的去清除不需要的数据,定时进行碎片整理(MyISAM)