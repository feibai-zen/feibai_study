
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