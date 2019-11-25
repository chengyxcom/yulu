package cn.eleven.app.mapper;

import cn.eleven.app.entity.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> showComments(Integer postUid, Integer uid, Integer postId);

    Integer saveComment(Comment comm);

    List<Comment> showAllComments(Integer postUid, Integer postId);
}
