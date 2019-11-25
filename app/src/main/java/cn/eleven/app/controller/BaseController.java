package cn.eleven.app.controller;

import cn.eleven.app.service.ex.*;
import cn.eleven.app.util.JsonResult;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class BaseController {
    /**
     * 响应成功的标识码
     */
    public static final int SUCCESS = 2000;

    public static final long FILE_MAXSIZE = 2000 * 1024;

    /**
     * 从Session中获取当前登录的用户的id
     *
     * @param session Session对象
     * @return 当前登录的用户的id
     */
    protected Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 从Session中获取当前登录的用户的验证码
     *
     * @param session Session对象
     * @return 当前登录的用户发送的验证码
     */
    protected String getStandardCodeFromSession(HttpSession session) {
        return String.valueOf(session.getAttribute("standardCode").toString());
    }

    /**
     * 从Session中获取当前登录的用户名
     *
     * @param session Session对象
     * @return 当前登录的用户名
     */
    protected String getPhoneFromSession(HttpSession session) {
        return session.getAttribute("phone").toString();
    }

    /**
     * 图片上传
     *
     * @param e
     * @return
     */
    protected String saveImg(@RequestParam("file") MultipartFile file, String path) {
        //保存图片
        if (file.getSize() > FILE_MAXSIZE) {
            throw new FileSizeException("不允许上传超过" + FILE_MAXSIZE + "KB的文件");
        }


        //String dirPath = request.getServletContext().getRealPath("postImg");
        String dirPath = null;
        try {
            //获得根目录
            dirPath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(dirPath);

        File dir = new File(dirPath, path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //扩展名
        String suffix = "";
        int index = originalFilename.lastIndexOf(".");
        if (index > 0) {
            suffix = originalFilename.substring(index);
        }
        //文件全名
        String filename = UUID.randomUUID().toString() + suffix;
        //保存到的文件
        File dest = new File(dir, filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("出现未知错误");
        }

        return filename;
    }

    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> jr = new JsonResult<>(e);

        if (e instanceof PhoneConflictException) {
            //4001手机号冲突异常
            jr.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            //用户查询失败异常
            jr.setState(4001);
        } else if (e instanceof InsertException) {
            //插入用户数据时失败异常
            jr.setState(4002);
        } else if (e instanceof VCodeSendException) {
            //发送验证码失败异常
            jr.setState(4003);
        } else if (e instanceof PasswordNotMatchException) {
            // 4002-验证密码失败
            jr.setState(4004);
        } else if (e instanceof VCodeNotMatchException) {
            // 4003-验证验证码失败
            jr.setState(4005);
        } else if (e instanceof UpdateException) {
            // 4003-验证验证码失败
            jr.setState(4006);
        }

        return jr;
    }

}
