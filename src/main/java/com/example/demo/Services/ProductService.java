package com.example.demo.Services;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Product;
import com.example.demo.Repositories.ProductRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import java.util.List;
@Stateful
public class ProductService {
    @EJB
    ProductRepository productRepository;

    public List<Product> getAll(){ return productRepository.getAll();}

    public Product getProducttById(Long id) {
        return productRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewProduct(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        productRepository.save(product, session);
        tx1.commit();
        session.close();
    }

    public void updateProductPrice(Long id, int price) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Product product = productRepository.findById(id, session);
        product.setPrice(price);
        productRepository.update(product,session);
        tx1.commit();
        session.close();
    }

    public void deleteProductById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Product product = productRepository.findById(id,session);
        productRepository.delete(product, session);
        tx1.commit();
        session.close();
    }
}
