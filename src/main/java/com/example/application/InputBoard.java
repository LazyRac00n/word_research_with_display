package com.example.application;

import java.util.ArrayList;
import java.util.List;

public class InputBoard {
	private List<List<Cell>> cellLists = new ArrayList<List<Cell>>();
	private int valueOfWords = 0;
	private char[][] _board = null;
	List<String> words = new ArrayList<>();
	
	
	public InputBoard() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	public void addValue(String word) {
		this.setValueOfWords(this.getValueOfWords() + 1);
		this.words.add(word);
		
	}
	
	public void addCell(int row, int col, char letter, List<Cell> cellRow) {
		Cell cell = new Cell(row, col, letter);
		cellRow.add(cell);
		
	}
	
	public void addRowToCellList(List<Cell> cellRow) {
		this.getCellLists().add(cellRow);
	}

	public void createBoard() {
		char[][] board = new char[words.size()][];
		for(int i=0;i < words.size();i++) {
			char[] row = new char[words.get(i).length()];
			int j =0;
			for(Character c : words.get(i).toCharArray()) {
				row[j] = c;
				j++;
			}
			board[i] = row;
		}
		this._board = board;
	}
	
	public int getRow() {
		return this._board.length;
	}
	
	public int getCol(int row) {
		return this._board[row].length;
	}
	
	public char getCell(int row, int col) {
		return this._board[row][col];
	}
	
	public void setCell(int row, int col,char t) {
		this._board[row][col] = t;
	}
	
	public void deleteBoard() {
		this._board = null;
		this.setValueOfWords(0);
		this.words.clear();	
		this.getCellLists().clear();
	}


	public List<List<Cell>> getCellLists() {
		return cellLists;
	}


	public void setCellLists(List<List<Cell>> cellLists) {
		this.cellLists = cellLists;
	}


	public int getValueOfWords() {
		return valueOfWords;
	}


	public void setValueOfWords(int valueOfWords) {
		this.valueOfWords = valueOfWords;
	}
}
