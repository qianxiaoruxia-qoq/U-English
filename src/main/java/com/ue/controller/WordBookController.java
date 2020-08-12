package com.ue.controller;

import com.ue.pojo.AllWords;
import com.ue.pojo.LearnBook;
import com.ue.pojo.NewWord;
import com.ue.pojo.User;
import com.ue.service.AllWordsService;
import com.ue.service.LearnBookService;
import com.ue.service.NewWordService;
import com.ue.util.LoginUser;
import com.ue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("wordBook")
public class WordBookController {

    @Autowired
    private LearnBookService learnBookService;

    @Autowired
    private AllWordsService allWordsService;

    @Autowired
    private NewWordService newWordService;

    /**
     * 添加单词书
     * @param request
     * @param wordBookId
     * @return
     */
    @RequestMapping("addlearnWordBook")
    @ResponseBody
    public Result addlearnWordBook(HttpServletRequest request, Integer wordBookId) {
        User loginUser = LoginUser.getLoginUser(request);
        List<LearnBook> learnBooks = learnBookService.findByUserIdAndBookId(loginUser.getId(), wordBookId);
        if (learnBooks.size() == 0) {
            LearnBook learnBook = new LearnBook();
            learnBook.setBookId(wordBookId);
            learnBook.setUserId(loginUser.getId());
            learnBook.setWordId(0);
            learnBook.setIslearn(0);
            learnBook.setCreateTime(new Date());
            learnBookService.saveLearnBook(learnBook);
        } else {
            LearnBook learnBook = learnBooks.get(0);
            learnBook.setIslearn(0);
            learnBook.setCreateTime(new Date());
            learnBookService.updateLearnBook(learnBook);
        }

        return Result.ok();
    }

    @RequestMapping("addNewWord")
    @ResponseBody
    public Result addNewWord(HttpServletRequest request, Integer wordId) {
        User loginUser = LoginUser.getLoginUser(request);
        if (loginUser == null) {
            return Result.error("请先登录");
        }
        NewWord newWord = new NewWord();
        newWord.setUserId(loginUser.getId());
        newWord.setWordId(wordId);
        newWordService.addNewWord(newWord);
        return Result.ok();
    }

    @RequestMapping("getNewWordList")
    public String getNewWordList(HttpServletRequest request) {
        User loginUser = LoginUser.getLoginUser(request);
        List<AllWords> newWords = allWordsService.findNewWordByUserId(loginUser.getId());
        request.setAttribute("newWords", newWords);
        return "newWordList";
    }
}
