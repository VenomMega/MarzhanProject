package com.example.demo.RepositoryImpl;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Airport;
import com.example.demo.Repositories.AirportRepository;
import org.hibernate.Session;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class AirportRepositoryImpl implements AirportRepository{

    @Override
    public List<Airport> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
    }

    @Override
    public Airport findById(Long id, Session session) {
        return session.get(Airport.class, id);
    }

    @Override
    public void save(Airport t, Session session) {
        session.save(t);
    }

    @Override
    public void update(Airport t, Session session) {
        session.update(t);
    }

    @Override
    public void delete(Airport t, Session session) {
        session.delete(t);
    }


}
