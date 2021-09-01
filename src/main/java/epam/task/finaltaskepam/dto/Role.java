package epam.task.finaltaskepam.dto;

/**
 * @author Aleksandr Ovcharenko
 */
public enum Role {
    VISITOR, CUSTOMER, MANAGER;

    public static Role getRole(AppUserDto user) {
        int roleId = user.getRole();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
