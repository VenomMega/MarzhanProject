package com.example.demo.RepositoryImpl;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Product;
import com.example.demo.Repositories.ProductRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT a FROM Product a", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id, Session session) {
        return session.get(Product.class, id);
    }

    @Override
    public void save(Product t, Session session) {
        session.save(t);
    }

    @Override
    public void update(Product t, Session session) {
        session.update(t);
    }

    @Override
    public void delete(Product t, Session session) {
        session.delete(t);
    }


}
