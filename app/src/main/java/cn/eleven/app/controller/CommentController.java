package cn.eleven.app.controller;

import cn.eleven.app.entity.Comment;
import cn.eleven.app.service.ICommentService;
import cn.eleven.app.service.IPostService;
import cn.eleven.app.service.IUserService;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @RequestMapping("saveComment")
    public JsonResult<String> saveComment(Integer commentUid, Integer postId, String comment, Integer mark) {
        Integer postUid = postService.getUidByPostId(postId);
        String commentUsername = userService.getUsernameByUid(commentUid);
        String postUsername = userService.getUsernameByUid(postUid);

        Comment comm = new Comment();
        comm.setPostId(postId);
        comm.setCommentUid(commentUid);
        comm.setCommentUsername(commentUsername);
        comm.setPostUid(postUid);
        comm.setPostUsername(postUsername);
        comm.setComment(comment);
        comm.setCommentTime(new Date());
        comm.setMark(mark);

        Integer result = commentService.saveComment(comm);
        if (result == 1) {
            return new JsonResult<>(SUCCESS, "评论成功");
        } else {
            return new JsonResult<>(SUCCESS, "未知错误");
        }
    }

    @RequestMapping("showComments")
    public JsonResult<List<Comment>> showComments(Integer postUid, Integer uid, Integer postId) {
        List<Comment> lists = commentService.showComments(postUid, uid, postId);
        return new JsonResult<>(SUCCESS, lists);
    }
}
