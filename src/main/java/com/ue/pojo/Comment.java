package com.ue.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Table(name = "comment")
public class Comment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "user_id")
    private Integer userId;

    // 昵称
    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_avatar")
    private String userAvatar;

    // 评论内容
    private String comment;

    @Column(name = "comment_time")
    private Date commentTime;

    public String getCommentTime2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (commentTime != null){
            return simpleDateFormat.format(commentTime);
        }
        return "";
    }
}
