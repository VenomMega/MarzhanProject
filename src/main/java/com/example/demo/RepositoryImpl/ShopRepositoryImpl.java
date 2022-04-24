package com.example.demo.RepositoryImpl;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Shop;
import com.example.demo.Repositories.ShopRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class ShopRepositoryImpl implements ShopRepository {
    @Override
    public List<Shop> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT f FROM Shop f", Shop.class).getResultList();

    }

    @Override
    public Shop findById(Long id, Session session) {
        return session.get(Shop.class, id);
    }

    @Override
    public void save(Shop shop, Session session) {
        session.save(shop);
    }

    @Override
    public void update(Shop shop, Session session) {
        session.update(shop);
    }

    @Override
    public void delete(Shop shop, Session session) {
        session.delete(shop);
    }
}
