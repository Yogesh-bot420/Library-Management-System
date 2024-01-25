package com.spark.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.spark.lms.common.Constants;
import com.spark.lms.model.User;
import com.spark.lms.service.UserService;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Override
    //perform initialization from the database
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initDatabaseEntities();
    }


    //add two predefined users for admin and librarian by using userService
    private void initDatabaseEntities() {

        if( userService.getAllUsers().size() == 0) {
            userService.addNew(new User("Mr. Admin", "admin", "admin", Constants.ROLE_ADMIN));
            userService.addNew(new User("Mr. Librarian", "librarian", "librarian", Constants.ROLE_LIBRARIAN));
        }

    }
}