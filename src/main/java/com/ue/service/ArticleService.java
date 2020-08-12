package com.ue.service;

import com.ue.pojo.Article;
import com.ue.util.PageResult;

import java.util.List;

public interface ArticleService {
    void saveArticle(Article article);

    List<Article> getMyArticle(Integer id);

    void deleteArticle(Integer articleId);

    PageResult<Article> queryArticleByPage(Integer id, String key, int page, int size, String order, Integer sort, Integer ispass);

    PageResult<Article> queryByUserIdAndSign(Integer userId, Integer sign, int page, int size);

    Integer countByUserIdAndSign(Integer userId, Integer sign);

    Article queryArticleById(Integer articleId);

    void editArticleHot(Article article);
}
