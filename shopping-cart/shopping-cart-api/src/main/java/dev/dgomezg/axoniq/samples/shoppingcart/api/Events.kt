package dev.dgomezg.axoniq.samples.shoppingcart.api

data class ShoppingCartCreatedEvent(val cartId: String, val customerId: String)