// for each of the four quarters of the screen
// the color slowly fades to a different color
// mouseY in upper or lower half should change blue color
// mouseX in left or right should change red color
// mousePressed should change green


//section 1
float r=0;
float g=0;
float b=0;

void setup () {
    size (200, 200);
}

// Section 2
void draw () {
    background (r, g, b);
    stroke (0);
    line (width/2, 0, width/2, height);
    line (0, height/2, width, height/2);

    // Section3
    if (mouseX > width/2) {
        r = r+1;
    } else {
        r =r-1;
    }

    // Section 4
    if (mouseY > height/2) {
        b = b+1;
    } else {
        b = b-1;
    }

    // Section 5
    if (mousePressed) {
        g = g+1;
    } else {
        g = g-1;
    }


    // Section 6
    r = constrain (r, 0, 255);
    g = constrain (g, 0, 255);
    b = constrain (b, 0, 255);

    println(r, g, b);
}
