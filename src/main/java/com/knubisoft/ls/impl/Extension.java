package com.knubisoft.ls.impl;

import com.knubisoft.ls.FileStrategy;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

public class Extension implements FileStrategy {

    @Override
    public String getValue(File file) {
        return file.isFile() ? FilenameUtils.getExtension(file.getName()) : "";
    }
}
