/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crocking.shared.player;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import crocking.shared.entity.EntityLiving;
import java.io.IOException;

/**
 *
 * @author petredy
 */
public class Player extends EntityLiving{
    
    public String name;
    
    public Player(){
        
    }
    
    
    public boolean isHuman(){
        return true;
    }
    
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule caps = ex.getCapsule(this);
        caps.write(name, "name", "guest");
    }
    
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule caps = im.getCapsule(this);
        name = caps.readString("name", "guest");
    }
}
