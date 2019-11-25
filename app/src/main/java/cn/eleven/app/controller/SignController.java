package cn.eleven.app.controller;

import cn.eleven.app.entity.SignRecommend;
import cn.eleven.app.entity.SignType;
import cn.eleven.app.service.ISignRecommendService;
import cn.eleven.app.service.ISignService;
import cn.eleven.app.service.ISignTypeService;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sign")
public class SignController extends BaseController {

    @Autowired
    private ISignService signService;

    @Autowired
    private ISignTypeService signTypeService;

    @Autowired
    private ISignRecommendService signRecommendService;


    @RequestMapping("getSign")
    public JsonResult<Map<String, Object>> getSign(Integer id, String signType) {
        System.out.println("uid=" + id + ",signType=" + signType);
        Integer random = signService.Sign(id, signType);
//        SignType signType = signTypeService.getBySingTypeId(signId);
        SignType signType1 = signTypeService.getBySingTypeId(random);
        Integer count = signService.getCountSign(id);
        Map<String, Object> map = new HashMap<>();
        if (random == 0) {
            map.put("img", "/sign/yiqiandao.png");
        } else {
            map.put("signType", signType1);
        }

        map.put("count", count);
        return new JsonResult<>(SUCCESS, map);
    }

    @RequestMapping("getSignType")
    public JsonResult<Map<String, Object>> getSignType(String signType) {
        System.out.println(signType);
        SignType signType1 = signTypeService.getBySignType(signType);
//        Integer count = signService.getCountSign(id);
        Map<String, Object> map = new HashMap<>();
        map.put("sign", signType1);
//        map.put("count",count);
        return new JsonResult<>(SUCCESS, map);
    }


    @RequestMapping("getAll")
    public JsonResult<List<SignType>> getAll() {
        List<SignType> list = signTypeService.getAll();
        return new JsonResult<>(SUCCESS, list);
    }

    @RequestMapping("getRecommend")
    public JsonResult<SignRecommend> getRecommend(String signType) {
        SignRecommend signRecommend = signRecommendService.getSignType(signType);
        return new JsonResult<>(SUCCESS, signRecommend);
    }

}
