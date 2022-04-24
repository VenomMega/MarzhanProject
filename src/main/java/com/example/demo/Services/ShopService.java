package com.example.demo.Services;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.Shop;
import com.example.demo.Repositories.ShopRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class ShopService {
    @EJB
    ShopRepository shopRepository;

    public List<Shop> getAll(){ return shopRepository.getAll();}

    public Shop getShopById(Long id) {
        return shopRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewShop(Shop shop) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        shopRepository.save(shop, session);
        tx1.commit();
        session.close();
    }

    public void addProductToShop(Long id, Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Shop shop = shopRepository.findById(id, session);
        List<Product> productList = shop.getProducts();
        productList.add(product);
        shop.setProducts(productList);
        shopRepository.update(shop,session);
        tx1.commit();
        session.close();
    }

    public void deleteShopById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Shop shop = shopRepository.findById(id,session);
        shopRepository.delete(shop, session);
        tx1.commit();
        session.close();
    }
}
