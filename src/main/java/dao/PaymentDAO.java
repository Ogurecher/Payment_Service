package dao;

import entity.Payment;
import util.CustomLogger;
import util.HibernateUtil;
import org.hibernate.Session;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentDAO {
    private HibernateUtil hibernateUtil;
    private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());

    public PaymentDAO (HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public void save(Payment obj) {
        Session session = null;
        try {
            session = hibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
            logger.log(Level.INFO, "Database: successfully saved payment " + obj.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            logger.log(Level.SEVERE, "Database: could not save payment " + obj.toString());
        } finally {
            hibernateUtil.closeSession(session);
        }
    }
}