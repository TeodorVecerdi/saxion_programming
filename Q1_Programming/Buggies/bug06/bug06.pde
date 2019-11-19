// fill the circle with red if the mouse
// is inside the circle at the middle of the
// screen and blue if it is outside the circle.

void setup() {
  size (400,400);
  strokeWeight( 3 );
}


int diameter = 200;

void draw() {
  background (0);
  if ( keyPressed ) {
    if ( key == 'g' || key == 'G' ) {
      diameter++;  // grow
    } else if ( key == 's' || key == 'S' ) {
      diameter--;  // shrink
    }
  }
  
  if ( dist( mouseX, mouseY, 200, 200 ) < diameter/2f ) {
    fill( 255, 0, 0 );
  } else {
    fill( 0, 0, 255 );
  }
  ellipse(200,200,diameter,diameter);
}
