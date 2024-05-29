package com.datawarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DataWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataWarehouseApplication.class, args);
    }

}
