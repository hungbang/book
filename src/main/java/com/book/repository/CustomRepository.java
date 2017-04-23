package com.book.repository;

import com.book.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hung on 4/23/2017.
 */
public interface CustomRepository {
    List<UserVO> getUserVOByUserName(String username);
}
