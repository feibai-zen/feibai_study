
#show profiling statistic
#说明: 1:Total_R--子任务总的执行耗时   2:Pct_R--时间占比   4:Calls--子任务执行次数  5:R/Call--子任务每执行一次的平均耗时
SET @query_id = 1;// 需要修改具体的query id

SELECT STATE, SUM(DURATITON) AS Total_R,
    ROUND(100 * SUM(DURATION)/(SELECT SUM(DURATION) FROM INFORMATION_SCHEMA.PROFILING WHERE QUERY_ID = @query_id),2) AS Pct_R,
    COUNT(*) AS Calls,
    SUM(DURATION)/COUNT(*) AS "R/Call"
FROM INFORAMTION_SCHEMA.PROFILING
WHERE QUERY_ID = @query_id
GROUP BY STATE
ORDER BY Total_R DESC;


#show status Page:85
#说明: 1:SHOW STATUS显式的是计数器   2:注意查看的会话级别,默认是SESSION,可以设置为GLOBAL   3:通过计算器是实际的测量结果,EXPLAIN是通过估计得到的结果,EXPALIN无法告知临时表是否是磁盘表
FLUSH STATUS;
#SELECT * FROM tb_name; #just a example.
SHOW STATUS WHERE Variable_name LIKE 'Handler%' OR Variable_name LIKE 'Created%';


#每秒查询数 | Threads_Connected | Thread_running(正在执行的查询),这三个数据的趋势对于服务器级别偶尔停顿的敏感性很高.该命令每秒钟捕获一次show GLOBAL status的数据 Page:90
关于这三个指标的说明:Thead_running正常情况下不会超过10,这也与CPU的核心数有密切关系,可以任务连续5秒Thread_running保持20以上为异常情况.超过15可以认为有少量查询堆积.

mysqladmin ext -i1 | awk '/Queries/{q=$4-qp;qp=$4}/Threads_connected/{tc=$4}/Threads_running/{printf "%5d %5d %5d\n",q,tc,$4}'

#查看线程状态,可以查看是否有大量线程出于线程不正常的状态 Page:91
mysql -e 'SHOW PROCESSLIST\G' |grep State: |sort |uniq -c |sort -rn

#统计慢查询日志中每秒的查询数量 Page:92
awk '/^# Time:/{print $3, $4, c;c=0}/^# User/{c++}' slow-query.log