// The sun should rise with the mouseX position
//     left corner should be low on the horizon
//     right corner should be all the way up.
// excellent would be if the sun folows a half circle


void setup(){
  size(400,400);
}
void draw(){
  float sunX = mouseX;
  float sunH = mouseX/(float)width;
  float sunY = 200-200*sunH;
  background(0, 255,255);
  fill(255,255,0);
  ellipse(sunX,sunY+sunH,50,50);
  fill( 0, 255, 0 );
  rect( 0, 200, width, 200 );
}
