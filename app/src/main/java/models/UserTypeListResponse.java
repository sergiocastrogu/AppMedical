package models;

import java.util.List;

public class UserTypeListResponse {
    private List<UserType> userTypes;

    public List<UserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<UserType> userTypes) {
        this.userTypes = userTypes;
    }
}
