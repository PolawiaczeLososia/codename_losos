package com.erpsystem.entity.complex_item;

import com.erpsystem.entity.complex_item.complex_item.ComplexItem;

import java.util.List;

public interface ComplexItemService {
    ComplexItem save(ComplexItem item);
    ComplexItem deleteById(Integer id);
    ComplexItem overwrite(ComplexItem item);

    List<ComplexItem> findAll();
    ComplexItem findById(Integer id);
}
