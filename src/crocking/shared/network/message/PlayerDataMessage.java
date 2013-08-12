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
public class PlayerDataMessage extends AbstractMessage
{
    private byte[] playerStream;
    
    public PlayerDataMessage()
    {
        
    }
    
    public PlayerDataMessage(byte[] playerStream)
    {
        this.playerStream = playerStream;
        setReliable(true);
    }
    
    public byte[] getPlayerStream()
    {
        return playerStream;
    }
}
