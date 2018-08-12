package com.gmail.olgabovkaniuk.app.servlets.model;

import com.gmail.olgabovkaniuk.app.dao.model.UserRoleEnum;

import java.io.Serializable;

public class UserPrincipal implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRoleEnum role;

    private UserPrincipal(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        role = builder.role;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private UserRoleEnum role;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withRole(UserRoleEnum val) {
            role = val;
            return this;
        }

        public UserPrincipal build() {
            return new UserPrincipal(this);
        }
    }
}
