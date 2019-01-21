package com.claragoncalves.peps.model.repository;

import android.app.Application;
import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.OrderDetailRoomDAO;
import com.claragoncalves.peps.model.dao.OrderRoomDAO;
import com.claragoncalves.peps.model.dao.ProductRoomDAO;
import com.claragoncalves.peps.model.pojo.Order;
import com.claragoncalves.peps.model.pojo.OrderContainer;
import com.claragoncalves.peps.model.pojo.OrderDetail;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepository {

    private OrderDetailRoomDAO orderDetailRoomDAO;
    private OrderRoomDAO orderRoomDAO;
    private ProductRoomDAO productRoomDAO;

    public OrderDetailRepository(Application application){
        PepsRoomDB pepsRoomDB = PepsRoomDB.getDatabase(application);
        orderDetailRoomDAO = pepsRoomDB.orderDetailDAO();
        orderRoomDAO = pepsRoomDB.orderDAO();
        productRoomDAO = pepsRoomDB.productDAO();
    }

    public List<Integer> getAllIdsOfOrdersFromContact(String contactId){
        return orderDetailRoomDAO.getAllIdsOfOrdersFromContact(contactId);
    }

    public List<OrderContainer> getAllOrderAndDetailsFromContact(String contactId){
        List<OrderContainer> orderContainers = new ArrayList<>();
        for (Integer orderId:getAllIdsOfOrdersFromContact(contactId)) {
            Order order = orderRoomDAO.getOrderById(orderId);
            OrderContainer orderContainer = new OrderContainer(order);
            orderContainer.setProducts(getAllProductsFromOrderDetails(getContactOrderDetailsFromOrder(contactId, orderId)));
            orderContainers.add(orderContainer);
        }

        return orderContainers;
    }

    public List<Product> getAllProductsFromOrderDetails(List<OrderDetail> orderDetails){
        List<Product> products = new ArrayList<>();
        for (OrderDetail detail:orderDetails) {
            Product product = productRoomDAO.getProductById(detail.getProductId());
            product.setQuantity(detail.getProductQuantity());
            products.add(product);
        }
        return products;
    }

    public void insertOrderDetails(List<OrderDetail> orderDetails){
        orderDetailRoomDAO.insertOrderDetails(orderDetails);
    }

    public List<OrderDetail> getAllOrderDetailsFromOrder(Integer orderId){
        return orderDetailRoomDAO.getAllOrderDetailsFromOrder(orderId);
    }


    public List<OrderDetail> getContactOrderDetailsFromOrder(String contactId, Integer orderId){
        return orderDetailRoomDAO.getContactOrderDetailsFromOrder(contactId, orderId);
    }

}
