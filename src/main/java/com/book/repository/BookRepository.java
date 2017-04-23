package com.book.repository;

import com.book.model.Book;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Hung on 4/23/2017.
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    Book findById(int id);

}
