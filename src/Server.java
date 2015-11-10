import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static int portNumber;
    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static PrintWriter out;
    public static BufferedReader in;
    public static Hashtable<Character, Integer> frequency;


    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }

        portNumber = Integer.parseInt(args[0]);

        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Listening on port " + portNumber);

            clientSocket = serverSocket.accept();

            System.out.println("Client connected at " + clientSocket.toString());

            out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Connected to Server at port " + portNumber);

            in = new BufferedReader(
                new InputStreamReader( clientSocket.getInputStream() )
            );

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(processData(inputLine));
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public static String processData(String line) {
        frequency = new Hashtable<Character, Integer>();

        Character key = null;
        Integer count = null;
        for (char c : line.toCharArray()) {
            key = new Character(c);

            count = frequency.get(key);

            if (count != null) {
                frequency.put(key, count.intValue() + 1);
            }
            else {
                frequency.put(key, 1);
            }
        }

        return frequency.toString();
    }
}
