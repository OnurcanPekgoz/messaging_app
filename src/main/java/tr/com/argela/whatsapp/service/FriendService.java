package tr.com.argela.whatsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.argela.whatsapp.config.KafkaTopicConfig;
import tr.com.argela.whatsapp.entity.Friend;
import tr.com.argela.whatsapp.entity.User;
import tr.com.argela.whatsapp.repository.FriendRepository;

@Service
public class FriendService {

    @Autowired
    UserService userService;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    KafkaTopicConfig kafkaTopicConfig;

    public Friend addFriend(String sessionId, String friendPhone) throws Exception {
        User user = userService.getUserBySessionId(sessionId);
        User friendUser = userService.getUserByPhone(friendPhone);
    
        Friend friend = getFriend( user.getId(), friendUser.getId());

        if(friend!=null){
            throw new Exception(); // Friend is already exists
        }

        friend = new Friend();
        friend.setUser(user);
        friend.setFriend(friendUser);
        String topicName;
        if(user.getTelephone().compareTo(friendUser.getTelephone())<0){
            topicName=user.getTelephone()+"-"+friendUser.getTelephone();
        }
        else{
            topicName=friendUser.getTelephone()+"-"+user.getTelephone();
        }
        
        kafkaTopicConfig.buildTopic(topicName);
        return friendRepository.save(friend);
    }

    private Friend getFriend(Long userID, Long friendUserID) {
        return friendRepository.getFriend(userID,friendUserID);
    }

    public List<Friend> getFriends(String sessionId) throws Exception {
        User user = userService.getUserBySessionId(sessionId);
        return friendRepository.getFriendsByUserId(user.getId());
    }
}
