package com.gmail.olgabovkaniuk.app.services.impl;

import com.gmail.olgabovkaniuk.app.dao.UserDao;
import com.gmail.olgabovkaniuk.app.dao.connection.ConnectionService;
import com.gmail.olgabovkaniuk.app.dao.impl.UserDaoImpl;
import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.services.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User save(User user) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            User savedUser = userDao.save(connection, user);
            connection.commit();
            return savedUser;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            User savedUser = userDao.findUserByEmail(connection, email);
            connection.commit();
            return savedUser;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        Connection connection = ConnectionService.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            List<User> savedUserList = userDao.findAll(connection);
            connection.commit();
            return savedUserList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }
}
