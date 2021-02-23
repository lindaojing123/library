package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private Integer id;
    private String bookName;
    private Integer pageNum;
    private Integer wordNum;
    private Double price;
    private String src;
    private Double ddPrice;
    private Integer stock;
    private String author;
    private String editorRecommend;
    private String company;
    private String publishTime;
    private String contentAbstract;
    private Integer edition;
    private String printTime;
    private String authorAbstract;
    private String printCount;
    private String isbn;
    private String director;
    private String sizes;
    private String paper;
    private String mediaCommentary;
    private String pack;
    private Integer saleCount;
    private String cateId;

}
