package cn.eleven.app.mapper;

import cn.eleven.app.entity.Post;

import java.util.List;

public interface SearchMapper {
    List<Post> searchPost(String key);
}
