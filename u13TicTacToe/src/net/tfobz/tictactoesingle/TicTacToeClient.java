package net.tfobz.tictactoesingle;

import java.net.Socket;

import tictactoe.TicTacToe;

public class TicTacToeClient extends TicTacToe{

	private Socket clientSocket;
	private static String IP;
	private static final int FELDGROESSE = 3;
	private static final int PORT = 65000;
	
	public TicTacToeClient(int feldgroesse) {
		super(feldgroesse);
	}

	public static void main(String[] args) {
			//TicTacToeServer geht nicht
	}

}
