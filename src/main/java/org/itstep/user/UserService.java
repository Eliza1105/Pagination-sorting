package org.itstep.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        return (Page<User>) userRepository.findAll(pageable);
    }

    public List<User> findByOrderByName(Sort sort){
        //return userRepository.findByOrderByName();
        return userRepository.findAll(sort);
    }

    public Page<User> getAllComplains(int page,int size, String name, String order) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(order, name));
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc"))
            direction = Sort.Direction.DESC;
        return userRepository.findAll(pageable);
    }
}