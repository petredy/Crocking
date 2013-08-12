package crocking.client;

import com.jme3.app.SimpleApplication;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.xml.XMLImporter;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;
import com.jme3.renderer.RenderManager;
import crocking.client.appstate.MenuAppState;
import crocking.shared.network.message.*;
import crocking.shared.player.Player;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static Client client;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        try {
            //this.loadPlayer();
            this.test();
            client = Network.connectToServer("localhost", 6413);
            client.start();
            byte[] playerStream = loadPlayer("greengnom");
            Serializer.registerClass(PlayerDataMessage.class);
            Serializer.registerClass(PlayerUpdateMessage.class);
            client.addMessageListener(new ClientMessageListener());
            if(playerStream != null)
            {
                PlayerDataMessage message = new PlayerDataMessage(playerStream);
                client.send(message);
                PlayerUpdateMessage update = new PlayerUpdateMessage((Object)19 , 0);
                client.send(update);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class ClientMessageListener implements MessageListener<Client>
    {
        public void messageReceived(Client source, Message message)
        {
            
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private byte[] loadPlayer(String playerName)
    {
        File playersDir = new File("data", "players");
        File file = new File(playersDir, playerName + ".j3o");
        XMLImporter importer = XMLImporter.getInstance();
        
        Player player = null;
        try {
          player = (Player) importer.load(file);
        } catch (IOException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
        }
        
        BinaryExporter exporter = BinaryExporter.getInstance();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            exporter.save(player, stream);
            return stream.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
    }

    private void test() {
        MenuAppState menu = new MenuAppState();
        menu.initialize(stateManager, this);
        stateManager.attach(menu);
        
    }
}
