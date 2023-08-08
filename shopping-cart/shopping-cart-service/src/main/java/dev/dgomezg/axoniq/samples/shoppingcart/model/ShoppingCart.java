package dev.dgomezg.axoniq.samples.shoppingcart.model;

import dev.dgomezg.axoniq.samples.shoppingcart.api.CreateShoppingCartCommand;
import dev.dgomezg.axoniq.samples.shoppingcart.api.ShoppingCartCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static dev.dgomezg.axoniq.samples.shoppingcart.util.DemoLogger.LogAggregateLifecyclePhase.*;
import static dev.dgomezg.axoniq.samples.shoppingcart.util.DemoLogger.log;

@Aggregate
public class ShoppingCart {

    @AggregateIdentifier
    private String cartId;

    private String customerId;

    public ShoppingCart() {
        log(AGGREGATE_LOAD, "new ShoppingCart");
    }

    @CommandHandler
    public ShoppingCart(CreateShoppingCartCommand command) {
        log(CREATE, "handle 'CreateShoppingCartCommand'");

        log(SEND_EVENT, "send 'ShoppingCartCreatedEvent'");
        AggregateLifecycle.apply(new ShoppingCartCreatedEvent(command.getCartId(), command.getCustomerId()));

    }

    @EventSourcingHandler
    public void on(ShoppingCartCreatedEvent event) {
        this.cartId = event.getCartId();
        this.customerId = event.getCustomerId();

        log("[ ShoppingCartCreatedEvent ] setCartId = " + cartId
                + ", setCustomerId = " + customerId + "')");
    }
}
