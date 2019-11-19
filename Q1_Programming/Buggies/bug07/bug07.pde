// fill the circle with red if the mouse is on the
// right half of the screen and black otherwise.

void setup() {
  size (400,400);
}

void draw() {
  background (128);
  stroke( 0 );
  strokeWeight( 3 );
  line( width / 2 , 0, width / 2 , height );
  fill( 0 );
  if ( mouseX > 200 )
    fill( 255, 0, 0 );
  ellipse( 200, 200, 200, 200 );
}
