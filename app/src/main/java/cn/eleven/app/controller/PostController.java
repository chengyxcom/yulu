package cn.eleven.app.controller;

import cn.eleven.app.entity.BigPost;
import cn.eleven.app.entity.Post;
import cn.eleven.app.service.IPostService;
import cn.eleven.app.service.ex.FileSizeException;
import cn.eleven.app.util.JsonResult;
import cn.eleven.app.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("post")
public class PostController extends BaseController {
    @Autowired
    private IPostService postService;

    @RequestMapping("showPostWords")
    public JsonResult<List<Post>> showPostWords(String words) {
        List<Post> lists = postService.showPostWords(words);
        return new JsonResult<>(SUCCESS, lists);
    }

    @RequestMapping("showBigPostByTime")
    public JsonResult<List<BigPost>> showBigPostByTime() {
        List<BigPost> lists = postService.showBigPostByTime();
        return new JsonResult<>(SUCCESS, lists);
    }

    @RequestMapping("showPostBySupport")
    public JsonResult<List<Post>> showPostBySupport() {
        List<Post> lists = postService.showPostBySupport();
        return new JsonResult<>(SUCCESS, lists);
    }

    @RequestMapping("showBigPostBySupport")
    public JsonResult<List<BigPost>> showBigPostBySupport() {
        List<BigPost> lists = postService.showBigPostBySupport();
        return new JsonResult<>(SUCCESS, lists);
    }

    @RequestMapping("showPostByMyUid")
    public JsonResult<List<Post>> showPostByMyUid(Integer uid) {
        List<Post> lists = postService.showPostByMyUid(uid);
        return new JsonResult<>(SUCCESS, lists);
    }

    @RequestMapping("savePost")
    public JsonResult<Void> savePost(Post post, @RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request) {
        //保存图片
        if (!file.isEmpty()) {
            String filename = saveImg(file, "static/postImg/");
            String postImg = "/postImg/" + filename;
            post.setPostImg(postImg);
        }
        post.setPostTime(new Date());
        postService.savePost(post);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("deletePost")
    public JsonResult<String> deletePost(Integer postId) {
        Integer result = postService.deletePost(postId);
        if (result == 1) {
            return new JsonResult<>(SUCCESS, "删除成功");
        } else {
            return new JsonResult<>(SUCCESS, "未知错误");
        }
    }
}
