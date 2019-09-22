// fill the rectangle with yellow if the mouse
// is inside rectangle at the middle of the
// screen and black if it is outside the middle.

void setup() {
  size (400,400);
}

void draw() {
  background (0);
  rect (100,100,200,200);
  if (mouseX > 100 && mouseY > 100 && mouseX < width-100 && mouseY < height-100) {
    fill (255,255,0);
  } else {
    fill (0);
  }
}
