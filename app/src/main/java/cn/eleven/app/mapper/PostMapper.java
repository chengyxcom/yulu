package cn.eleven.app.mapper;

import cn.eleven.app.entity.BigPost;
import cn.eleven.app.entity.Post;

import java.util.List;

public interface PostMapper {
    List<Post> showPostBySupport();

    void savePost(Post post);

    List<Post> getPostWords(String words);

    List<BigPost> showBigPostByTime();

    Integer getUidByPostId(Integer postId);

    List<Post> showPostByMyUid(Integer uid);

    Integer deletePost(Integer postId);

    List<BigPost> showBigPostBySupport();
}
