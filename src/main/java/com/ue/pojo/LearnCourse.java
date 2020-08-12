package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "learncourse")
public class LearnCourse {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime;
}
