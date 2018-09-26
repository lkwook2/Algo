package Boggle;

import java.util.ArrayList;
import java.util.Scanner;

public class Boggle {
	Scanner scan;
	
	int wordCount;
	ArrayList<String> words;
	
	char board[][][];
	int boardCount;
	
	public ArrayList<ArrayList<String>> result_words;
	
	int nextX[] = {-1,0,1,-1,1,-1,0,1};
	int nextY[] = {1,1,1,0,0,-1,-1,-1};
	
	public Boggle() {
		scan = new Scanner(System.in);
		words = new ArrayList<>();
		
		//result_words = new ArrayList<ArrayList<String>>();
	}
	
	public void readWords() {
		wordCount = scan.nextInt();
		scan.nextLine();
		for(int w = 0 ; w < wordCount ; w++) {
			String inputword = scan.nextLine();
			if(!inputword.equals("")) {
				words.add(inputword);
			}
			
		}
		scan.nextLine();
	}
	
	public void readBoard() {
		boardCount = scan.nextInt();
		result_words = new ArrayList<>();
		board = new char[boardCount][4][4];
		
		for(int b=0; b < boardCount ; b++) {
			for(int i = 0; i < 4 ; i++) {
				String line = scan.nextLine();
				if(line.equals("")) {
					line = scan.nextLine();
				}
				for(int j=0; j < 4 ; j++) {
					board[b][i][j] = line.charAt(j);
				}
			}
			result_words.add(new ArrayList<String>());
		}
	}
	
	public void findNextLetter(int boardnum, String targetword, String finded, int x, int y) {
//		System.out.println(targetword);
//		System.out.println(finded);
		if(targetword.equals(finded)) {
			
			if(!result_words.get(boardnum).contains(finded)) {
				result_words.get(boardnum).add(finded);
			}
			
			
			return;
		}
		if(targetword.length() <= finded.length()) {
			return;
		}
		else {
			for(int i = 0; i< 8; i++) {
				
				int nx = x + nextX[i];
				int ny = y + nextY[i];
				
				if(nx < 0 || ny < 0 || nx > 3 || ny > 3) {
					continue;
				}
				
				
				if(board[boardnum][nx][ny] == targetword.charAt(finded.length())) {
					finded = finded + board[boardnum][nx][ny];
					findNextLetter(boardnum, targetword, finded, nx, ny);
					finded = finded.substring(0, finded.length()-1);
				}
			}
		}
	}
	
	public void findWord(int boardnum,String word) {
		for(int i=0; i < 4; i++) {
			for (int j = 0; j < 4 ; j++) {
				String firststr = "" + board[boardnum][i][j];
				if(word.substring(0, 1).equals(firststr)) {
					int beforefindedCount = result_words.get(boardnum).size();
					findNextLetter(boardnum, word, firststr, i, j);
					int afterfindedCount = result_words.get(boardnum).size();
					if(afterfindedCount > beforefindedCount) {
						return;
					}
				}
			}
		}
		
	}
	
	public void findWords() {
		//full search board
		
		//1. get word
		for(int i = 0 ; i < words.size(); i++) {
			String targetWord = words.get(i);
			
			for(int j = 0 ; j < boardCount ; j++) {
				//2. search board 
				findWord(j, targetWord);
			}
		}
	}
	
	public static void main(String args[]) {
		Boggle test = new Boggle();
		test.readWords();
		test.readBoard();
		test.findWords();
		
		
		
		for(int i=0; i< test.result_words.size(); i++) {
			int score = 0;
			int maxlength = 0;
			String maxString = "";
			for(int j=0 ; j < test.result_words.get(i).size(); j++) {
				int wordlength = test.result_words.get(i).get(j).length();
				
				if(wordlength >= maxlength) {
					maxlength = wordlength;
					maxString = test.result_words.get(i).get(j);
				}
				
				if(wordlength == 3 || wordlength == 4) {
					score = score + 1;					
				}
				else if(wordlength == 5) {
					score = score + 2;
				}
				else if(wordlength == 6) {
					score = score + 3;
				}
				else if(wordlength == 7) {
					score = score + 5;
				}
				else if(wordlength == 8) {
					score = score + 11;
				}
			}
			System.out.println(score + " " + maxString + " " + test.result_words.get(i).size());
		}
		
	}
}
