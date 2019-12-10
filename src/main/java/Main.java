import static spark.Spark.*;

import com.google.gson.Gson;
import dao.PaymentDAO;
import dto.UserDetailsDTO;
import service.PaymentService;
import util.CustomLogger;
import util.HibernateUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());
    private static PaymentService paymentService = new PaymentService(
            new PaymentDAO(new HibernateUtil())
    );

    public static void main(String[] args) {
        CustomLogger.init();
        port(1810);

        put("/api/orders/:orderId/payment", (req, res) -> {
            logger.log(Level.INFO, "Got an API call " + req.url() + " with body " + req.body());
            try {
                return paymentService.performPayment(
                        Long.parseLong(req.params("orderId")),
                        new Gson().fromJson(req.body(), UserDetailsDTO.class)
                );
            } catch (Exception e) {
                Gson gson = new Gson();
                return gson.toJson(e.getMessage());
            }
        });
    }
}
