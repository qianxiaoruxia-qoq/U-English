package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "wordbook")
public class WordBook {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;

    @Column(name = "book_img")
    private String bookImg;
}
