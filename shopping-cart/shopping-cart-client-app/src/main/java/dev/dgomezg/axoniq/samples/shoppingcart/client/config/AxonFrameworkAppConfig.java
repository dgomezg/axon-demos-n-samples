package dev.dgomezg.axoniq.samples.shoppingcart.client.config;

import com.thoughtworks.xstream.XStream;
import org.axonframework.config.Configuration;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.xml.XStreamSerializer;

public class AxonFrameworkAppConfig {
    private static AxonFrameworkAppConfig INSTANCE;

    private final Configuration configuration;

    private AxonFrameworkAppConfig() {
        System.setProperty("axon.application.name","shopping-cart-client");

        configuration = defaultConfiguration().buildConfiguration();
        configuration.start();
    }

    public static Configuration getConfig(){
        start();
        return INSTANCE.configuration;
    }

    public synchronized static void start() {
        if (INSTANCE == null) {
            INSTANCE = new AxonFrameworkAppConfig();
        }
    }

    public static void shutdown() {
        if (INSTANCE != null) {
            INSTANCE.configuration.shutdown();
        }
    }

    public static Configurer defaultConfiguration() {
        // By default, we'll use the XStreamSerailizer (recommended to set the secure types on it)
        Serializer defaultSerializer = getDefaultXStreamSerializer();

        return DefaultConfigurer.defaultConfiguration()
                .configureSerializer(config -> defaultSerializer);
    }

    private static Serializer getDefaultXStreamSerializer() {
        XStream xStream = new XStream();
        XStreamSerializer defaultSerializer = XStreamSerializer.builder()
                                                                .xStream(xStream)
                                                                .build();
        return defaultSerializer;
    }

    private static Serializer getSecureXStreamSerializer() {
        XStream xStream = new XStream();
        //Set the secure types on the xtream instance.
        xStream.allowTypesByWildcard(new String [] {
                "org.axonframework.**",
                "dev.dgomezg.axoniq.samples.**"
        });
        XStreamSerializer defaultSerializer = XStreamSerializer.builder()
                .xStream(xStream)
                .build();
        return defaultSerializer;
    }

}
