/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crocking.shared.network.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Michael
 */
@Serializable
public class PlayerUpdateMessage extends AbstractMessage
{
    private Object playerUpdate;
    private int updateType;
    
    public PlayerUpdateMessage()
    {
        
    }
    
    public PlayerUpdateMessage(Object playerUpdate, int updateType)
    {
        this.playerUpdate = playerUpdate;
        this.updateType = updateType;
        setReliable(true);
    }
    
    public Object getPlayerUpdate()
    {
        return playerUpdate;
    }
    
    public int getUpdateType()
    {
        return updateType;
    }
}
