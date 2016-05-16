package cz.hombre.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ondrej.dlabola
 */
@Configuration
@ComponentScan("hombre")
@Import(LogConfig.class)
public class AppConfig {

}
