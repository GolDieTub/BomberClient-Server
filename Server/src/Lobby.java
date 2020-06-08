import java.util.Set;

public class Lobby extends Thread {

    public static final int LOBBY_CLIENT_COUNT = 4;

    private boolean active;
    private final Set<Client> lobbyClients;

    public Lobby(final Set<Client> lobbyClients) {
        this.lobbyClients = lobbyClients;
    }
}
