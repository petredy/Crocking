/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crocking.shared.entity;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.export.Savable;
import java.io.IOException;

/**
 *
 * @author petredy
 */
public class EntityLiving extends Entity implements Savable{
    
    public int armor;
    public int strength;
    public int intelligence;
    public int stamina;
    
    public int health;
    
    
    public EntityLiving(){
        
    }
    
    public boolean isHuman(){
        return false;
    }
    
    public int getMaxHealth(){
        return this.stamina * 10;
    }

    public void write(JmeExporter ex) throws IOException {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(armor, "armor", 0);
        capsule.write(strength, "strength", 0);
        capsule.write(intelligence, "intelligence", 0);
        capsule.write(stamina, "stamina", 0);
        capsule.write(health, "health", 0);
    }

    public void read(JmeImporter im) throws IOException {
        InputCapsule caps = im.getCapsule(this);
        armor = caps.readInt("armor", 0);
        strength = caps.readInt("strength", 0);
        intelligence = caps.readInt("intelligence", 0);
        stamina = caps.readInt("stamina", 0);
        health = caps.readInt("health", 0);
    }
    
    
    
    
}
