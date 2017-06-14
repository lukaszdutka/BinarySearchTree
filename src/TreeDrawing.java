import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Dasi on 2017-05-10.
 */
public class TreeDrawing<T> extends Canvas implements Runnable {

    Node mainNode;

    private Thread thread;
    private boolean running = false;

    static final int WIDTH = 1024;
    static final int HEIGHT = 1024;
    BinarySearchTree<T> insideBST;

    public TreeDrawing(BinarySearchTree tree){
        mainNode = tree.getRoot();
        insideBST = tree;

        new Window(WIDTH, HEIGHT, "Tree Viewer", this);
    }

    public void draw(Node node, int leftBorder, int rightBorder, int height, Graphics g){

        TreeDrawing.drawIT(node, (leftBorder+rightBorder)/2, height, g);
        int parentWidth = (leftBorder+rightBorder)/2;
        int parentHeight = height;

        int leftChildWidth = (3*leftBorder + rightBorder)/4;
        int rightChildWidth = (leftBorder + 3*rightBorder)/4;
        int childHeight = height + 50;

        int parentSize = node.getValue().hashCode()/20;
        int leftSize;
        int rightSize;

        if(node.getLeft() != null) {
            leftSize = node.getLeft().getValue().hashCode()/20;
            g.setColor(Color.black);
            g.drawLine(parentWidth + parentSize, parentHeight + parentSize, leftChildWidth + leftSize, childHeight + leftSize);

            draw(node.getLeft(), leftBorder, (leftBorder + rightBorder) / 2, childHeight, g);

            g.setColor(Color.blue);
            g.drawString("L", (parentWidth + leftChildWidth + 8)/2, (parentHeight + childHeight + 8)/2);
        }
        if(node.getRight() != null) {
            rightSize = node.getRight().getValue().hashCode()/20;
            g.setColor(Color.black);
            g.drawLine(parentWidth + parentSize, parentHeight + parentSize, rightChildWidth + rightSize, childHeight + rightSize);

            draw(node.getRight(), (leftBorder + rightBorder) / 2, rightBorder, childHeight, g);

            g.setColor(Color.blue);
            g.drawString("R", (parentWidth + rightChildWidth + 8)/2, (parentHeight + childHeight + 8)/2);
        }
    }

    public static void drawIT(Node node,  int width, int height, Graphics g){
        //Random r = new Random();
        //float h = r.nextFloat()*240+15;
        //float s = r.nextFloat()*240+15;
        //float b = r.nextFloat()*240+15;

        g.setColor(Color.GREEN);
        //g.fillRect(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(20)+5, r.nextInt(20)+5);

        int nodeSize = node.getValue().hashCode()/10;
        g.fillRect(width, height, nodeSize, nodeSize);
        g.setColor(Color.black);
        g.drawString(node.getValue().toString(), width, height);
    }


    public void startDrawing(Graphics g){
        draw(mainNode, 0, WIDTH, 50, g);
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            while (System.nanoTime() - now < 250000000){

            }
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
            }
            if (running)
                render();

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    protected void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        startDrawing(g);

        g.dispose();
        bs.show();
    }

    protected synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected BinarySearchTree<T> getInsideBST(){
        return insideBST;
    }
}
