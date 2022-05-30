package view;


import model.Bishop;
import model.ChessColor;
import model.ChessComponent;
import model.EmptySlotComponent;
import model.King;
import model.Knight;
import model.Pawn;
import model.Queen;
import model.Rook;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private static final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor = ChessColor.WHITE;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    private Consumer<String> consumer;
    private boolean gameOver=false;


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        //System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        // FIXME: Initialize chessboard for testing only.
        init();
    }
    
    
    
    public boolean isGameOver() {
		return gameOver;
	}
    
    public void setConsumer(Consumer<String> consumer) {
		this.consumer = consumer;
	}
    
    public void reset() {
		init();
		currentColor = ChessColor.WHITE;
		repaint();
	}
    
    private void init() {
    	gameOver=false;
    	for (int i = 0; i < CHESSBOARD_SIZE; i++) {
			for (int j = 0; j < CHESSBOARD_SIZE; j++) {
				ChessComponent chessComponent=chessComponents[i][j];
				if (chessComponent!=null) {
					remove(chessComponent);
				}
				
			}
		}
    	initiateEmptyChessboard();
    	initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        initPawns();
        initKnights();
        initBishops();
        initKings();
        initQueens();
	}
    
    private void initQueens() {
    	ChessComponent chessComponent;
		int r, c;
		ChessColor chessColor;
		r=0;c=4;
		chessColor=ChessColor.BLACK;
		chessComponent=new Queen(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=CHESSBOARD_SIZE-1;c=4;
		chessColor=ChessColor.WHITE;
		chessComponent=new Queen(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
	}
    
    private void initKings() {
    	ChessComponent chessComponent;
		int r, c;
		ChessColor chessColor;
		r=0;c=3;
		chessColor=ChessColor.BLACK;
		chessComponent=new King(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=CHESSBOARD_SIZE-1;c=3;
		chessColor=ChessColor.WHITE;
		chessComponent=new King(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
	}
    
    private void initBishops() {
    	ChessComponent chessComponent;
		int r, c;
		ChessColor chessColor;
		r=0;c=2;
		chessColor=ChessColor.BLACK;
		chessComponent=new Bishop(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=0;c=CHESSBOARD_SIZE-3;
		chessColor=ChessColor.BLACK;
		chessComponent=new Bishop(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=CHESSBOARD_SIZE-1;c=2;
		chessColor=ChessColor.WHITE;
		chessComponent=new Bishop(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=CHESSBOARD_SIZE-1;c=CHESSBOARD_SIZE-3;
		chessColor=ChessColor.WHITE;
		chessComponent=new Bishop(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
	}
    
    
    
    private void initKnights() {
    	ChessComponent chessComponent;
		int r, c;
		ChessColor chessColor;
		r=0;c=1;
		chessColor=ChessColor.BLACK;
		chessComponent=new Knight(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=0;c=CHESSBOARD_SIZE-2;
		chessColor=ChessColor.BLACK;
		chessComponent=new Knight(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=CHESSBOARD_SIZE-1;c=1;
		chessColor=ChessColor.WHITE;
		chessComponent=new Knight(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
		r=CHESSBOARD_SIZE-1;c=CHESSBOARD_SIZE-2;
		chessColor=ChessColor.WHITE;
		chessComponent=new Knight(new ChessboardPoint(r, c), calculatePoint(r, c), chessColor, clickController, CHESS_SIZE);
		chessComponent.setVisible(true);
		putChessOnBoard(chessComponent);
		
	}
    
    private void initPawns() {
		for (int i = 0; i < CHESSBOARD_SIZE; i++) {
			Pawn pawn=new Pawn(new ChessboardPoint(1, i),calculatePoint(1, i), ChessColor.BLACK, clickController, CHESS_SIZE);
			pawn.setVisible(true);
			putChessOnBoard(pawn);
		}
		for (int i = 0; i < CHESSBOARD_SIZE; i++) {
			Pawn pawn=new Pawn(new ChessboardPoint(CHESSBOARD_SIZE-2, i),calculatePoint(CHESSBOARD_SIZE-2, i), ChessColor.WHITE, clickController, CHESS_SIZE);
			pawn.setVisible(true);
			putChessOnBoard(pawn);
		}
	}

    public static ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            if (chess2 instanceof King) {
				status(currentColor.toString()+" Win!");
				gameOver=true;
			}
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;

        chess1.repaint();
        chess2.repaint();
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        status(currentColor.getName());
        
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new Rook(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
    	gameOver=false;
        if (chessData.size()<8) {
			status("error 101");
			return;
		}
        for (int i = 0; i <8; i++) {
			if (chessData.get(i).length()!=8) {
				status("error 101");
				return;
			}
		}
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
			for (int j = 0; j < CHESSBOARD_SIZE; j++) {
				ChessComponent chessComponent=chessComponents[i][j];
				if (chessComponent!=null) {
					remove(chessComponent);
				}
				
			}
		}
        
        
        
        initiateEmptyChessboard();
        
        for (int i = 0; i <8; i++) {
			String string=chessData.get(i);
			for (int j = 0; j <string.length(); j++) {
				ChessComponent chessComponent=null;
				ChessColor chessColor=ChessColor.NONE;
				char ch=string.charAt(j);
				if (Character.isUpperCase(ch)) {
					chessColor=ChessColor.BLACK;
				}else if (Character.isLowerCase(ch)) {
					chessColor=ChessColor.WHITE;
				}
				switch (ch) {
				case '_':
					continue;
				case 'B':
				case 'b':
					chessComponent=new Bishop(new ChessboardPoint(i, j), calculatePoint(i, j), chessColor, clickController, CHESS_SIZE);
					break;
				case 'K':
				case 'k':
					chessComponent=new King(new ChessboardPoint(i, j), calculatePoint(i, j), chessColor, clickController, CHESS_SIZE);
					break;
				case 'N':
				case 'n':
					chessComponent=new Knight(new ChessboardPoint(i, j), calculatePoint(i, j), chessColor, clickController, CHESS_SIZE);
					break;
				case 'P':
				case 'p':
					chessComponent=new Pawn(new ChessboardPoint(i, j), calculatePoint(i, j), chessColor, clickController, CHESS_SIZE);
					break;
				case 'Q':
				case 'q':
					chessComponent=new Queen(new ChessboardPoint(i, j), calculatePoint(i, j), chessColor, clickController, CHESS_SIZE);
					break;
				case 'R':
				case 'r':
					chessComponent=new Rook(new ChessboardPoint(i, j), calculatePoint(i, j), chessColor, clickController, CHESS_SIZE);
					break;

				default:
					status("error 102");
					return;
				}
				chessComponent.setVisible(true);
				putChessOnBoard(chessComponent);
			}
			
		}
        
        if (chessData.size()<9) {
			status("error 103");
			return;
		}
		String string2=chessData.get(8);
		string2=string2.toLowerCase();
		if (string2.equals("w")) {
			currentColor=ChessColor.WHITE;
		}else if (string2.equals("b")) {
			currentColor=ChessColor.BLACK;
		}else {
			status("error 103");
			return;
		}
		repaint();
    }
    
    private void status(String inf) {
		if (consumer!=null) {
			if (gameOver) {
				return;
			}
			consumer.accept(inf);
		}
	}
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	StringBuilder stringBuilder=new StringBuilder();
    	for (ChessComponent[] chessComponents2 : chessComponents) {
			for (ChessComponent chessComponent : chessComponents2) {
				stringBuilder.append(chessComponent);
			}
			stringBuilder.append("\r\n");
		}
    	if (currentColor==ChessColor.WHITE) {
    		stringBuilder.append("w");
		} else {
			stringBuilder.append("b");

		}
    	
    	return stringBuilder.toString();
    }
}
