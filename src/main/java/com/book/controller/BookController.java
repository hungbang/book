package com.book.controller;

import com.book.model.Book;
import com.book.service.BookService;
import com.book.vo.BookJsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

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
    public BookJsonObject databooks(HttpServletRequest req){
        Integer pageNumber = 0;
        Integer pageDisplayLength = 0;
        String checkError = null;
        String searchParameter = req.getParameter("sSearch");

        if (null != req.getParameter("iDisplayStart")) {
            //add more +1 if using Hibernate , else not need.
            //pageNumber = (Integer.valueOf(req.getParameter("iDisplayStart")) / 10) + 1;
            pageNumber = Integer.valueOf(req.getParameter("iDisplayStart"));
        }

        if(null != req.getParameter("iDisplayLength")){
            pageDisplayLength = Integer.valueOf(req
                    .getParameter("iDisplayLength"));

        }
        Page<Book> pageBooks = bookService.bookList(pageNumber/pageDisplayLength, pageDisplayLength);
        BookJsonObject jsonObject = new BookJsonObject();
        jsonObject.setAaData(pageBooks.getContent());
        jsonObject.setiTotalDisplayRecords((int)pageBooks.getTotalElements());
        jsonObject.setiTotalRecords((int)pageBooks.getTotalElements());
        return jsonObject;
    }

    @PostMapping(value = "/getBookById")
    @ResponseBody
    public Book getBookById(@RequestParam(value="id") int id){
        Book book = bookService.getBookById(id);
        return book;

    }

    @PostMapping(value = "/saveBook", produces = "application/x-www-form-urlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView saveBook(@ModelAttribute("book") Book book, BindingResult result, HttpServletResponse response, HttpServletRequest request){
        ModelAndView model = new ModelAndView("redirect:booklist");
        book.setDateUpdate(new Timestamp(System.currentTimeMillis()));
        Book saveBook = bookService.update(book);
        return model;

    }

    @PostMapping(value = "/addBook", produces = "application/x-www-form-urlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addBook(@ModelAttribute("book") Book book, BindingResult result, HttpServletResponse response, HttpServletRequest request){
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


    @GetMapping("/viewBook/{id}")
    public ModelAndView viewBook(@PathVariable(value="id") int id){
        ModelAndView model = new ModelAndView("site.bookdetail");
        Book book = bookService.getBookById(id);
        model.addObject("book", book);
        return model;
    }


}
