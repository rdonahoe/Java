import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trie {
	private class TrieNode {
		private HashMap<Character, TrieNode> nodes;
		
		TrieNode(char key) {
			if(key != '#')
				this.nodes = new HashMap<Character, TrieNode>();
			else
				this.nodes = null;
		}
		
		private TrieNode getNode(char c) {
			return nodes.get(c);
		}
		
		private TrieNode addNode(char c) {
			TrieNode temp = new TrieNode(c);
			nodes.put(c, temp);
			return temp;
		}
	}
	
	private HashMap<Character, TrieNode> nodes;
	
	public Trie() {
		this.nodes = new HashMap<Character, TrieNode>();
	}
	
	public void add(String str) {
		if(str.contains("#")) { return; }
		
		char temp = str.charAt(0);
		TrieNode cur = nodes.get(temp);
		if(cur == null)
		{
			cur = new TrieNode(temp);
			nodes.put(temp, cur);
		}
		for(int i = 1; i < str.length(); i++) {
			temp = str.charAt(i);
			TrieNode testNode = cur.getNode(temp);
			if(testNode == null)
				cur = cur.addNode(temp);
			else
				cur = testNode;
		}
		if(cur.getNode('#') == null)
			cur.addNode('#');		
	}
	
	public boolean contains(String str) {
		TrieNode cur = nodes.get(str.charAt(0));
		if (cur == null) { return false; }
		
		for(int i = 1; i < str.length(); i++) {
			cur = cur.getNode(str.charAt(i));
			if (cur == null) { return false; }
		}
		
		if(cur.getNode('#') == null) {return false;}
		return true;
	}
	
	public static void main(String[] args) {
		BufferedReader br = null;
		Trie trie = new Trie();
		try {
			br = new BufferedReader(new FileReader(new File("en_US.dic")));
			
			while(true) {
				String line = br.readLine();
				trie.add(line.replaceAll("\\/.*", "").toLowerCase());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				br.close();
			}
			catch(IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
		System.out.print(trie.contains("this"));
	}
	
}
