package com.olegandreevich.messenger.util;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//@Component
//public class DatabaseInitializer {
//
//    @Autowired
//    private ReactiveMongoTemplate template;
//
//    public void init() {
//        initializeAdminUser(template).subscribe();
//        initializeMessengerUser(template).subscribe();
//    }
//
//    private Mono<Void> initializeAdminUser(ReactiveMongoTemplate template) {
//        return template.executeCommand("{ createUser: 'root', pwd: 'example', roles: [ { role: 'readWriteAnyDatabase', db: 'admin' } ] }").then();
//    }
//
//    private Mono<Void> initializeMessengerUser(ReactiveMongoTemplate template) {
//        return template.executeCommand("{ createUser: 'messenger_user', pwd: 'password', roles: [ { role: 'readWrite', db: 'messenger_db' } ] }").then();
//    }
//}
