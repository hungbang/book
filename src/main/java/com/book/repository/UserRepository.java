package com.book.repository;

import com.book.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hung on 4/23/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
