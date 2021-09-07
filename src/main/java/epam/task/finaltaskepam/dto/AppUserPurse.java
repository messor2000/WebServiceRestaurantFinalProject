package epam.task.finaltaskepam.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Aleksandr Ovcharenko
 */
public class AppUserPurse implements Serializable {
    private static final long serialVersionUID = 6297385302078200611L;

    private int idPurse;
    private int idAppUser;
    private long amount;

    public static class Builder {
        private final AppUserPurse appUserPurse;

        public Builder() {
            appUserPurse = new AppUserPurse();
        }

        public Builder withIdPurse(int idPurse) {
            appUserPurse.idPurse = idPurse;
            return this;
        }

        public Builder withIdAppUser(int idAppUser) {
            appUserPurse.idAppUser = idAppUser;
            return this;
        }

        public Builder withAmount(long amount) {
            appUserPurse.amount = amount;
            return this;
        }

        public AppUserPurse build() {
            return appUserPurse;
        }
    }

    public int getIdPurse() {
        return idPurse;
    }

    public AppUserPurse setIdPurse(int idPurse) {
        this.idPurse = idPurse;
        return this;
    }

    public int getIdAppUser() {
        return idAppUser;
    }

    public AppUserPurse setIdAppUser(int idAppUser) {
        this.idAppUser = idAppUser;
        return this;
    }

    public long getAmount() {
        return amount;
    }

    public AppUserPurse setAmount(long amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserPurse that = (AppUserPurse) o;
        return idPurse == that.idPurse &&
                idAppUser == that.idAppUser &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPurse, idAppUser, amount);
    }

    @Override
    public String toString() {
        return "AppUserCheck{" +
                "idCheck=" + idPurse +
                ", idAppUser=" + idAppUser +
                ", amount=" + amount +
                '}';
    }
}
