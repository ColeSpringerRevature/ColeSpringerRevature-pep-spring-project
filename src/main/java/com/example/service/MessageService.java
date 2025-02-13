package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

/**
 * Service class for {@link Message} entity.
 */
@Service
public class MessageService {

    /**
     * {@link MessageRepository}
     */
    private final MessageRepository messageRepository;

    /**
     * Creates message.
     * @param message - {@link Message}
     * @return {@link Message} with provided info and assigned ID
     */
    @Transactional
    public Message creatMessage(final Message message) {
        if (message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new ResponseStatusException(HttpStatus.valueOf(400), "Invalid message.");
        }

        Integer count = messageRepository.getNumberOfAccountsByPostedBy(message.getPostedBy());
        if (count == null || count != 1) {
            throw new ResponseStatusException(HttpStatus.valueOf(400), "postedBy does not match an existing account.");
        }

        return messageRepository.save(message);
    }

    /**
     * Gets all messages.
     * @return List of messages.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Gets message by id.
     * @param id - Id of message.
     * @return {@link Message}
     */
    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    /**
     * Deletes message by id.
     * @param id - Message id.
     * @return Number of deleted rows. Null if none were deleted.
     */
    @Transactional
    public Integer deleteMessageById(int id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return 1;
        }
        return null;
    }

    /**
     * Updates message by id.
     * @param id - Id.
     * @param message - {@link Message}
     * @return Number of messages updated.
     */
    @Transactional
    public Integer updateMessageTextById(int id, Message message) {
        if (message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new ResponseStatusException(HttpStatus.valueOf(400), "Invalid message text.");
        }
        if (!(messageRepository.existsById(id))) {
            throw new ResponseStatusException(HttpStatus.valueOf(400), "Message with given id does not exist.");
        }
        // already checked message exists
        Message oldMessage = messageRepository.findById(id).get();
        oldMessage.setMessageText(message.getMessageText());
        messageRepository.save(oldMessage);
        return 1;
    }

    /**
     * Gets all messages for a particular account.
     * @param accountId - Account id.
     * @return List of messages for the given account id.
     */
    public List<Message> getAllMessagesForAccountById(int accountId) {
        return messageRepository.getAllMessagesOfAccountByPostedBy(accountId);
    }

    /**
     * Constructor.
     * @param messageRepository - {@link MessageRepository}
     */
    @Autowired
    public MessageService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}
