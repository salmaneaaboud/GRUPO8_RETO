import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Main container with BorderLayout
        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // North area with FlowLayout
        // JCheckBox for the boxes
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("News"));
        northPanel.add(new JButton("Ranking"));
        northPanel.add(new JButton("Others"));
        mainContainer.add(northPanel, BorderLayout.NORTH);

    }
}