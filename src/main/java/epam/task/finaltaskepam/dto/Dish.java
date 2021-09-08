package epam.task.finaltaskepam.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Aleksandr Ovcharenko
 */
public class Dish implements Serializable {
    private static final long serialVersionUID = 6297385302078200522L;

    private int dishId;
    private String name;
    private long price;
    private String category;
    private int amount;

    public static final class Builder {
        private final Dish dish;

        public Builder() {
            dish = new Dish();
        }

        public Builder withDishId(int dishId) {
            dish.dishId = dishId;
            return this;
        }

        public Builder withName(String name) {
            dish.name = name;
            return this;
        }

        public Builder withPrice(long price) {
            dish.price = price;
            return this;
        }

        public Builder withCategory(String category) {
            dish.category = category;
            return this;
        }

        public Builder withAmount(int amount) {
            dish.amount = amount;
            return this;
        }

        public Dish build() {
            return dish;
        }
    }

    public int getDishId() {
        return dishId;
    }

    public Dish setDishId(int dishId) {
        this.dishId = dishId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dish setName(String name) {
        this.name = name;
        return this;
    }

    public long getPrice() {
        return price;
    }

    public Dish setPrice(long price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Dish setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Dish setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return dishId == dish.dishId &&
                price == dish.price &&
                amount == dish.amount &&
                Objects.equals(name, dish.name) &&
                Objects.equals(category, dish.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, name, price, category, amount);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
