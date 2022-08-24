package tr.com.argela.whatsapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.argela.whatsapp.entity.UserSession;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from UserSession where session_id = :sessionId", nativeQuery = true)
    public void logoutSession(@Param("sessionId") String sessionId);

    @Transactional
    @Query(value = "Select u from UserSession u WHERE u.sessionId = :sessionId")
    public List<UserSession> getBySessionId(@Param("sessionId") String sessionId);

}
