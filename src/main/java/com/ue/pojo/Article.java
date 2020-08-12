package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Table(name = "article")
public class Article {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "title")
    private String title;

    private String content;

    private Integer hot;

    private Integer sort;

    private String media;

    @Column(name = "is_pass")
    private Integer isPass;

    @Column(name = "unpass_reason")
    private String unPassReason;

    @Column(name = "create_time")
    private Date createTime;

    // 使用@Transient注解可以忽略字段,添加该注解的字段不会作为表字段使用.
    @Transient
    private Integer goodNum;

    @Transient
    private Integer badNum;

    @Transient
    private Integer collection;

    public String getCreateTime2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (createTime != null){
            return simpleDateFormat.format(createTime);
        }
        return "";
    }

}
