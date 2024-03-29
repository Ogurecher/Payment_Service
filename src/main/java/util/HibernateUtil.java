package util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration()
				.configure() // configures settings from hibernate.cfg.xml
				.buildSessionFactory();
		}
		catch (Exception e){
			e.printStackTrace();
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

