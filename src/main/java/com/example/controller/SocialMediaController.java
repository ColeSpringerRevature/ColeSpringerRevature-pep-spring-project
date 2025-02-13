package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * Social Media App Controller.
 */
@RestController
@RequestMapping("")
public class SocialMediaController {

    /**
     * {@link AccountService}
     */
    private final AccountService accountService;

    /**
     * {@link MessageService}
     */
    private final MessageService messageService;

    /**
     * Registers a users username and password.
     * @param account - Account with username and password.
     * @return Account with their login and id.
     */
    @PostMapping("/register")
    public Account registerAccount(@RequestBody final Account account) {
        return accountService.registerAccount(account);
    }

    /**
     * Checks if provided credentials match in database.
     * @param account - Account with username and password.
     * @return Account of successfull login.
     */
    @PostMapping("/login")
    public Account userLogin(@RequestBody final Account account) {
        return accountService.userLogin(account);
    }

    /**
     * Creates message.
     * @param message - Given message.
     * @return Message with original details and assigned id.
     */
    @PostMapping("/messages")
    public Message createMessage(@RequestBody final Message message) {
        return messageService.creatMessage(message);
    }

    /**
     * Gets all messages.
     * @return List of all messages.
     */
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    /**
     * Gets message by message id.
     * @param id - Id of message.
     * @return Message of matched id.
     */
    @GetMapping("/messages/{messageId}")
    public Message getMessageById(@PathVariable("messageId") int id) {
        return messageService.getMessageById(id);
    }

    /**
     * Deletes message by is.
     * @param id - Id of message.
     * @return Number of rows deleted or NULL if none were deleted.
     */
    @DeleteMapping("/messages/{messageId}")
    public Integer deleteMessageById(@PathVariable("messageId") int id) {
        return messageService.deleteMessageById(id);
    }

    /**
     * Updates message by id with given message text.
     * @param id - Id of message.
     * @param message - Message text to replace original text.
     * @return Number of rows updated.
     */
    @PatchMapping("/messages/{messageId}")
    public Integer updateMessageTextById(@PathVariable("messageId") int id, @RequestBody Message message) {
        return messageService.updateMessageTextById(id, message);
    }

    /**
     * Gets all messages for a given account id.
     * @param id - Account id.
     * @return List of all messages for the given account id.
     */
    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getAllMessagesForAccountById(@PathVariable("accountId") int id) {
        return messageService.getAllMessagesForAccountById(id);
    }

    /**
     * Constructor.
     * @param accountService {@link AccountService}
     * @param messageService {@link MessageService}
     */
    @Autowired
    public SocialMediaController(final AccountService accountService, final MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

}
