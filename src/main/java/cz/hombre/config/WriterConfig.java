package cz.hombre.config;

import cz.hombre.writer.DevelWriter;
import cz.hombre.writer.ProdWriter;
import cz.hombre.writer.Writer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author ondrej.dlabola
 */
@Configuration
public class WriterConfig {

    @Bean
    @Profile("TEST")
    public static Writer debugWriter(){
        return new DevelWriter();
    };

    @Bean
    @Profile({"PROD_POSTGRESQL","PROD_MONGODB"})
    public static Writer defaultWriter(){
        return new ProdWriter();
    };
}
