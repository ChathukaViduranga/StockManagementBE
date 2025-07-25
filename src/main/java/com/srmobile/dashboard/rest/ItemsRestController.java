package com.srmobile.dashboard.rest;

import com.srmobile.dashboard.dao.ItemsDAO;
import com.srmobile.dashboard.entity.Items;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemsRestController {
    private ItemsDAO itemsDAO;

    public ItemsRestController(ItemsDAO itemsDAO) {
        this.itemsDAO = itemsDAO;
    }

    @GetMapping("/items")
    public List<Items> getAllItems() {
        return itemsDAO.findAll();
    }

    @GetMapping("/items/{id}")
    public Items getItemById(@PathVariable String id) {
        return itemsDAO.findById(id);
    }

    @PostMapping("/items")
    public Items saveItem(@RequestBody Items item) {
        return itemsDAO.save(item);
    }

    @PutMapping("/items/{id}")
    public Items changeStatus(@PathVariable String id, @RequestBody Items.ItemStatus status) {
        return itemsDAO.changeStatus(id, status);
    }

    @GetMapping("/items/available")
    public List<Items> getAvailableItems() {
        return itemsDAO.findAvailable();
    }

    @GetMapping("/items/available/{id}")
    public ResponseEntity<?> getAvailableItemById(@PathVariable String id) {
        Items item = itemsDAO.getAvailableItemById(id);
        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No AVAILABLE item found for id: " + id);
        }
        return ResponseEntity.ok(item);
    }

}
