package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Stan on 23-1-2017.
 */
public class ServerCommunication extends Thread{
    String ip;
    int portNumber;
    Handle handle;
    Socket socket;
    PrintWriter out;

    public ServerCommunication(String ip, int portNumber, Handle handle){
        this.ip = ip;
        this.portNumber = portNumber;
        this.handle = handle;
        try {
            socket = new Socket(ip, portNumber);
            System.out.println("Connected " + socket);
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connecting Socket " + ip + ":" + portNumber);
        } catch (IOException e) {
            System.out.println("Failed to connect");
        }
    }

    public void write(String str) {
        out.println(str);
        System.out.println("Sent to Server: " + str);
    }

    public void read() {
        try {
            while (true) {
                InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
                BufferedReader inputStream = new BufferedReader(inputReader);

                String serverMessage = "";
                while (!serverMessage.equals("Close")) {
                    if (!serverMessage.equals("") && !serverMessage.equals("Close")) {
                        handle.handleCommand(serverMessage);
                        System.out.println(serverMessage);
                    }
                    serverMessage = inputStream.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
