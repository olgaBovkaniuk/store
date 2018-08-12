package com.gmail.olgabovkaniuk.app.config;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static ConfigurationManager instance;

    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";

    public static final String DATABASE_DRIVER_NAME = "database.driver.name";
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USERNAME = "database.username";
    public static final String DATABASE_PWD = "database.password";

    public static final String LOGIN_PAGE_PATH = "login.page.path";
    public static final String REGISTER_PAGE_PATH = "register.page.path";
    public static final String USERS_PAGE_PATH = "users.page.path";
    public static final String ADMIN_PRODUCTS_PAGE_PATH = "admin.products.page.path";
    public static final String USER_PRODUCTS_PAGE_PATH = "user.products.page.path";
    public static final String USER_ORDERS_PAGE_PATH = "user.orders.page.path";
    public static final String ADMIN_ORDERS_PAGE_PATH = "admin.orders.page.path";
    public static final String ERRORS_PAGE_PATH = "errors.page.path";
    public static final String UPLOAD_FILE_PATH = "upload.file.path";

    public static final String USER_PRODUCTS_CMD_URL = "user.products.cmd.url";
    public static final String ADMIN_PRODUCTS_CMD_URL = "admin.products.cmd.url";
    public static final String ADMIN_ORDERS_CMD_URL = "admin.orders.cmd.url";
    public static final String USER_ORDERS_CMD_URL = "user.orders.cmd.url";
    public static final String USERS_CMD_URL = "users.cmd.url";
    public static final String LOGIN_CMD_URL = "login.cmd.url";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
