/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crocking.client.appstate;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import crocking.client.screencontroller.MenuScreenController;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author petredy
 */
public class MenuAppState extends AbstractAppState{
    
    
    private SimpleApplication app;
    private AppStateManager stateManager;
    
    private Nifty nifty;
    private NiftyJmeDisplay niftyDisplay;
    
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.stateManager = stateManager;
        this.app = (SimpleApplication) app;
        
        
    }
    
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
    
    
    public void stateAttached(AppStateManager stateManager) {
        niftyDisplay = new NiftyJmeDisplay(app.getAssetManager(),
                                                        app.getInputManager(),
                                                        app.getAudioRenderer(),
                                                        app.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/menu.xml", "menu", new MenuScreenController());


        // attach the nifty display to the gui view port as a processor
        app.getGuiViewPort().addProcessor(niftyDisplay);

        app.getInputManager().setCursorVisible(true);
        app.getFlyByCamera().setDragToRotate(true);
    
    }

    public void stateDetached(AppStateManager stateManager) {
        
        app.getGuiViewPort().removeProcessor(niftyDisplay);
    }

    public void update(float tpf) {
    }

    public void render(RenderManager rm) {
    }

    public void postRender(){
    }

    
}
