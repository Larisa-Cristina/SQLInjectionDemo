package com.example.sqlinjectiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // http://localhost:8080
    @RequestMapping("/")
    public String home() {
        return "home";
    }


    // http://localhost:8080/login?username=admin&password=123
    // http://localhost:8080/login?username=admin&password=password' OR '1'='1
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("home");
        Boolean auth = userService.authenticate(username, password);
        System.out.println(auth);
        modelAndView.addObject("auth", auth);
        return modelAndView;
    }


}
