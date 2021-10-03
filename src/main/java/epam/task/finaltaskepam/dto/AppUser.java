package epam.task.finaltaskepam.dto;

import java.io.Serializable;
import java.util.Objects;

public class AppUser implements Serializable {
    private static final long serialVersionUID = 6297385302078200511L;

    private int idUser;
    private String username;
    private String email;
    private String password;
    private String role;
    private AppUserPurse purse;

    public static final class Builder {
        private final AppUser appUser;

        public Builder() {
            appUser = new AppUser();
        }

        public Builder withIdUser(int idUser) {
            appUser.idUser = idUser;
            return this;
        }

        public Builder withUsername(String username) {
            appUser.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            appUser.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            appUser.email = email;
            return this;
        }

        public Builder withRole(String role) {
            appUser.role = role;
            return this;
        }

        public Builder withPurse(AppUserPurse purse) {
            appUser.purse = purse;
            return this;
        }

        public AppUser build() {
            return appUser;
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public AppUser setIdUser(int idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AppUser setRole(String role) {
        this.role = role;
        return this;
    }

    public AppUserPurse getPurse() {
        return purse;
    }

    public AppUser setPurse(AppUserPurse purse) {
        this.purse = purse;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return idUser == appUser.idUser &&
                Objects.equals(username, appUser.username) &&
                Objects.equals(email, appUser.email) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(role, appUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, username, email, password, role);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
