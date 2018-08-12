package com.gmail.olgabovkaniuk.app.dao.model;

import java.util.Objects;

public class User extends Identifier {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String additionalInfo;
    private String password;
    private UserRoleEnum role;

    private User(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        mobileNumber = builder.mobileNumber;
        additionalInfo = builder.additionalInfo;
        password = builder.password;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getPassword() {
        return password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(mobileNumber, user.mobileNumber) &&
                Objects.equals(additionalInfo, user.additionalInfo) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, mobileNumber, additionalInfo, password, role);
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String mobileNumber;
        private String additionalInfo;
        private String password;
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

        public Builder withMobileNumber(String val) {
            mobileNumber = val;
            return this;
        }

        public Builder withAdditionalInfo(String val) {
            additionalInfo = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withRole(UserRoleEnum val) {
            role = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
