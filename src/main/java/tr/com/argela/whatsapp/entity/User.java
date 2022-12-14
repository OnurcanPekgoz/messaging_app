package tr.com.argela.whatsapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tr.com.argela.whatsapp.model.result.WhatsappResult;

@Getter
@Setter
@Entity
@Data
@Table(name = "users")
public class User implements WhatsappResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String telephone;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Friend> friends;
}
