package com.book.vo;

import com.book.model.Book;
import java.util.List;

/**
 * Created by Hung on 4/24/2017.
 */
public class BookJsonObject {
    private int iTotalRecords;

    private int iTotalDisplayRecords;

    private String sEcho;

    private String sColumns;

    private List<Book> aaData;


    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    public List<Book> getAaData() {
        return aaData;
    }

    public void setAaData(List<Book> aaData) {
        this.aaData = aaData;
    }
}
