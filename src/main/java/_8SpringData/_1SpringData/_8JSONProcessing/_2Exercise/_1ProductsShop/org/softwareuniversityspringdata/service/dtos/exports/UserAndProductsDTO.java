package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.exports;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserAndProductsDTO implements Serializable {

    @Expose
    private int usersCount;

    @Expose
    private List<UserSoldDTO> users;

    public UserAndProductsDTO() {
    }

    public UserAndProductsDTO(int usersCount, List<UserSoldDTO> users) {
        this.usersCount = usersCount;
        this.users = users;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserSoldDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldDTO> users) {
        this.users = users;
    }
}
