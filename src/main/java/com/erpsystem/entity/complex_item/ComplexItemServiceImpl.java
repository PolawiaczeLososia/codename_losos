package com.erpsystem.entity.complex_item;

import com.erpsystem.entity.complex_item.complex_item.ComplexItemRepository;
import com.erpsystem.entity.complex_item.detail.ComplexItemDetailRepository;
import com.erpsystem.entity.complex_item.complex_item.ComplexItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComplexItemServiceImpl implements ComplexItemService {

    @Autowired
    private ComplexItemRepository repositoryItem;

    @Autowired
    private ComplexItemDetailRepository repositoryDetail;

    @Override
    public ComplexItem save(ComplexItem item) {
        return repositoryItem.save(item);
    }

    @Override
    public ComplexItem deleteById(Integer id) {
        ComplexItem item = repositoryItem.findById(id).orElse(null);
        if(item != null)
            repositoryItem.deleteById(id);
        return item;
    }

    @Override
    public ComplexItem overwrite(ComplexItem item) {
        if(repositoryItem.findById(item.getId()).orElse(null)==null)
            return null;
        else
            return repositoryItem.save(item);
    }

    @Override
    public List<ComplexItem> findAll() {

        List<ComplexItem> items = new ArrayList<>();
        repositoryItem.findAll().forEach(items::add);

        return items;
    }

    @Override
    public ComplexItem findById(Integer id) {
        return repositoryItem.findById(id).orElse(null);
    }

}
