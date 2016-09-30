package com.sukhesh.mobilelocationtracker.imeitracker.boot;


import com.sukhesh.mobilelocationtracker.imeitracker.conf.ConfigLoader;
import com.sukhesh.mobilelocationtracker.imeitracker.conf.GlobalConfig;
import com.sukhesh.mobilelocationtracker.imeitracker.util.BeanLookupHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by sukhesh on 16/04/16.
 */

//@ComponentScan(basePackages = "com.sukhesh")
//@EnableAutoConfiguration
//@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.sukhesh")
//@EntityScan(basePackages = "com.sukhesh")
@ComponentScan(basePackages = "com.sukhesh")
@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sukhesh")
@EntityScan(basePackages = "com.sukhesh")

public class ImeiTrackerServiceInit {
    private static final Logger logger = LoggerFactory.getLogger(ImeiTrackerServiceInit.class);

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory(GlobalConfig.INSTANCE.getServicePort());
        return factory;
    }

    public static void main(String[] args) {

        ConfigLoader.INSTANCE.loadConfig();

        ConfigurableApplicationContext applicationContext=
                SpringApplication.run(ImeiTrackerServiceInit.class, args);

        BeanLookupHelper.INSTANCE.init(applicationContext);

//        ServiceInitializer.INSTANCE.registerChangeHandlers();
//
//        ServiceInitializer.INSTANCE.initializeRepos();

//        EventManager.INSTANCE.init();

        System.out.println();



    }
}
