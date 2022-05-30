package controller;

import view.Chessboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameController {
    private Chessboard chessboard;
    private Consumer<String> consumer;
     
   

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
        
    }

    public List<String> loadGameFromFile(String path) {
        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            chessboard.loadGame(chessData);
            return chessData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void reset() {
		chessboard.reset();
	}
    
    public void save() {
		JFileChooser jFileChooser=new JFileChooser("./");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("text file","txt"));
		jFileChooser.showSaveDialog(null);
		String pathString=jFileChooser.getSelectedFile().getAbsolutePath()+".txt";
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(pathString);
			printWriter.write(chessboard.toString());
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
    
    public void setConsumer(Consumer<String> consumer) {
		this.consumer = consumer;
		chessboard.setConsumer(consumer);
	}
    
    
    public Chessboard getChessboard() {
		return chessboard;
	}
    

}
