package com.example.application;
import java.util.HashMap;

public class TrieNode {
	HashMap<Character, TrieNode> child = new HashMap<Character, TrieNode>();
	String  word = null;
	public TrieNode() {};
}
