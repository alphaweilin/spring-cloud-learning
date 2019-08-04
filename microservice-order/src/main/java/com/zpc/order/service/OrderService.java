package com.zpc.order.service;

import com.zpc.order.entity.Item;
import com.zpc.order.entity.Order;
import com.zpc.order.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    private static final Map<String, Order> ORDER_DATA = new HashMap<String, Order>();

    static {
        // 模拟数据库，构造测试数据
        Order order = new Order();
        order.setOrderId("201810300001");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

        Item item = new Item();// 此处并没有商品的数据，只是保存了商品ID，需要调用商品微服务获取
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        item = new Item(); // 构造第二个商品数据
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        order.setOrderDetails(orderDetails);

        ORDER_DATA.put(order.getOrderId(), order);
        
        
        Order order2 = new Order();
        order2.setOrderId("201810300002");
        order2.setCreateDate(new Date());
        order2.setUpdateDate(order2.getCreateDate());
        order2.setUserId(3L);
        List<OrderDetail> orderDetails2 = new ArrayList<OrderDetail>();

        Item item2 = new Item();// 此处并没有商品的数据，只是保存了商品ID，需要调用商品微服务获取
        item2.setId(3L);
        orderDetails2.add(new OrderDetail(order2.getOrderId(), item2));

        item2 = new Item(); // 构造第二个商品数据
        item2.setId(5L);
        orderDetails2.add(new OrderDetail(order2.getOrderId(), item2));
        order2.setOrderDetails(orderDetails2);
        ORDER_DATA.put(order2.getOrderId(), order2);
    }

    @Autowired
    private ItemService itemService;

    /**
     * 根据订单id查询订单数据
     *
     * @param orderId
     * @return
     */
    public Order queryOrderById(String orderId) {
        Order order = ORDER_DATA.get(orderId);
        if (null == order) {
            return null;
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            // 通过商品微服务查询商品详细数据
            Item item = this.itemService.queryItemById(orderDetail.getItem()
                    .getId());
            if (null == item) {
                continue;
            }
            orderDetail.setItem(item);
        }

        return order;
    }
    
    public Order queryOrderByIdx(String orderId) {
        Order order = ORDER_DATA.get(orderId);
        if (null == order) {
            return null;
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            // 通过商品微服务查询商品详细数据
            Item item = this.itemService.queryItemById3(orderDetail.getItem()
                    .getId());
            if (null == item) {
                continue;
            }
            orderDetail.setItem(item);
        }

        return order;
    }

}