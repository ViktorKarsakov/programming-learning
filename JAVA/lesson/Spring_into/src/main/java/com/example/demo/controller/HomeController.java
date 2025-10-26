package com.example.demo.controller;


import com.example.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//REST (Representational State Transfer)
//API (Application Programming Interface)

//HTTP ( 80 443 (HTTPS))
//-GET

//Client          (Front , mob , brauzer)
//REST API        (Svyazuyuyee zveno))
//Kuxnya          (bekend)

//RestController => Controller + ResponseBody

@RestController
@RequestMapping("/api/home")
public class HomeController {

//    List<Person> people;
    List<String> list = new ArrayList<>();

    public HomeController() {
//        people = new ArrayList<>();
//        people.add(new Person("Dima","Bilan",62));
//        people.add(new Person("Oleq","Qazmanov",82));
//        people.add(new Person("Farid","Abdullayev",29));
    }


    @GetMapping("/list")
    public List<String> hi1(){
        return list;
    }


//    @GetMapping("/add")
//    public Boolean add(
//            @RequestParam(name = "name")  String userName
//    ) {
//        if (userName != null && !userName.isEmpty() && !list.contains(userName))
//            return list.add(userName);
//        else
//            return false;
//    }


//    @GetMapping("/add")
//    public Boolean add(String name) {
//        if (name != null && !name.isEmpty())
//            return list.add(name);
//        else
//            return false;
//    }

//    @GetMapping("/people")
//    public List<Person> hi1(){
//        return people;
//    }



//    @GetMapping("/hi")
//    public Boolean hi1(){
//        return true;
//    }

//    @GetMapping("/test")
//    public String hi2(){
//        return "test";
//    }
}
