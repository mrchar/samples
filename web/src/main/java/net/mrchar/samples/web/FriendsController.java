package net.mrchar.samples.web;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendsController {
    private final List<String> friends = new ArrayList<>();

    @GetMapping("/friends")
    public List<String> getFriends() {
        return this.friends;
    }

    @PostMapping("/friends")
    public void createFriend(@RequestBody FriendRequest request) {
        boolean exists = this.friends.contains(request.getName());
        if (exists) {
            throw new IllegalArgumentException("对象已存在");
        }

        this.friends.add(request.getName());
    }

    @PutMapping("/friends/{name}")
    public void modifyFriend(@PathVariable String name, @RequestBody FriendRequest request) {
        int index = this.friends.indexOf(name);
        if (index == -1) {
            throw new IllegalArgumentException("对象不存在");
        }

        this.friends.set(index, request.getName());
    }

    @DeleteMapping("/friends/{name}")
    public void deleteFriend(@PathVariable String name) {
        int index = this.friends.indexOf(name);
        if (index == -1) {
            throw new IllegalArgumentException("对象不存在");
        }

        this.friends.remove(index);
    }
}
