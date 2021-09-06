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
    private String price;
    private String category;

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

    public String getPrice() {
        return price;
    }

    public Dish setPrice(String price) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return dishId == dish.dishId &&
                price == dish.price &&
                Objects.equals(name, dish.name) &&
                Objects.equals(category, dish.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, name, price, category);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishId=" + dishId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
