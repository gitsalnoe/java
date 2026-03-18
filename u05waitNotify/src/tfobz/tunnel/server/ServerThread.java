package tfobz.tunnel.server;

import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * Der Thread liest vom Socket die Anzahl, und dabei werden die drei F�lle 
 * � gr��er 0, kleiner 0 oder gleich 0 � unterschieden und entsprechen am 
 * VisitorsMonitor die Anfragen gestellt. Das  Ergebnis wird an den Client 
 * zur�ck geschickt. der ServerThread erh�lt den Socket des Clients und eine 
 * Referenz auf VisitorsMonitor
 */
public class ServerThread extends Thread

{
	/**
	 * Der Clientsocket von welchem die Besucheranzahl gelesen werden kann
	 */
	protected Socket client = null;
	/**
	 * VisitorsMonitor an dem die Anfrage nach Besuchern bzw. die R�ckgabe
	 * der Besucher nach Beendigung einer Besichtigung gestellt werden kann
	 */
	protected VisitorsMonitor visitorsMonitor = null;
	
	/**
	 * Konstruktor erh�lt den Clientsocket und den VisitorsMonitor als
	 * Referenz. Als Threadname wird die IP-Adresse des Clients gesetzt.
	 * Die IP-Adresse kann �ber den Clientsocket durch die Methode
	 * getInetAdress() erfragt werden
	 * @param client
	 * @param visitorsMonitor
	 */
	public ServerThread(Socket client, VisitorsMonitor visitorsMonitor) {
		this.client = client;
        this.visitorsMonitor = visitorsMonitor;
        this.setName("/" + client.getInetAddress().getHostAddress());	//gibt die ip adresse als string aus
	}
	
	/**
	 * Diese Methode liest zuerst vom Clientsocket die Anzahl. Je nach dem
	 * welche Werte in anzahl stehen, werden folgende Aufgaben erledigt:<br><br>
	 * <b>anzahl == 0</b><br>
	 * Es wird die Anzahl der am VisitorsMonitor momentan verf�gbaren Benutzer
	 * abgefragt und an den Client zur�ck geschickt<br><br>
	 * <b>anzahl > 0</b><br>
	 * Es werden am VisitorsMonitor die Benutzer angefordert<br><br>
	 * <b>anzahl < 0</b><br>
	 * Es werden dem VisitorsMonitor die Anzahl an Benutzer zur�ck gegeben
	 */
	public void run() {
		try (DataInputStream in = new DataInputStream(client.getInputStream()); DataOutputStream out = new DataOutputStream(client.getOutputStream())) {
	            int anzahl = in.readInt();
	            if (anzahl == 0) {
	                out.writeInt(visitorsMonitor.getAvailableVisitors());
	            } else if (anzahl > 0) {
	                System.out.println(this.getName() + " requests " + anzahl + " visitors");
	                visitorsMonitor.request(anzahl);
	                out.writeInt(1);
	            } else if (anzahl < 0) {
	                int releaseCount = Math.abs(anzahl);
	                System.out.println(this.getName() + " releases " + releaseCount + " visitors");
	                visitorsMonitor.release(releaseCount);
	                out.writeInt(1);
	            }  
	        } catch (IOException e) {
	            ServerMain.behandleException(e);
	        } finally {
	            try {
	                if (client != null && !client.isClosed()) {
	                    client.close();
	                }
	            } catch (IOException e) {
	                ServerMain.behandleException(e);
	            }
	        }
	 }
}
