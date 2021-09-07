package epam.task.finaltaskepam;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author Aleksandr Ovcharenko
 */
public class Demo {

    public static final int ID_LENGTH = 10;

    public String generateUniqueId() {
        return RandomStringUtils.randomAlphanumeric(ID_LENGTH);
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.generateUniqueId());
    }
}
