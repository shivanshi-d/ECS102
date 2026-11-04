import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleServer2 {
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter out;
    private Scanner in;

    // TODO 1 · open the door
    public SimpleServer2(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Waiting on port " + port);
    }

    // TODO 2 · wait for a client
    public void acceptClient() throws IOException {
        socket = serverSocket.accept();
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new Scanner(socket.getInputStream());
        System.out.println("Client connected");
    }

    // TODO 3 · send and read
    public String receiveMessage() {
        return in.nextLine();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    // TODO 4 · hang up
    public void close() {
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException e) { }
    }

    public static void main(String[] args) {
        try {
            SimpleServer2 s = new SimpleServer2(9090);
            s.acceptClient();
            while (true) {
                s.sendMessage("Hello ");
                String reply = s.receiveMessage();
                System.out.println(reply + " ");
                if (reply.equals("stop")) {
                    break;
                }
            }
            s.close();
        } catch (IOException e) { }
    }

    // TODO: what you would add next
}