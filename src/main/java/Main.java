import dao.PaymentDAO;
import dto.OrderDTO;
import dto.UserDetailsDTO;
import entity.enums.CardAuthorizationInfo;
import service.PaymentService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");

        long placeholderId = 1;
        UserDetailsDTO placeholderUserDetails = new UserDetailsDTO("placeholder_username", CardAuthorizationInfo.AUTHORIZED);
        PaymentService paymentService = new PaymentService();
        OrderDTO placeholderOrderDTO = paymentService.performPayment(placeholderId, placeholderUserDetails);
        System.out.println(placeholderOrderDTO.getStatus());
        System.out.println(placeholderOrderDTO.getId());
        PaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.getPaymentList();
    }
}
