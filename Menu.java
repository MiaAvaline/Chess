package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Menu extends JFrame implements ActionListener {

    private JButton Game_Start;
    private JButton Game_Over;
    private JLabel backLabel;


    public Menu(){
        //定义按钮的排列方式
        setTitle("Main Menu");
        setLayout(new FlowLayout(1,80,300));
        this.setSize(600,500);
        this.setLocation(400,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        Game_Start = new JButton("BEGIN");
        Game_Over = new JButton("QUIT");
        this.add(Game_Start);
        this.add(Game_Over);
        //Game_Start.setBounds(50,50,100,100);
        Dimension preferredSize=new Dimension(160, 60);
        Game_Start.setPreferredSize(preferredSize);
        Game_Over.setPreferredSize(preferredSize);
        Game_Start.addActionListener(this);
        Game_Over.addActionListener(this);
        this.setVisible(true);

        setLayout(null);

        ImageIcon back = new ImageIcon("./images/BACK.png");
        Image image = back.getImage();
        Image smallImage = image.getScaledInstance(100, 100, Image.SCALE_FAST);
        ImageIcon backbrounds = new ImageIcon(smallImage);
        backLabel = new JLabel(backbrounds);
        backLabel.setBounds(0,0,100,100);
        this.add(backLabel);
        this.setVisible(true);
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Game_Start){
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(800, 760);
                mainFrame.setVisible(true);

            });
        }
        if(e.getSource() == Game_Over){
            this.dispose();
            System.exit(0);
        }
    }
}