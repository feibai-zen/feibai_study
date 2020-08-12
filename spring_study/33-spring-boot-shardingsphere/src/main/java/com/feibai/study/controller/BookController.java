package com.feibai.study.controller;

import com.feibai.study.entity.Book;
import com.feibai.study.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

  @Autowired
  BookService bookService;

  @RequestMapping(value = "/book", method = RequestMethod.GET)
  public List<Book> getItems() {
    return bookService.getBookList();
  }

  @RequestMapping(value = "/book", method = RequestMethod.POST)
  public Boolean saveItem(Book book) {
    return bookService.save(book);
  }
}
