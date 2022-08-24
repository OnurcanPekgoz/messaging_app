package tr.com.argela.whatsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.argela.whatsapp.entity.Friend;
import tr.com.argela.whatsapp.entity.User;
import tr.com.argela.whatsapp.entity.UserSession;
import tr.com.argela.whatsapp.repository.FriendRepository;
import tr.com.argela.whatsapp.repository.UserRepository;
import tr.com.argela.whatsapp.repository.UserSessionRepository;

@Service
public class FriendService {

    @Autowired
    UserService userService;

    @Autowired
    FriendRepository friendRepository;

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
