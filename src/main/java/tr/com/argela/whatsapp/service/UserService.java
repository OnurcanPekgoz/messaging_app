package tr.com.argela.whatsapp.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.argela.whatsapp.entity.User;
import tr.com.argela.whatsapp.entity.UserSession;
import tr.com.argela.whatsapp.repository.UserRepository;
import tr.com.argela.whatsapp.repository.UserSessionRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSessionRepository userSessionRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserSession login(String telephone, String password) throws Exception {
        List<User> userList = userRepository.login(telephone, password);
        if(userList.isEmpty()){
            throw new Exception();
        }

        User user= userList.get(0);
        UserSession userSession= new UserSession();
        userSession.setUser(user);
        userSession.setSessionId(createSessionID());
        userSession.setLoginDate(new Date());
        return userSessionRepository.save(userSession);
    }

    private String createSessionID() {
        return UUID.randomUUID().toString();
    }
}
