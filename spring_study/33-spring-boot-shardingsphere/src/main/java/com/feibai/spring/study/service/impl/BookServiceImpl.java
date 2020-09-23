package com.feibai.spring.study.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feibai.spring.study.entity.Book;
import com.feibai.spring.study.mapper.BookMapper;
import com.feibai.spring.study.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

  @Override
  public List<Book> getBookList() {
    return baseMapper.selectList(Wrappers.<Book>lambdaQuery());
  }

  @Override
  public boolean save(Book book) {
    return super.save(book);
  }
}
