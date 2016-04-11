package cz.hombre;

import cz.hombre.config.LogConfig;
import cz.hombre.writer.Writer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        LogConfig cfg = ctx.getBean(LogConfig.class);
        Writer writer = ctx.getBean(Writer.class);
        writer.write(cfg.toString());
    }

}