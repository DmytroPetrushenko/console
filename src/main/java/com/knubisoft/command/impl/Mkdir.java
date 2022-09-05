package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import lombok.SneakyThrows;

public class Mkdir extends Command {
    public Mkdir(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public List<String> execute(List<String> args) {
        if (args.size() == 0) {
            return List.of("You forgot input the name of creating directory! "
                    + "Enter directory name too!");
        }
        String directoryName = args.get(0);
        File directory = new File(context.getCurrentDirectory(), directoryName);
        Files.createDirectories(directory.toPath());
        return List.of("The directories was created!\n");
    }
}
