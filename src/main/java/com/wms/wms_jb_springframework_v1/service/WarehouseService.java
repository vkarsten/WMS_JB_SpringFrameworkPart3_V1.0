package com.wms.wms_jb_springframework_v1.service;

import com.wms.wms_jb_springframework_v1.model.Item;
import com.wms.wms_jb_springframework_v1.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class WarehouseService {

    public Set<Integer> getWarehouses() { return WarehouseRepository.getWarehouses(); }

    public List<Item> getAllItems() { return WarehouseRepository.getAllItems(); }

    public List<Item> getItemsByWarehouse(int warehouse) { return WarehouseRepository.getItemsByWarehouse(warehouse); }

    public Set<String> getCategories() { return WarehouseRepository.getCategories(); }

    public List<Item> getItemsByCategory(String category) { return WarehouseRepository.getItemsByCategory(category); }

    public List<Item> getSearchItems(String keyword) {
        List<Item> items = WarehouseRepository.getAllItems();
        List<Item> result = new ArrayList<>();
        String itemName;

        for (Item item : items) {
            itemName = item.getState() + " " + item.getCategory();
            if (StringUtils.containsIgnoreCase(itemName, keyword, Locale.ENGLISH)) { result.add(item); }
        }

        return result;
    }

    public List<Item> find(String item) {
        List<Item> items = WarehouseRepository.getAllItems();
        List<Item> result = new ArrayList<>();
        String itemName;

        for (Item element : items) {
            itemName = element.getState() + " " + element.getCategory();
            if (itemName.equalsIgnoreCase(item)) { result.add(element); }
        }

        return result;
    }

    public void removeItemsFromRepositoryAfterOrder(String item, int orderAmount) {
//        List<Item> items = WarehouseRepository.getAllItems();
//        int removedItems = 0;
//        String itemName;
//
//        for (Item element : items) {
//            if (removedItems == orderAmount) { break; }
//
//            itemName = element.getState() + " " + element.getCategory();
//
//            if (itemName.equalsIgnoreCase(item)) {
//                    items.remove(element);
//                    removedItems++;
//            }
//        }
    }
}
