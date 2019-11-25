package cn.eleven.app;

import cn.eleven.app.util.SpringContextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AppApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        //保存文件的绝对路径
        WebApplicationContext webApplicationContext = (WebApplicationContext) SpringContextUtils.applicationContext;
        ServletContext servletContext = webApplicationContext.getServletContext();
        String realPath = servletContext.getRealPath("/");
        String filePath = realPath + "WEB-INF" + File.separator + "classes" + File.separator + "static" + File.separator + "resource";
        System.out.println("绝对路径:" + filePath);
    }
}
