package com.knubisoft.ls.impl;

import com.knubisoft.ls.Strategy;
import java.io.File;

public class Type implements Strategy {

    @Override
    public String getValue(File file) {
        return file.isFile() ? "file" : "folder";
    }
}
