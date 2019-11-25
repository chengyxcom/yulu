package cn.eleven.app.service;

import cn.eleven.app.entity.Comment;

import java.util.List;

public interface ICommentService {
    //uid为评论人的id
    List<Comment> showComments(Integer postUid, Integer uid, Integer postId);

    Integer saveComment(Comment comm);
}
