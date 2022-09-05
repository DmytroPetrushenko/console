package com.knubisoft.console;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

public class Console {
    private final Context context = new Context();

    @SneakyThrows
    public void launchConsole() {
        context.setCurrentDirectory(new File(System.getenv("PWD")));
        Map<String, Command> commands = getCommands();
        context.setCommands(commands);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            List<String> nextLine = List.of(scanner.nextLine().split(" "));
            String commandName = nextLine.get(0);
            if (commandName.equals("q") || commandName.equals("exit")) {
                break;
            }
            Command currentCommand = commands.get(commandName);
            if (currentCommand == null) {
                System.out.println("This command is absent! Use command \"help\"!");
                continue;
            }
            List<String> strings = currentCommand.execute(nextLine.subList(1, nextLine.size()));
            strings.forEach(System.out::print);
        }
    }

    private Map<String, Command> getCommands() {
        Reflections reflections = new Reflections("com.knubisoft", Scanners.SubTypes);
        return reflections.getSubTypesOf(Command.class).stream()
                .collect(Collectors.toMap(clazz -> clazz.getSimpleName().toLowerCase(),
                        this::getInstance));
    }

    @SneakyThrows
    private <T> T getInstance(Class<T> clazz) {
        return clazz.getConstructor(Context.class).newInstance(context);
    }
}
