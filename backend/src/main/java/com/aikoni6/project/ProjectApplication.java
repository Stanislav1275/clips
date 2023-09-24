package com.aikoni6.project;

import com.aikoni6.project.Config.ProjectInit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*
    TODO:
    вывод объявления, фильтрация, создание, удаление, поиск, редактирование

    уведомления по сокету
    админка
    пагинация
    показ, редактирование профиля
*/
@SpringBootApplication
public class ProjectApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ProjectApplication.class, args);
        ProjectInit PI = ctx.getBean(ProjectInit.class);
        PI.init();
    }
}
