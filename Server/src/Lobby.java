import game.Direction;

import java.util.Set;

public class Lobby extends Thread {

    public static final int LOBBY_CLIENT_COUNT = 2;

    private boolean active;
    private final Set<Client> lobbyClients;

    public Lobby(final Set<Client> lobbyClients) {
        //в лобби должна быть левелдата
        this.lobbyClients = lobbyClients;
        int clientId = 0;
        for (Client client : lobbyClients){
            client.setId(clientId);
            clientId++;
        }
    }

    public boolean isActive(){
        return true;
    }

    private class LobbyThread extends Thread{
        @Override
        public void run() {
            while (isActive()){
                for (Client client : lobbyClients){
                    String command = client.getCommand();
                    if (command != null){
                        //выполнение команды
//                        if (command.equals("top")){
//                            int id = client.getId();
//                            levelData.move(id, Direction.TOP);
//                        }
                    }
                }
            }
        }
    }
}
