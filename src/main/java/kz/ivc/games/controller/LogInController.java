package kz.ivc.games.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
        public  String showLoginForm(){
        return "logIn";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public  String login(@RequestParam String password,@RequestParam String userId){
        return "";
    }
}
