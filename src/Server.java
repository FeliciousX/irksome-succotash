import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Listening on port " + portNumber);

            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected at " + clientSocket.toString());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("Connected to Server at port " + portNumber);

            BufferedReader in = new BufferedReader(
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
        // TODO: process the data
        return line;
    }
}
