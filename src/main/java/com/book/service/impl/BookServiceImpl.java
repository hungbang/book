package com.book.service.impl;

import com.book.repository.BookRepository;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import com.book.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hung on 4/23/2017.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> bookList(Integer pageNumber, Integer pageDisplayLength) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageDisplayLength);
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void removeBook(int id) {
        bookRepository.delete(id);
    }

}
