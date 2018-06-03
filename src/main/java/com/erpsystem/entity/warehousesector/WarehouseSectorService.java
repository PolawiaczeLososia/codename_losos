package com.erpsystem.entity.warehousesector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseSectorService {

    @Autowired
    private WarehouseSectorRepository warehouseSectorRepository;

    public WarehouseSector save(WarehouseSector warehouseSector){
        warehouseSectorRepository.save(warehouseSector);
        return warehouseSector;
    }

    public List<WarehouseSector> findAll(){
        return warehouseSectorRepository.findAll();
    }

    public WarehouseSector findById(String id){
        return warehouseSectorRepository.findById(id).orElse(null);
    }

    public WarehouseSector overwrite(WarehouseSector warehouseSector){
        WarehouseSector overwrittenWarehouseSector = warehouseSectorRepository.findById(warehouseSector.getId()).orElse(null);
        if(overwrittenWarehouseSector != null){
            warehouseSectorRepository.save(warehouseSector);
        }
        return overwrittenWarehouseSector;
    }

    public void deleteAll(){
        warehouseSectorRepository.deleteAll();
    }

    public void delete(WarehouseSector warehouseSector){
        warehouseSectorRepository.delete(warehouseSector);
    }

    public WarehouseSector deleteById(String id) {
        WarehouseSector warehouseSector = warehouseSectorRepository.findById(id).orElse(null);
        warehouseSectorRepository.deleteById(id);
        return warehouseSector;
    }
}
