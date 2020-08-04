package com.microhe.stacks.starter;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// 解决启动慢的问题
// scutil --set HostName "localhost"
// scutil --get HostName 命令查看是否生效
@ComponentScan(basePackages = {"com.microhe"})
@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        startup(App.class, args);
    }

    protected static ConfigurableApplicationContext startup(Class<?> clazz, String... args) {
        // debugger 时拿不到 implementation 相关信息
        SpringApplication app = new SpringApplication(clazz);
        app.addInitializers((context) -> {
            System.setProperty("appName", context.getEnvironment().getProperty("spring.application.name"));
            System.setProperty("kafkaServers", context.getEnvironment().getProperty("logging.kafka.servers"));
        });
        List<String> _args = new ArrayList<>();
        _args.add(clazz.getName());
        if (args != null && args.length == 0) {
            for (int i = 0; i < args.length; i++) {
                _args.add(args[i]);
            }
        }
        return app.run(_args.stream().toArray(String[]::new));
    }

    @Override
    public void run(String... args) throws Exception {
        String mainClass = args[0];
        if (this.getClass().getName().startsWith(mainClass)) {
            LOGGER.info("当前启动参数: mainClass={}, args={}", mainClass,
                    args.length == 1 ? "(N/a)" : String.join(",", args));
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                // FileSystemUtils.deleteRecursively(new File(appConfig.getTomcatHome()));
                LOGGER.info("当前服务已停止!");
            }
        });
    }

}
