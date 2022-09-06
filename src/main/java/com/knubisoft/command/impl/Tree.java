package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Tree extends Command {
    private static final String SUFFIX = "+--";
    private static final String ROW_END = "\n";
    private static final String PREFIX = "|  ";
    private final List<String> treeList = new ArrayList<>();
    private int limit = Integer.MAX_VALUE;

    public Tree(Context context) {
        super(context);
    }

    @Override
    public List<String> execute(List<String> args) {
        if (args.size() != 0) {
            limit = Integer.parseInt(args.get(0));
        }
        int level = 0;
        File currentDirectory = context.getCurrentDirectory();
        createFolderTree(currentDirectory, level);
        return treeList;
    }

    private void createFolderTree(File currentDirectory, int level) {
        createRowTree(currentDirectory, level);
        if ((level = level + 1) == limit) {
            return;
        }
        for (File file : Objects.requireNonNull(currentDirectory.listFiles())) {
            if (file.isDirectory()) {
                createFolderTree(file, level);
            } else {
                createRowTree(file, level);
            }
        }

    }

    private String getPrefix(int level) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, level).forEach((x) -> builder.append(PREFIX));
        return builder.toString();
    }

    private void createRowTree(File currentDirectory, int level) {
        StringBuilder builder = new StringBuilder();
        builder.append(getPrefix(level));
        builder.append(SUFFIX);
        builder.append(currentDirectory.getName());
        builder.append(ROW_END);
        treeList.add(builder.toString());
    }
}
