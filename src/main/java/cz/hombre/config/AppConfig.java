package cz.hombre.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ondrej.dlabola
 */
@Configuration
@ComponentScan("cz.hombre")
@Import(LogConfig.class)
public class AppConfig {

}
