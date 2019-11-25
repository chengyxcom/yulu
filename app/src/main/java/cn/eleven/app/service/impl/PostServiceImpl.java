package cn.eleven.app.service.impl;

import cn.eleven.app.entity.BigPost;
import cn.eleven.app.entity.Post;
import cn.eleven.app.mapper.PostMapper;
import cn.eleven.app.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> showPostBySupport() {
        return postMapper.showPostBySupport();
    }

    @Override
    public void savePost(Post post) {
        postMapper.savePost(post);
    }

    @Override
    public List<Post> showPostWords(String words) {
        return postMapper.getPostWords(words);
    }

    @Override
    public List<BigPost> showBigPostByTime() {
        return postMapper.showBigPostByTime();
    }

    @Override
    public Integer getUidByPostId(Integer postId) {
        return postMapper.getUidByPostId(postId);
    }

    @Override
    public List<Post> showPostByMyUid(Integer uid) {
        return postMapper.showPostByMyUid(uid);
    }

    @Override
    public Integer deletePost(Integer postId) {
        return postMapper.deletePost(postId);
    }

    @Override
    public List<BigPost> showBigPostBySupport() {
        return postMapper.showBigPostBySupport();
    }

}
