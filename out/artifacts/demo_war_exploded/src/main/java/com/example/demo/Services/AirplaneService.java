package com.example.demo.Services;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Airplane;
import com.example.demo.Entity.Airport;
import com.example.demo.Entity.Flight;
import com.example.demo.Repositories.AirplaneRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class AirplaneService {
    @EJB
    AirplaneRepository airplaneRepository;

    public List<Airplane> getAll(){ return airplaneRepository.getAll();}

    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id, HibernateSessionFactoryUtil.getSessionFactory().openSession());
    }

    public void createNewAirplane(Airplane airport) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        airplaneRepository.save(airport, session);
        tx1.commit();
        session.close();
    }

    public void updateAirplaneByName(Long id, String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Airplane airplane = airplaneRepository.findById(id, session);
        airplane.setName(name);
        airplaneRepository.update(airplane,session);
        tx1.commit();
        session.close();
    }

    public void deleteAirplaneById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Airplane airplane = airplaneRepository.findById(id,session);
        airplaneRepository.delete(airplane, session);
        tx1.commit();
        session.close();
    }

    public void addAirplaneToFlight(Long airplaneId, Long flightId){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        airplaneRepository.addToFlight(airplaneId, flightId, session);
        tx1.commit();
        session.close();
    }
}
