package com.erpsystem.entity.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client save(Client client){
        repository.save(client);
        return client;
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client findById(String id){
        return repository.findById(id).orElse(null);
    }

    public Client overwrite(Client client){
        Client overwrittenClient = repository.findById(client.getId()).orElse(null);
        if(overwrittenClient != null){
            repository.save(client);
        }
        return overwrittenClient;
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public Client deleteById(String id) {
        Client client = repository.findById(id).orElse(null);
        repository.deleteById(id);
        return client;
    }
}
