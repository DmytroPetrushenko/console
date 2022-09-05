package com.knubisoft.util;

import com.knubisoft.command.Command;
import java.io.File;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Context {
    private Map<String, Command> commands;
    private File currentDirectory;

}
