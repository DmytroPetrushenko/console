package com.knubisoft.util;

import com.knubisoft.command.Command;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Map;

@Getter
@Setter
public class Context {
    private Map<String, Command> commands;
    private File currentDirectory;

}
