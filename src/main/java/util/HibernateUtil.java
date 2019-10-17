package util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;
import java.util.logging.Level;

public class HibernateUtil {
	private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());
	private static SessionFactory sessionFactory;

	private  HibernateUtil(){}
	static {
		try {
			sessionFactory = new Configuration()
				.configure() // configures settings from hibernate.cfg.xml
				.buildSessionFactory();
		}
		catch (Exception e){
			//e.printStackTrace();
			logger.log(Level.SEVERE, "Hibernate error: could not establish session");
		}
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

	public static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
}

