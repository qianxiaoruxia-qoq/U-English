package com.ue.controller;

import com.ue.pojo.*;
import com.ue.service.*;
import com.ue.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("perCenter")
public class PerCenterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SymbolService symbolService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("perCenter")
    public String perCenter(HttpServletRequest request) {
        MenuUtil.getTopMenus(request);
        User user = LoginUser.getLoginUser(request);
        request.setAttribute("user", user);
        return "perCenter";
    }

    /**
     * 修改用户信息
     * @param request
     * @return
     */
    @RequestMapping("editUserInfo")
    public String editUserInfo(HttpServletRequest request) {
        User user = LoginUser.getLoginUser(request);
        request.setAttribute("user", user);
        return "userInfoEdit";
    }

    /**
     * 修改用户信息具体代码
     * @param request
     * @param user 前端传进来的用户对象
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public Result updateUserInfo(HttpServletRequest request, User user) {
        if (user == null) {
            return Result.error("出现错误！");
        }

        if (LoginUser.getLoginUser(request).equals(user)) {
            return Result.ok();
        }

        User user1 = LoginUser.getLoginUser(request);

        user.setAvatar(user1.getAvatar());

        userService.editUser(user);

        LoginUser.setLoginUser(request, user);

        return Result.ok();
    }

    /**
     * 更改头像
     * @return
     */
    @RequestMapping("changeAvatar")
    public String changeAvatar() {
        return "avatarChange";
    }

    /**
     * 更改头像具体代码
     * @param request
     * @param x  表示所截图的左上角所对应的点的x轴数值
     * @param y  表示所截图的左上角所对应的点的y轴数值
     * @param finalWidth  所截图像的宽度
     * @param finalHeight  所截图像的高度
     * @param pic  原图像的base64数据
     * @return
     */
    @RequestMapping(value = "updateAvatar")
    @ResponseBody
    public Result updateAvatar(HttpServletRequest request, Integer x, Integer y, Integer finalWidth, Integer finalHeight, String pic) {
        int index = pic.indexOf("base64,");
        String trueData = pic.substring(index + 7);
        // base64解码
        byte[] decode = Base64.getDecoder().decode(trueData);
        try {
            String avatarPic = "/libs/img/" + cutAndSaveAvatar(decode, request, x, y, finalWidth, finalHeight);
            User loginUser = LoginUser.getLoginUser(request);
            loginUser.setAvatar(avatarPic);
            userService.editUser(loginUser);
            commentService.editUserAvatar(loginUser.getId(), avatarPic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    /**
     * 上传文章
     * @return
     */
    @RequestMapping("postArticle")
    public String postArticle() {
        return "articlePost";
    }

    /**
     * 处理放入到富文本编辑器里的图片
     * 当有图片放到富文本编辑器中时，会将文件保存到服务器中，并返回相应格式的数据格式
     * @param file   放到富文本编辑器中的图片
     * @param request
     * @return 返回ImageResult,如果errno为0时成功，为1时失败。data为图片的地址
     * @throws Exception
     */
    @PostMapping("upload")
    @ResponseBody
    public ImageResult upload(@RequestParam("imgFile") MultipartFile file, HttpServletRequest request) throws Exception {

        ImageResult imageResult = new ImageResult();
        if (null == file || file.isEmpty()) {
            imageResult.setErrno(1);
            return imageResult;
        }

        // 生成一个新文件名
        String newFileName = UUID.randomUUID().toString() + "." + getExtName(file.getOriginalFilename());

        String uploadDir = request.getServletContext().getRealPath("/libs/img");
        BufferedImage bi= ImageIO.read(file.getInputStream());
        ImageIO.write(bi,"jpg", new File(uploadDir, newFileName));

        // 封装成 wangEditor要求返回的数据格式
        imageResult.setErrno(0);

        imageResult.getData().add(request.getContextPath() + "/libs/img/" + newFileName);

        //
        return imageResult;
    }

    // 得到图片的扩展名
    private String getExtName(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        return originalFilename.substring(index + 1);
    }

    /**
     * 保存文章
     * @param request
     * @param title 文章标题
     * @param article 文章内容
     * @return
     */
    @RequestMapping(value = "saveArticle")
    @ResponseBody
    public Result saveArticle(HttpServletRequest request, String title, String article) {
        if (title == null || "".equals(title.trim())) {
            return Result.error("请先起个名字吧！");
        }
        if (article == null || "".equals(article.trim())) {
            return Result.error("你还什么都没写啊！");
        }
        User loginUser = LoginUser.getLoginUser(request);

        Article art = new Article();
        art.setUserId(loginUser.getId());
        art.setTitle(title);
        art.setContent(article);
        art.setCreateTime(new Date());
        art.setHot(0);
        art.setSort(1);
        articleService.saveArticle(art);

        return Result.ok();
     }

    /**
     * 我的发布
     * @param request
     * @param key  查询条件
     * @param currentPage  当前页
     * @param size  当前页大小
     * @param userId  用户id
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

    @RequestMapping("myCollection")
    public String myCollection(HttpServletRequest request,
                            @RequestParam(value = "sign", defaultValue = "0") Integer sign,
                            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                            @RequestParam(value = "page", defaultValue = "5") Integer size,
                            @RequestParam(value = "userId", defaultValue = "-1") Integer userId
    ) {
        PageResult<Article> pageResult = articleService.queryByUserIdAndSign(userId, sign, currentPage, size);
        request.setAttribute("pageResult", pageResult);
        return "collectionView";
    }

    @RequestMapping("myCourse")
    public String myCourse(HttpServletRequest request, Integer userId) {
        List<Course> myCourses = courseService.findMyCourseByUserId(userId);
        request.setAttribute("courses", myCourses);
        return "courseList";
    }

    /**
     * 删除文章
     * @param request
     * @param articleId
     * @return
     */
    @RequestMapping("delArticle")
    @ResponseBody
    public Result delArticle(HttpServletRequest request, Integer articleId) {
        if (articleId == null || articleId < 0) {
            return Result.error("系统出错！");
        }
        articleService.deleteArticle(articleId);
        return Result.ok();
    }

    @RequestMapping("delCollection")
    @ResponseBody
    public Result delCollection(HttpServletRequest request, Integer articleId) {
        if (articleId == null || articleId < 0) {
            return Result.error("系统出错！");
        }
        articleService.deleteArticle(articleId);
        return Result.ok();
    }

    /**
     * 保存评论
     * @param request
     * @param comment  评论内容
     * @param articleId  文章id
     * @return
     */
    @RequestMapping("saveComment")
    @ResponseBody
    public Result saveComment(HttpServletRequest request, String comment, Integer articleId) {
        if ("".equals(comment) || comment == null) {
            return Result.error("你什么都没写啊！");
        }
        User loginUser = LoginUser.getLoginUser(request);
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setArticleId(articleId);
        comment1.setUserId(loginUser.getId());
        comment1.setUserNickname(loginUser.getNickname());
        comment1.setUserAvatar(loginUser.getAvatar());
        comment1.setCommentTime(new Date());
        commentService.saveComment(comment1);
        return Result.ok(comment1);
    }

    /**
     * 点赞、点倒赞、收藏
     * @param request
     * @param articleId  文章id
     * @param sign  标志：当值是-1时表示点bad，当值是1时表示点good，当值是0时表示收藏
     * @return
     */
    @RequestMapping("symbol")
    @ResponseBody
    public Result symbol(HttpServletRequest request, Integer articleId, Integer sign) {
        User loginUser = LoginUser.getLoginUser(request);

        Symbol symbol = new Symbol();
        symbol.setUserId(loginUser.getId());
        symbol.setArticleId(articleId);
        symbol.setSign(sign);

        Symbol findSymbol = symbolService.findSymbol(loginUser.getId(), articleId, sign);
        if (findSymbol != null) {
            symbolService.removeSymbol(findSymbol.getId());
            return Result.ok(0);
        }

        // 当sign为-1时为点倒赞
        if (sign == -1) {
            Symbol goodSymbol = symbolService.findSymbol(loginUser.getId(), articleId, 1);
            if (goodSymbol == null) {
                symbolService.saveSymbol(symbol);
            } else {
                symbolService.removeSymbol(goodSymbol.getId());
                symbolService.saveSymbol(symbol);
            }
            return Result.ok();
        }

        // 当sign为1时为点赞
        if (sign == 1) {
            Symbol badSymbol = symbolService.findSymbol(loginUser.getId(), articleId, -1);
            if (badSymbol == null) {
                symbolService.saveSymbol(symbol);
            } else {
                symbolService.removeSymbol(badSymbol.getId());
                symbolService.saveSymbol(symbol);
            }
            return Result.ok();
        }

        symbolService.saveSymbol(symbol);

        return Result.ok();
    }

     /**
     * 修改密码
     * @return
     */
    @RequestMapping("changePass")
    public String changePass() {
        return "passChange";
    }

    /**
     * 修改密码具体代码
     * @param request
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return
     */
    @RequestMapping(value = "savePass")
    @ResponseBody
    public Result savePass(HttpServletRequest request, String oldPass, String newPass) {
        if (oldPass == null || "".equals(oldPass.trim())) {
            return Result.error("请输入旧密码!");
        }
        if (newPass == null || "".equals(oldPass.trim())) {
            return Result.error("请输入新密码!");
        }
        User loginUser = LoginUser.getLoginUser(request);

        oldPass = DigestUtils.md5DigestAsHex(oldPass.getBytes());

        if (!loginUser.getPassword().equals(oldPass)) {
            return Result.error("旧密码错误");
        }

        newPass = DigestUtils.md5DigestAsHex(newPass.getBytes());

        loginUser.setPassword(newPass);
        userService.editUser(loginUser);
        return Result.ok();
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "loginOut")
    @ResponseBody
    public Result loginOut(HttpServletRequest request) {
        LoginUser.loginOut(request);
        return Result.ok();
    }

    /**
     * 截取图片并保存到文件夹中
     * @param bytes 原图片的字节数组
     * @param request
     * @param x 截取部分左上角那个点的x轴坐标
     * @param y  截取部分左上角那个点的y轴坐标
     * @param width  图片的宽度
     * @param height  图片的高
     * @return  返回图片名称
     * @throws IOException
     */
    private String cutAndSaveAvatar(byte[] bytes, HttpServletRequest request, Integer x, Integer y, Integer width, Integer height) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage bi= ImageIO.read(inputStream);
        BufferedImage subImage = bi.getSubimage(x, y, width, height);

        // 保存的目录
        String uploadDir = request.getServletContext().getRealPath("/libs/img");
        // 使用UUID做图片的名称
        String newFileName = UUID.randomUUID().toString() + ".jpg";
        // 输出为文件
        // 保存新图片
        ImageIO.write(subImage,"jpg", new File(uploadDir, newFileName));
        return newFileName;
    }
}
