package org.lbchild.model;

public class User {
    private static User user;
    private String userName;
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public static User getInstance() {
        if (user == null)
            user = new User();
        
        return user;
    }
                                      
}
