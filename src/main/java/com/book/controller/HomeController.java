package com.book.controller;

import com.book.model.Book;
import com.book.repository.BookRepository;
import com.book.repository.CustomRepository;
import com.book.repository.UserRepository;
import com.book.vo.UserVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Hung on 4/23/2017.
 */
@Controller
public class HomeController {

    private final static Logger log = Logger.getLogger(HomeController.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CustomRepository customRepository;

    @RequestMapping("/home")
    String home(){
        return "site.index";
    }

    @RequestMapping("/login")
    String login(){
        return "site.login";
    }


    @RequestMapping("/booklisting")
    String list(){
        return "site.booklisting";
    }

}
