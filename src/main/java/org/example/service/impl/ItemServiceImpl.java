package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.Item;
import org.example.entity.ItemEntity;
import org.example.repository.ItemRepository;
import org.example.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;
    @Override
    public void saveItem(Item item) {
        try {
            itemRepository.save(modelMapper.map(item, ItemEntity.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getAllItems() {
        ArrayList<Item> itemArrayList=new ArrayList<>();
        try {
            List<ItemEntity> itemEntities = itemRepository.findAll();
            itemEntities.forEach(itemEntity -> {
                itemArrayList.add(modelMapper.map(itemEntity, Item.class));
            });
            return itemArrayList;
        } catch (Exception e) {
            return null;
        }
    }
}
