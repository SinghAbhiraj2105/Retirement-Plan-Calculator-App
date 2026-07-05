package calculator;

/*
 * Project Name: Retirement Calculator
 * Course: CSCI 185-M01
 * Contributor: Yuquan Zeng
 * Last Updated: 5/10/2026
 */
import javax.swing.*;
import java.awt.*;


public class StartPage extends JFrame{

    public StartPage(){
        setTitle("Retirement Plan Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,550);
        setLocationRelativeTo(null); // start at center

        add(welcomePage());
        setVisible(true);

    }

    public JPanel welcomePage(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        //center panel
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.Y_AXIS));
        cPanel.setOpaque(false);

        cPanel.add(Box.createVerticalGlue());

        //title panel in the center
        JLabel title = new JLabel("RETIREMENT PLAN CALCULATOR");
        title.setFont(new Font("Arial",Font.BOLD,36));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        cPanel.add(title);

        cPanel.add(Box.createRigidArea(new Dimension(0,10)));

        //text in label in the center
        JLabel wLabel = new JLabel("Your financial future is in your hands");
        wLabel.setFont(new Font("Arial",Font.PLAIN,25));
        wLabel.setForeground(Color.GRAY);
        wLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cPanel.add(wLabel);

        //adds space between the components
        cPanel.add(Box.createRigidArea(new Dimension(0,10)));

        //action button to go to next page
        JButton startBtn = new JButton("Start");
        startBtn.setFocusPainted(false);
        startBtn.setFont(new Font("Arial",Font.PLAIN,20));
        startBtn.setForeground(Color.BLUE);
        startBtn.setPreferredSize(new Dimension(100,50));
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new PlanFrame().setVisible(true));
            dispose();
        });
        cPanel.add(startBtn);
        cPanel.add(Box.createVerticalGlue());

        panel.add(cPanel,BorderLayout.CENTER);

        return panel;

    }



public static void main(String [] args) {
    SwingUtilities.invokeLater(() -> new StartPage());
}
}
