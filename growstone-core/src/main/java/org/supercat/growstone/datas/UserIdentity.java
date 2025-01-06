package org.supercat.growstone.datas;

public class UserIdentity {
    public String id;
    public String email;

    public static UserIdentity of(String id, String email) {
        var userIdentity = new UserIdentity();
        userIdentity.id = id;
        userIdentity.email = email;
        return userIdentity;
    }
}
