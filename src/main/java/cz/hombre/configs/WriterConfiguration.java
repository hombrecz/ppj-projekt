package cz.hombre.configs;

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
public class WriterConfiguration {

    @Bean
    @Profile("devel")
    public static Writer debugWriter(){
        return new DevelWriter();
    };

    @Bean
    @Profile("prod")
    public static Writer defaultWriter(){
        return new ProdWriter();
    };
}
