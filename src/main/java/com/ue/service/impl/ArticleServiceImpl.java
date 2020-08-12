package com.ue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.collections.MappingChange;
import com.ue.dao.ArticleMapper;
import com.ue.pojo.Article;
import com.ue.service.ArticleService;
import com.ue.service.SymbolService;
import com.ue.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private SymbolService symbolService;

    @Override
    public void saveArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public List<Article> getMyArticle(Integer id) {
        Condition condition = new Condition(Article.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId", id);
        return articleMapper.selectByExample(condition);
    }

    @Override
    public void deleteArticle(Integer articleId) {
        articleMapper.deleteByPrimaryKey(articleId);
    }

    /**
     * 分页查询
     *
     * @param id     用户id
     * @param key    关键字查询
     * @param page   第几页
     * @param size   每页数量
     * @return
     */
    @Override
    public PageResult<Article> queryArticleByPage(Integer id, String key, int page, int size, String order, Integer sort, Integer ispass) {
        //分页
        PageHelper.startPage(page, size);
        //过滤
        Example example = new Example(Article.class);

        System.out.println("sort>>>" + sort + "ispass>>>" + ispass);

        if (id >= 0) {
            example.createCriteria().andEqualTo("userId", id);
        }
        if (sort >= 0) {
            example.createCriteria().andEqualTo("sort", sort);
        }

        example.createCriteria().andEqualTo("isPass", ispass);

        if (!"".equals(key) || key != null) {
            //过滤条件
            example.createCriteria().andLike("title", "%" + key.toLowerCase() + "%");
        }

        //排列
        example.setOrderByClause(order + " DESC");

        //查询
        List<Article> articles = articleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(articles)) {
            return null;
        }

        for (Article article : articles) {
            article.setBadNum(symbolService.countSymbol(article.getId(), -1));
            article.setGoodNum(symbolService.countSymbol(article.getId(), 1));
            article.setCollection(symbolService.countSymbol(article.getId(), 0));
        }

        //解析分页结果
        PageInfo<Article> info = new PageInfo<>(articles);
        Integer total = (int) info.getTotal();
        Integer pageCount = total % size == 0 ? total / size: total / size + 1;
        System.out.println("count>>>" + pageCount);
        return new PageResult<>(page, total, articles, pageCount);
    }

    @Override
    public PageResult<Article> queryByUserIdAndSign(Integer userId, Integer sign, int page, int size) {
        Integer first = (page - 1) * size;
        Integer maxResult = size;
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sign", sign);
        map.put("first", first);
        map.put("maxResult", maxResult);
        List<Article> articles = articleMapper.selectPageByUserIdAndSign(map);

        for (Article article : articles) {
            article.setBadNum(symbolService.countSymbol(article.getId(), -1));
            article.setGoodNum(symbolService.countSymbol(article.getId(), 1));
            article.setCollection(symbolService.countSymbol(article.getId(), 0));
        }

        Integer total = countByUserIdAndSign(userId, sign);
        Integer pageCount = total % size == 0 ? total / size: total / size + 1;
        System.out.println("count>>>" + pageCount);
        PageResult<Article> pageResult = new PageResult<>(page, total, articles, pageCount);
        return pageResult;
    }

    @Override
    public Integer countByUserIdAndSign(Integer userId, Integer sign) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sign", sign);
        return articleMapper.countByUserIdAndSign(map);
    }

    @Override
    public Article queryArticleById(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public void editArticleHot(Article article) {
        articleMapper.updateByPrimaryKey(article);
    }
}
