package swagger;

import com.google.gson.Gson;
import dao.PaymentDAO;
import dto.UserDetailsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.config.BeanConfig;
import service.ItemService;
import service.OrderService;
import service.PaymentService;
import util.CustomLogger;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.port;
import static spark.Spark.put;

@Api(value = "Payment Service", description = "Payment Service API")
public class SwaggerApplication extends Application {

    public SwaggerApplication() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("org.jazzteam");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

    private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());
    private static PaymentService paymentService = new PaymentService(
            logger,
            new PaymentDAO(logger),
            new OrderService(),
            new ItemService()
    );

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
}

