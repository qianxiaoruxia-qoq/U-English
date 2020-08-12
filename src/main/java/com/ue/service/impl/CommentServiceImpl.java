package com.ue.service.impl;

import com.ue.dao.CommentMapper;
import com.ue.pojo.Comment;
import com.ue.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentByArticleId(Integer articleId) {
        Condition condition = new Condition(Comment.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        condition.setOrderByClause("comment_time DESC");
        return commentMapper.selectByExample(condition);
    }

    @Override
    public void saveComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public void editUserAvatar(Integer userId, String avatarPic) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("avatarPic", avatarPic);
        commentMapper.updateUserAvatar(map);
    }
}
