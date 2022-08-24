package tr.com.argela.whatsapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.argela.whatsapp.entity.Friend;
@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{

    @Transactional
    @Query(value = "Select f from Friend f WHERE f.user.id = :userID and f.friend.id = :friendUserID")
    Friend getFriend(@Param("userID") Long userID, @Param("friendUserID") Long friendUserID);

    @Transactional
    @Query(value = "Select f from Friend f WHERE f.user.id = :userID")
    List<Friend> getFriendsByUserId(@Param("userID") Long userID);

}
