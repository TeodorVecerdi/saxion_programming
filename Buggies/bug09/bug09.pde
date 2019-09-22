// The pupil should follow the mouse horizontally
// strange phenomenon, if you press the mouse button
// and mouse extreme left or right, it kinda works

void setup() {
  size(800,600);
}

void draw() {
  background(100);
  // eyeball
  fill(255);
  ellipse(width/2,height/2,100,100);
  
  // pupil
  fill(0);

  ///// buggy:
  ellipse(width / 2 + mouseX / (float)width * 80 - 40 , height/2, 20, 20);  
}
