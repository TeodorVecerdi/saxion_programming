// Why do I not see my character anymore after I tried
// to draw a background?

boolean erase = false;
float x = 175;
float y = 100;


void setup() {
  size(1000, 500);
  noStroke();
  background(255);
}
void draw() {
  //here's the paper bg
  
  background(255);
  
  for (float paperY = 50; paperY < height; paperY+=50){
    fill(199,199,255);
    rect(0,paperY,width,10);
    paperY+=50;
  }
  fill(250,100,100);
  rect(50, 0, 10, height);
  
  //this is the stuff for the line +erasing it
  fill(0);
  if (erase==false) {
    rect (width-200, 0+100, 5, 200);
  }
  if (x > width-345) {
    erase = true;
  }

  //everything else in draw is the character
  //these are the blue jeans, they're supposed to not be squares for the lil triangle space
  fill(130, 177, 255);
  rect(x+25, y+200, 25, 150);
  //^left leg
  rect(x+100, y+200, 25, 150);
  rect(x+50, y+200, 50, 30);
  //^right leg
  //here's the body, its lowered onto the quad legs above: the body is here so the pink from the legs doesn't
  //go over the legs
  fill(226, 58, 60);
  rect(x, y, 150, 150, 9);
  rect(x, y+175, 150, 35, 9);
  //simple belt and stuff
  fill(214, 214, 214);
  rect(x+7, y+150, 136, 25);
  //eyeball time baby
  fill(255);
  ellipse(x+40, y+50, 30, 30);
  //the second eye is slightly bigger for that Quirky look
  ellipse(x+110, y+50, 35, 35);
  //pupils, the point at which an ellipse is generated is in the middle of the circle so pupils are the same just smaller
  fill(0);
  ellipse(x+40, y+50, 10, 10);
  ellipse(x+110, y+50, 12, 12);
  //this is all shoe stuff
  //first is the red triangles
  fill(119, 111, 93);
  triangle(x+25, y+350, x+25, y+375, x-25, y+375);
  triangle(x+125, y+350, x+125, y+375, x+175, y+375);
  //here come the rest of the red thats like the heel
  rect(x+25, y+350, 25, 25);
  rect(x+100, y+350, 25, 25);
  //below is the actual white lines of the shoes
  fill(1);
  rect(x+100, y+375, 75, 10);
  rect(x-25, y+375, 75, 10);
  
  //lastly we get the constrain to keep him within frame
  x= constrain(x, 0, width-150);
}

//keybinds
void keyPressed() {
  if (keyCode == 'D') {
    x+=10;
  }
  if (keyCode == 'A') {
    x-=10;
  }
  if (key == ENTER) {
    erase = false;
  }
}
