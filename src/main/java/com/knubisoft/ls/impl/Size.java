package com.knubisoft.ls.impl;

import com.knubisoft.ls.Strategy;
import java.io.File;

public class Size implements Strategy {

    @Override
    public String getValue(File file) {
        return file.length() + " bytes";
    }
}
