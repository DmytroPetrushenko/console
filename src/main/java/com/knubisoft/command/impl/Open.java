package com.knubisoft.command.impl;

import com.knubisoft.command.Command;
import com.knubisoft.util.Context;
import java.util.List;

public class Open extends Command {
    public Open(Context context) {
        super(context);
    }

    @Override
    public List<String> execute(List<String> args) {
        return null;
    }
}
