package com.book.service;

import com.book.repository.CustomRepository;
import com.book.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hung on 4/23/2017.
 */
@Service
public class UserService {

    @Autowired
    private CustomRepository customRepository;

    public Map<String, Object> getUserByUsername(String username) {
        Map<String, Object> userMap = null;
        List<UserVO> userVOs = customRepository.getUserVOByUserName(username);
        if (!userVOs.isEmpty()) {
            userMap = new HashMap<>();
            for (UserVO u : userVOs) {
                userMap.put("username", u.getUsername());
                userMap.put("password", u.getPassword());
                userMap.put("role", u.getRoleType());
            }
        }
        return userMap;
    }
}
