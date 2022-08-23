package tr.com.argela.whatsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import tr.com.argela.whatsapp.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(value = "Select u from User u WHERE u.telephone = :telephone and u.password = :password")
    public List<User> login(@Param("telephone") String telephone, @Param("password") String password);
}
