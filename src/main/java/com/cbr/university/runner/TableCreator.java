package com.cbr.university.runner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cbr.university.spring.config.AppConfig;
import com.cbr.university.spring.tablemanagement.TableRecreator;

public class TableCreator {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class);
        TableRecreator tableRecreator = context.getBean(TableRecreator.class);

        tableRecreator.deleteTables();
        tableRecreator.createTables();

        context.close();
    }
}
