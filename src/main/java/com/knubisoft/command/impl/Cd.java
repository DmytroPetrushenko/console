package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Cd extends Command {

    public Cd(Context context) {
        super(context);
    }

    @Override
    public List<String> execute(List<String> args) {
        List<String> emptyList = new ArrayList<>(0);
        File currentDirectory = context.getCurrentDirectory();
        if (args.size() == 0) {
            return List.of("You forgot input the name of command/directory! "
                    + "Enter command/directory name too!");
        }
        String toDo = args.get(0);
        if (toDo.equals("..")) {
            context.setCurrentDirectory(currentDirectory.getParentFile());
            return emptyList;
        }
        Optional<File> optional = Arrays.stream(Objects
                        .requireNonNull(currentDirectory.listFiles()))
                .filter(File::isDirectory)
                .filter(file -> file.getName().equals(toDo))
                .findFirst();
        if (optional.isEmpty()) {
            System.out.println("this folder is absent! Create it or Choose other one!");
            return emptyList;
        }
        context.setCurrentDirectory(optional.get());
        return emptyList;
    }
}
