package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

/**
 * JpaRepository for the {@link Account} entity.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    /**
     * Gets all account with specified username.
     * @param username - Username/
     * @return List of all accounts with specified username.
     */
    List<Account> findAccountsByUsername(String username);

    /**
     * Gets account that contains given username and password.
     * @param username - Username.
     * @param password - Password.
     * @return Account with matching username and password.
     */
    Account findAccountByUsernameAndPassword(String username, String password);
}
