package com.ue.controller;

import com.ue.pojo.*;
import com.ue.service.*;
import com.ue.util.LoginUser;
import com.ue.util.MenuUtil;
import com.ue.util.PageResult;
import com.ue.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SymbolService symbolService;

    @Autowired
    private LearnBookService learnBookService;

    @Autowired
    private AllWordsService allWordsService;

    @Autowired
    private WordBookService wordBookService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LearnCourseService learnCourseService;

    /**
     * 我的发布
     * @return
     */
    @RequestMapping("myRelease")
    public String myRelease(HttpServletRequest request,
                            @RequestParam(value = "key", defaultValue = "") String key,
                            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                            @RequestParam(value = "page", defaultValue = "5") Integer size,
                            @RequestParam(value = "userId", defaultValue = "-1") Integer userId,
                            @RequestParam(value = "order", defaultValue = "create_time") String order,
                            @RequestParam(value = "sort", defaultValue = "-1") Integer sort,
                            @RequestParam(value = "ispass", defaultValue = "1") Integer ispass
                            ) {
        PageResult<Article> pageResult = articleService.queryArticleByPage(userId, key, currentPage, size, order, sort, ispass);
        request.setAttribute("pageResult", pageResult);
        return "myRelease";
    }

    /**
     * 显示文章界面
     * @param request
     * @param articleId
     * @return
     */
    @RequestMapping("showArticle")
    public String showArticle(HttpServletRequest request, Integer articleId) {
        Article article = articleService.queryArticleById(articleId);
        request.setAttribute("article", article);
        User user = LoginUser.getLoginUser(request);
        request.setAttribute("user", user);
        MenuUtil.getTopMenus(request);
        return "articleView";
    }

    /**
     * 得到评论信息
     * @param request
     * @param articleId
     * @return
     */
    @RequestMapping("getComment")
    public String getComment(HttpServletRequest request, Integer articleId) {
        List<Comment> comments = commentService.getCommentByArticleId(articleId);
        request.setAttribute("comments", comments);
        return "commentView";
    }

    /**
     * 得到文章标题下的介绍信息
     * @param request
     * @param articleId
     * @return
     */
    @RequestMapping("getWriter")
    public String getWriter(HttpServletRequest request, Integer articleId) {
        Article article = articleService.queryArticleById(articleId);
        User articleUser = userService.queryUserById(article.getUserId());
        // 点赞数
        Integer good = symbolService.countSymbol(articleId, 1);
        // 点倒赞数
        Integer bad = symbolService.countSymbol(articleId, -1);
        // 收藏数
        Integer collection = symbolService.countSymbol(articleId, 0);

        User loginUser = LoginUser.getLoginUser(request);

        Symbol myGood = null;
        Symbol myBad = null;
        Symbol myCollection = null;
        if (loginUser != null) {
            // 是否点赞
            myGood = symbolService.findSymbol(loginUser.getId(), articleId, 1);
            myBad = symbolService.findSymbol(loginUser.getId(), articleId, -1);
            myCollection = symbolService.findSymbol(loginUser.getId(), articleId, 0);
        }

        request.setAttribute("article", article);
        request.setAttribute("articleUser", articleUser);
        request.setAttribute("good", good);
        request.setAttribute("bad", bad);
        request.setAttribute("collection", collection);
        request.setAttribute("myGood", myGood == null ? "0": "1");
        request.setAttribute("myBad", myBad == null ? "0": "1");
        request.setAttribute("myCollection", myCollection == null ? "0": "1");
        return "writerView";
    }

    /**
     * 列表
     * @param request
     * @param key  查询条件
     * @param currentPage  当前页
     * @param size  每页数量
     * @param userId  用户id
     * @param order  排序方法
     * @return
     */
    @RequestMapping("articleList")
    public String articleList(HttpServletRequest request,
                            @RequestParam(value = "key", defaultValue = "") String key,
                            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                            @RequestParam(value = "page", defaultValue = "5") Integer size,
                            @RequestParam(value = "userId", defaultValue = "-1") Integer userId,
                            @RequestParam(value = "order", defaultValue = "create_time") String order,
                            @RequestParam(value = "sort", defaultValue = "-1") Integer sort,
                            @RequestParam(value = "ispass", defaultValue = "1") Integer ispass
    ) {
        System.out.println("sort>>>" + sort);
        PageResult<Article> pageResult = articleService.queryArticleByPage(userId, key, currentPage, size, order, sort, ispass);
        request.setAttribute("pageResult", pageResult);
        return "articleList";
    }

    /**
     * 列表
     * @param request
     * @param key  查询条件
     * @param currentPage  当前页
     * @param size  每页数量
     * @param userId  用户id
     * @param order  排序方法
     * @return
     */
    @RequestMapping("toMore")
    public String toMore(HttpServletRequest request,
                              @RequestParam(value = "key", defaultValue = "") String key,
                              @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                              @RequestParam(value = "page", defaultValue = "5") Integer size,
                              @RequestParam(value = "userId", defaultValue = "-1") Integer userId,
                              @RequestParam(value = "order", defaultValue = "create_time") String order,
                              @RequestParam(value = "sort", defaultValue = "-1") Integer sort,
                              @RequestParam(value = "ispass", defaultValue = "1") Integer ispass
    ) {
        PageResult<Article> pageResult = articleService.queryArticleByPage(userId, key, currentPage, size, order, sort, ispass);
        if (pageResult == null) {
            pageResult = new PageResult<>();
        }
        request.setAttribute("pageResult", pageResult);
        request.setAttribute("sort", sort);
        return "mainList";
    }

    /**
     * 当浏览页面时，hot会自动加1
     * @param request
     * @param articleId
     * @return
     */
    @RequestMapping("addHot")
    @ResponseBody
    public Result addHot(HttpServletRequest request, Integer articleId) {
        Article article = articleService.queryArticleById(articleId);
        article.setHot(article.getHot() + 1);
        articleService.editArticleHot(article);
        return Result.ok();
    }

    /**
     * 加载媒体到前端
     * @param request
     * @param media
     * @return
     */
    @RequestMapping("getMedia")
    public String getMedia(HttpServletRequest request, String media, Integer sort) {
        request.setAttribute("media", media);
        if (sort == 2 || sort == 3) {
            return "listenMedia";
        }
        return "movieMedia";
    }

    /**
     * 获取学习的单词
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping("getWord")
    public String getWord(HttpServletRequest request, Integer userId) {
        System.out.println("userId>>>" + userId);
        List<AllWords> words = null;
        if (userId == null || "".equals(userId)) {
            words = allWordsService.findWords();
        } else {
            List<LearnBook> islearn = learnBookService.findByUserId(userId, 0);
            if (islearn.size() != 0) {
                words = allWordsService.findWordsByBookId(islearn.get(0).getBookId(), islearn.get(0).getWordId());
            } else {
                words = allWordsService.findWords();
            }
        }
        request.setAttribute("words", words);
        return "showWord";
    }

    @RequestMapping("getWordBookList")
    public String getWordBookList(HttpServletRequest request, Integer userId, Integer islearn) {
        List<WordBook> wordBooks = null;
        if (islearn >= 0) {
            wordBooks = wordBookService.findLearnWordBooks(userId, islearn);
        } else {
            wordBooks = wordBookService.findNotLearnWordBooks(userId);
        }
        request.setAttribute("wordBooks", wordBooks);
        return "wordBookList";
    }

    @RequestMapping("getCourseList")
    public String getCourseList(HttpServletRequest request, Integer flag) {
        List<Course> courses = courseService.findByFlag(flag);
        request.setAttribute("courses", courses);
        return "courseList";
    }

    @RequestMapping("showCourse")
    public String showCourse(HttpServletRequest request, Integer courseId) {
        User user = LoginUser.getLoginUser(request);
        request.setAttribute("user", user);
        MenuUtil.getTopMenus(request);

        Course course = courseService.findById(courseId);
        request.setAttribute("course", course);
        return "showCourse";
    }

    @RequestMapping("articleExam")
    public String articleExam(HttpServletRequest request, Integer articleId) {
        request.setAttribute("articleId", articleId);
        return "articleExam";
    }

    @RequestMapping("addLearnCourse")
    @ResponseBody
    public Result addLearnCourse(HttpServletRequest request, Integer courseId) {
        User loginUser = LoginUser.getLoginUser(request);
        if (loginUser == null) {
            return Result.ok();
        }

        LearnCourse learnCourse1 = learnCourseService.findByUserIdAndCourseId(loginUser.getId(), courseId);
        if (learnCourse1 == null) {
            LearnCourse learnCourse = new LearnCourse();
            learnCourse.setCourseId(courseId);
            learnCourse.setUserId(loginUser.getId());
            learnCourse.setCreateTime(new Date());
            learnCourseService.saveLearnCourse(learnCourse);
        }
        return Result.ok();
    }

    /**
     * 保存审核结果
     * @param articleId
     * @param ispass
     * @param reason
     * @return
     */
    @RequestMapping("saveExam")
    @ResponseBody
    public Result saveExam(Integer articleId, Integer ispass, String reason) {
        System.out.println("ispass>>" + ispass + ",reason>>" + reason);
        Article article = articleService.queryArticleById(articleId);
        article.setIsPass(ispass);
        if (ispass == -1) {
            article.setUnPassReason(reason);
        }
        articleService.editArticleHot(article);
        return Result.ok();
    }

    @RequestMapping("selectList")
    public String selectList(HttpServletRequest request, String selectText) {
        request.setAttribute("selectText", selectText);
        return "selectList";
    }

    @RequestMapping("wordView")
    public String wordView(HttpServletRequest request, String selectText) {
        List<AllWords> allWords = allWordsService.findWordsByName(selectText);
        request.setAttribute("words", allWords);
        return "showWord";
    }
}
