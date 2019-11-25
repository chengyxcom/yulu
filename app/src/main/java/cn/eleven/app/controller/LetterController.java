package cn.eleven.app.controller;

import cn.eleven.app.entity.BigLetter;
import cn.eleven.app.service.ILetterService;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("letter")
public class LetterController extends BaseController {
    @Autowired
    private ILetterService letterService;

    @RequestMapping("saveLetter")
    public JsonResult<Boolean> saveLetter(Integer userLeft, Integer userRight, String letter) {
        Boolean result = letterService.saveLetter(userLeft, userRight, letter);
        if (result) {
            return new JsonResult<>(SUCCESS, result);
        } else {
            return new JsonResult<>(SUCCESS, result);
        }
    }

    @RequestMapping("showLetter")
    public JsonResult<List<BigLetter>> showLetter(Integer userLeft, Integer userRight) {
        List<BigLetter> list = letterService.showLetter(userLeft, userRight);
        return new JsonResult<>(SUCCESS, list);
    }

    @RequestMapping("deleteLetter")
    public JsonResult<Boolean> deleteLetter(Integer letterId) {
        Boolean result = letterService.deleteLetter(letterId);
        if (result) {
            return new JsonResult<>(SUCCESS, result);
        } else {
            return new JsonResult<>(SUCCESS, result);
        }
    }

    @RequestMapping("showUserList")
    public JsonResult<List<BigLetter>> showUserList(Integer userLeft) {
        List<BigLetter> list = letterService.showUserList(userLeft);
        return new JsonResult<>(SUCCESS, list);
    }

    @RequestMapping("deleteList")
    public JsonResult<Boolean> deleteList(Integer userLeft, Integer userRight) {
        Boolean result = letterService.deleteList(userLeft, userRight);
        if (result) {
            return new JsonResult<>(SUCCESS, result);
        } else {
            return new JsonResult<>(SUCCESS, result);
        }
    }

    @RequestMapping("setTop")
    public JsonResult<Boolean> setTop(Integer userLeft, Integer userRight) {
        Boolean result = letterService.setTop(userLeft, userRight);
        if (result) {
            return new JsonResult<>(SUCCESS, result);
        } else {
            return new JsonResult<>(SUCCESS, result);
        }
    }

    @RequestMapping("cancelTop")
    public JsonResult<Boolean> cancelTop(Integer userLeft, Integer userRight) {
        Boolean result = letterService.cancelTop(userLeft, userRight);
        if (result) {
            return new JsonResult<>(SUCCESS, result);
        } else {
            return new JsonResult<>(SUCCESS, result);
        }
    }

    @RequestMapping("changeIsReadState")
    public void changeIsReadState(Integer userLeft, Integer userRight) {
        letterService.changeIsReadState(userLeft, userRight);
    }

}
