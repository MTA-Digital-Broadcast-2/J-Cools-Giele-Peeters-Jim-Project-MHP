package hellotvxlet;

import javax.tv.xlet.*;
import org.davic.resources.ResourceProxy;
import org.dvb.ui.*;
import org.dvb.event.*;
import java.awt.*;
import java.awt.event.*;
import org.havi.ui.event.*;
import org.havi.ui.*;
import java.util.Timer;
import org.davic.resources.*;

//rechtermuisknop add abstract interface , er komt methode bij vanonvder waar alles in moetuserventRecieved

public class HelloTVXlet implements Xlet, UserEventListener, ResourceClient, HBackgroundImageListener
{
    
    private XletContext actueleXletContext;
    private HScene scene;
    private boolean debug=true;
   
    //Background image
    private HScreen screen;
    private HBackgroundDevice bgDevice;
    private HBackgroundConfigTemplate bgTemplate;
    private HStillImageBackgroundConfiguration bgConfiguration;
    private HBackgroundImage bgImage = new HBackgroundImage("backgroundimage.jpg");
    
    

    
    //---------------------
    

    public void initXlet(XletContext xletContext) throws XletStateChangeException 
    {
        
      if(debug) System.out.println("Xlet Initialiseren");
      this.actueleXletContext = xletContext;

      //Background image
      // HScreen object opvragen
      screen = HScreen.getDefaultHScreen();
      
      // HBackgroundDevice opvragen
      bgDevice = screen.getDefaultHBackgroundDevice();
      
      // HBackgroundDevice proberen te reserveren
      if ( bgDevice.reserveDevice(this))
      {
          System.out.println("BackgroundImage device has been reserved.");
      }
      else
      {
          System.out.println("Background image device cannot be reserved.");
      }
      
      // Template maken
      bgTemplate = new HBackgroundConfigTemplate();
      
      // Configurateinstelling "STILL_IMAGE"
      bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, HBackgroundConfigTemplate.REQUIRED);
      
      // Configuratie aanvragen en activeren en OK
      bgConfiguration = (HStillImageBackgroundConfiguration)bgDevice.getBestConfiguration(bgTemplate);
      
      try 
      {
          bgDevice.setBackgroundConfiguration(bgConfiguration);
      }
      catch (Exception s)
      {
          System.out.println(s.toString());
      }
      
      //scene instellen
      HSceneTemplate sceneTemplate = new HSceneTemplate();
      sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, 
                                  new HScreenDimension(1.0f, 1.0f), 
                                  HSceneTemplate.REQUIRED);
      
      sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, 
                                  new HScreenPoint(0.0f, 0.0f), 
                                  HSceneTemplate.REQUIRED);
      
      scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        
        spaceship = new Sprite("spaceship.png", 0, 0);
        scene.add(spaceship);
        
        sterren = new Sprite("sterren.png", 0, 0);
        scene.add(sterren);
        
        sterren2 = new Sprite("sterren.png", 0, 0);
        scene.add(sterren2);
      
      
      
      scene.validate();
      scene.setVisible(true);
      
    }

    public void startXlet() throws XletStateChangeException{
        
    if(debug) System.out.println("Xlet Starten");
    
    // Image laden
    bgImage.load(this);
    
    EventManager manager = EventManager.getInstance();
    
    UserEventRepository repository = new UserEventRepository("Voorbeeld");
    
    repository.addKey(org.havi.ui.event.HRcEvent.VK_UP);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_DOWN);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_LEFT);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_RIGHT);
    
    manager.addUserEventListener(this, repository);
    
       
    }

    public void pauseXlet() 
    {
        if(debug) System.out.println("Xlet Pauzeren");
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException 
    {
         if(debug) System.out.println("Xlet Destroy");
          bgImage.flush();
    }


    public void userEventReceived(org.dvb.event.UserEvent e) 
    {
       if(e.getType() == KeyEvent.KEY_PRESSED){
           System.out.println("Pushed Button");
           switch(e.getCode())
           {
               case HRcEvent.VK_LEFT:
                   System.out.println("Left key pressed");
                   break;
               case HRcEvent.VK_RIGHT:
                   System.out.println("Right key pressed");
                   break;
               case HRcEvent.VK_UP:
                   System.out.println("Up key pressed");
                   break;
               case HRcEvent.VK_DOWN:
                   System.out.println("Down key pressed");
                   break;
                   
           }
       }
    }

    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void release(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyRelease(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void imageLoaded(HBackgroundImageEvent e) {
        try
        {
            bgConfiguration.displayImage(bgImage);
        }    
        catch(Exception s)
        {
            System.out.println(s.toString());
        }
    }

    public void imageLoadFailed(HBackgroundImageEvent e) {
        System.out.print("Image kan niet geladen worden.");
        throw new UnsupportedOperationException("Not supported yet.");
    }
}