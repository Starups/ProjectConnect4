package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Stan on 23-1-2017.
 */
public class ServerCommunication extends Thread {
	// instance variables
    String ip;
    int portNumber;
    Handle handle;
    Socket socket;
    PrintWriter out;
    
    //constructor
    /*
     * constructor of ServerCommunication, connects with the server
     * 
     * @param ip (the IP that we will make a connection to)
     * @param portNumber (the port number where we will make a connection to)
     * @param handle ()
     */
    public ServerCommunication(String ip, int portNumber, Handle handle) {
        this.ip = ip;
        this.portNumber = portNumber;
        this.handle = handle;
        try {
            socket = new Socket(ip, portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Failed to connect: " + e);
        }
    }
    
    /*
     * reads the data comming from the server
     */

    public void run() {
        read();
    }
    /*
     * writes the data to the server
     * 
     * @param str (the String of data that will be sent)
     */
    public void write(String str) {
        out.println(str);
        if (!str.contains("joinrequest")) {
            System.out.println("Sent to Server: " + str);
        }
    }
    /*
     * 
     */

    public void read() {
        try {
            while (true) {
                InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
                BufferedReader inputStream = new BufferedReader(inputReader);

                String serverMessage = "";
                while (!serverMessage.equals("Close")) {
                    if (!serverMessage.equals("") && !serverMessage.equals("Close")) {
                        handle.handleCommand(serverMessage);
                    }
                    serverMessage = inputStream.readLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
     *  closes the connection with the server
     */

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}