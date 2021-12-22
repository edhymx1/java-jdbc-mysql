package org.edgar.dev.jdbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected Logger getLogger() {
        return logger;
    }
}
