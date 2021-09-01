package epam.task.finaltaskepam.dto;

import java.io.Serializable;
import java.util.Objects;

public class AppUserDto implements Serializable {
    private static final long serialVersionUID = 6297385302078200511L;

    private int idUser;
    private String username;
    private String email;
    private String password;
    private String role;

    public static final class Builder {
        AppUserDto userDto;

        public Builder() {
            userDto = new AppUserDto();
        }

        public Builder withIdUser(int idUser) {
            userDto.idUser = idUser;
            return this;
        }

        public Builder withUsername(String username) {
            userDto.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            userDto.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            userDto.email = email;
            return this;
        }

        public Builder withRoleId(String roleId) {
            userDto.role = roleId;
            return this;
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public AppUserDto setIdUser(int idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AppUserDto setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserDto that = (AppUserDto) o;
        return idUser == that.idUser &&
                role == that.role &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, username, email, password, role);
    }
}
