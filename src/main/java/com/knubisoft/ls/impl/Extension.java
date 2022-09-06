package com.knubisoft.ls.impl;

import com.knubisoft.ls.Strategy;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

public class Extension implements Strategy {

    @Override
    public String getValue(File file) {
        return file.isFile() ? FilenameUtils.getExtension(file.getName()) : "";
    }
}
