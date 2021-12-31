package com.feibai.spring.study.strategy;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author mazhq
 * @Title: TableShardingAlgorithm
 * @ProjectName: zeus
 * @Description: TODO
 * @date 2019/8/1 17:14
 */
@Component
public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {
  @Override
  public String doEqualSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
    for (String each : tableNames) {
      if (each.endsWith(shardingValue.getValue() % 2 + "")) {
        return each;
      }
    }
    throw new IllegalArgumentException();
  }

  @Override
  public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
    Collection<String> result = new LinkedHashSet<>(tableNames.size());
    for (Long value : shardingValue.getValues()) {
      for (String tableName : tableNames) {
        if (tableName.endsWith(value % 2 + "")) {
          result.add(tableName);
        }
      }
    }
    return result;
  }

  @Override
  public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
    Collection<String> result = new LinkedHashSet<>(tableNames.size());
    Range<Long> range = shardingValue.getValueRange();
    for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
      for (String each : tableNames) {
        if (each.endsWith(i % 2 + "")) {
          result.add(each);
        }
      }
    }
    return result;
  }
}
