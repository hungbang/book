package com.book.controller;

import com.book.model.Book;
import com.book.service.BookService;
import com.book.vo.BookJsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.PageAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Hung on 4/23/2017.
 */
@Controller
public class BookController {

    private final static Logger logger = Logger.getLogger(HomeController.class);


    @Autowired
    private BookService bookService;

    @GetMapping("/booklist")
    public ModelAndView bookList(){
        ModelAndView view = new ModelAndView("site.booklist");
        return view;
    }


    @GetMapping(value = "/data/booklists", produces = "application/json")
    @ResponseBody
    public String databooks(HttpServletRequest req){
        ModelAndView view = new ModelAndView("site.booklist");

        Integer pageNumber = 0;
        Integer pageDisplayLength = 0;
        String checkError = null;
        String searchParameter = req.getParameter("sSearch");

        if (null != req.getParameter("iDisplayStart")) {
            logger.info("111111111: "+ req.getParameter("iDisplayStart"));
            //add more +1 if using Hibernate , else not need.
            //pageNumber = (Integer.valueOf(req.getParameter("iDisplayStart")) / 10) + 1;
            pageNumber = Integer.valueOf(req.getParameter("iDisplayStart"));
        }

        if(null != req.getParameter("iDisplayLength")){
            logger.info("222222222:: "+ req.getParameter("iDisplayLength"));
            pageDisplayLength = Integer.valueOf(req
                    .getParameter("iDisplayLength"));

        }

        Page<Book> pageBooks = bookService.bookList(pageNumber);
        BookJsonObject jsonObject = new BookJsonObject();
        jsonObject.setAaData(pageBooks.getContent());
        jsonObject.setiTotalDisplayRecords(pageBooks.getTotalPages());
        jsonObject.setiTotalRecords(pageBooks.getTotalPages());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonObject);
        return json;
    }

    @PostMapping(value = "/getBookById")
    @ResponseBody
    public Book getBookById(@RequestParam(value="id") int id){
        Book book = bookService.getBookById(id);
        return book;

    }

    @PostMapping(value = "/saveBook", produces = "application/x-www-form-urlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView saveBook(@ModelAttribute("book") Book book, BindingResult result, HttpServletResponse response, HttpServletRequest request){
        logger.info("=====book: "+ book.getDescription() + "===id: "+ book.getId());
        ModelAndView model = new ModelAndView("redirect:booklist");
        book.setDateUpdate(new Timestamp(System.currentTimeMillis()));
        Book saveBook = bookService.update(book);
        return model;

    }

    @PostMapping(value = "/addBook", produces = "application/x-www-form-urlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addBook(@ModelAttribute("book") Book book, BindingResult result, HttpServletResponse response, HttpServletRequest request){
        logger.info("=====book: "+ book.getDescription() + "===id: "+ book.getId());
        ModelAndView model = new ModelAndView("redirect:booklist");
        book.setDateUpdate(new Timestamp(System.currentTimeMillis()));
        book.setDateCreate(new Timestamp(System.currentTimeMillis()));
        Book saveBook = bookService.update(book);
        return model;

    }

    @PostMapping(value = "/deleteBook")
    public ModelAndView deleteBook(@RequestParam(value="id") int id, HttpServletResponse response){
        bookService.removeBook(id);
        ModelAndView model = new ModelAndView("redirect:booklist");
        response.setStatus(response.SC_OK);
        return model;
    }



}
