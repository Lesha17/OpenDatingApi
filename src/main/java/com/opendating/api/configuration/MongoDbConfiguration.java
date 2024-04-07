package com.opendating.api.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MongoDbConfiguration {

    @Bean("opendatingCodecRegistry")
    public CodecRegistry opendatingCodecRegistry() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        return CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));
    }

    @Bean("mongoClient")
    public MongoClient mongoClient(@Value("${mongodb.connection.uri}") String connectionUri,
                                   @Qualifier("opendatingCodecRegistry") CodecRegistry codecRegistry) {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionUri))
                .codecRegistry(codecRegistry)
                .build();
        return MongoClients.create(mongoClientSettings);
    }
}
