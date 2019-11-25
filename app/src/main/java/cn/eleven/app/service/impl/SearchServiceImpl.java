package cn.eleven.app.service.impl;

import cn.eleven.app.entity.Post;
import cn.eleven.app.mapper.SearchMapper;
import cn.eleven.app.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService {
    @Autowired
    private SearchMapper searchMapper;

    @Override
    public List<Post> searchPost(String key) {
        return searchMapper.searchPost(key);
    }
}
