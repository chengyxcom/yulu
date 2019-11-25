package cn.eleven.app.controller;

import cn.eleven.app.entity.Post;
import cn.eleven.app.service.ISearchService;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController extends BaseController {
    @Autowired
    private ISearchService searchService;

    @RequestMapping("searchPost")
    public JsonResult<List<Post>> searchPost(String key) {
        List<Post> lists = searchService.searchPost(key);
        return new JsonResult<>(SUCCESS, lists);
    }
}
