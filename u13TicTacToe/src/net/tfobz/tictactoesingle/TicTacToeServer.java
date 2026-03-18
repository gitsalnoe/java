package net.tfobz.tictactoesingle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

import tictactoe.TicTacToe;

/**
 * Diese Klasse spielt im TicTacToe den zweiten Zug und wartet auf den Zug vom TicTacToeClient.
 * Wenn er einen Zug spielt schickt er es am TicTacToeClient weiter
 */
public class TicTacToeServer extends TicTacToe {
	
	private java.net.ServerSocket server;
	private java.net.Socket clientSocket;	//Nimmt zug auf und sendet nächsten weiter
	private static final int FELDGROESSE = 3;
	private static final int PORT = 65000;	

	/**
	 * Konstruktor von TicTacToeServer
	 * @param feldgroesse, die feldgrösse vom spielfeld
	 * @param port, port wo der server läuft
	 * @throws java.io.IOException
	 */
	public TicTacToeServer(int feldgroesse, int port) throws java.io.IOException {
		super(feldgroesse);
		server = new ServerSocket(port);
	}
	
	/**
	 * Hier wird das TicTacToe spiel im Server angezeigt.
	 * Zuerst setzt der Client den Zug und schickt ihn an den Server,
	 *  der Server setzt dann den Zug und schickt ihn am Client weiter bis jemand gewonnen hat
	 * @param args
	 */
	public static void main(String[] args) {
		TicTacToeServer tictactoeServer = null;
		try {
		    System.out.println("Tic Tac Toe - Server");
		    System.out.println("=====================");
		    tictactoeServer = new TicTacToeServer(FELDGROESSE, PORT);

		    while (tictactoeServer.getGewonnen() == 0) {	//Hier startet das Spiel und endet wenn jemand gewinnt
		        System.out.println(tictactoeServer.toString());	//Ausgabe des Spielfeldes
		        System.out.println("Warten auf den Zug des gegners...");

		        while (true) {
		            int zug;
		            try {
		                zug = TestScannerErweitert.readInt("Ihr Zug: ");	//Eingabe vom Zug
		            } catch (Exception e) {
		            	
		                System.out.println("Bitte geben Sie eine zahl ein");	//Falls keine Zahl eingegeben worden ist
		                continue; 					//Starten den while loop von oben wieder neu
		            }

		            try {			//Falls zug erfolgreich gelesen wurde
		                switch (tictactoeServer.setMeinZug(zug)) {
		                    case -1:
		                        System.out.println("zug liegt ausserhalb des spielfelds");
		                        continue; 
		                    case -2:
		                        System.out.println("zug ist bereits gesetzt");
		                        continue; 
		                    case -3:
		                        System.out.println("clientsocket gibt es nicht");
		                        continue; 
		                    case 0:
		                        break;
		                }
		            } catch (IOException e) {
		                System.out.println("Verbindung zum client verloren");
		                tictactoeServer.close();
		                return; 			//Beendet das Spiel
		            }

		            int gewinner = tictactoeServer.getGewonnen();	//Prüft das es ein Gewinner gibt
		            if (gewinner == tictactoeServer.SPIELER1) {
		                System.out.println("Spieler1 hat gewonnen");
		            }
		            if (gewinner == tictactoeServer.SPIELER2) {
		                System.out.println("Spieler2 hat gewonnen");
		            }
		            break; 	//Wenn der zug keinen fehler zurueck gibt
		        } 
		    } 

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {					//Damit der Server immer geschlossen wird
		    try {
		        if (tictactoeServer != null)
		            tictactoeServer.close();
		    } catch (Exception e) {
		        System.out.println("Fehler beim schliessen des Servers");
		        e.printStackTrace();
		    }
		}
	}
	
	/**
	 * Diese Methode schliesst den Server und den ClientSocket.
	 */
	public void close()	{
		try	{
			server.close();
			clientSocket.close();
		}	catch(IOException e)	{
			
		}
	}
	
	/**
	 * Diese Methode wartet dass der Client die Verbindung mit dem Server aufnimmt und einen Zug sendet.
	 * Wenn der Zug gesendet wurde, wird der ClientSocket nicht geschlossen, denn der Sercer analysiert den Zug, trägt ihn ins Spielfeld ein
	 * und schickt seinen Zug über denselben ClientSocket zurück zum Client
	 * @return 0, falls erfolgreich empfanfen oder Zug 0 geschickt
	 * @return -1 falls der empfangenen Zug ausserhalb des Spielfeldes liegt
	 * @return -2 falls der empfangene Zug bereits gesetzt wurde
	 * @return -3 falls Clientsocket bereits existiert
	 * @throws IOException
	 */
	public int getGegnerZug() throws IOException	{
		try {
			if (clientSocket == null) {
				clientSocket = server.accept();
				InputStream in = clientSocket.getInputStream();
				int zug = (byte)in.read();
				return setZugSpieler1(zug);
			}
		}	catch(IOException e)	{
		}
		return -3;
	}
	
	/**
	 * Es wird über den bereits vorhandenen ClientSockte der Zug des Servers an den Client geschickte.
	 * Dabei muss der ClientSocket existieren. Nach dem Schicken wird der ClientSocket geschlossen.
	 * @param zug
	 *  @return -1 falls der empfangenen Zug ausserhalb des Spielfeldes liegt
	 * @return -2 falls der empfangene Zug bereits gesetzt wurde
	 * @return -3 falls kein ClientSocket vorhanden ist
	 * @throws IOException
	 */
	public int setMeinZug(int zug) throws IOException	{
		if (clientSocket.isConnected())	{
			OutputStream out = clientSocket.getOutputStream();
			if (setZugSpieler2(zug) == 0) {
				out.write(zug);
				clientSocket.close();
			}
			return setZugSpieler2(zug);
		}
		return -3;
	}
}
