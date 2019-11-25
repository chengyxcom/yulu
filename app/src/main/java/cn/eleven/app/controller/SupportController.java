package cn.eleven.app.controller;

import cn.eleven.app.service.ISupportService;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("support")
public class SupportController extends BaseController {
    @Autowired
    private ISupportService supportService;

    @RequestMapping("doSupport")
    public JsonResult<Map<String, Integer>> doSupport(Integer postId, Integer uid) {
        supportService.doSupport(uid, postId, new Date());
        //更新点赞数
        Integer count = supportService.countSupport(postId);
        Integer result = supportService.findPostId(postId);
        if (result == null) {
            supportService.insertPostId(postId);
        } else {
            supportService.updateSupportCount(postId, count);
        }
        Integer counts = supportService.countSupport(postId);
        Map<String, Integer> map = new HashMap<>();
        map.put("count", counts);
        return new JsonResult<>(SUCCESS, map);
    }

    @RequestMapping("undoSupport")
    public JsonResult<Map<String, Integer>> undoSupport(Integer postId, Integer uid) {
        supportService.undoSupport(uid, postId);
        //更新点赞数
        Integer count = supportService.countSupport(postId);
        Integer result = supportService.findPostId(postId);
        if (result == null) {
            supportService.insertPostId(postId);
        } else {
            supportService.updateSupportCount(postId, count);
        }
        Integer counts = supportService.countSupport(postId);
        Map<String, Integer> map = new HashMap<>();
        map.put("count", counts);
        return new JsonResult<>(SUCCESS, map);
    }

    @RequestMapping("countSupport")
    public JsonResult<Map<String, Integer>> countSupport(Integer postId) {
        Integer count = supportService.countSupport(postId);
        Map<String, Integer> map = new HashMap<>();
        map.put("count", count);
        return new JsonResult<>(SUCCESS, map);
    }
}
