package com.erpsystem.entity.client;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}