package com.gmail.olgabovkaniuk.app.dao.model;

public enum UserRoleEnum {
    ADMIN,
    USER;

    public static UserRoleEnum getRole(String role) {
        try {
            return UserRoleEnum.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Role does not found!");
            e.printStackTrace();
        }
        return null;
    }
}



