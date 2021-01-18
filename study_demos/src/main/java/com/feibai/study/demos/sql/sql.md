```sql

load jdbc.`mysql7.lamia.tb_live_record_all`(uid,actual_stop_at,actual_start_at)  where actual_stop_at > 0 as t;

select date_add("2020-12-10",1) as dt,a.uid from (
 -- 过去一周开播过。如果过去一周没有开播过，开播时长不会增加。同时，有效开播天数也不会增加
 select distinct uid from t where actual_stop_at >= unix_timestamp(date_add("2020-12-10",1),'yyyy-MM-dd') * 1000 and actual_stop_at < unix_timestamp(date_add("2020-12-10",8),'yyyy-MM-dd') * 1000
)a 
left join(
  -- 2020年10月01日前开播过的主播不算新主播
 select distinct uid from t where actual_stop_at < unix_timestamp("2020-10-01 00:00:00.0") * 1000
)b on a.uid = b.uid where b.uid is null as new_anchor;

select uid, cast(sum(actual_stop_at-actual_start_at)/60000 as bigint) as listen_min from t where actual_stop_at >= unix_timestamp(date_add("2020-12-10",1),'yyyy-MM-dd') * 1000 and actual_stop_at < unix_timestamp(date_add("2020-12-10",8),'yyyy-MM-dd') * 1000 group by uid as listen_duration;

select cast(year(date_add("2020-12-10",8)) as int) as year,cast(weekofyear(date_add("2020-12-10",8)) as int) as week, na.uid from new_anchor na
left join listen_duration ld on na.uid = ld.uid
where ld.listen_min >= 240 as tb_result;

load jdbc.`mysql9.growth.tb_anchor_valid_day_statistics`(date,uid)  where date >= "2020-10-01 00:00:00.0" as
 anchor_valid;
select uid, count(*) as cnt from anchor_valid where date < date_add("2020-12-10",1) group by uid having cnt >= 2 as valid_broast_anchor;

select * from tb_result where exists (select uid from valid_broast_anchor where valid_broast_anchor.uid = tb_result
.uid) as result_tb_tmp;

-- 排除历史参加过的主播
load jdbc.`mysql3.growth.tb_new_anchor_growth_schedule` as history;
select distinct uid from history as history_anchor;



select * from result_tb_tmp where not exists (select uid from history_anchor where uid = result_tb_tmp.uid) as
 result_final;
```