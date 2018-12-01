package com.gmail.fredllaranjo.visitarapi.core.aop.logging;

import java.io.File;

import ch.qos.logback.core.PropertyDefinerBase;

public class DefaultLogDirDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        return new File(".").getAbsolutePath().replace(".", "logs");
    }
}
