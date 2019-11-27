import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Entry extends PApplet {
    public static Entry Instance;
    public float deltaTime = 0f;
    public SecureRandom SecureRandom;
    private PImage image;
    private ArrayList<Utils.Point2f> currentWorkingTriangle = new ArrayList<>();
    private int numVertex = 0;
    private ArrayList<Utils.Triangle> triangles = new ArrayList<>();

    private int activeDragIndex = -1;

    public static void main (String[] args) {
        PApplet.main(Entry.class.getName());
    }

    public void settings () {
        Instance = this;
        size(1920, 1080);
        fullScreen(2);
    }

    public void setup () {
        frameRate(1000);
        image = loadImage("working.png");
        SecureRandom = new SecureRandom();
    }

    public void update () {
        if (Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            //place vertex
            if (numVertex < 3) {
                currentWorkingTriangle.add(new Utils.Point2f(Input.MousePosition));
                numVertex++;
            }
        }
        //get collision with triangle vertex
        if (Input.GetButtonDown(KeyEvent.VK_RIGHT)) {
            for (int i = 0; i < currentWorkingTriangle.size(); i++) {
                var dist = Utils.distanceSq(currentWorkingTriangle.get(i), Input.MousePosition);
                System.out.println(dist);
                if (dist < 8 * 8) {
                    //make point active
                    activeDragIndex = i;
                    break;
                }
            }
        }
        //check if mouse on triangle and if so make editable
        if (Input.GetKeyDown(KeyEvent.VK_SPACE) && currentWorkingTriangle.isEmpty()) {
            int collisionTriangle = -1;
            for (int i = 0; i < triangles.size(); i++) {
                if (Utils.PointInTriangle(Input.MousePosition, triangles.get(i))) {
                    collisionTriangle = i;
                    break;
                }
            }
            if (collisionTriangle != -1) {
                var tri = triangles.get(collisionTriangle);
                triangles.remove(collisionTriangle);
                currentWorkingTriangle.add(tri.v1);
                currentWorkingTriangle.add(tri.v2);
                currentWorkingTriangle.add(tri.v3);
                numVertex = 3;
            }
        }
        //finish triangle
        if (numVertex == 3 && Input.GetKeyDown(KeyEvent.VK_ENTER)) {
            triangles.add(new Utils.Triangle(currentWorkingTriangle.get(0), currentWorkingTriangle.get(1), currentWorkingTriangle.get(2)));
            currentWorkingTriangle.clear();
            numVertex = 0;
        }
        //delete last point
        if(numVertex > 0 && Input.GetKeyDown(KeyEvent.VK_BACK_SPACE)) {
            currentWorkingTriangle.remove(currentWorkingTriangle.size()-1);
            numVertex--;
        }

        //move point
        if (activeDragIndex != -1) {
            currentWorkingTriangle.get(activeDragIndex).Set(Input.MousePosition);
        }

        // print triangles
        if (Input.GetKeyDown(KeyEvent.VK_E)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < triangles.size(); i++) {
                var t = triangles.get(i);
                sb.append(String.format("%d:\n", i));
                sb.append(t).append("\n");
                sb.append(String.format("%.0f, %.0f, %.0f, %.0f, %.0f, %.0f\n", t.v1.x, t.v1.y, t.v2.x, t.v2.y, t.v3.x, t.v3.y));
                sb.append(String.format("new Utils.Triangle(%.0f, %.0f, %.0f, %.0f, %.0f, %.0f)\n", t.v1.x, t.v1.y, t.v2.x, t.v2.y, t.v3.x, t.v3.y));
                sb.append(String.format(".AddCollisionTriangle(new Utils.Triangle(%.0f, %.0f, %.0f, %.0f, %.0f, %.0f))\n", t.v1.x, t.v1.y, t.v2.x, t.v2.y, t.v3.x, t.v3.y));
                sb.append("\n");
            }
            var exportText = sb.toString();
            var saveLocation = "exports/"+frameCount+"/";
            saveFrame(saveLocation+"exp-"+frameCount+".png");
            saveStrings(saveLocation + "exp-" + frameCount + ".txt", new String[]{exportText});
        }
    }

    public void render () {
        background(0);
        image(image, 0, 0);

        for(int i = 0; i < triangles.size(); i++) {
            var t = triangles.get(i);
            var center = Utils.TriangleCenter(t);

            fill(0x4400ff00);
            strokeWeight(1);
            stroke(0x00);
            triangle(t.v1.x, t.v1.y, t.v2.x, t.v2.y, t.v3.x, t.v3.y);
            fill(0xffff0000);
            textSize(12);
            textAlign(CENTER, CENTER);
            text(String.format("%d", i), center.x, center.y);
            textAlign(LEFT, BASELINE);
        }
        if (currentWorkingTriangle.size() == 3) {
            fill(0x4400ff00);
            strokeWeight(1);
            stroke(0x00);

            triangle(currentWorkingTriangle.get(0).x, currentWorkingTriangle.get(0).y, currentWorkingTriangle.get(1).x, currentWorkingTriangle.get(1).y, currentWorkingTriangle.get(2).x, currentWorkingTriangle.get(2).y);
        }
        currentWorkingTriangle.forEach(v -> {
            strokeWeight(16);
            stroke(0x33ff0000);
            point(v.x, v.y);
            strokeWeight(3);
            stroke(0xffff0000);
            point(v.x, v.y);
        });
        fill(0, 255, 0);
        textSize(20);
        text(String.format("FPS %.4f\ndT  %.6f", frameRate, deltaTime), 1920 - 200, 20);
        fill(0);
        text(String.format("%s", Input.MousePosition), 1920 - 250, 90);

    }

    public void draw () {
        deltaTime = 1f / frameRate;
        update();
        render();

        Input.Refresh();
    }

    //<editor-fold desc="Input Handling">
    @Override
    public void keyPressed (processing.event.KeyEvent event) {
        super.keyPressed(event);
        Input.PressKey(event.getKeyCode());
    }

    @Override
    public void keyReleased (processing.event.KeyEvent event) {
        super.keyReleased(event);
        Input.ReleaseKey(event.getKeyCode());
    }

    @Override
    public void mouseWheel (MouseEvent event) {
        super.mouseWheel(event);
        Input.Scroll(event.getCount());
    }

    @Override
    public void mousePressed (MouseEvent event) {
        super.mousePressed(event);
        Input.PressButton(event.getButton());
    }

    @Override
    public void mouseReleased (MouseEvent event) {
        super.mouseReleased(event);
        Input.ReleaseButton(event.getButton());

        //if active point make not active
        activeDragIndex = -1;
    }

    @Override
    public void mouseMoved (MouseEvent event) {
        super.mouseMoved(event);
        Input.MouseX = event.getX();
        Input.MouseY = event.getY();
        Input.MousePosition.x = Input.MouseX;
        Input.MousePosition.y = Input.MouseY;
    }

    @Override
    public void mouseDragged (MouseEvent event) {
        super.mouseMoved(event);
        Input.MouseX = event.getX();
        Input.MouseY = event.getY();
        Input.MousePosition.x = Input.MouseX;
        Input.MousePosition.y = Input.MouseY;
    }
    //</editor-fold>
}
