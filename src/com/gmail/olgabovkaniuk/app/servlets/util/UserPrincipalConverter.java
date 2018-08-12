package com.gmail.olgabovkaniuk.app.servlets.util;

import com.gmail.olgabovkaniuk.app.dao.model.User;
import com.gmail.olgabovkaniuk.app.servlets.model.UserPrincipal;

public class UserPrincipalConverter {
    public static UserPrincipal toUserPrincipal(User user) {
        return UserPrincipal.newBuilder()
                .withId(user.getId())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withEmail(user.getEmail())
                .withRole(user.getRole())
                .build();
    }
}
