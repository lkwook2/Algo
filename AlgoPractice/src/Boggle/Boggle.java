package Boggle;

import java.util.ArrayList;
import java.util.Scanner;

public class Boggle {
	Scanner scan;

	int wordCount;
	ArrayList<String> words;

	String board[][];
	boolean visited[][][];
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
		board = new String[boardCount][4];



		for(int b=0; b < boardCount ; b++) {
			for(int i = 0; i < 4 ; i++) {
				String line = scan.nextLine();
				if(line.equals("")) {
					line = scan.nextLine();
				}
				board[b][i] = line;


			}
			result_words.add(new ArrayList<String>());
		}
	}

	public boolean findNextLetter(int boardnum, String targetword, int x, int y) {
		//x y
		if(x < 0 || y < 0 || x > 3 || y > 3) {
			return false;
		}
		if(!targetword.substring(0,1).equals(board[boardnum][y].substring(x,x+1))) {
			return false;
		}
		if(visited[boardnum][y][x] == true) {
			return false;
		}
		if(targetword.length() == 1) {

			return true;
		}

		visited[boardnum][y][x] = true;
		for(int i=0; i < 8 ; i++) {
			int nX = x + nextX[i];
			int nY = y + nextY[i];


			if(findNextLetter(boardnum, targetword.substring(1),nX,nY) == true) {
				return true;
			}

		}
		visited[boardnum][y][x] = false;

		return false;

	}

	public void findWord(int boardnum,String word) {
		for(int i=0; i < 4; i++) {
			for (int j = 0; j < 4 ; j++) {
				if(findNextLetter(boardnum, word, i, j)==true) {
					if(!result_words.contains(word)) {
						result_words.get(boardnum).add(word);
					}

					return;
				}
			}
		}
	}

	public void findWords() {
		//full search board

		//1. get word
		for(int i = 0 ; i < words.size(); i++) {
			String targetWord = words.get(i);
			visited = new boolean[boardCount][4][4];
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

				if(wordlength > maxlength) {
					maxlength = wordlength;

					maxString = test.result_words.get(i).get(j);
				}
				if(wordlength == maxlength) {
					if(maxString.compareTo(test.result_words.get(i).get(j)) > 0 ) {
						maxString = test.result_words.get(i).get(j);
					}
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
