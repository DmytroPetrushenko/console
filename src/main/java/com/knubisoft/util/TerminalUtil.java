package com.knubisoft.util;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import lombok.SneakyThrows;

public class TerminalUtil {

    @SneakyThrows
    public Terminal getTerminal() {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        TerminalPosition startPosition = terminal.getCursorPosition();
        return terminal;
    }
}
