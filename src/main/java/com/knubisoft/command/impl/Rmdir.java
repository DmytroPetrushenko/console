package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.SneakyThrows;

public class Rmdir extends Command {
    public Rmdir(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public List<String> execute(List<String> args) {
        if (args.size() == 0) {
            return List.of("You forgot input the name of removing directory! "
                    + "Enter directory name too!");
        }
        String folderName = args.get(0);
        Optional<File> optional = Arrays.stream(Objects.requireNonNull(context
                        .getCurrentDirectory().listFiles()))
                .filter(file -> file.isDirectory() && file.getName().equals(folderName))
                .findFirst();
        if (optional.isEmpty()) {
            return List.of("The folder : " + folderName + " is absent!\n");
        }
        File file = optional.get();
        Files.delete(file.toPath());
        return List.of("The folder was deleted!\n");
    }
}
