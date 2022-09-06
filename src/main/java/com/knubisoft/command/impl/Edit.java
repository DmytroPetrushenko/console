package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import com.knubisoft.util.TerminalUtil;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

public class Edit extends Command {
    public Edit(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public List<String> execute(List<String> args) {
        if (args.size() == 0) {
            return List.of("Enter file name!");
        }
        File[] files = context.getCurrentDirectory().listFiles();
        if (files == null) {
            return List.of("No files are in this folder!");
        }
        String fileName = args.get(0);
        Optional<File> optional = Arrays.stream(files)
                .filter(file -> file.getName().equals(fileName))
                .findFirst();
        if (optional.isEmpty()) {
            return List.of("this file (" + fileName + ") is absent in this folder!");
        }
        File file = optional.get();
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        content = TerminalUtil.launchTerminal(content);
        FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
        return List.of(fileName + " was changed!\n");
    }
}
