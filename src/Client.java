import java.io.*;
import java.net.*;

public class Client {
    public static String hostName;
    public static int portNumber;
    public static Socket clientSocket;
    public static PrintWriter out;
    public static BufferedReader in;
    public static BufferedReader stdIn;

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                    "Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        hostName = args[0];
        portNumber = Integer.parseInt(args[1]);

        try {
            clientSocket = new Socket(hostName, portNumber);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            );
            stdIn = new BufferedReader(
                new InputStreamReader(System.in)
            );

            System.out.println("FROM SERVER: " + in.readLine());
            System.out.print("[CLIENT]: ");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("FROM SERVER: " + in.readLine());
                System.out.print("[CLIENT]: ");
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        } 
    }
}
