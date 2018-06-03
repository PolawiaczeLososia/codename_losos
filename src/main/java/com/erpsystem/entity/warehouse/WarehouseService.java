package com.erpsystem.entity.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseService {

  @Autowired
  private WarehouseRepository repository;

  public Warehouse save(Warehouse warehouse) {
    repository.save(warehouse);
    return warehouse;
  }

  public List<Warehouse> findAll() {
    return repository.findAll();
  }

  public Warehouse findById(String id) {
    return repository.findById(id).orElse(null);
  }

  public Warehouse overwrite(Warehouse warehouse) {
    Warehouse overwrittenWarehouse = repository.findById(warehouse.getId()).orElse(null);
    if (overwrittenWarehouse != null) {
      repository.save(warehouse);
    }
    return overwrittenWarehouse;
  }

  public void deleteAll() {
    repository.deleteAll();
  }

  public Warehouse deleteById(String id) {
    Warehouse warehouse = repository.findById(id).orElse(null);
    repository.deleteById(id);
    return warehouse;
  }
}
