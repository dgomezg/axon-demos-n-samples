# Shopping Cart demo

This project shows a Shopping Cart application implemented with AxonFramework 
following the DDD, CQRS and Event-Sourcing.

## Modules

This ShoppingCart example is a multi-module project composed of the following modules:
- [Shopoing Cart API](./shopping-cart-api) is a module containing the definition of Messages (Commands, Events and Queries) and any other classes shared by different modules.
- [Shopping Cart Service](./shopping-cart-service) is an SpringBoot App that defines the Service implementation (the CommandHandlers) for the ShoppingCart
- [Shopping Cart Client App](./shopping-cart-client-app) is simple java app that offers a command line prompt to allow the user sending some commands to the service. 


## Running the code.

In order to run the code you will need to start an Axon Server instance. 

Axon Server is  AxonIQ's Message broker and Event Store that is specifically designed for distributed Event-based applications
designed following the principles of Event-Sourcing, CQRS and DDD.

> [!NOTE]  
 You don't require any license to run Axon Server and, although some enterprise features are enabled when a license is
> provided, the Server is fully operational without any license.
> 
> You can read more about Axon Server in [AxonIQ Developer Portal](https://developer.axoniq.io/axon-server/) or download 
> from the [Developer portal's downloads page](https://developer.axoniq.io/download)


### 1. Starting Axon Server

You can start Axon Server either as an standalone application in your machine, or via docker. 
#### Dowloading and starting Axon Server in your machine

```console
$ wget https://download.axoniq.io/axonserver/AxonServer.zip

‘AxonServer.zip’ saved

$ unzip AxonServer.zip
Archive:  AxonServer.zip
   creating: AxonServer-2023.1.1/
   ...
   
$ cd AxonServer-2023.1.1

AxonServer-2023.1.1 $ java -jar axonserver.jar
    _                     ____
    / \   __  _____  _ __ / ___|  ___ _ ____   _____ _ __
   / _ \  \ \/ / _ \| '_ \\___ \ / _ \ '__\ \ / / _ \ '__|
  / ___ \  >  < (_) | | | |___) |  __/ |   \ V /  __/ |
 /_/   \_\/_/\_\___/|_| |_|____/ \___|_|    \_/ \___|_|
 2023.1.1                      Powered by AxonIQ

INFO 40179 --- [main] io.axoniq.axonserver.AxonServer          : Starting AxonServer using Java 20 on Pokelene.home with PID 40179 (/Users/dgomezg/tmp/AxonServer-2023.1.1/axonserver.jar started by dgomezg in /Users/dgomezg/tmp/AxonServer-2023.1.1)
INFO 40179 --- [main] io.axoniq.axonserver.AxonServer          : No active profile set, falling back to 1 default profile: "default"
...
INFO 40179 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8024 (http) with context path ''
INFO 40179 --- [main] io.axoniq.axonserver.grpc.Gateway        : Axon Server Gateway started on port: 8124 - no SSL
INFO 40179 --- [main] io.axoniq.axonserver.AxonServer          : Started AxonServer in 46.192 seconds (JVM running for 47.79)
```
After a few seconds you open the URL [http://localhost:8024]()http://localhost:8024 with your browser to check that the 
server is running and Finishing the setup configuring the instance as a standalone service using the console.

#### Running AxonServer via Docker

Alternatively, if you have Docker installed in your machine, you can simply run Axon Server with Docker:

```console
$ docker run -d --name my-axon-server -p 8024:8024 -p 8124:8124 axoniq/axonserver
```

### 2. Running the ShoppingCart example.

To run the demo you need to run the [Shopping Cart Service](./shopping-cart-service) and [Shopping Cart Client App](./shopping-cart-client-app) modules:
- For the `shopping-cart-service`, run from your IDE the `ShoppingCartServiceApplication`
- For the `shopping-cart-client-app`, run from your IDE the `ShellApp` class. You should see a prompt in the console 
 to start sending commands as it is described in the [Shopping Cart Client App README.md](./shopping-cart-client-app) 



## See Also
The sample is a merge and compilation of other different project's:

 - [The URJC Axon ShoppingCart Example](https://github.com/dgomezg/urjc-axon-shopping-cart-es-sample) I used as proposed 
assigment during a guest class for the Cloud Master at the URJC Univervisity in Madrid.
 - The Milen Dyankov's [memories-demo-talk repo](https://github.com/azzazzel/memories-talk-demo/) containing the demo used
during his [Give your application memories talk](https://www.youtube.com/watch?v=pKIHqhHga1s) 
 