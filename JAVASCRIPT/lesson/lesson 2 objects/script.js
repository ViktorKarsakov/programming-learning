// function test() {
//     console.log("Farid Abdullayev");
// }


// let person = {
//     name: 'Farid',
//     surname: 'Abdullayev',
//     age: 29,
//     fathername: 'Ashraf',
//     foo: test,
//     hi: function() {
//         console.log("Hello world!");
//     },
//     car: {
//         marka: 'Lada',
//         model: '05',
//         year: 1999
//     },
//     dogs: [{
//             name: 'Rexs',
//             year: 1
//         },
//         {
//             name: 'Jmurka',
//             year: 2
//         },
//         {
//             name: 'Barsik',
//             year: 1
//         }
//     ]
// }

// let num = person.foo();
// console.log(num);

// console.log(person['name']);


// person.gender = 'MAN';
// delete person.name;
// console.log(person);

// person.foo();
// console.log(person.hi());


// const name = 10;
// name = 20;
// console.log(name);


// let createPerson = (n, a, c) => {
//     return {
//         name: n,
//         age: a,
//         city: c,
//         greet: function() {
//             return `Privet , menya zovut ${this.name}`;
//         }
//     }
// }




// let people = [];
// let names = ['Farid', 'David', 'Cavid'];
// let cities = ['Baku', 'Moscow', 'Paris'];


// for (let i = 0; i < 3; i++) {
//     let person = createPerson(names[i], (i + 2 * 2), cities[i])
//     people.push(person);
// }

// console.log(people);



////////////////////////////////////////////////////////////////////////////////


// function Person(n, a, c) {
//     this.name = n;
//     this.city = c;
//     this.age = a;
//     this.greet = function() {
//         return `Privet , menya zovut ${this.name}`;
//     }
// }

// let people = [];
// let names = ['Farid', 'David', 'Cavid'];
// let cities = ['Baku', 'Moscow', 'Paris'];


// for (let i = 0; i < 3; i++) {
//     let person = new Person(names[i], (i + 2 * 2), cities[i])
//     people.push(person);
// }

// console.log(people);


////////////////////////////////////////////////////////////////////////////////


// class Person {
//     constructor(n, a, c) {
//         this.name = n;
//         this.city = c;
//         this.age = a;
//     }

//     greet() {
//         return `Privet , menya zovut ${this.name}`;
//     }
// }

// let people = [];
// let names = ['Farid', 'David', 'Cavid'];
// let cities = ['Baku', 'Moscow', 'Paris'];


// for (let i = 0; i < 3; i++) {
//     let person = new Person(names[i], (i + 2 * 2), cities[i])
//     people.push(person);
// }

// console.log(people);