package re.bt3.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private List<Item> inventory = new ArrayList<>();

    static class Item {
        private Long id;
        private String name;
        private Integer quantity;

        public Item() {}
        public Item(Long id, String name, Integer quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Item> getAllItems() {
        return inventory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return inventory.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .map(item -> ResponseEntity.ok(item))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        inventory.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemUpdates) {
        for (Item item : inventory) {
            if (item.getId().equals(id)) {
                item.setName(itemUpdates.getName());
                item.setQuantity(itemUpdates.getQuantity());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean removed = inventory.removeIf(i -> i.getId().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}