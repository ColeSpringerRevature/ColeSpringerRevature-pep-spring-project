package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

/**
 * JpaRepository for the {@link Message} entity.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Gets number of account associated with the postedBy field.
     * @param postedBy - postedBy field of {@link Message} instance.
     * @return Number of associated accounts.
     */
    @Query(value = "SELECT COUNT(accountId) FROM Account a LEFT JOIN Message m ON m.postedBy = a.accountId WHERE m.postedBy = ?1", nativeQuery = true)
    Integer getNumberOfAccountsByPostedBy(Integer postedBy);

    /**
     * Gets all messages of an account given the postedBy field.
     * @param postedBy - postedBy field of {@link Message} instance.
     * @return List of all associated messages of the provided account.
     */
    List<Message> getAllMessagesOfAccountByPostedBy(Integer postedBy);
}
