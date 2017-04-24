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

    private static final Integer PAGE_SIZE = 10;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> bookList(Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, PAGE_SIZE);
        return bookRepository.findAll(pageRequest);
    }

}
