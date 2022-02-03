package com.wms.wms_jb_springframework_v1.controller;

import com.wms.wms_jb_springframework_v1.model.Item;
import com.wms.wms_jb_springframework_v1.model.OrderItem;
import com.wms.wms_jb_springframework_v1.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping(path = "/getWarehouses")
    public Set<Integer> getWarehouses() {
        return warehouseService.getWarehouses();
    }

    @GetMapping(path = "/getAllItems")
    public List<Item> getAllItems() {
        return warehouseService.getAllItems();
    }

    @GetMapping(path = "/getAllItems/{warehouseId}")
    public List<Item> getItemsByWarehouse(@PathVariable("warehouseId") int warehouse) {
        return warehouseService.getItemsByWarehouse(warehouse);
    }

    @GetMapping(path = "/getCategories")
    public Set<String> getCategories() {
        return warehouseService.getCategories();
    }

    @GetMapping(path = "/getItemsByCategory/{category}")
    public List<Item> getItemsByCategory(@PathVariable("category") String category) {
        return warehouseService.getItemsByCategory(category);
    }

    @GetMapping(path = "/searchItem/{keyword}")
    public List<Item> searchItem(@PathVariable("keyword") String keyword) {
        return warehouseService.getSearchItems(keyword);
    }

    @GetMapping(path = "removeItems/{itemName}/{amount}")
    public void removeItems(@PathVariable("itemName") String itemName,
                            @PathVariable("amount") Integer amount) {
        warehouseService.removeItemsFromRepositoryAfterOrder(itemName, amount);
    }
}
