package com.example.demo.Repositories;

import com.example.demo.Entity.Product;
import org.hibernate.Session;

import java.util.List;


public interface ProductRepository {
    List<Product> getAll();
    Product findById(Long id, Session session);
    void save(Product t, Session session);
    void update(Product t, Session session);
    void delete(Product t, Session session);
}

// Airport - product