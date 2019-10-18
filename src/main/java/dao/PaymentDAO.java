package dao;

import entity.Payment;
import util.HibernateUtil;
import org.hibernate.Session;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentDAO {
    private Logger logger;

    public PaymentDAO(Logger logger) {
        this.logger = logger;
    }

    public void save(Payment obj) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
            logger.log(Level.INFO, "Database: successfully saved payment " + obj.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            logger.log(Level.SEVERE, "Database: could not save payment " + obj.toString());
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}