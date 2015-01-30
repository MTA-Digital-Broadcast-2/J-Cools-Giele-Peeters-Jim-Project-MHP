package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.ui.*;
import org.dvb.event.*;
import java.awt.*;
import java.awt.event.*;
import org.havi.ui.event.*;
import org.havi.ui.*;
import java.util.Timer;


//rechtermuisknop add abstract interface , er komt methode bij vanonvder waar alles in moetuserventRecieved
public class HelloTVXlet implements Xlet, UserEventListener {

    public HelloTVXlet() {
        
    }
    

    private XletContext actueleXletContext;
    private HScene scene;
    private boolean debug=true;
    private MijnComponent schip = new MijnComponent("spaceship.png",0,0);
    private MijnComponent sterren = new MijnComponent("sterren.png",0,0);
     private MijnComponent sterren2 = new MijnComponent("sterren.png",0,-569);
    
    private int schipx = 0;
    private int schipy = 0;
    
    private int sterreny = 0;
    
    //---------------------
    

    public void initXlet(XletContext xletContext) throws XletStateChangeException {
        
      if(debug) System.out.println("Xlet Initialiseren");
      this.actueleXletContext = xletContext;

      scene= HSceneFactory.getInstance().getDefaultHScene();
     //---------------------
      scene.add(schip);
      scene.add(sterren);
      scene.add(sterren2);
     
      scene.validate();
      scene.setVisible(true);
      
      
      
    }

    public void startXlet() throws XletStateChangeException{
        
    System.out.println("Xlet gestart");
    
    EventManager manager = EventManager.getInstance();
    
    UserEventRepository repository = new UserEventRepository("Voorbeeld");
    
    repository.addKey(org.havi.ui.event.HRcEvent.VK_UP);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_DOWN);
     repository.addKey(org.havi.ui.event.HRcEvent.VK_LEFT);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_RIGHT);
    
    manager.addUserEventListener(this, repository);
    
       
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
     
    }
    
    public void callback()
    {
        sterreny++;
        sterren.verplaats(0, sterreny);
        sterren2.verplaats(0,sterreny-569);
        if(sterreny>569)
        {
            sterreny-=569;
        }
        
    }

    public void userEventReceived(org.dvb.event.UserEvent e) {
       if(e.getType() == KeyEvent.KEY_PRESSED){
           System.out.println("Pushed Button");
           switch(e.getCode())
           {
               case HRcEvent.VK_LEFT:
                   schipx -= 10;
                   schip.verplaats(schipx,schipy);
                   break;
               case HRcEvent.VK_RIGHT:
                   schipx += 10;
                   schip.verplaats(schipx,schipy);
                   break;
               case HRcEvent.VK_UP:
                   schipy -= 10;
                   schip.verplaats(schipx,schipy);
                   break;
               case HRcEvent.VK_DOWN:
                   schipy += 10;
                   schip.verplaats(schipx,schipy);
                   break;
                   
           }
       }
    }
}
