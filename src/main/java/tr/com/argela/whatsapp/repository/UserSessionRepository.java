package tr.com.argela.whatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.argela.whatsapp.entity.UserSession;
@Repository
public interface UserSessionRepository  extends JpaRepository<UserSession, Long> {
  
}
