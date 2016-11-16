package com.thevirtugroup.postitnote;

import com.thevirtugroup.postitnote.config.MvcConfig;
import com.thevirtugroup.postitnote.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 */
@SpringBootApplication
@Import({MvcConfig.class, WebSecurityConfig.class})
@ComponentScan({
        "com.thevirtugroup.postitnote.rest"
})
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) throws Exception {
        System.setProperty("spring.devtools.restart.enabled", "true");

        SpringApplication.run(Application.class, args);
    }

}
