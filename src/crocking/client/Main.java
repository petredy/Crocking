package crocking.client;

import com.jme3.app.SimpleApplication;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.export.xml.XMLExporter;
import com.jme3.export.xml.XMLImporter;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeContext;
import crocking.client.appstate.MenuAppState;
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
            
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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

    private void loadPlayer() {
        File playersDir = new File("data", "players");
        File file = new File(playersDir, "greengnom.j3o");
        XMLImporter importer = XMLImporter.getInstance();
        
        Player greengnom = null;
        try {
          greengnom = (Player) importer.load(file);
        } catch (IOException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
        }
        
        System.out.println(greengnom.name);
        
        
        BinaryExporter exporter = BinaryExporter.getInstance();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] data = null;
        try {
            exporter.save(greengnom, stream);
            data = stream.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    private void test() {
        MenuAppState menu = new MenuAppState();
        menu.initialize(stateManager, this);
        stateManager.attach(menu);
        
    }
}
