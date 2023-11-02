package com.cc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("appConfig")
@Data
public class AppConfig {

    @Value("${spring.mail.username}")
    private String sendUserName;

    @Value("${admin.emails}")
    private String adminEmails;

    @Value("${project.folder}")
    private String projectFolder;

}
