import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Dasi on 2017-05-10.
 */
public class Window extends Canvas implements ActionListener {

    int width;
    int height;
    String title;
    JFrame frame;
    TreeDrawing treeDrawing;

    public Window(int width, int height, String title, TreeDrawing treeDrawing){
        this.width = width;
        this.height = height;
        this.title = title;
        this.treeDrawing = treeDrawing;

        frame = new JFrame();

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(title);

        /** Button **/
        JButton przycisk = new JButton("Dodaj element (1-220)");
        przycisk.addActionListener(this);
        frame.add(przycisk);
        frame.getContentPane().add(BorderLayout.SOUTH, przycisk);
        /** Koniec o buttonie**/

        frame.add(treeDrawing);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        treeDrawing.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random r = new Random();
        treeDrawing.getInsideBST().addNode(r.nextInt(220));
    }
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
