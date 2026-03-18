package tictactoe;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TicTacToeClient extends TicTacToe{

	    private static final int FELDGROESSE = 3;
	    private static String ipAdresse;
	    private static final int PORT = 65003;

	    private Socket client;

	    public TicTacToeClient(int feldgroesse) {
	        super(feldgroesse);
	    }

	    public static void main(String[] args) throws IOException {

	        TicTacToeClient tClient = new TicTacToeClient(FELDGROESSE);
	        tClient.ipAdresse = args[0];

	        System.out.println("TicTacToe - Client");
	        System.out.println("==================");

	        boolean spielLaeuft = true;

	        while (spielLaeuft && tClient.getGewonnen() == 0) {

	            System.out.println(tClient.toString());

	            int zug = -1;
	            boolean zugErfolgreich = false;

	            while (!zugErfolgreich) {
	                try {
	                    zug = TestScannerErweitert.readInt("Ihr Zug: ");
	                } catch (Exception e) {
	                    System.out.println("keine gültige Zahl eingegeben");
	                    continue;	//Startet den while loop nochmals 
	                }

	                int ret;
	                try {
	                    ret = tClient.setMeinZug(zug);
	                } catch (UnknownHostException e) {
	                    System.out.println("Verbindung zum Server verloren");
	                    tClient.close();
	                    spielLaeuft = false;
	                    break;
	                }

	                switch (ret) {
	                    case 0:
	                        zugErfolgreich = true;
	                        break;
	                    case -1:
	                        System.out.println("Zug liegt ausserhalb des Spielfeldes");
	                        break;
	                    case -2:
	                        System.out.println("Zug wurde bereits gesetzt");
	                        break;
	                    case -3:
	                        System.out.println("clientsocket existiert bereits");
	                        break;
	                }

	                if (!spielLaeuft)	{
	                	break;
	                }

	            
	                System.out.println(tClient.toString());			//Zug des Gegners
	                System.out.println("Warten auf den Zug des Gegners...");

	                int retGegner;
	                try {
	                	retGegner = tClient.getGegnerZug();
	                } catch (IOException e) {
	                	System.out.println("verbindung zum Server verloren");
	                	tClient.close();
	                	break;
	                }

	                switch (retGegner) {
	                	case -3:
	                		System.out.println("kein clientsocket vorhanden");
	                		break;
	                	case -2:
	                	case -1:
	                		System.out.println("Ungültiger zug vom Gegner");
	                		break;
	                	case 0:		//gültiger Zug empfangen
	                		break;
	                }
	            
	            }	
	            	
	        int gewinner = tClient.getGewonnen();	//auswertung am spielende

	        switch (gewinner) {
	            case -2:
	                System.out.println("Der Gegner hat gewonnen!");
	                break;
	            case -1:
	                System.out.println("Sie haben gewonnen!");
	                break;
	            case 0:
	                break;
	        }

	        tClient.close();
	        }
	    }
	    public int setMeinZug(int zug) throws IOException, UnknownHostException {
	        int ret = this.setZugSpieler2(zug);

	        if (ret == 0) {
	            if (this.client == null || this.client.isClosed()) {
	                this.client = new Socket(ipAdresse, PORT);
	                OutputStream out = this.client.getOutputStream();
	                out.write(zug);
	            } else {
	                ret = -3;
	            }
	        }
	        return ret;
	    }

	    public int getGegnerZug() throws IOException {		//zug vom Server empfanen
	        int ret = -3;
	        if (this.client != null && !this.client.isClosed()) {
	            InputStream in = this.client.getInputStream();
	            int zug = (byte) in.read();
	            ret = this.setZugSpieler1(zug);
	            if (ret == 0) {
	                close();
	            }
	        }
	        return ret;
	    }

	    public void close() throws IOException {
			this.client.close();
		}
}
