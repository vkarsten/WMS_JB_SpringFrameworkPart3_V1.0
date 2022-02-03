package com.wms.wms_jb_springframework_v1.controller;

import com.wms.wms_jb_springframework_v1.model.Item;
import com.wms.wms_jb_springframework_v1.model.OrderItem;
import com.wms.wms_jb_springframework_v1.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {
    RestTemplate restTemplate;
    WarehouseService warehouseService;

    @Autowired
    public HomeController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/")
    public String showHome() {
        return "index.html";
    }

    @GetMapping("/listAllItems")
    public String getListAllItemsPage(HttpServletRequest request, Model model) {
        restTemplate = new RestTemplate();
        String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getAllItems";
        String warehouseResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getWarehouses";

        List<Item> response = restTemplate.getForObject(
                itemResourceUrl,
                List.class
        );

        Set<Integer> warehouseResponse = restTemplate.getForObject(
                warehouseResourceUrl,
                Set.class
        );

        model.addAttribute("warehouses", warehouseResponse);
        model.addAttribute("items", response);
        model.addAttribute("itemCount", response.size());
        return "items_list";
    }

    @GetMapping("/listItemsByWarehouse/{warehouseId}")
    public String getListItemByWarehousePage(HttpServletRequest request, Model model, @PathVariable("warehouseId") int warehouseId) {
        restTemplate = new RestTemplate();
        String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getAllItems/" + warehouseId;

        List<Item> response = restTemplate.getForObject(
                itemResourceUrl,
                List.class
        );

        model.addAttribute("warehouse", warehouseId);
        model.addAttribute("items", response);
        model.addAttribute("itemCount", response.size());
        return "items_list_by_warehouse";
    }

    @GetMapping("/browseByCategory")
    public String getBrowseByCategoryPage(HttpServletRequest request, Model model) {
        restTemplate = new RestTemplate();
        String categoryResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getCategories";

        Set<String> response = restTemplate.getForObject(
                categoryResourceUrl,
                Set.class
        );

        model.addAttribute("categories", response);
        model.addAttribute("categoryCount", response.size());
        return "browse_by_category";
    }

    @GetMapping("/browseByCategory/{category}")
    public String getListItemByCategoryPage(HttpServletRequest request, Model model, @PathVariable("category") String category) {
        restTemplate = new RestTemplate();
        String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/getItemsByCategory/" + category;

        List<Item> response = restTemplate.getForObject(
                itemResourceUrl,
                List.class
        );

        model.addAttribute("category", category);
        model.addAttribute("items", response);
        model.addAttribute("itemCount", response.size());
        return "browse_by_specific_category";
    }


    @RequestMapping(value = "/searchItem", method = RequestMethod.GET)
    public String searchItemByKeyword(@RequestParam(value = "search", defaultValue = "") String searchItem, HttpServletRequest request, Model model) {
        if (!searchItem.isBlank()) {
            restTemplate = new RestTemplate();

            String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/searchItem/" + searchItem;

            List<Item> response = restTemplate.getForObject(
                    itemResourceUrl,
                    List.class
            );

            model.addAttribute("search", response);
            model.addAttribute("itemCount", response.size());
        }

        return "search_items_page";
    }

    @RequestMapping("/loginPage")
    public String login(@RequestParam(value = "loginFailed", defaultValue = "false") Boolean loginFailed, Model model) {
        model.addAttribute("loginFailed", loginFailed);
        return "login";
    }

    @GetMapping("/orderPage")
    public String orderPage(@RequestParam(value = "state") String state,
                            @RequestParam(value = "category") String category,
                            Model model) {
        String itemName = state + " " + category;
        List<Item> availableItems = warehouseService.find(itemName);

        model.addAttribute("availableItems", availableItems);
        model.addAttribute("count", availableItems.size());
        model.addAttribute("orderItem", new OrderItem(itemName, 1));
        return "order_item_page";
    }

    @PostMapping("/orderPage")
    public String postOrderPage(@ModelAttribute("orderItem") @Valid OrderItem orderItem, HttpServletRequest request, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "order_item_page";
        }

        restTemplate = new RestTemplate();

        String itemResourceUrl = "http://localhost:" + request.getLocalPort() + "/warehouse/removeItems/" + orderItem.getItemName() + "/" + orderItem.getQuantity();

        List<Item> response = restTemplate.getForObject(
                itemResourceUrl,
                List.class
        );

        model.addAttribute("orderedItems", orderItem.getItemName());
        model.addAttribute("amount", orderItem.getQuantity());
        return "post_order_item_page";
    }
}
