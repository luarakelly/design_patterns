package proxy.protected_documents;

import java.util.Objects;

public final class User {
    private final String username;

    public User(String username) {
        this.username = Objects.requireNonNull(username, "username");
        if (username.isBlank()) {
            throw new IllegalArgumentException("username must not be blank");
        }
    }

    public String getUsername() {
        return username;
    }
}
