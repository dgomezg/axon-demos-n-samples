# Shopping Cart Console Client App

This module implements a Client App for the Shopping Cart demo that allows, through 
a console prompt (the Shell), the user to issue some commands that will be handled by
the Client and sent, as Commands, via AxonFramework's <code>commandGateway</code>.

This module allows to easily show how to send commands directly to the MessageBroker from the console

For example:

<pre>
Shopping Cart client -> createShoppingCart
Error! Usage: createShoppingCart userId
Shopping Cart client -> createShoppingCart dgomezg
Exception: [NoHandlerForCommandException] No Handler for command: dev.dgomezg.axoniq.samples.shoppingcart.api.CreateShoppingCartCommand
Shopping Cart client -> createShoppingCart dgomezg
Sending CreateShoppingCartCommand
Shopping Cart client -> exit
</pre>

## See Also

This module is inspired in the [memories-demo-client module](https://github.com/azzazzel/memories-talk-demo/tree/master/memories-demo-client)
from [azzazzel's memories-demo-talk](https://github.com/azzazzel/memories-talk-demo/) project used for his [Give your application memories talk](https://www.youtube.com/watch?v=pKIHqhHga1s) 