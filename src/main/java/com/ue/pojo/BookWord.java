package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "bookword")
public class BookWord {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "word_id")
    private Integer wordId;

    private Integer islast;
}
