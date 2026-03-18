package tictactoe;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class TicTacToeServer extends TicTacToe{
	 private ServerSocket server;
	    public Socket clientSocket;

	    private static final int FELDGROESSE = 3;
	    private static final int PORT = 65003;

	    public TicTacToeServer(int feldgroesse, int port) throws IOException {
	    	super(FELDGROESSE);
	        server = new ServerSocket(port);
	    }

	    public static void main(String[] args) {

	        TicTacToeServer tServer = null;

	        try {
	            tServer = new TicTacToeServer(FELDGROESSE, PORT);
	            System.out.println("TicTacToe - Server");
	            System.out.println("==================");

	            boolean serverLaeuft = true;

	            while (serverLaeuft && tServer.getGewonnen() == 0) {

	                System.out.println(tServer.toString());
	                System.out.println("Warten auf den Zug des gegners...");

	                int retGegner = tServer.getGegnerZug();

	                switch (retGegner) {
	                    case -3:
	                        System.out.println("clientsocket existiert bereits");
	                        tServer.close();
	                        serverLaeuft = false;
	                        break;
	                    case -2:
	                    case -1:
	                        System.out.println("Ungültiger Zug des Gegners.");
	                        break;
	                    case 0:		//Falls gültiger Zug
	                        break;
	                }

	                if (!serverLaeuft || tServer.getGewonnen() != 0 ) {
	                    break;
	                }
	                
	                boolean zugErfolgreich = false;

	                while (!zugErfolgreich) {

	                    int zug;

	                    try {
	                        zug = TestScannerErweitert.readInt("Ihr Zug: ");
	                    } catch (Exception e) {
	                        System.out.println("Bitte eine gültige Zahl eingeben.");
	                        continue;
	                    }

	                    int ret = 0;

	                    try {
	                        ret = tServer.setMeinZug(zug);
	                    } catch (IOException e) {
	                        System.out.println("Verbindung zum Client verloren!");
	                        tServer.close();
	                        serverLaeuft = false;
	                        break;
	                    }

	                    switch (ret) {
	                        case 0:
	                            zugErfolgreich = true;
	                            break;

	                        case -1:
	                            System.out.println("Zug liegt ausserhalb des Spielfelds");
	                            break;

	                        case -2:
	                            System.out.println("Zug wurde bereits gesetzt");
	                            break;

	                        case -3:
	                            System.out.println("clientsocket nicht vorhanden");
	                            break;

	                        default:
	                            System.out.println("Unbekannter fehler");
	                    }
	                }
	            }
	            
	            int gewinner = tServer.getGewonnen();		//Spielende

	            switch (gewinner) {
	                case -1: //SPIELER1
	                    System.out.println("Der Gegner hat gewonnen!");
	                    break;

	                case -2: //SPIELER2
	                    System.out.println("Sie haben gewonnen!");
	                    break;

	                case 0:
	                    break;
	            }

	        } catch (IOException e) {
	            e.printStackTrace();

	        } finally {
	            try {
	                if (tServer != null) tServer.close();
	            } catch (Exception e) {}
	        }
	    }

	    /**
	     * Schliessen der Sockets
	     * @throws IOException
	     */
	    public void close() throws IOException {
	        if (clientSocket != null && !clientSocket.isClosed()) {
	            clientSocket.close();
	        }
	        if (server != null && !server.isClosed()) {
	            server.close();
	        }
	    }

	   /**
	    * Wartet dass der Client die Verbindung mit dem Server aufnimmt und einen Zug sendet.
	    * Wenn der Zug gesendet wurde, wird der ClientSocket nich geschlossen, denn der Server analysiert den Zug,
	    * trägt ihn ins Spielfeld ein und schickt seinen Zug über denselbern ClientSocket zurück zum Client.
	    * @return 0, falls der zug erfolgreich empfangen wurde
	    * @return -1, falls der empangene Zug ausserhalb des Spielfeldes liegt
	    * @return -2, falls der empfangene Zug bereits gesetzt wurde
	    * @return -3, falls ClientSocket existiert
	    * @throws IOException
	    */
	    public int getGegnerZug() throws IOException {

	        int ret = -3;

	        if (clientSocket == null || clientSocket.isClosed()) {

	            clientSocket = server.accept();

	            InputStream in = clientSocket.getInputStream();
	            int zug = (byte) in.read();

	            ret = setZugSpieler1(zug);
	        }

	        return ret;
	    }

	   /**
	    * Es wird über den ClientSocket der Zug des Servers and den Client geschickt.
	    * Dabei muss der ClientSocket existieren. Nach dem Schicken wird der ClientSocket geschlossen.
	    * @param zug
	    * @return
	    * @throws IOException
	    */
	    public int setMeinZug(int zug) throws IOException {

	        int ret = -3;

	        if (clientSocket != null && clientSocket.isConnected()) {

	            OutputStream out = clientSocket.getOutputStream();

	            ret = setZugSpieler2(zug);

	            if (ret == 0) {
	                out.write(zug);
	                clientSocket.close();
	            }
	        }

	        return ret;
	    }
}
