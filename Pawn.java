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

public class Pawn extends ChessComponent {

	private static Image WHITE;
    private static Image BLACK;
	private Image image;
	private boolean firstStep=true;
	public Pawn(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor,
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
		if (chessboard[destination.getX()][destination.getY()] instanceof EmptySlotComponent) {
			if (getChessboardPoint().getY()==destination.getY()) {
				if (chessColor==ChessColor.BLACK&&(destination.getX()==getChessboardPoint().getX()+1||
						destination.getX()==getChessboardPoint().getX()+2&&firstStep)||
						chessColor==ChessColor.WHITE&&(destination.getX()==getChessboardPoint().getX()-1||
								destination.getX()==getChessboardPoint().getX()-2&&firstStep)) {
					if (firstStep) {
						if (destination.getX()==getChessboardPoint().getX()+2) {
							if (!(chessboard[getChessboardPoint().getX()+1][getChessboardPoint().getY()] instanceof EmptySlotComponent)) {
								return false;
							}
						}
						if (destination.getX()==getChessboardPoint().getX()-2) {
							if (!(chessboard[getChessboardPoint().getX()-1][getChessboardPoint().getY()] instanceof EmptySlotComponent)) {
								return false;
							}
						}
					}
					firstStep=false;
					return true;
				}
			}
		} else {
			if (chessColor==ChessColor.BLACK&&
					destination.getX()==getChessboardPoint().getX()+1&&
					(destination.getY()==getChessboardPoint().getY()+1||destination.getY()==getChessboardPoint().getY()-1)||
					chessColor==ChessColor.WHITE&&
					destination.getX()==getChessboardPoint().getX()-1&&
					(destination.getY()==getChessboardPoint().getY()-1||destination.getY()==getChessboardPoint().getY()+1)) {
				firstStep=false;
				return true;
			}

		}
		
		
		return false;
	}

	@Override
	public void loadResource() throws IOException {
		// TODO Auto-generated method stub
		if (WHITE == null) {
            WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (BLACK == null) {
            BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }

	}
	public void changeResource()throws IOException{
		WHITE = ImageIO.read(new File("./images/pawn-white2.png"));
		BLACK = ImageIO.read(new File("./images/pawn-black2.png"));
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
				return "P";
			}
			return "p";
		}

}
