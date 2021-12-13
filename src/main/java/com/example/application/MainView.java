package com.example.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
@Route
public class MainView extends VerticalLayout {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row = 0;
	private List<String> _words = new ArrayList<String>();
	private List<Result> _result = new ArrayList<Result>();
	
	public MainView() {
		VerticalLayout lines = new VerticalLayout();
		VerticalLayout resultLines = new VerticalLayout();
		InputBoard board= new InputBoard();
		TextField taskField = new TextField(); 
	  
		Algorithm al = new Algorithm();
	  
		Button addButton = new Button("Add"); 
		addButton.addClickShortcut(Key.ENTER);
		addButton.addClickListener(click -> {
			List<Cell> cellRow = new ArrayList<>();
			HorizontalLayout line = new HorizontalLayout();
			board.addValue(taskField.getValue());
			int col = 0;
			board.addRowToCellList(cellRow);
			for(Character c : taskField.getValue().toCharArray()) {
				board.addCell(this.row, col, c, cellRow);
				line.add(board.getCellLists().get(this.row).get(col).getB());
				col++;
			} 
			lines.add(line);
			this.row ++;
			taskField.clear();
		});
    
    
		Button search = new Button("Search");
		search.addThemeVariants(ButtonVariant.LUMO_LARGE);
		search.addClickListener(click->{
			try {
				HorizontalLayout resultLine = new HorizontalLayout();
				board.createBoard();
				readLine();
			 	this._result = al.findWords(board, _words);
			
			 	for(Result r : _result) {
			 		r.createButton(board.getCellLists());				
			 		resultLine.add(r.getB());
			 	}
			 	resultLines.add(resultLine);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			search.setEnabled(false);
		});
	
	  
		Button dowload = new Button("Download");
		dowload.addClickListener(click->{
			writeLine();
		});
	  
	  
		Button clear = new Button("Clear");
		clear.addClickListener(click->{
			this.row = 0;
			lines.removeAll();
			resultLines.removeAll();
			board.deleteBoard();
			_result.clear();
			search.setEnabled(true);
		});
	
		add(
			  new H1("Words Search"),
			  new VerticalLayout(
					  lines,
					  new HorizontalLayout(
							  taskField,
							  addButton,
							  clear
							  ),
					  new HorizontalLayout(      
							  search,dowload
							  ),
					  new H3("Result:"),
					  resultLines
					  )
			);
  		}
  
  		private void writeLine() {
  			BufferedWriter writer;
  			try {
  				String str = "";
  				String homepath = System.getProperty("user.home");
  				String filePath = homepath+"/Downloads/results.txt";
  				writer = new BufferedWriter(new FileWriter(filePath));
  				for(Result r : this._result) {
  					str = str + r.getWord() +"\n";
  				}
  				writer.write(str);
  				writer.close();
  			}catch(IOException e) {
  				e.printStackTrace();
  			}
  		}
  		private void readLine() {
  			BufferedReader reader;
  			try {
  				/*String filePath = new File("").getAbsolutePath();
  				reader = new BufferedReader(new FileReader(filePath+"/words.txt"));*/
  				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
  				InputStream in = classloader.getResourceAsStream("/words.txt");
  				reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
  				String line = reader.readLine();
  				while(line != null) {
  					this._words.add(line);
  					line = reader.readLine();
  				}
  				reader.close();
  			} catch(IOException e) {
  				e.printStackTrace();
  			}
  		}
	}