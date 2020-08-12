package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "symbol")
public class Symbol {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "user_id")
    private Integer userId;

    // 标志：当值是-1时表示点bad，当值是1时表示点good，当值是0时表示收藏
    private Integer sign;
}
