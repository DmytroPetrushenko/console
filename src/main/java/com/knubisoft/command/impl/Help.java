package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.util.List;
import java.util.stream.Collectors;

public class Help extends Command {
    public Help(Context context) {
        super(context);
    }

    @Override
    public List<String> execute(List<String> args) {
        return context.getCommands().values().stream()
                .map(object -> object.getClass().getSimpleName().toLowerCase() + "\n")
                .collect(Collectors.toList());
    }
}
