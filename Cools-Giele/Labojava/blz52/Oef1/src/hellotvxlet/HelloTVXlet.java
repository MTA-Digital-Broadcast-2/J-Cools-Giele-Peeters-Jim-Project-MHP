package hellotvxlet;

import java.io.IOException;
import javax.tv.xlet.*;
import java.awt.event.*;
import org.dvb.event.UserEvent;
import org.havi.ui.*;
import org.havi.ui.event.*;
import org.davic.resources.*;
import org.dvb.event.EventManager;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;

public class HelloTVXlet implements Xlet, ResourceClient, HBackgroundImageListener, UserEventListener
{
    private boolean debug = true;
    
    private HScreen screen;
    private HBackgroundDevice bgDevice;
    private HBackgroundConfigTemplate bgTemplate;
    private HStillImageBackgroundConfiguration bgConfiguration;
    private HBackgroundImage bgImg1 = new HBackgroundImage("pizza1.m2v");
    private HBackgroundImage bgImg2 = new HBackgroundImage("pizza2.m2v");
    private HBackgroundImage bgImg3 = new HBackgroundImage("pizza3.m2v");
    private HBackgroundImage bgImg4 = new HBackgroundImage("pizza4.m2v");
    
    private HBackgroundImage bgImg;
    
    private int teller = 0;
    
    
    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        return false;
    }
    public void release(ResourceProxy proxy) {}
    public void notifyRelease(ResourceProxy proxy) {}
 
    public HelloTVXlet() 
    {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException 
    {
        if(debug) System.out.println("Xlet Initialiseren");
    
           screen = HScreen.getDefaultHScreen();
           bgDevice = screen.getDefaultHBackgroundDevice();
        
        if(bgDevice.reserveDevice(this))
        {
            System.out.print("BackgroundImage device has been reserved.");
        }
        else
        {
            System.out.print("Background image device cannot be reserved");
        }
        
        bgTemplate = new HBackgroundConfigTemplate();
        bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, HBackgroundConfigTemplate.REQUIRED);
        bgConfiguration = (HStillImageBackgroundConfiguration) bgDevice.getBestConfiguration(bgTemplate);
        
        try
        {
            bgDevice.setBackgroundConfiguration(bgConfiguration);
        }
        catch(java.lang.Exception e)
        {
            System.out.print(e.toString());
        }     
        
        EventManager manager = EventManager.getInstance();
        UserEventRepository repository = new UserEventRepository("knoppen");
        
        repository.addKey(HRcEvent.VK_UP);
        repository.addKey(HRcEvent.VK_DOWN);
        repository.addKey(HRcEvent.VK_LEFT);
        repository.addKey(HRcEvent.VK_RIGHT);
        
        manager.addUserEventListener(this, repository);
        
        bgImg = bgImg1;
    }

    public void startXlet() throws XletStateChangeException 
    {
        if(debug) System.out.println("Xlet Starten");
        
        bgImg1.load(this);  
        bgImg2.load(this);    
        bgImg3.load(this); 
        bgImg4.load(this);
    }

    public void pauseXlet() 
    {
     
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException
    {
        if(debug) System.out.println("Xlet Stoppen");
        bgImg1.flush();  
        bgImg2.flush();    
        bgImg3.flush(); 
        bgImg4.flush();
    }
   
    public void imageLoaded(HBackgroundImageEvent e) 
    {
          teller++;
          if (teller==4) 
          { 
              System.out.println("alles geladen");
                try 
                {
                    bgConfiguration.displayImage(bgImg1);
                }
                catch(Exception s)
                {
                    System.out.println(s.toString());
                }         
          }
    }

    public void imageLoadFailed(HBackgroundImageEvent e) 
    {
        System.out.print("Image kan niet geladen worden.");
    }

    public void userEventReceived(UserEvent e) 
    {
        if(e.getType() == KeyEvent.KEY_PRESSED)
        {
            System.out.println("Pushed Button");
            switch(e.getCode())
            {
                case HRcEvent.VK_LEFT:
                    System.out.println("VK_LEFT is PRESSED");
                    if (teller > 0)
                    {
                     teller--;
                    }
                    else 
                    {
                        teller = 3;
                    }
                    
                    loadImage();
                    //bgImg.load(this);
                    break;  
                    
                case HRcEvent.VK_RIGHT:
                    System.out.println("VK_RIGHT is PRESSED");
                    if(teller < 3)
                    {
                     teller++;
                    }
                    else
                    {
                     teller = 0;
                    }
                   
                    loadImage();
                    //bgImg.load(this);
                    break;         
            }        
        }
    }
    
    public void loadImage()
    {
        switch (teller)
        {
            case 0:
                bgImg = bgImg1;
            break;
                
            case 1:
                bgImg = bgImg2;
            break;

            case 2:
                bgImg = bgImg3;
            break;

            case 3:
                bgImg = bgImg4;
            break;        
        }
        try {
            bgConfiguration.displayImage(bgImg);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (HPermissionDeniedException ex) {
            ex.printStackTrace();
        } catch (HConfigurationException ex) {
            ex.printStackTrace();
        }
    }
}
