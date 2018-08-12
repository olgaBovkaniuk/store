package com.gmail.olgabovkaniuk.app.dao.impl;

import com.gmail.olgabovkaniuk.app.dao.UserDao;
import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.dao.model.UserRoleEnum;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User save(Connection connection, User user) {
        String saveIntoTableSql = "INSERT INTO T_USER (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, MOBILE_NUMBER, ADDITIONAL_INFO, ROLE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(saveIntoTableSql)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getMobileNumber());
                preparedStatement.setString(6, user.getAdditionalInfo());
                preparedStatement.setString(7, String.valueOf(user.getRole()));
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public User findUserByEmail(Connection connection, String email) {
        String selectTableSql = "SELECT * FROM T_USER WHERE EMAIL=?";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectTableSql)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return getUser(resultSet);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public List<User> findAll(Connection connection) {
        List<User> userList = new ArrayList<>();
        String selectFromTableSql = "SELECT * FROM T_USER";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectFromTableSql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        User user = getUser(resultSet);
                        userList.add(user);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return userList;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = User.newBuilder()
                .withId(resultSet.getLong("ID"))
                .withFirstName(resultSet.getString("FIRST_NAME"))
                .withLastName(resultSet.getString("LAST_NAME"))
                .withEmail(resultSet.getString("EMAIL"))
                .withPassword(resultSet.getString("PASSWORD"))
                .withMobileNumber(resultSet.getString("MOBILE_NUMBER"))
                .withAdditionalInfo(resultSet.getString("ADDITIONAL_INFO"))
                .withRole(UserRoleEnum.valueOf(resultSet.getString("ROLE")))
                .build();
        return user;
    }
}
