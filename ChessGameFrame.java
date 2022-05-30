package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {

    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private JPanel rightJPanel;
    private JLabel statusLabel;
    private JLabel backLabel;
    private JLabel timeLabel;
    private int delay;
    private int curSec;


    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Chess"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        setSize(width + 200, height + 20);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        getContentPane().setLayout(new BorderLayout(0, 0));
        rightJPanel = new JPanel();
        rightJPanel.setPreferredSize(new Dimension(200, 10));
        getContentPane().add(rightJPanel, BorderLayout.EAST);
        rightJPanel.setLayout(new BoxLayout(rightJPanel, BoxLayout.Y_AXIS));


        addChessboard();
        addLabel();
        BackLabel();
        TimeLabel();
        addResetButton();
        addSaveButton();
        addLoadButton();
        addSkinButton();
    }

    private void addSaveButton() {
        JButton button = new JButton("Save");
        button.setMaximumSize(new Dimension(2000, 50));
        button.addActionListener((e) -> gameController.save());
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);

        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        rightJPanel.add(button);
    }




    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        gameController.setConsumer(e -> statusLabel.setText(e));
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        getContentPane().add(chessboard, BorderLayout.CENTER);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel("Sample label");
        statusLabel.setText(gameController.getChessboard().getCurrentColor().getName());
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setMaximumSize(new Dimension(200, 50));
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        rightJPanel.add(statusLabel);
        //statusLabel.setOpaque();
    }
    private void TimeLabel() {

        int delay=60000;    //时间间隔，单位为毫秒
        this.delay = delay;
        this.curSec =delay;
       ActionListener taskPerformer = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gameController.reset();
            }
        };
        new Timer(delay,taskPerformer).start();


    timeLabel = new JLabel();
        timeLabel.setText("Time:"+"s");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setLocation(HEIGTH, HEIGTH / 590);
        timeLabel.setMaximumSize(new Dimension(200, 50));
        timeLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        rightJPanel.add(timeLabel);
    }


    private void BackLabel() {
        ImageIcon backbround = new ImageIcon("./images/BACK.png");

        Image image = backbround.getImage();
        Image smallImage = image.getScaledInstance(1300, 850, Image.SCALE_FAST);
        ImageIcon backbrounds = new ImageIcon(smallImage);

        backLabel = new JLabel(backbrounds);
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.setLocation(0, 0);
        add(backLabel);
    }


    private void addResetButton() {
        JButton button = new JButton("Reset");
        button.setMaximumSize(new Dimension(2000, 50));
        button.addActionListener((e) -> gameController.reset());
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);

        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        rightJPanel.add(button);
    }

    private void addSkinButton() {
        JButton button = new JButton("Skin");
        button.setMaximumSize(new Dimension(2000, 50));
        button.addActionListener((e) -> {
            try {
                Bishop bishop1 = (Bishop) Chessboard.getChessComponents()[7][2];
                Bishop bishop2 = (Bishop) Chessboard.getChessComponents()[7][5];
                bishop1.changeResource();
                bishop1.initiateImage(ChessColor.WHITE);
                bishop1.repaint();
                bishop2.changeResource();
                bishop2.initiateImage(ChessColor.WHITE);
                bishop2.repaint();
                Bishop bishop3 = (Bishop) Chessboard.getChessComponents()[0][2];
                Bishop bishop4 = (Bishop) Chessboard.getChessComponents()[0][5];
                bishop3.changeResource();
                bishop3.initiateImage(ChessColor.BLACK);
                bishop3.repaint();
                bishop4.changeResource();
                bishop4.initiateImage(ChessColor.BLACK);
                bishop4.repaint();


                Knight knight1 = (Knight) Chessboard.getChessComponents()[7][1];
                Knight knight2 = (Knight) Chessboard.getChessComponents()[7][6];
                knight1.changeResource();
                knight1.initiateImage(ChessColor.WHITE);
                knight1.repaint();
                knight2.changeResource();
                knight2.initiateImage(ChessColor.WHITE);
                knight2.repaint();
                Knight knight3 = (Knight) Chessboard.getChessComponents()[0][1];
                Knight knight4 = (Knight) Chessboard.getChessComponents()[0][6];
                knight3.changeResource();
                knight3.initiateImage(ChessColor.BLACK);
                knight3.repaint();
                knight4.changeResource();
                knight4.initiateImage(ChessColor.BLACK);
                knight4.repaint();


                Pawn pawn0 = (Pawn) Chessboard.getChessComponents()[6][0];
                Pawn pawn1 = (Pawn) Chessboard.getChessComponents()[6][1];
                Pawn pawn2 = (Pawn) Chessboard.getChessComponents()[6][2];
                Pawn pawn3 = (Pawn) Chessboard.getChessComponents()[6][3];
                Pawn pawn4 = (Pawn) Chessboard.getChessComponents()[6][4];
                Pawn pawn5 = (Pawn) Chessboard.getChessComponents()[6][5];
                Pawn pawn6 = (Pawn) Chessboard.getChessComponents()[6][6];
                Pawn pawn7 = (Pawn) Chessboard.getChessComponents()[6][7];
                pawn0.changeResource();
                pawn0.initiateImage(ChessColor.WHITE);
                pawn0.repaint();
                pawn1.changeResource();
                pawn1.initiateImage(ChessColor.WHITE);
                pawn1.repaint();
                pawn2.changeResource();
                pawn2.initiateImage(ChessColor.WHITE);
                pawn2.repaint();
                pawn3.changeResource();
                pawn3.initiateImage(ChessColor.WHITE);
                pawn3.repaint();
                pawn4.changeResource();
                pawn4.initiateImage(ChessColor.WHITE);
                pawn4.repaint();
                pawn5.changeResource();
                pawn5.initiateImage(ChessColor.WHITE);
                pawn5.repaint();
                pawn6.changeResource();
                pawn6.initiateImage(ChessColor.WHITE);
                pawn6.repaint();
                pawn7.changeResource();
                pawn7.initiateImage(ChessColor.WHITE);
                pawn7.repaint();
                Pawn pawn8 = (Pawn) Chessboard.getChessComponents()[1][0];
                Pawn pawn9 = (Pawn) Chessboard.getChessComponents()[1][1];
                Pawn pawn10 = (Pawn) Chessboard.getChessComponents()[1][2];
                Pawn pawn11 = (Pawn) Chessboard.getChessComponents()[1][3];
                Pawn pawn12 = (Pawn) Chessboard.getChessComponents()[1][4];
                Pawn pawn13 = (Pawn) Chessboard.getChessComponents()[1][5];
                Pawn pawn14 = (Pawn) Chessboard.getChessComponents()[1][6];
                Pawn pawn15 = (Pawn) Chessboard.getChessComponents()[1][7];
                pawn8.changeResource();
                pawn8.initiateImage(ChessColor.BLACK);
                pawn8.repaint();
                pawn9.changeResource();
                pawn9.initiateImage(ChessColor.BLACK);
                pawn9.repaint();
                pawn10.changeResource();
                pawn10.initiateImage(ChessColor.BLACK);
                pawn10.repaint();
                pawn11.changeResource();
                pawn11.initiateImage(ChessColor.BLACK);
                pawn11.repaint();
                pawn12.changeResource();
                pawn12.initiateImage(ChessColor.BLACK);
                pawn12.repaint();
                pawn13.changeResource();
                pawn13.initiateImage(ChessColor.BLACK);
                pawn13.repaint();
                pawn14.changeResource();
                pawn14.initiateImage(ChessColor.BLACK);
                pawn14.repaint();
                pawn15.changeResource();
                pawn15.initiateImage(ChessColor.BLACK);
                pawn15.repaint();

                Rook rook1 = (Rook) Chessboard.getChessComponents()[7][0];
                Rook rook2 = (Rook) Chessboard.getChessComponents()[7][7];
                rook1.changeResource();
                rook1.initiateRookImage(ChessColor.WHITE);
                rook1.repaint();
                rook2.changeResource();
                rook2.initiateRookImage(ChessColor.WHITE);
                rook2.repaint();
                Rook rook3 = (Rook) Chessboard.getChessComponents()[0][0];
                Rook rook4 = (Rook) Chessboard.getChessComponents()[0][7];
                rook3.changeResource();
                rook3.initiateRookImage(ChessColor.BLACK);
                rook3.repaint();
                rook4.changeResource();
                rook4.initiateRookImage(ChessColor.BLACK);
                rook4.repaint();

                Queen queen1 = (Queen) Chessboard.getChessComponents()[7][4];
                queen1.changeResource();
                queen1.initiateImage(ChessColor.WHITE);
                queen1.repaint();
                Queen queen2 = (Queen) Chessboard.getChessComponents()[0][4];
                queen2.changeResource();
                queen2.initiateImage(ChessColor.BLACK);
                queen2.repaint();

                King king1 = (King) Chessboard.getChessComponents()[7][3];
                king1.changeResource();
                king1.initiateImage(ChessColor.WHITE);
                king1.repaint();
                King king2 = (King) Chessboard.getChessComponents()[0][3];
                king2.changeResource();
                king2.initiateImage(ChessColor.BLACK);
                king2.repaint();


            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 180);

        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        rightJPanel.add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setMaximumSize(new Dimension(2000, 50));

        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        rightJPanel.add(button);

        button.addActionListener(e -> {
            //System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");

            if (!path.endsWith(".txt")) {
                statusLabel.setText("error 104");
                return;
            }
            gameController.loadGameFromFile(path);
        });
    }

}
