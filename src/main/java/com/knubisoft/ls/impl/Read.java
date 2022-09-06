package com.knubisoft.ls.impl;

import com.knubisoft.ls.Strategy;
import java.io.File;

public class Read implements Strategy {

    @Override
    public String getValue(File file) {
        return String.valueOf(file.canRead());
    }
}
