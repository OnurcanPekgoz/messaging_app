package tr.com.argela.whatsapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tr.com.argela.whatsapp.model.result.WhatsappResult;

@Getter
@Setter
@Entity
@Data
@Table(name = "friends")
public class Friend implements WhatsappResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "friend_user_id", referencedColumnName = "id")
    private User friend;

}
