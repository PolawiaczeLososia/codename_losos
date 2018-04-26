package com.erpsystem.entity.simple_item;

import com.erpsystem.entity.simple_item.detail.SimpleItemDetailRepository;
import com.erpsystem.entity.simple_item.simple_item.SimpleItem;
import com.erpsystem.entity.simple_item.simple_item.SimpleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleItemServiceImpl implements SimpleItemService {

    @Autowired
    private SimpleItemRepository repositoryItem;

    @Autowired
    SimpleItemDetailRepository repositoryDetail;

    @Override
    public SimpleItem save(SimpleItem item) {
        return repositoryItem.save(item);
    }

    @Override
    public SimpleItem deleteById(Integer id) {
        SimpleItem item = repositoryItem.findById(id).orElse(null);
        if(item != null)
            repositoryItem.deleteById(id);
        return item;
    }

    @Override
    public SimpleItem overwrite(SimpleItem item) {
        if(repositoryItem.findById(item.getId()).orElse(null)==null)
            return null;
        else
            return repositoryItem.save(item);
    }

    @Override
    public List<SimpleItem> findAll() {

        List<SimpleItem> items = new ArrayList<>();
        repositoryItem.findAll().forEach(items::add);

        return items;
    }

    @Override
    public SimpleItem findById(Integer id) {
        return repositoryItem.findById(id).orElse(null);
    }

}
