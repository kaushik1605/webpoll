package no.hib.megagruppe.webpoll.util;

import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import java.security.SecureRandom;

@ApplicationScoped
public class SecurePasswordHasher implements PasswordHasher {
    private static final int LOG_ROUNDS = 5;

    @Override
    public String hashPassword(String password) {
        verifyPasswordInput(password);

        String salt = BCrypt.gensalt(LOG_ROUNDS, new SecureRandom());
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public boolean comparePassword(String password, String hash) {
        verifyPasswordInput(password);
        if (hash == null) throw new IllegalArgumentException("Hash cannot be null");
        return BCrypt.checkpw(password, hash);
    }

    private void verifyPasswordInput(String password) {
        if (password == null)
            throw new IllegalArgumentException("Password cannot be null");
        if (password.length() < 1)
            throw new IllegalArgumentException("Password cannot be less than one character");
    }
}
