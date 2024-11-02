package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Item;
import org.example.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/save")
    ResponseEntity<String> saveItem(@RequestBody Item item){
        try{
            itemService.saveItem(item);
            return ResponseEntity.ok("Item saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    ResponseEntity<List<Item>> getAllItems(){
        try{
            return ResponseEntity.ok(itemService.getAllItems());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
