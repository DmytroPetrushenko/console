package com.knubisoft.util;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListenerAdapter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public class TerminalUtil {
    private static String result;

    @SneakyThrows
    public static String launchTerminal(String content) {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        try (TerminalScreen screen = new TerminalScreen(terminal)) {
            screen.startScreen();
            final WindowBasedTextGUI textGui = new MultiWindowTextGUI(screen);
            final Window window = new BasicWindow("My Terminal");

            Panel panel = new Panel();
            TextBox textBox = new TextBox(screen.getTerminalSize());
            textBox.setText(content);
            panel.addComponent(textBox);
            window.setComponent(panel);

            window.addWindowListener(new WindowListenerAdapter() {
                @Override
                public void onInput(Window basePane, KeyStroke keyStroke,
                                    AtomicBoolean deliverEvent) {
                    super.onInput(basePane, keyStroke, deliverEvent);
                    if (keyStroke.getKeyType().equals(KeyType.Escape)) {
                        result = textBox.getText();
                        window.close();
                    }
                }
            });
            textGui.addWindowAndWait(window);
        }
        return result;
    }
}
