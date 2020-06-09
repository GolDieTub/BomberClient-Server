import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ServerOne extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final Object LOCK = new Object();
    private List<Lobby> lobbies;
    private List<Client> clients = Server.getClients();

    public ServerOne(Socket s) throws IOException {
        //clients = new ArrayList<>();
        lobbies = new ArrayList<>();
        socket = s;
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        new ServerOne.LobbyCreator().start();
        start();
    }


    private class LobbyCreator extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (LOCK) {
                        if (clients.size() >= Lobby.LOBBY_CLIENT_COUNT) {

                            final Set<Client> lobbyClients = new HashSet<>();

                            for (int i = 0; i < Lobby.LOBBY_CLIENT_COUNT; i++) {
                                final Client client = clients.get(0);
                                lobbyClients.add(client);
                                clients.remove(client);
                            }
                            final Lobby lobby = new Lobby(lobbyClients);
                            lobbies.add(lobby);
                        }
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run() {
        try {
            while (true) {
                if (Server.getClients().size()==2){
                    out.println("start");
                }
                String str = in.readLine();
                System.out.println("Получено: " + str);
                //out.println(str);
            }
        } catch (IOException e) {
            System.err.println("оишбка ввода/вывода");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Сокет не закрыт");
            }
        }
    }
}

public class Server {

    static final int Port = 8285;
    private static List<Client> clients = new ArrayList<>();

    public static List<Client> getClients(){
        return clients;
    }


    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(Port);
        System.out.println("Мультипоточный сервер стартовал");
        try {
            while (true) {
                Socket socket = s.accept();
                try {
                    System.out.println("Новое соединение установлено");
                    System.out.println("Данные клиента: "+
                            socket.getInetAddress());
                    clients.add(new Client(socket));
                    new ServerOne(socket);
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }
}