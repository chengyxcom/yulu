package cn.eleven.app.service;

import cn.eleven.app.entity.BigPost;
import cn.eleven.app.entity.Post;

import java.util.List;

public interface IPostService {
    List<Post> showPostBySupport();

    void savePost(Post post);

    List<Post> showPostWords(String words);

    List<BigPost> showBigPostByTime();

    Integer getUidByPostId(Integer postId);

    List<Post> showPostByMyUid(Integer uid);

    Integer deletePost(Integer postId);

    List<BigPost> showBigPostBySupport();
}
