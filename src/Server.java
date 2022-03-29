import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try {
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept(); // ss.accept() IS A blocking method, returns a socket
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);
                /*Objects | made from a class that implements 'runnable'| can be ran in a new thread*/

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
        }
    }

    public void closeServerSocket(){
        try {
            if (serverSocket != null) serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Server server = new Server(serverSocket);
        server.startServer();
    }

}
