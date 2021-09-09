package epam.task.finaltaskepam.dto.order;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Aleksandr Ovcharenko
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 6297385302078201511L;

    private int orderId;
    private Status orderStatus;
    private int userId;

    public static final class Builder {
        private final Order order;

        public Builder() {
            order = new Order();
        }

        public Builder withOrderId(int orderId) {
            order.orderId = orderId;
            return this;
        }

        public Builder withOrderStatus(Status orderStatus) {
            order.orderStatus = orderStatus;
            return this;
        }

        public Builder withUserId(int userId) {
            order.userId = userId;
            return this;
        }

        public Order build() {
            return order;
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public Order setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Order setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                userId == order.userId &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderStatus, userId);
    }
}
