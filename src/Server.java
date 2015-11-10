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
    public static Hashtable<Character, String> binaryMap;

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
                frequency = mapCharacterFrequency(inputLine);
                binaryMap = deriveBinaryMap(frequency);
                out.println(binaryMap);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    public static Hashtable<Character, Integer> mapCharacterFrequency(String line) {
        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();

        Character key = null;
        Integer count = null;

        for (char c : line.toCharArray()) {
            key = new Character(c);

            count = ht.get(key);

            if (count != null)
                ht.put(key, count.intValue() + 1);
            else
                ht.put(key, 1);
        }

        return ht;
    }
    
    public static Hashtable<Character, String> deriveBinaryMap(Hashtable<Character, Integer> frequencyMap) {
        Hashtable<Character, String> ht = new Hashtable<Character, String>();

        int charSize = frequencyMap.size();
        System.out.println("Number of characters: " + charSize);

        int binaryLength = (int) (Math.log(charSize) / Math.log(2));

        // if there's decimal places, +1 to binary number
        if (Math.log(charSize) % Math.log(2) != 0)
            binaryLength++;

        // TODO: get keys from frequencyMap and loop through it
        // while looping, assign a binary representation and store into the map
        return ht;
    }
}
