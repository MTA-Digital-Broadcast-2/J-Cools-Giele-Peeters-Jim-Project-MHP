package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.event.*;
import org.havi.ui.*;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet implements Xlet, HActionListener 
{
    private XletContext actueleXletContext;
    private HScene scene;
    
    private boolean debug = true;
    
    private MijnComponent mijnComponent;
  
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
        
        mijnComponent = new MijnComponent(0, 0, 300, 200);
        scene.add(mijnComponent);
        
        scene.validate();
        scene.setVisible(true);
        
    }

    public void startXlet() 
    {
        if(debug) System.out.println("Xlet Starten");
        
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
}
