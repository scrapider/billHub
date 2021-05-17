package com.qzy.boot;

import com.qzy.boot.component.MyLocalResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootApplication
@MapperScan("com.qzy.boot.mapper")
public class SpringbootBillApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootBillApplication.class, args);

    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

}
