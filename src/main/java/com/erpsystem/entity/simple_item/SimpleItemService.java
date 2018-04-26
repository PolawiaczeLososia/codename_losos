package com.erpsystem.entity.simple_item;

import com.erpsystem.entity.simple_item.simple_item.SimpleItem;

import java.util.List;

public interface SimpleItemService {
    SimpleItem save(SimpleItem item);
    SimpleItem deleteById(Integer id);
    SimpleItem overwrite(SimpleItem item);

    List<SimpleItem> findAll();
    SimpleItem findById(Integer id);
}
