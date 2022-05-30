package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ClickController;
import view.ChessboardPoint;

public class Knight extends ChessComponent {

	private static Image WHITE;
    private static Image BLACK;
	private Image image;
	public Knight(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor,
			ClickController clickController, int size) {
		super(chessboardPoint, location, chessColor, clickController, size);
		// TODO Auto-generated constructor stub
		initiateImage(chessColor);
	}
	
	public void initiateImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                image = WHITE;
            } else if (color == ChessColor.BLACK) {
                image = BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	@Override
	public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
		// TODO Auto-generated method stub
		ChessboardPoint source = getChessboardPoint();
		ChessComponent chessComponent=chessboard[destination.getX()][destination.getY()];
		if (getChessColor()!=chessComponent.getChessColor()) {
			if (Math.abs(source.getX()-destination.getX())==1&&Math.abs(source.getY()-destination.getY())==2||
					Math.abs(source.getY()-destination.getY())==1&&Math.abs(source.getX()-destination.getX())==2) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void loadResource() throws IOException {
		// TODO Auto-generated method stub
		if (WHITE == null) {
            WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (BLACK == null) {
            BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }

	}
	public void changeResource()throws IOException{
		WHITE = ImageIO.read(new File("./images/knight-white2.png"));
		BLACK = ImageIO.read(new File("./images/knight-black2.png"));
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 if (chessColor==ChessColor.BLACK) {
			return "N";
		}
		return "n";
	}

}
