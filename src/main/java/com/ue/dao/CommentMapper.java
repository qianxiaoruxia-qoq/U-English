package com.ue.dao;

import com.ue.pojo.Comment;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface CommentMapper extends Mapper<Comment> {
    void updateUserAvatar(Map<String, Object> map);
}
