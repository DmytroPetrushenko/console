package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Cd extends Command {

    public Cd(Context context) {
        super(context);
    }

    @Override
    public List<String> execute(List<String> args) {
        List<String> returnList = new ArrayList<>(0);
        File currentDirectory = context.getCurrentDirectory();
        String toDo = args.get(0);
        if (toDo.equals("..")) {
            context.setCurrentDirectory(currentDirectory.getParentFile());
            return returnList;
        }
        Optional<File> optional = Arrays.stream(Objects.requireNonNull(currentDirectory.listFiles()))
                .filter(File::isDirectory)
                .filter(file -> file.getName().equals(toDo))
                .findFirst();
        if (optional.isEmpty()) {
            System.out.println("this folder is absent! Create it or Choose other one!");
            return returnList;
        }
        context.setCurrentDirectory(optional.get());
        return returnList;
    }
}
