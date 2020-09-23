package com.feibai.spring.study.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("book")
public class Book extends Model<Book> {
  private int id;
  private String name;
  private int count;
}
