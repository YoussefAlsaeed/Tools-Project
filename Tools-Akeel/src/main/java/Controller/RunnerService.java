package Controller;

import entity.Order;
import entity.OrderStatus;
import entity.Restaurant;
import entity.RestaurantReport;
import entity.Runner;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.List;
//@RolesAllowed("RUNNER")
@Stateless
@Path("/runner")
public class RunnerService {
	@PersistenceContext
	private EntityManager entityManager;
	@Path("/markOrderAsDelivered/{orderId}/{runnerID}")
	@PUT
    public String markOrderAsDelivered(@PathParam("orderId")String orderId,@PathParam("runnerID") String runnerId) {
        Order order =entityManager.find(Order.class,orderId);
        Runner runner = entityManager.find(Runner.class,runnerId);

        
        if (order.getStatus().equals(OrderStatus.PREPARING)) {
            return "Order is not accepted yet '~' ";
        }
        if (order.getStatus() == OrderStatus.CANCELED) {
            return "the order is already canceled '~' ";
        }
        if (order.getStatus() == OrderStatus.DELIVERED) {
            return "the order is already delivered '-' ";
        }
        
        if (order.getStatus().equals(OrderStatus.DELIVERING)) {
            order.setStatus(OrderStatus.DELIVERED);
        
            runner.setAvailable(true);
            entityManager.persist(order);
            entityManager.persist(runner);
            Restaurant restaurant =order.getRestauarant();
            RestaurantReport restaurantReport = restaurant.getRestaurantReport();
            restaurantReport.addNumberOfCompletedOrders();
            restaurantReport.addTotalAmountEarned(order.getTotal());

        }
        return "orderId: " + orderId + "  is deliverd ;-) ";
    }
	



}
