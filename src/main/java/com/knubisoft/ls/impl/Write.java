package com.knubisoft.ls.impl;

import com.knubisoft.ls.FileStrategy;
import java.io.File;

public class Write implements FileStrategy {

    @Override
    public String getValue(File file) {
        return String.valueOf(file.canWrite());
    }
}
