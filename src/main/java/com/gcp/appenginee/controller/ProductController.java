package com.gcp.appenginee.controller;

import com.gcp.appenginee.model.Products;
import com.gcp.appenginee.model.ProductsDTO;
import com.gcp.appenginee.repository.ProductRepository;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepo;

    @GetMapping("/welcome")
    public String welcome() {
        return "your rest endpoint works";
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ProductsDTO productDTO) {
        Products product = new Products();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        Products prod = productRepo.save(product);
        return generateResponse("Items saved successfully!", HttpStatus.OK, prod);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map,status);
    }

    @GetMapping("/getItems")
    public ResponseEntity<Object> getItems(){
        List<Products> items = productRepo.findAll();
        return generateResponse("Complete Data!", HttpStatus.OK, items);
    }
}
