package com.knubisoft.ls.impl;

import com.knubisoft.ls.FileStrategy;
import java.io.File;

public class Name implements FileStrategy {

    @Override
    public String getValue(File file) {
        return file.getName();
    }
}
