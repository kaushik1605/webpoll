package no.hib.megagruppe.webpoll.util;

import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.security.SecureRandom;

@ApplicationScoped
class SecurePasswordHasher implements PasswordHasher {
    private static final int LOG_ROUNDS = 5;

    @Override
    public String hashPassword(String password) {
        verifyPasswordCandidate(password);

        String salt = BCrypt.gensalt(LOG_ROUNDS, new SecureRandom());
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public boolean comparePassword(String password, String hash) {
        verifyPasswordCandidate(password);
        return BCrypt.checkpw(password, hash);
    }

    private void verifyPasswordCandidate(String password) {
        if (password == null)
            throw new IllegalArgumentException("Password cannot be null");
        if (password.length() < 1)
            throw new IllegalArgumentException("Password cannot be less than one character");
    }
}