package com.tower.reback.controller;


import com.tower.reback.entity.Result;
import com.tower.reback.pojo.User;
import com.tower.reback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestParam("username") String username,@RequestParam("password") String password){
        User user = userService.findByUserAndPassword(username,password);
        if (user != null){

        }
        return null;
    }

    public Result group(){
        return null;
    }
}
