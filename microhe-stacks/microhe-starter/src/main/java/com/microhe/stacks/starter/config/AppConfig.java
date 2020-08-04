package com.microhe.stacks.starter.config;

import java.util.List;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.builder.ParentContextApplicationContextInitializer.ParentContextAvailableEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;

@Configuration
public class AppConfig implements ApplicationListener<ApplicationEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Value("${server.tomcat.basedir:N/a}")
    private String tomcatBasedir;

    @Autowired
    private Environment environment;


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ParentContextAvailableEvent) {
            onApplicationEnvironmentPreparedEvent((ParentContextAvailableEvent) event);
        }
        if (event instanceof ApplicationReadyEvent) {
            LOGGER.info("Spring Boot. ({}) {} 已启动, tomcatBasedir={}, 端口={} ", getVersion(),
                    ThreadContext.get("appname"), this.getTomcatHome(), getTomcatPort());
        }
    }

    private void onApplicationEnvironmentPreparedEvent(ParentContextAvailableEvent event) {
        System.setProperty("appName", event.getApplicationContext().getEnvironment().getProperty("spring.application.name"));
        System.setProperty("kafkaServers", event.getApplicationContext().getEnvironment().getProperty("logging.kafka.servers"));
    }

    List<EnvironmentPostProcessor> loadPostProcessors() {
        return SpringFactoriesLoader.loadFactories(EnvironmentPostProcessor.class,
                getClass().getClassLoader());
    }

    /**
     * 获取主目录
     * 
     * @return
     */
    public String getTomcatHome() {
        return this.tomcatBasedir;
    }

    /**
     * 取得 tomcat 端口
     * 
     * @return
     */
    public String getTomcatPort() {
        return environment.getProperty("local.server.port");
    }

    /**
     * 获取 spring 版本
     * 
     * @return
     */
    public static String getVersion() {
        Package pkg = SpringBootVersion.class.getPackage();
        String springVersion = (pkg != null ? pkg.getImplementationVersion() : null);
        return springVersion;
    }

}
