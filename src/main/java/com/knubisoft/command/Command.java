package com.knubisoft.command;

import com.knubisoft.util.Context;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Command {
    protected final Context context;

    public abstract List<String> execute(List<String> args);
}
