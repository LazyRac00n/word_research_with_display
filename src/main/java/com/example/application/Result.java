package com.example.application;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String word = null;
	private String direction = null;
	private int startpointRow = 0;
	private int startpointCol = 0;
	private int endpointCol = 0;
	private int endpointRow = 0;
	private Button b;
	public Result() {};
	
	public void init() {
		this.setWord(null);
		this.setDirection(null);
	}
	
	public void createButton(List<List<Cell>> cellLists) {
		this.setB(new Button(this.getWord()));
		getB().addClickListener(click->{
			
			for(List<Cell> L : cellLists) {
				for(Cell c: L) {
					c.getB().removeThemeVariants(ButtonVariant.LUMO_SMALL,ButtonVariant.LUMO_PRIMARY);
				}
			}
			List<Cell> resultCell = new ArrayList<>();
			resultCell = findCell(cellLists);
			for(Cell c : resultCell) {
				c.doAction();
			};
			
		});
	}
	private List<Cell> findCell(List<List<Cell>> cellLists) {
		List<Cell> resultCell = new ArrayList<>();
		// TODO Auto-generated method stub
		int j = 0;
		switch(this.getDirection()) {
			case "Top":
				for(int i = getStartpointRow(); i >= getEndpointRow(); i--) {
					resultCell.add(cellLists.get(i).get(getStartpointCol()));
				}
				break;
			case "Down":
				for(int i = getStartpointRow(); i <= getEndpointRow(); i++) {
					resultCell.add(cellLists.get(i).get(getStartpointCol()));
				}
				break;
			case "Left":
				for(int i = getStartpointCol(); i >= getEndpointCol(); --i) {
					resultCell.add(cellLists.get(getStartpointRow()).get(i));
				}
				break;
			case "Right":
				for(int i = getStartpointCol(); i <= getEndpointCol(); i++) {
					resultCell.add(cellLists.get(getStartpointRow()).get(i));
				}
				break;
			case "LeftTop":
				j = getStartpointCol();
				for(int i = getStartpointRow(); i >= getEndpointRow(); i--) {
					if(j >= getEndpointCol()) {
						resultCell.add(cellLists.get(i).get(j));
						j--;
					}
				}
				break;
			case "RightTop":
				j = getStartpointCol();
				for(int i = getStartpointRow(); i >= getEndpointRow(); i--) {
					if(j <= getEndpointCol()) {
						resultCell.add(cellLists.get(i).get(j));
						j++;
					}
				}
				break;
			case "LeftDown":
				j = getStartpointCol();
				for(int i = getStartpointRow(); i <= getEndpointRow(); i++) {
					if(j >= getEndpointCol()) {
						resultCell.add(cellLists.get(i).get(j));
						j--;
					}
				}
				break;
			case "RightDown":
				j = getStartpointCol();
				for(int i = getStartpointRow(); i <= getEndpointRow(); i++) {
					if(j <= getEndpointCol()) {
						resultCell.add(cellLists.get(i).get(j));
						j++;
					}
				}
				break;
		}
		return resultCell;
	}

	public Result makeClone() throws IOException, ClassNotFoundException {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    ObjectOutputStream out = new ObjectOutputStream(outputStream);
	    out.writeObject(this);

	    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	    ObjectInputStream in = new ObjectInputStream(inputStream);
	    Result copied = (Result)in.readObject();
	    return copied;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getStartpointCol() {
		return startpointCol;
	}

	public void setStartpointCol(int startpointCol) {
		this.startpointCol = startpointCol;
	}

	public int getStartpointRow() {
		return startpointRow;
	}

	public void setStartpointRow(int startpointRow) {
		this.startpointRow = startpointRow;
	}

	public int getEndpointRow() {
		return endpointRow;
	}

	public void setEndpointRow(int endpointRow) {
		this.endpointRow = endpointRow;
	}

	public int getEndpointCol() {
		return endpointCol;
	}

	public void setEndpointCol(int endpointCol) {
		this.endpointCol = endpointCol;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Button getB() {
		return b;
	}

	public void setB(Button b) {
		this.b = b;
	}
}
