package cn.eleven.app.service.impl;

import cn.eleven.app.entity.PushMessage;
import cn.eleven.app.service.ILetterService;
import cn.eleven.app.service.ISocketIoService;
import cn.eleven.app.service.IUserService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service(value = "socketIOService")
public class SocketIoServiceImpl implements ISocketIoService {

    /**
     * 用来存已连接的客户端
     */
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    /**
     * socketIo的对象
     */
    @Autowired
    private SocketIOServer socketIOServer;

    @Autowired
    private ILetterService letterService;

    @Autowired
    private IUserService userService;

    /**
     * 功能描述：当前的service被初始化的时候执行以下的方法
     *
     * @throws Exception
     */
    @PostConstruct
    private void autoStartUp() throws Exception {
        start();
    }

    /**
     * 功能描述：当我们的系统停止的时候关闭我们的socketIo
     *
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    @Override
    public void start() throws Exception {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            /**
             * 此处实现我们的socket的连接的用户的逻辑，此处我前端传的是loginUser这个参数，大家可以根据自己的情况来定义入参
             */
            String loginUser = getParamsByClient(client).get("loginUser").get(0);
            System.out.println(loginUser);
            clientMap.put(loginUser, client);
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String loginUser = getParamsByClient(client).get("loginUser").get(0);
            if (loginUser != null && !"".equals(loginUser)) {
                clientMap.remove(loginUser);
                client.disconnect();
            }
        });

        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener(PUSH_EVENT, PushMessage.class, (client, data, ackSender) -> {
            // TODO do something
            System.out.println("socketIOServer.addEventListener()");
        });
        socketIOServer.start();

    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    /**
     * 功能描述：发送消息到前端
     *
     * @param pushMessage 发送消息的实体
     */
    @Override
    public PushMessage pushMessageToUser(PushMessage pushMessage) {
        System.out.println("pushMessageToUser()");
        System.out.println("pushMessage.getReceiveUser()==" + pushMessage.getReceiveUser());
        System.out.println("clientMap.get()==" + clientMap.get(pushMessage.getReceiveUser()));

        Integer userLeft = Integer.parseInt(pushMessage.getLoginUser());
        Integer userRight = Integer.parseInt(pushMessage.getReceiveUser());
        //先判断receiveUser是否在线
        if (clientMap.get(pushMessage.getReceiveUser()) != null) {
            //获取发送人的头像
            String avatar = userService.getAvatarByUid(pushMessage.getLoginUser());
            pushMessage.setLoginUserAvatar(avatar);
            //如果在线，向receiveUser发送实时消息
            //List<PushMessage> list = new ArrayList<>();
            //list.add(pushMessage);
            clientMap.get(pushMessage.getReceiveUser()).sendEvent(PUSH_EVENT, pushMessage);
            //也将消息返回给发送人的客户端
            //clientMap.get(pushMessage.getLoginUser()).sendEvent(PUSH_EVENT, pushMessage);
            //将发送内容存进数据库
            letterService.saveLetter(userLeft, userRight, pushMessage.getContent());
        } else {
            //如果离线，则先存入数据库表，等待receiveUser上线后再推送
            letterService.saveLetter(userLeft, userRight, pushMessage.getContent());
        }
        return pushMessage;
    }

    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     *
     * @param client
     * @return
     */
    private Map<String, List<String>> getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        return params;
    }
}
