// // function test() {
// //     console.log("Farid : " + count++);
// // }


// //1s - 1000ms

// // setTimeout(test, 5 * 1000);
// // setTimeout(() => {
// //     console.log("Farid");
// // }, 5 * 1000);

// // let count = 1;
// // // setInterval(test, 1 * 1000)
// // setInterval(() => {
// //     console.log("Farid : " + count++);
// // }, 1 * 1000)


// // let count = 1;
// // setInterval(() => {
// //     if (count == 10) {
// //         return;
// //     }
// //     console.log("Farid : " + count++);


// // }, 1000);


// // let count = 1;
// // setInterval(() => {
// //     if (count == 10) {
// //         console.log("STOP");

// //         return;
// //     }
// //     console.log("Farid : " + count++);
// // }, 500);


// let count = 1;
// let id = setInterval(() => {

//     console.log("Farid : " + count++);
//     if (count == 10) {
//         console.log('Stop');

//         clearInterval(id);
//     }
// }, 500);