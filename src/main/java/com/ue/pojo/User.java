package com.ue.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Table
@EqualsAndHashCode
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String username;

    private String password;

    // 昵称
    private String nickname;

    private String sex;

    // 前台向后台传日期的接收模型
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    // 简介
    private String introduction;

    // 头像
    private String avatar;

    public String getBirthday2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (birthday != null){
            return simpleDateFormat.format(birthday);
        }
        return "";
    }

}
