package antasmes.tech.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import antasmes.tech.demo.models.Message;
import antasmes.tech.demo.repositories.MessageRepository;

@Service("messageService")
@DependsOn({ "sshinit" })
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Should be ObjectI
    public List<Message> getBySenderID(ObjectId fromUserId, ObjectId toUserId) {
        Optional<List<Message>> optMessages = messageRepository.findMessages(fromUserId, toUserId);
        if (optMessages.isPresent()) {
            return optMessages.get();
        }
        return new ArrayList<Message>();
    }

    public void insert(Message message) {
        messageRepository.insert(message);
    }
}
