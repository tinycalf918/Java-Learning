package io.kimmking.rpcfx.demo.api;

import org.springframework.stereotype.Component;

@Component
public interface UserService {

    User findById(int id);

}
