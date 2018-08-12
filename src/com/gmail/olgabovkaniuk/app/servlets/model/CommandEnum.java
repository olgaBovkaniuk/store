package com.gmail.olgabovkaniuk.app.servlets.model;

public enum CommandEnum {
    LOGIN,
    USERS,
    ADMIN_PRODUCTS,
    USER_PRODUCTS,
    REGISTER_USER,
    ADD_ORDER,
    DELETE_USER_ORDER,
    DELETE_ADMIN_ORDER,
    USER_ORDERS,
    ADMIN_ORDERS,
    UPLOAD_XML;

    public static CommandEnum getCommand(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Command does not found!");
            e.printStackTrace();
        }
        return null;
    }

}
