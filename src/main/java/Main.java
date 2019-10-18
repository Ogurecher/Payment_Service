/*import static spark.Spark.*;

import com.google.gson.Gson;
import dto.UserDetailsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import service.PaymentService;
import util.CustomLogger;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@Api (value = "Payment Service", description = "Payment Service API")
public class Main {
    private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());
    private static PaymentService paymentService = new PaymentService();

    public static void main(String[] args) {
        CustomLogger.init();

        port(1810);

        performPayment();
    }

    @Path("/api/orders/:orderId/payment")
    @PUT
    @ApiOperation(value = "Perform Payment")
    public static void performPayment() {
        put("/api/orders/:orderId/payment", (req, res) -> {
            logger.log(Level.INFO, "Got an API call " + req.url() + " with body " + req.body());
            return paymentService.performPayment(
                    Long.parseLong(req.params("orderId")),
                    new Gson().fromJson(req.body(), UserDetailsDTO.class)
            );
        });
    }
}*/
