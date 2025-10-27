package com.example.demo.controller;


import com.example.demo.model.ErrorResponse;
import com.example.demo.model.Person;
import com.example.demo.model.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//HTTP Status codes
//1xx     -     Informational/Информационные (100 Continue, 101 Switching Protocols)
//2xx     -     Successful/Успешные (200 ОК , 201 Created , 204 No Content )
//3xx     -     Redirection/Перенапралвение ( 301 Moved Permantly  , 302 Fund  , 304 Not modified )
//4xx     -     Client Errors/Ошибка клиента (400 Bad request , 401 Unauthorized , 403 Forbidden , 404 Not Found ,405 Method not allowed )
//5xx     -     Server Errors/Ошибка сервера ( 500 Internal Server Error , 501 Not Implemented , 503 Server Unvaliable)

@RestController
@RequestMapping("/api/person")
public class PersonController {

    List<Person> people;

    public PersonController() {
        people = new ArrayList<>();
        people.add(new Person("Dima", "Bilan", 62));
        people.add(new Person("Oleq", "Qazmanov", 82));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(people);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletById(@PathVariable(name = "id") Integer id) {
        System.out.println("Id => " + id);
        boolean removed = people.removeIf(p -> p.getId() == id);

        if (removed) {
            return ResponseEntity.noContent().build();//204
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/getById/{id}")
//    public ResponseEntity<Person> getById(@PathVariable(name = "id") Integer id){
//        Optional<Person> first = people.stream().filter(p -> p.getId() == id).findFirst();
//
//        if(first.isPresent()){
////            return ResponseEntity.ok(first.get());
////            return ResponseEntity.status(404).body(first.get());
//            return ResponseEntity
//                    .status(HttpStatus.OK)
////                    .header("Custom-Header", "value")
//                    .body(first.get());
//        }else{
////            return  ResponseEntity.notFound().build();
////            return  ResponseEntity.status(404).build();
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//

    /// /        return  people.stream().filter(p->p.getId()==id).findFirst().get();
//    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        Optional<Person> first = people.stream().filter(p -> p.getId() == id).findFirst();

        return first.isPresent()
                ?  ResponseEntity
                        .status(HttpStatus.OK)
                        .body(first.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();


    }

    //ResponseEntity
    //Http status codes
    //Http headers
    //Response body

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody PersonDTO person
    ) {

        List<String> validationMessages = new ArrayList<>();


        if (person.getAge() < 0) {
            validationMessages.add("Age cannot be less than 0");
        }

        if (person.getAge() > 100) {
            validationMessages.add("Age cannot be greater than 100");
        }

        if (person.getName() == null) {
            validationMessages.add("Name cannot be null");
        }

        if (person.getName() != null && person.getName().isEmpty()) {
            validationMessages.add("Name cannot be empty");
        }

        if (person.getSurname() == null) {
            validationMessages.add("Surname cannot be null");
        }

        if (person.getSurname() != null && person.getName().isEmpty()) {
            validationMessages.add("Surname cannot be empty");
        }


        if (!validationMessages.isEmpty()) {
            System.out.println("Error in update");
           ErrorResponse response =  ErrorResponse.badRequest("Validation failed", validationMessages);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        Optional<Person> isResult = people.stream().filter(p -> p.getId() == id).findFirst();

        if (isResult.isPresent()) {

            Person p = isResult.get();

            if (person.getName() != null) {
                p.setName(person.getName());
            }

            if (person.getSurname() != null) {
                p.setSurname(person.getSurname());
            }

            if (person.getAge() != null) {
                p.setAge(person.getAge());
            }

//"Person updated Successfully"
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Location", "/api/person/update/" + id)
                    .header("Message", "Person updated Successfully")
                    .body(p);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id " + id + " not found");
    }

    @PostMapping("/add")
    public String add(
            @RequestBody PersonDTO person
    ) {
        if (person.getAge() < 0) return "Age can't be less than 0";
        if (person.getAge() > 100) return "Age can't be greater than 100";
        if (people.stream().anyMatch(x -> x.getName().equalsIgnoreCase(person.getName()) && x.getSurname().equalsIgnoreCase(person.getSurname())))
            return "This is person already exist";

        boolean isResult = people.add(new Person(person.getName(), person.getSurname(), person.getAge()));

        if (isResult) return "success";
        else return "fail";
    }


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
