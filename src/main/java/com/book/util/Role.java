package com.book.util;

/**
 * Created by Hung on 4/23/2017.
 */
public enum Role {
    ROLE_ADMIN(1),
    ROLE_USER(2);
    private int statusCode;
    private Role(int i) {
        statusCode = i;
    }

    private int getStatusCode(){
        return statusCode;
    }
}
