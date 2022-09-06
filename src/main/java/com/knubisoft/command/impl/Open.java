package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import com.knubisoft.util.ScannerUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class Open extends Command {
    private static final String STOP_WRITE = "close_file";

    public Open(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public List<String> execute(List<String> args) {
        if (args.isEmpty()) {
            return List.of("Please enter name file, which you want to open!");
        }
        String fileName = args.get(0);
        File[] files = context.getCurrentDirectory().listFiles();
        List<String> readList = new ArrayList<>();
        if (files != null) {
            Optional<File> optional = Arrays.stream(files)
                    .filter(file -> file.isFile() && file.getName().equals(fileName))
                    .findFirst();
            if (optional.isPresent()) {
                System.out.println("WRITE YOUR TEXT IN THE FILE:");
                Scanner scanner = ScannerUtil.getScanner();
                while (true) {
                    if (scanner.hasNext()) {
                        String line = scanner.nextLine();
                        if (line.equals(STOP_WRITE)) {
                            break;
                        }
                        readList.add(line + "\n");
                    }
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                    readList.forEach(s -> writeToFile(writer, s));
                }
                return List.of("The information was saved in file: " + fileName + "!\n");
            }
        }
        return List.of("The file is absent with name: " + fileName + "!\n");
    }

    @SneakyThrows
    private void writeToFile(BufferedWriter writer, String s) {
        writer.write(s);
    }
}
