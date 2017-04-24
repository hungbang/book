package com.book.service;

import java.util.List;
import com.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

/**
 * Created by Hung on 4/23/2017.
 */

public interface BookService {
    Page<Book> bookList(Integer pageNumber, Integer pageDisplayLength);

    Book getBookById(int id);

    Book update(Book book);

    void removeBook(int id);

}
