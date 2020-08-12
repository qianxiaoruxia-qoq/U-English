package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "learnbook")
public class LearnBook {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "user_id")
    private Integer userId;

    private Integer islearn;

    // 学习到的单词
    @Column(name = "word_id")
    private Integer wordId;

    @Column(name = "create_time")
    private Date createTime;
}
