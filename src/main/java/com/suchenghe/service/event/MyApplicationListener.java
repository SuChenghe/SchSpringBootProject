package com.suchenghe.service.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author SuChenghe
 * @date 2019/1/28 9:57
 */
@Component
public class MyApplicationListener {

    @Async
    @EventListener
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        myApplicationEvent.printMsg();
    }
}
