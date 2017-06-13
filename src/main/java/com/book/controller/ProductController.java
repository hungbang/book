package com.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by KAI on 5/10/17.
 */
@RestController
public class ProductController {

    @GetMapping("/getList/{id}")
    public ResponseEntity<String> list(@PathVariable("id") String id){
        return ResponseEntity.ok("This is response." + id);
    }

    @PostMapping("/getList2")
    public ResponseEntity<String> list2(@RequestParam String id){
        return ResponseEntity.ok("this post method. " + id);
    }
    @PostMapping(value = "/getList3")
    public ResponseEntity<String> list3(@RequestBody String id){
        return ResponseEntity.ok("this post method. " + id);
    }
}
