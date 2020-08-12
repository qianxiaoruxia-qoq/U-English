package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "newword")
public class NewWord {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "word_id")
    private Integer wordId;
}
