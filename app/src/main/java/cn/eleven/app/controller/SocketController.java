package cn.eleven.app.controller;

import cn.eleven.app.entity.PushMessage;
import cn.eleven.app.service.ISocketIoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socket")
public class SocketController {

    @Autowired
    private ISocketIoService socketIoService;

    @GetMapping("/send")
    public PushMessage sendMsg(@RequestParam("content") String content,
                               @RequestParam("loginUser") String loginUser,
                               @RequestParam("receiveUser") String receiveUser) {
        PushMessage pushMessage = socketIoService.pushMessageToUser(
                new PushMessage(loginUser, receiveUser, content));
        return pushMessage;
    }
}
