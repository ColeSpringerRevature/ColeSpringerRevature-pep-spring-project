package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

/**
 * Service class for {@link Account} entity.
 */
@Service
public class AccountService {

    /**
     * {@link AccountRepository}
     */
    private final AccountRepository accountRepository;

    /**
     * Registers account in the database.
     * @param account - {@link Account} to register.
     * @return Account with provided information with assigned id.
     * @throws ResponseStatusException
     */
    @Transactional
    public Account registerAccount(final Account account) throws ResponseStatusException {
        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            throw new ResponseStatusException(HttpStatus.valueOf(400), "Bad account credentials.");
        }
        if (accountRepository.findAccountsByUsername(account.getUsername()).size() != 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists.");
        }
        return accountRepository.save(account);
    }

    /**
     * Checks if account matches login already in database.
     * @param account - {@link Account} to check.
     * @return Account of matched login.
     */
    public Account userLogin(final Account account) {
        Account user = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad login credentials.");
        }
        return user;
    }

    /**
     * Constructor.
     * @param accountRepository - {@link AccountRepository}
     */
    @Autowired
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
