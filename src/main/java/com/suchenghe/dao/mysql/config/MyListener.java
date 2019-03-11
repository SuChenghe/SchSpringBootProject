package com.suchenghe.dao.mysql.config;

import com.mysql.cj.jdbc.Driver;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * @author SuChenghe
 * @date 2018/12/22 14:31
 */
@Logger
@Component
public class MyListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        Enumeration drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = (Driver) drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}
