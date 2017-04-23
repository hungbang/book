package com.book.controller;

import com.book.model.Book;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Hung on 4/23/2017.
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/booklist")
    public ModelAndView bookList(){
        ModelAndView view = new ModelAndView("site.booklist");
        Page<Book> books = bookService.bookList(null);
        view.addObject("books", books);
        return view;
    }


}
