package crocking.server;

import com.jme3.app.SimpleApplication;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.export.xml.XMLImporter;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.renderer.RenderManager;
import com.jme3.system.JmeContext;
import crocking.shared.config.PlayerUpdateConfig;
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

    
    public static int port = 6413;
    public static Server server;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start(JmeContext.Type.Headless);
    }

    @Override
    public void simpleInitApp() {
        try {
            server = Network.createServer(port);
            server.start();
            Serializer.registerClass(PlayerDataMessage.class);
            Serializer.registerClass(PlayerUpdateMessage.class);
            server.addMessageListener(new ServerMessageListener());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class ServerMessageListener implements MessageListener<HostedConnection>
    {
        public void messageReceived(HostedConnection source, Message message)
        {
            if(message instanceof PlayerDataMessage)
            {
                PlayerDataMessage msg = (PlayerDataMessage) message;
                Player player = getPlayerFromStream(msg.getPlayerStream());
                source.setAttribute("Player", player);
            }
            
            if(message instanceof PlayerUpdateMessage)
            {
                PlayerUpdateMessage msg = (PlayerUpdateMessage)message;
                
                if(msg.getUpdateType() == PlayerUpdateConfig.HEALTHUPDATE)
                {
                    ((Player)source.getAttribute("Player")).health += (Integer)msg.getPlayerUpdate();
                }
            }
        }
    }
    
    public Player getPlayerFromStream(byte[] playerStream)
    {
        BinaryImporter importer = BinaryImporter.getInstance();
        
        try {
            return (Player)importer.load(playerStream);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /*
    public Player loadPlayer(String playerName)
    {
        File playersDir = new File("data", "players");
        File file = new File(playersDir, playerName + ".j3o");
        XMLImporter importer = XMLImporter.getInstance();
        
        Player player = null;
        try {
          player = (Player) importer.load(file);
        } catch (IOException ex) {
          Logger.getLogger(crocking.client.Main.class.getName()).log(Level.SEVERE, "Error: Failed to load player!", ex);
        }
        
        return player;
    }*/
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}


