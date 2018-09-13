package com.pengq.eureka;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by pengq on 2018/9/11 20:41.
 */
@Component
public class EurekaStateChangeListener {
    private Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    /**
     * @throws
     * @Title: listen
     * @Description: 服务下线事件
     * @param: @param event
     * @return: void
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        String appName = event.getAppName();
        String serverId = event.getServerId();
        String message = LocalDateTime.now() + "\r\n" + appName + "\r\n" + serverId;
        logger.info(message + " 服务下线");
    }

    /**
     * @throws
     * @Title: listen
     * @Description: 服务注册事件
     * @param: @param event
     * @return: void
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info(instanceInfo.getAppName() + "服务进行注册");
    }

    /**
     * @throws
     * @Title: listen
     * @Description: 服务续约事件
     * @param: @param event
     * @return: void
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.info(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约");
    }

    /**
     * @throws
     * @Title: listen
     * @Description: 注册中心启动事件
     * @param: @param event
     * @return: void
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("注册中心 启动");
    }

    /**
     * @throws
     * @Title: listen
     * @Description: EurekaServer启动事件
     * @param: @param event
     * @return: void
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("Eureka Server 启动");
    }
}
