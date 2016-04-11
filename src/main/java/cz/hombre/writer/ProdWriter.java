package cz.hombre.writer;

import cz.hombre.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProdWriter implements Writer {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Override
    public void write(String message) {
        log.info(message);
    }
}
