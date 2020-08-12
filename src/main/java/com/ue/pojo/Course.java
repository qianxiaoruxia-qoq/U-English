package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "course")
public class Course {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;

    private Integer flag;

    private String media;
}
