package cn.eleven.app.controller;

import cn.eleven.app.entity.User;
import cn.eleven.app.service.IUserService;
import cn.eleven.app.service.ex.FileSizeException;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 处理用户数据相关请求的控制器类
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    private String standardCode = null;

    @Autowired
    private IUserService userService;

    @RequestMapping("acquireVcode")
    public JsonResult<Void> acquireVcode(String phone) {
        standardCode = userService.acquireVcode(phone);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user, String code) {
        userService.reg(user, code, standardCode);
        System.out.println("controller_user.getPassword==" + user.getPassword());
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("loginByPassword")
    public JsonResult<User> loginByPassword(String phone, String password, HttpSession session) {
        System.out.println("password==" + password);
        User data = userService.loginByPassword(phone, password);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("phone", data.getPhone());
        return new JsonResult<>(SUCCESS, data);
    }

    @RequestMapping("loginByCode")
    public JsonResult<User> loginByCode(String phone, String code, HttpSession session) {
        String standardCode = getStandardCodeFromSession(session);
        User data = userService.loginByCode(phone, code, standardCode);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("phone", data.getPhone());
        return new JsonResult<>(SUCCESS, data);
    }

    @RequestMapping("changePasswordByOld")
    public JsonResult<Void> changePasswordByOld(
            @RequestParam("old_password") String oldPassword,
            @RequestParam("new_password") String newPassword,
            HttpSession session) {
        Integer uid = getUidFromSession(session);
        String phone = getPhoneFromSession(session);
        userService.changePasswordByOld(uid, phone, oldPassword, newPassword);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("changeBirthday")
    public JsonResult<Date> changeBirthday(
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
            Integer uid) {
        System.out.println("date1:" + birthday);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(birthday);
//        Integer uid = getUidFromSession(session);
        userService.changeBirthday(uid, birthday);
        return new JsonResult<>(SUCCESS, birthday);
    }

    @RequestMapping("changePasswordByCode")
    public JsonResult<Void> changePasswordByCode(
            @RequestParam("code") String code,
            @RequestParam("new_password") String newPassword,
            HttpSession session) {
        String standardCode = getStandardCodeFromSession(session);
        Integer uid = getUidFromSession(session);
        userService.changePasswordByCode(uid, code, newPassword, standardCode);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("changeBackgroundImg")
    public JsonResult<Void> changeBackgroundImg(User user, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        System.out.println(file);
        //保存图片
        if (!file.isEmpty()) {
            String filename = saveImg(file, "static/backgroundImg/");
            String backgroundImg = "/backgroundImg/" + filename;
            user.setBackgroundImg(backgroundImg);
        }
        userService.changeBackgroundImg(user);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(Integer uid) {
        //Integer uid = getUidFromSession(session);
        User data = userService.getByUid(uid);
        return new JsonResult<>(SUCCESS, data);
    }

    @RequestMapping("changeUsername")
    public JsonResult<String> changUsername(String username, Integer uid) {
        userService.changeUsername(uid, username);
        return new JsonResult<>(SUCCESS, username);
    }

    @RequestMapping("changeMessage")
    public JsonResult<String> changeMessage(String message, Integer uid) {
        userService.changeMessage(uid, message);
        return new JsonResult<>(SUCCESS, message);
    }

    @RequestMapping("changeGender")
    public JsonResult<Void> changeGender(Integer gender, Integer uid) {
        userService.changeGender(gender, uid);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("changeDistrict")
    public JsonResult<Void> changeDistrict(String provinceCode, String cityCode, Integer uid) {
//        Integer uid = getUidFromSession(session);
        userService.changeDistrict(provinceCode, cityCode, uid);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("changePasswordByPhone")
    public JsonResult<Void> changePasswordByPhone(Integer uid, String password) {
        userService.changePasswordByPhone(uid, password);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("changeAvatar")
    public JsonResult<Void> changeAvatar(User user, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        System.out.println(file);
        //保存图片
        if (!file.isEmpty()) {
            String filename = saveImg(file, "static/avatar/");
            String avatar = "/avatar/" + filename;
            user.setAvatar(avatar);
        }
        userService.changeAvatar(user);
        return new JsonResult<>(SUCCESS);
    }

    @RequestMapping("showUserByUid")
    public JsonResult<User> showUserByUid(Integer uid) {
        User user = userService.showUserByUid(uid);
        return new JsonResult<>(SUCCESS, user);
    }

    @RequestMapping("showUserInformation")
    public JsonResult<Map<String, Object>> showUserInformation(Integer uid) {
        User user = userService.getByUid(uid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(user.getBirthday());
        String[] strings = str.split("-");
        Integer year = Integer.parseInt(strings[0]);
        Integer month = Integer.parseInt(strings[1]);
        Integer day = Integer.parseInt(strings[2]);
        String yearLabel = null;
        if (year > 1980 && year < 1990) {
            yearLabel = "80";
        } else if (year >= 1990 && year < 2000) {
            yearLabel = "90";
        } else if (year >= 2000 && year < 2010) {
            yearLabel = "00";
        } else if (year > 2010) {
            yearLabel = "10";
        }
        String constellation = getConstellation(month, day);

        Map<String, Object> map = new HashMap<>();
        map.put("avatar", user.getAvatar());
        map.put("username", user.getUsername());
        map.put("message", user.getMessage());
        map.put("gender", user.getGender());
        map.put("yearLabel", yearLabel);
        map.put("constellation", constellation);
        map.put("provinceName", user.getProvinceName());
        map.put("backgroundImg", user.getBackgroundImg());

        return new JsonResult<>(SUCCESS, map);
    }

    //判断星座的
    private static String getConstellation(Integer m, Integer d) {
        final String[] constellationArr = {"魔羯座", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
        final int[] constellationEdgeDay = {20, 18, 20, 20, 20, 21, 22, 22, 22, 22, 21, 21};
        int month = m;
        int day = d;
        if (day <= constellationEdgeDay[month - 1]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        return constellationArr[11];
    }

}
