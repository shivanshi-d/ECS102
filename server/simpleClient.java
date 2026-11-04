import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleClient {
    private Socket socket;
    private PrintWriter out;
    private Scanner in;

    public SimpleClient(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new Scanner(socket.getInputStream());
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String receiveMessage() {
        return in.nextLine();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) { }
    }

    // "Done early" version: sends "stop" after 5 messages
    public static void main(String[] args) {
        try {
            SimpleClient c = new SimpleClient("localhost", 9090);
            int count = 0;
            while (true) {
                String msg = c.receiveMessage();
                System.out.println(msg);
                count++;
                if (count == 5) {
                    c.sendMessage("stop");
                    break;
                }
                c.sendMessage("Client");
            }
            c.close();
        } catch (IOException e) { }
    }
}