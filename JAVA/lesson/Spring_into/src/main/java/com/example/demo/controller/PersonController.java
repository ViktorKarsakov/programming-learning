package com.example.demo.controller;


import com.example.demo.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/person")
public class PersonController {

    List<Person> people;

    public PersonController() {
        people = new ArrayList<>();
        people.add(new Person("Dima","Bilan",62));
        people.add(new Person("Oleq","Qazmanov",82));
    }


    @GetMapping("/all")
    public List<Person> getAll(){
        return people;
    }


    @GetMapping("/add")
    public String add(
            @RequestParam(name = "name",defaultValue = "No name")  String name,
            @RequestParam(name = "surname",required = false,defaultValue = "No surname") String surname,
            @RequestParam(name = "age",required = false,defaultValue = "0") Integer age
    ){
        if(age<0 ) return "Age can't be less than 0";
        if(age>100 ) return "Age can't be greater than 100";
        if(people.stream().anyMatch(x->x.getName().equalsIgnoreCase(name) && x.getSurname().equalsIgnoreCase(surname)))
         return "This is person already exist";

        boolean isResult =  people.add(new Person(name,surname,age));

        if(isResult)return "success";
        else return "fail";
    }




    @GetMapping("/hi/{surname}/{name}/information")
    public String add(
            @PathVariable(name = "surname")  String surname,
            @PathVariable(name = "name")  String name,
            @RequestParam(name = "t",required = false,defaultValue = "No surname") String t
    ){
        return "Hello world! " + name + " " + surname + " " + t;
    }


    @GetMapping("/surname")
    public String add2(
    ){
        return "Hello world! ";
    }


    //PathVariable                               @RequestParam
    // url после /                               в query-param после ? (Затем каждая след &)
    // обязательный                              не обязательно
    // идентификацмя ресурса (id)                фильтрация , пагинация , сортировка , настройки
    // строгая последовательность                рандомная
    // url может продолжаться                    url не может продолжаться
    // PathVariable                              RequestParam

}
