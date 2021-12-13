package com.example.application;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

public class Cell {
	private int row = 0;
	private int col = 0;
	private char letter = ' ';
	private Button b;
	public Cell(int r, int c, char letter) {
		this.setRow(r);
		this.setCol(c);
		this.setLetter(letter);
		this.setB(new Button(String.valueOf(letter)));
	}
	public void doAction() {
		this.getB().addThemeVariants(ButtonVariant.LUMO_SMALL,ButtonVariant.LUMO_PRIMARY);
	}
	public Button getB() {
		return b;
	}
	public void setB(Button b) {
		this.b = b;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
}
