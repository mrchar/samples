package net.mrchar.samples.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class FriendsController {
    private final FriendsRepository friendsRepository;

    @GetMapping("/friends")
    public Page<FriendEntity> getFriend(Pageable pageable) {
        return this.friendsRepository.findAll(pageable);
    }

    @PostMapping("/friends")
    public FriendEntity createFriend(@RequestBody FriendRequest request) {
        return this.friendsRepository.save(new FriendEntity(request.getName()));
    }

    @PutMapping("/friends/{id}")
    public FriendEntity modifyFriend(@PathVariable UUID id, @RequestBody FriendRequest request) {
        FriendEntity entity = this.friendsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("对象不存在"));

        entity.setName(request.getName());
        return this.friendsRepository.save(entity);
    }

    @DeleteMapping("/friends/{id}")
    public void deleteFriend(@PathVariable UUID id) {
        this.friendsRepository.deleteById(id);
    }
}
