package cn.eleven.app.service.impl;

import cn.eleven.app.entity.Comment;
import cn.eleven.app.mapper.CommentMapper;
import cn.eleven.app.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> showComments(Integer postUid, Integer uid, Integer postId) {
        if (postUid.equals(uid)) {
            return commentMapper.showAllComments(postUid, postId);
        } else {
            return commentMapper.showComments(postUid, uid, postId);
        }
    }

    @Override
    public Integer saveComment(Comment comm) {
        return commentMapper.saveComment(comm);
    }
}
