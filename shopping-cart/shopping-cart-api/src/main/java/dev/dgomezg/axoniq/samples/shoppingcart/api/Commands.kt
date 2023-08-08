package dev.dgomezg.axoniq.samples.shoppingcart.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateShoppingCartCommand(@TargetAggregateIdentifier val cartId: String, val customerId: String)