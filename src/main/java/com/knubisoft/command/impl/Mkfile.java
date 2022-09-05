package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import lombok.SneakyThrows;

public class Mkfile extends Command {
    public Mkfile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public List<String> execute(List<String> args) {
        if (args.size() == 0) {
            return List.of("You forgot input the name of creating file! Enter file name too!");
        }
        String fileName = args.get(0);
        File file = new File(context.getCurrentDirectory(), fileName);
        Files.createFile(file.toPath());
        return List.of("The files was created!\n");
    }
}
