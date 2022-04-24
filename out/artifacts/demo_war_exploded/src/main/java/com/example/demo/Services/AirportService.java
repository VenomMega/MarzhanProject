package com.example.demo.Services;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Airport;
import com.example.demo.Repositories.AirportRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import java.util.List;
@Stateful
public class AirportService {
    @EJB
    AirportRepository airportRepository;

    public List<Airport> getAll(){ return airportRepository.getAll();}

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewAirport(Airport airport) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        airportRepository.save(airport, session);
        tx1.commit();
        session.close();
    }

    public void updateAirportByName(Long id, String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Airport airport = airportRepository.findById(id, session);
        airport.setName(name);
        airportRepository.update(airport,session);
        tx1.commit();
        session.close();
    }

    public void deleteAirportById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Airport airport = airportRepository.findById(id,session);
        airportRepository.delete(airport, session);
        tx1.commit();
        session.close();
    }
}
