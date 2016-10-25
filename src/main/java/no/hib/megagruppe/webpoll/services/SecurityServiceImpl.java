package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.util.PasswordHasher;

import javax.inject.Inject;

public class SecurityServiceImpl implements SecurityService {
    private final SecurityAdapter securityAdapter;
    private final PasswordHasher passwordHasher;
    private final RepositoryFactory repositoryFactory;

    @Inject
    public SecurityServiceImpl(SecurityAdapter securityAdapter, PasswordHasher passwordHasher,
                               RepositoryFactory repositoryFactory) {
        this.securityAdapter = securityAdapter;
        this.passwordHasher = passwordHasher;
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    public boolean logIn(String username, String password) {
        UserEntity user = repositoryFactory.getUserRepository().findByEmail(username);
        if (user == null) return false;

        boolean passwordMatches = passwordHasher.comparePassword(password, user.getPassword());
        if (passwordMatches) securityAdapter.logIn(user.getId());
        return passwordMatches;
    }

    @Override
    public void logOut() {
        securityAdapter.logOut();
    }

    @Override
    public boolean isLoggedIn() {
        return securityAdapter.isLoggedIn();
    }

    @Override
    public UserEntity getLoggedInUser() {
        int sessionUser = securityAdapter.getLoggedInUser();
        if (sessionUser == SecurityAdapter.USER_NOT_LOGGED_IN) {
            return null;
        }

        return repositoryFactory.getUserRepository().findById(sessionUser);
    }
}
