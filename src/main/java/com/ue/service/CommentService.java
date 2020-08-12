package com.ue.service;

import com.ue.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByArticleId(Integer articleId);

    void saveComment(Comment comment);

    void editUserAvatar(Integer userId, String avatarPic);
}
