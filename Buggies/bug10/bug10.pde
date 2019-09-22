// lastname / achternaam:akwali
// firstname / voornaam:elisha
// class / klas:ekt1va
// student number/nummer:403892

color offWhite = color(255, 255, 248);

void setup() {
    size(600, 600); 
    rectMode( CENTER );
}

void draw() {
    background(offWhite);

    //int colorAddition = 0;
    //drawCone( 1 * width / 4, 1 * height / 4 );
    drawCone( 3 * width / 4, 1 * height / 4 );
    drawCone( 1 * width / 4, 3 * height / 4 );
    drawCone( 3 * width / 4, 3 * height / 4 );
}


void drawCone( int x, int y ) {
    //for (int x = width/2 ; x > 0; x = x - 10){
    float size = width / 2;
    float deltaX = ( mouseX - x ) / 30;
    float deltaY = ( mouseY - y ) / 30;
    for (int i = 0; i < 30; i++ ) {

        fill( 0, map( i, 0, 30, 0, 255), map( i, 0, 30, 128, 255) );
        ellipse( x, y, size, size);
        x += deltaX;
        y += deltaY;
        size -= 10;

        //colorAddition = colorAddition + 34;
    }
}
