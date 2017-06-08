package org.lbchild.model;

import java.io.BufferedReader;
import java.io.FileReader;

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
        if (user == null) {
            user = new User();
            try {
                BufferedReader in = new BufferedReader(new FileReader("src/main/resources/user.txt"));
                String userName = in.readLine();
                user.setUserName(userName);
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
                                      
}
