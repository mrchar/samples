package net.mrchar.samples.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FriendsRepository extends JpaRepository<FriendEntity, UUID> {
    @Query("select f from FriendEntity f where f.name like :#{'%'+#keyword+'%'}")
    Page<FriendEntity> findAllByNameLike(String keyword, Pageable pageable);
}
