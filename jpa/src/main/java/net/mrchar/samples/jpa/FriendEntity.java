package net.mrchar.samples.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Getter
@Entity
@Table(name = "friend")
@JsonIgnoreProperties({"new"})
public class FriendEntity extends AbstractPersistable<UUID> {
    @Setter
    private String name;

    public FriendEntity() {
    }

    public FriendEntity(String name) {
        this.name = name;
    }
}
