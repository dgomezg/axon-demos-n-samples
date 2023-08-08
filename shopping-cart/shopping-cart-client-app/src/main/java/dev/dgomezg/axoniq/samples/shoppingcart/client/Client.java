package dev.dgomezg.axoniq.samples.shoppingcart.client;

import dev.dgomezg.axoniq.samples.shoppingcart.api.CreateShoppingCartCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.Configuration;

import java.util.UUID;

public class Client {

    private boolean verbose = true;

    private final CommandGateway commandGateway;


    public Client(Configuration axonConfig) {
        commandGateway = axonConfig.commandGateway();
    }

    public void createShoppingCart(String customerId) {
        String cartId = UUID.randomUUID().toString();

        // With the send method, is faster (fire and forget) but we won't be notified in case there is no CommandHandler
        // active in our system.
        //commandGateway.send(new CreateShoppingCartCommand(cartId, customerId));

        // With the sendAndWait method, the caller thread will wait until the server has confirmed the command was
        // delivered to the commandHandler, or it will throw a NoHandlerForCommandException otherwise.
        commandGateway.sendAndWait(new CreateShoppingCartCommand(cartId, customerId));
        doLog("Sending CreateShoppingCartCommand");
    }

    private void doLog(String msg) {
        if (verbose) {
            System.out.println(msg);
        }
    }
}
