package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "allwords")
public class AllWords {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "word_english")
    private String wordEnglish;

    @Column(name = "word_chinese")
    private String wordChinese;

    @Column(name = "word_img")
    private String wordImg;
}
