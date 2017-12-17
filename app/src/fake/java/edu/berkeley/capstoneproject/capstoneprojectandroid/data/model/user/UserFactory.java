package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

/**
 * Created by Alex on 14/12/2017.
 */

public class UserFactory {

    private static User mAdmin;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static User admin() {
        if (mAdmin == null) {
            Authentication authentication = AuthenticationFactory.admin();
            mAdmin = new UserBuilder()
                    .withEmail("admin@admin.com")
                    .withFirstName("admin")
                    .withLastName("")
                    .withAuthentication(authentication)
                    .build();
        }
        return mAdmin;
    }

    public static class UserBuilder {

        private Authentication mAuthentication;

        private String mEmail;
        private String mFirstName;
        private String mLastName;

        public UserBuilder() {

        }

        public UserBuilder withAuthentication(Authentication authentication) {
            mAuthentication = authentication;
            return this;
        }

        public UserBuilder withEmail(String email) {
            mEmail = email;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            mLastName = lastName;
            return this;
        }

        public User build() {
            User user = new User(mEmail, mFirstName, mLastName);
            user.setAuthentication(mAuthentication);
            return user;
        }
    }
}
