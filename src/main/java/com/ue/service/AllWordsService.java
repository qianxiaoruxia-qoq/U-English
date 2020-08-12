package com.ue.service;

import com.ue.pojo.AllWords;
import com.ue.pojo.Article;
import com.ue.util.PageResult;

import java.util.List;

public interface AllWordsService {
    List<AllWords> findWordsByBookId(Integer bookId, Integer start);

    List<AllWords> findWords();

    List<AllWords> findWordsByName(String wordEnglish);

    List<AllWords> findBookWordById(Integer bookId);

    List<AllWords> findNewWordByUserId(Integer userId);
}
