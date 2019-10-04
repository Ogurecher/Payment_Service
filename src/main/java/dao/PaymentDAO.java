package dao;

import dto.OrderDTO;
import entity.Order;
import entity.Payment;
import util.HibernateUtil;
import org.hibernate.Session;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentDAO {

    public void save(Payment obj) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    public List<Payment> getPaymentList() {
        Session session = null;
        List<Payment> paymentList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            CriteriaQuery<Payment> query = session.getCriteriaBuilder().createQuery(Payment.class);
            query.from(Payment.class);
            paymentList = session.createQuery(query).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
        return paymentList.stream().collect(Collectors.toList());
    }
}