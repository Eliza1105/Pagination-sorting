package org.itstep.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/users/page")
    public Page<User> page1() {
        Pageable pageable = PageRequest.of(0, 10);
        return userService.findAll(pageable);
    }

    @GetMapping(value = "/users", params = {"page", "size"})
    public Page<User> pageUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.findAll(pageable);
    }

    @GetMapping(value = "/users", params = {"offset", "limit"})
    public Page<User> pageUsers2(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return userService.findAll(pageable);
    }

    @GetMapping(value = "/users/sort", params = {"name", "order"})
    public List<User> sortUsers(@RequestParam("name") String name, @RequestParam("order") String order) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc"))
            direction = Sort.Direction.DESC;
        return userService.findByOrderByName(Sort.by(direction, name));
    }

    @GetMapping("/sort")
    public Page<User> pageSort(@RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam("sort") String sortField) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return userService.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, sortField)));
    }
    /*
    @GetMapping(value = "/users/page/sort", params = {"page", "size", "name", "order"})
    public Page<User> pageSortUsers(@RequestParam("page") int page,
                                    @RequestParam("size") int size,
                                    @RequestParam("name") String name,
                                    @RequestParam("order") String order) {
        return userService.getAllComplains(page, order, name);
     */
}
