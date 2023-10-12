package net.mrchar.samples.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DataJpaTest
class FriendsRepositoryTest {
    @Autowired
    FriendsRepository friendsRepository;

    @Test
    void findAllByNameLike() {
        this.friendsRepository.save(new FriendEntity("alice"));
        this.friendsRepository.save(new FriendEntity("bob"));
        Page<FriendEntity> entities = this.friendsRepository.findAllByNameLike("a", Pageable.unpaged());
        Assertions.assertThat(entities.getContent()).isNotEmpty();
    }
}