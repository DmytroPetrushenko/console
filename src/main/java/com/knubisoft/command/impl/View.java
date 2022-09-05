package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.SneakyThrows;

public class View extends Command {
    private static final String DELIMITER = "-".repeat(15) + "\n";

    public View(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public List<String> execute(List<String> args) {
        if (args.size() == 0) {
            return List.of("You forgot input the name of viewing file! Enter file name too!");
        }
        String fileName = args.get(0);
        Optional<File> optional = Arrays.stream(Objects
                        .requireNonNull(context.getCurrentDirectory().listFiles()))
                .filter(file -> file.isFile() && file.getName().equals(fileName))
                .findFirst();
        if (optional.isEmpty()) {
            return List.of("The file is absent with this name: " + fileName + ".\n");
        }
        BufferedReader reader = new BufferedReader(new FileReader(optional.get()));
        List<String> result = new ArrayList<>();
        String line;
        result.add(DELIMITER);
        while ((line = reader.readLine()) != null) {
            result.add(line + "\n");
        }
        result.add(DELIMITER);
        return result;
    }
}
