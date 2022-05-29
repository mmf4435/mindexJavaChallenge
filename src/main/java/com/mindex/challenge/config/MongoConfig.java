


package com.mindex.challenge.config;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = EmployeeRepository.class)
@Configuration
public class MongoConfig{
    //template for employee repo
    @Primary
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoDbFactory(mongoClient));
    }

    //template for compensation repo
    @Bean(name = "compTemplate")
    public MongoTemplate compTemplate(MongoClient client){
        return new MongoTemplate(compMongoDbFactory(client));
    }

    //factory for employee repo
    @Primary
    @Bean
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDbFactory(mongoClient, "test");
    }

    //factory for compensation repo
    @Bean
    public MongoDbFactory compMongoDbFactory(MongoClient client){
        return new SimpleMongoClientDbFactory(client, "test2");
    }

    @Bean(destroyMethod="shutdown")
    public MongoServer mongoServer() {
        MongoServer mongoServer = new MongoServer(new MemoryBackend());
        mongoServer.bind();
        return mongoServer;
    }

    @Bean(destroyMethod="close")
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb:/" + mongoServer().getLocalAddress());
    }
}