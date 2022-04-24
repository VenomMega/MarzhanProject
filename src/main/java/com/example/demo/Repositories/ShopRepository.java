package com.example.demo.Repositories;

import com.example.demo.Entity.Shop;
import org.hibernate.Session;

import java.util.List;

public interface ShopRepository {
    List<Shop> getAll();
    Shop findById(Long id, Session session);
    void save(Shop t, Session session);
    void update(Shop t, Session session);
    void delete(Shop t, Session session);
}
// Flight - shop
