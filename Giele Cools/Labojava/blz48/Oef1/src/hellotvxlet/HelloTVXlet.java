package hellotvxlet;

import javax.tv.xlet.*;
import java.awt.event.*;
import java.util.Timer;
import org.dvb.event.*;
import org.havi.ui.*;
import org.havi.ui.event.*;

public class HelloTVXlet implements Xlet, UserEventListener 
{
    private XletContext actueleXletContext;
    private HScene scene;
    
    private boolean debug = true;
    
    private Sprite spaceship;
    private Sprite sterren;
    private Sprite sterren2;
    
    private int spaceshipX = 0; //voor bewegen van spaceship
    private int spaceshipY = 0;
    
    private int sterrenY = 0;
  
    public HelloTVXlet() 
    {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException 
    {
        if(debug) System.out.println("Xlet Initialiseren");
        this.actueleXletContext = context;
      
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

    public void startXlet() throws XletStateChangeException 
    {
        if(debug) System.out.println("Xlet Starten");
        
        //eventmanager aanvragen
        EventManager manager = EventManager.getInstance();
        
        //repository = verzameling van toetsen
        UserEventRepository repository = new UserEventRepository("toetsen");
        
        //events toevoegen
        repository.addKey(HRcEvent.VK_UP);
        repository.addKey(HRcEvent.VK_DOWN);
        repository.addKey(HRcEvent.VK_LEFT);
        repository.addKey(HRcEvent.VK_RIGHT);
        
        //bekend maken bij EventManager
        manager.addUserEventListener(this, repository);
        
        MijnTimerTask objMijnTimerTask = new MijnTimerTask(this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(objMijnTimerTask, 0, 100); //start na 0s, tick elke 300ms
        
        scene.validate();
        scene.setVisible(true);
    }

    public void pauseXlet() 
    {
     
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException
    {
     
    }
    
    public void actionPerformed(ActionEvent e)
    {
      
    }
    
    public void setButtonDefaultBackground(HTextButton button)
    {

    }
    
    public void reveilTwoAnswers()
    {
       
    }

    //opvangen van de key events
    public void userEventReceived(UserEvent e) 
    {
        if(e.getType() == KeyEvent.KEY_PRESSED)
        {
            System.out.println("Pushed Button");
            switch(e.getCode())
            {
                case HRcEvent.VK_UP:
                    System.out.println("VK_UP is PRESSED");
                    spaceshipY-=10;
                    spaceship.verplaats(spaceshipX, spaceshipY);
                    break;  
                case HRcEvent.VK_DOWN:
                    System.out.println("VK_DOWN is PRESSED");
                    spaceshipY+=10;
                    spaceship.verplaats(spaceshipX, spaceshipY);
                    break;  
                case HRcEvent.VK_LEFT:
                    System.out.println("VK_LEFT is PRESSED");
                    spaceshipX-=10;
                    spaceship.verplaats(spaceshipX, spaceshipY);
                    break;  
                case HRcEvent.VK_RIGHT:
                    System.out.println("VK_RIGHT is PRESSED");
                    spaceshipX+=10;
                    spaceship.verplaats(spaceshipX, spaceshipY);
                    break;  
                    
            }
                   
        }
    }
    
    public void callback()
    {
        sterrenY++;
        sterren.verplaats(0, sterrenY);
        sterren2.verplaats(0, sterrenY-569); //2e bitmap ligt dan vlak boven de andere
        if (sterrenY>569) sterrenY-=569; //zet sterreny terug op 0
    }
}
