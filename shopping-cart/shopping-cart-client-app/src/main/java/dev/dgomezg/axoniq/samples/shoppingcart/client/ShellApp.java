package dev.dgomezg.axoniq.samples.shoppingcart.client;

import dev.dgomezg.axoniq.samples.shoppingcart.client.config.AxonFrameworkAppConfig;
import org.jline.builtins.Completers.TreeCompleter;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class ShellApp {

    public static final String COMMAND_CREATE_SHOPPING_CART = "createShoppingCart";

    private final Client client;
    private final Terminal terminal;
    //reader to be used to read the prompts/commands issued by the user
    private final LineReader userPromptReader;

    public static void main(String[] args) throws IOException {
        ShellApp shellApp = new ShellApp();
        shellApp.commandLoop();
    }

    public ShellApp() throws IOException {
        this.client = new Client(AxonFrameworkAppConfig.getConfig());

        terminal = TerminalBuilder.terminal();

        TreeCompleter treeCompleter = new TreeCompleter(
                TreeCompleter.node(COMMAND_CREATE_SHOPPING_CART)
        );

        DefaultParser parser = new DefaultParser();
        parser.setEscapeChars(null);
        parser.setQuoteChars(new char[] {'\''});

        this.userPromptReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(parser)
                .completer(treeCompleter)
                .build();
    }

    private void commandLoop() {
        String line;
        while(true) {
            line = userPromptReader.readLine("Shopping Cart client -> ").trim();

            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                break;
            }

            ParsedLine parsedLine = userPromptReader.getParser().parse(line, 0);
            String command = parsedLine.words().get(0);
            String param = parsedLine.words().size() > 1? parsedLine.words().get(1): null;

            try {
                switch (command) {
                    case COMMAND_CREATE_SHOPPING_CART:
                        if (param == null) {
                            terminal.writer().println("Error! Usage: " + COMMAND_CREATE_SHOPPING_CART + " <userId>");
                        } else {
                            client.createShoppingCart(param);
                        }
                        break;
                }
            } catch (Exception e) {
                terminal.writer().println("Exception: [" + e.getClass().getSimpleName() + "] " +  e.getMessage());
            }
        }
    }

}
