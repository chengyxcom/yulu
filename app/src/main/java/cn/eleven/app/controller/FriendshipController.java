package cn.eleven.app.controller;

import cn.eleven.app.entity.User;
import cn.eleven.app.service.IFriendshipService;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("friendship")
public class FriendshipController extends BaseController {
    @Autowired
    private IFriendshipService friendshipService;

    @RequestMapping("saveFriendship")
    public JsonResult<Void> saveFriendship(Integer userRight, Integer userLeft) {
        //Integer userLeft = getUidFromSession(session);
        friendshipService.saveFriendship(userLeft, userRight);

        Integer result = friendshipService.getRelationByUserRight(userLeft, userRight);
        if (result != 0) {
            Integer relation = 2;
            friendshipService.updateRelationLeft(userLeft, userRight, relation);
            friendshipService.updateRelationRight(userLeft, userRight, relation);
        }
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("cancelFriendship")
    public JsonResult<Void> cancelFriendship(Integer userRight, Integer userLeft) {
        //Integer userLeft = getUidFromSession(session);
        friendshipService.cancelFriendship(userLeft, userRight);

        Integer result = friendshipService.getRelationByUserRight(userLeft, userRight);
        if (result != 0) {
            Integer relation = 1;
            friendshipService.updateRelationLeft(userLeft, userRight, relation);
            friendshipService.updateRelationRight(userLeft, userRight, relation);
        }
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("showMutualConcern")
    public JsonResult<List<User>> showMutualConcern(Integer userLeft) {
        //Integer userLeft = getUidFromSession(session);
        List<User> lists = friendshipService.showMutualConcern(userLeft);
        return new JsonResult<>(SUCCESS, lists);
    }

    @RequestMapping("showMyConcern")
    public JsonResult<List<User>> showMyConcern(Integer userLeft) {
        //Integer userLeft = getUidFromSession(session);
        List<User> lists = friendshipService.showMyConcern(userLeft);
        return new JsonResult<>(SUCCESS, lists);
    }

    @RequestMapping("showConcernAboutMe")
    public JsonResult<List<User>> showConcernAboutMe(Integer userRight) {
        //Integer userRight = getUidFromSession(session);
        List<User> lists = friendshipService.showConcernAboutMe(userRight);
        return new JsonResult<>(SUCCESS, lists);
    }
}
