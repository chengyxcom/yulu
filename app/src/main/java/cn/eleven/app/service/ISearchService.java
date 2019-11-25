package cn.eleven.app.service;

import cn.eleven.app.entity.Post;

import java.util.List;

public interface ISearchService {
    List<Post> searchPost(String key);
}
