package com.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    private Integer id;
    private String name;
    private int levels;
    private Category categoryParent;
    private List<Category> categories;
}
