// Divide the screen into four areas, red, green and grey
// when the mouse enters an area, it lights up in that color.

void setup() {
    size(200, 200);
}

void draw() {
    background(255);
    if (mouseX>0 && mouseX <50&& mouseY>0&&mouseY<50) {
        fill(255, 0, 0);
        rect(0, 0, 50, 50);
    } else if (mouseX>50 && mouseX <100 && mouseY>50 && mouseY<100) {
        fill(0, 255, 0);
        rect(50, 50, 50, 50);
    } else if (mouseX>100 && mouseX <150 && mouseY>100 &&mouseY<150) {
        fill(0, 0, 255);
        rect(100, 100, 50, 50);
    } else if (mouseX>150 && mouseX <200 && mouseY>150 &&mouseY<200) {
        fill(50, 50, 50);
        rect(150, 150, 50, 50);
    }
    // I don't if the above functionality (screen divided into four areas)
    // is intended or if it was intended to divide into two rows and two columns 
}
