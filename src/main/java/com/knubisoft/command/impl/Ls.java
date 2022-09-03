package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ls extends Command {
    private int maxLengthName = 0;

    public Ls(Context context) {
        super(context);
    }

    @Override
    public List<String> execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File[] files = currentDirectory.listFiles();
        maxLengthName = setMaxLengthName(files);
        if (files == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(files)
                .map(this::getString)
                .collect(Collectors.toList());
    }

    private int setMaxLengthName(File[] files) {
        return Arrays.stream(files)
                .map(value -> value.getName().length())
                .max(Comparator.naturalOrder())
                .orElseThrow();
    }

    private String getString(File file) {
        StringBuilder builder = new StringBuilder();
        String name = file.getName();
        String fileTypeSize = getFileTypeSize(file);
        builder.append(name);
        builder.append(" ".repeat(maxLengthName - name.length()));
        builder.append(" : ").append(fileTypeSize).append("\n");
        return builder.toString();
    }

    private String getFileTypeSize(File file) {
        String size = file.length() + " bytes";
        if (file.isDirectory()) {
            return "folder : " + size;
        }
        return "file   : " + size;
    }
}
