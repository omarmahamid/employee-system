package com.omar.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Service
public class BootEmployeeService implements ApplicationListener<ApplicationReadyEvent>, Ordered{

    private static final Logger LOGGER = LoggerFactory.getLogger(BootEmployeeService.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LOGGER.info("======== Employee Service Started and Ready ========");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
