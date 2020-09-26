package com.mondoDb.crud.controller;

import com.mondoDb.crud.dao.ProductDAO;
import com.mondoDb.crud.model.Product;
import com.mondoDb.crud.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    SequenceService sequenceService;

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        product.setId(sequenceService.generateSequence(product.SEQUENCE_NAME));
        return productDAO.save(product);
    }

    @GetMapping("/read")
    public List<Product> read() {
        return productDAO.findAll();
    }

    @GetMapping("/read/{id}")
    public Product read(@PathVariable Long id) {
        Optional<Product> product = productDAO.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("product not found with id " + id);
        }
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return productDAO.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Product> product = productDAO.findById(id);
        if (product.isPresent()) {
            productDAO.delete(product.get());
            return "product deleted with id " + id;
        } else {
            throw new RuntimeException("product not found for id " + id);
        }
    }
}
