import static spark.Spark.*;

import com.google.gson.Gson;
import dto.UserDetailsDTO;
import service.PaymentService;

public class Main {
    private static PaymentService paymentService = new PaymentService();

    public static void main(String[] args) {
        port(1810);

        exception(Exception.class, (exception, request, response) -> exception.printStackTrace());

        put("/api/orders/:orderId/payment", (req, res) ->
                paymentService.performPayment(
                        Long.parseLong(req.params("orderId")),
                        new Gson().fromJson(req.body(), UserDetailsDTO.class)
                )
        );
    }
}
