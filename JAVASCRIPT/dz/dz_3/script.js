function random(min, max){
  return Math.random() * (max - min) + min;
}
function randomInt(min, max){
  return Math.floor(Math.random() * (max - min + 1) + min);
}

const blocks = [];
const sizeBox = 100;
/* const widthWindow = window.innerWidth;
const heightWindow = window.innerHeight; */
let running = false;

let boxes = document.querySelectorAll('.box');
for (const box of boxes) {
  const x = randomInt(0, window.innerWidth - sizeBox);
  const y = randomInt(0, window.innerHeight - sizeBox);
  box.style.position = 'fixed';
  box.style.width = sizeBox + 'px';
  box.style.height = sizeBox + 'px';
  box.style.left = x + 'px';
  box.style.top = y + 'px';

  
  box.addEventListener('click', function(){
    
    if (!running){
      return;
    }
    box.remove();
    boxDestroyed++;
    scoreEL.innerText = boxDestroyed;
  })

  blocks.push({
    el: box,
    x: x,
    y: y,
    vx: random(-1000, 1000),
    vy: random(-1000, 1000),
    size: sizeBox
  });
}

const start = document.getElementById('startBtn');
const scoreEL = document.getElementById('score');
let boxDestroyed = 0;

let lastTime = performance.now();
function gameLoop(now){
  const dt = (now - lastTime) / 1000; 
  lastTime = now;

  for (const block of blocks) {
    block.x += block.vx * dt;
    block.y += block.vy * dt;

    if (block.x <= 0){
      block.x = 0;
      block.vx *= -1;
    }
    if (block.x >= window.innerWidth - block.size){
      block.x = window.innerWidth - block.size;
      block.vx *= -1;
    }
    if (block.y <= 0){
      block.y = 0;
      block.vy *= -1;
    }
    if (block.y >= window.innerHeight - block.size){
      block.y = window.innerHeight - block.size;
      block.vy *= -1;
    }

    block.el.style.left = block.x + 'px';
    block.el.style.top = block.y + 'px';
  }
  requestAnimationFrame(gameLoop);
}

start.addEventListener('click', function(){
  boxDestroyed = 0;
  running = true;
  scoreEL.innerText = boxDestroyed;
  lastTime = performance.now();
  requestAnimationFrame(gameLoop);
})



