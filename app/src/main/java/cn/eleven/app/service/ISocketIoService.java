package cn.eleven.app.service;

import cn.eleven.app.entity.PushMessage;

public interface ISocketIoService {
    /**
     * 推送的事件
     */
    String PUSH_EVENT = "push_event";

    /**
     * 启动服务
     *
     * @throws Exception
     */
    void start() throws Exception;

    /**
     * 停止服务
     */
    void stop();

    /**
     * 推送信息
     *
     * @param pushMessage
     */
    PushMessage pushMessageToUser(PushMessage pushMessage);
}
