package com.example.demo.controller;


import com.example.demo.model.Person;
import com.example.demo.model.PersonDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @DeleteMapping("/delete/{id}")
    public boolean deletById(@PathVariable(name = "id") Integer id){
        System.out.println("Id => " + id);
        return  people.removeIf(p->p.getId()==id);
    }

    @GetMapping("/getById/{id}")
    public Person getById(@PathVariable(name = "id") Integer id){
        return  people.stream().filter(p->p.getId()==id).findFirst().get();
    }


    //

    @PutMapping("/update/{id}")
    public String update(
        @PathVariable Integer id,
        @RequestBody PersonDTO person
    ){
        Optional<Person> isResult =  people.stream().filter(p->p.getId()==id).findFirst();

        if(isResult.isPresent()){

            Person p = isResult.get();

            if(person.getName()!=null){
                p.setName(person.getName());
            }

            if(person.getSurname()!=null){
                p.setSurname(person.getSurname());
            }

            if(person.getAge()!=null){
                p.setAge(person.getAge());
            }


            return "success";
        }
        else return "fail";
    }

    @PostMapping("/add")
    public String add(
            @RequestBody PersonDTO person
    ){
        if(person.getAge()<0 ) return "Age can't be less than 0";
        if(person.getAge()>100 ) return "Age can't be greater than 100";
        if(people.stream().anyMatch(x->x.getName().equalsIgnoreCase(person.getName()) && x.getSurname().equalsIgnoreCase(person.getSurname())))
            return "This is person already exist";

        boolean isResult =  people.add(new Person(person.getName(),person.getSurname(),person.getAge()));

        if(isResult)return "success";
        else return "fail";
    }



    //10:05


    //PathVariable                               @RequestParam
    // url после /                               в query-param после ? (Затем каждая след &)
    // обязательный                              не обязательно
    // идентификацмя ресурса (id)                фильтрация , пагинация , сортировка , настройки
    // строгая последовательность                рандомная
    // url может продолжаться                    url не может продолжаться
    // PathVariable                              RequestParam


    //Get         ->     Read
    //Post        ->     Creat
    //Put         ->     Update
    //Delete      ->     Delete
    //Patch       ->     Casticnoe obnovlenie



    //GET                                          POST
    //Polucit/Read dannie                          Send/Create dats
    //v url adress                                 v tele zaprosa
    //adresnaya stroka                             skriti
    ///add?name=Farid&age=20                       /add
    //Bezopasnot nizkaya                           Vishe
    //Size limit                                   not size limit
    //not send file                                send file
    //kewiruetsa                                   ne kewiruetsa
    //soxranyaetsa v istorii                       ne soxranyaetsa v istorii
    //mojno dobavit v zakladki                     nelzya dobavit v zakladki
    //only read                                    sozdat/izmenit
    //100zaprosov = 100otvetov                     100 => 100 raznix otvetov


}
