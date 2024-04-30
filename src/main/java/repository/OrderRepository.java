package repository;

import onlinestore.model.order.Order;

public class OrderRepository implements Repository<Order>{

    @Override
    public void saveEntity(Order entity) {
        DataStorage.orders.add(entity);
    }
}
