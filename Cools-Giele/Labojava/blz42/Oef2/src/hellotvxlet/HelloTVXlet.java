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
    
    private HStaticText tekstLabel;
    private HTextButton knop1, knop2, knop3, knop4, knopHulplijn;
  
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
     
        tekstLabel = new HStaticText("Wie wordt multimiljonair?");
        tekstLabel.setLocation(150, 30);
        tekstLabel.setSize(400, 100);
        tekstLabel.setBackground(new DVBColor(255, 255, 255, 179));
        tekstLabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(tekstLabel);
        
        knop1 = new HTextButton("Antwoord 1");
        knop1.setLocation(150, 150);
        knop1.setSize(400, 80);
        knop1.setBackground(new DVBColor(0, 0, 0, 179));
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
    
        knop2 = new HTextButton("Antwoord 2");
        knop2.setLocation(150, 250);
        knop2.setSize(400, 80);
        knop2.setBackground(new DVBColor(0, 0, 0, 179));
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop3 = new HTextButton("Antwoord 3");
        knop3.setLocation(150, 350);
        knop3.setSize(400, 80);
        knop3.setBackground(new DVBColor(0, 0, 0, 179));
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop4 = new HTextButton("Antwoord 4");
        knop4.setLocation(150, 450);
        knop4.setSize(400, 80);
        knop4.setBackground(new DVBColor(0, 0, 0, 179));
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knopHulplijn = new HTextButton("Hulplijn");
        knopHulplijn.setLocation(570, 460);
        knopHulplijn.setSize(100, 50);
        knopHulplijn.setBackground(new DVBColor(165, 165, 165, 179));
        knopHulplijn.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        knop1.setFocusTraversal(knop4, knop2, knopHulplijn, null);
        knop2.setFocusTraversal(knop1, knop3, null, null);
        knop3.setFocusTraversal(knop2, knop4, null, null);
        knop4.setFocusTraversal(knop3, knop1, null, knopHulplijn);
        knopHulplijn.setFocusTraversal(null, null, knop4, knop1);

        scene.add(knop1);
        scene.add(knop2);
        scene.add(knop3);   
        scene.add(knop4);
        scene.add(knopHulplijn);
        
        knop1.requestFocus();
        
        knop1.setActionCommand("knop1_actioned");
        knop2.setActionCommand("knop2_actioned");
        knop3.setActionCommand("knop3_actioned");
        knop4.setActionCommand("knop4_actioned");
        knopHulplijn.setActionCommand("knopHulplijn_actioned");
        
        knop1.addHActionListener(this);
        knop2.addHActionListener(this);
        knop3.addHActionListener(this);
        knop4.addHActionListener(this);
        knopHulplijn.addHActionListener(this);
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
      if(e.getActionCommand().equals("knop1_actioned"))
      {
          System.out.println(e.getActionCommand());
          knop1.setBackground(new DVBColor(0, 255, 0, 255)); //groen
          setButtonDefaultBackground(knop2);
          setButtonDefaultBackground(knop3);
          setButtonDefaultBackground(knop4);
          knop1.requestFocus(); //focus terug op knop zetten waarop gedrukt was
      }
      
      if(e.getActionCommand().equals("knop2_actioned"))
      {
          System.out.println(e.getActionCommand());
          knop2.setBackground(new DVBColor(255, 0, 0, 255)); //rood
          setButtonDefaultBackground(knop1);
          setButtonDefaultBackground(knop3);
          setButtonDefaultBackground(knop4);
          knop2.requestFocus();
      }
      
      if(e.getActionCommand().equals("knop3_actioned"))
      {
          System.out.println(e.getActionCommand());
          knop3.setBackground(new DVBColor(255, 0, 0, 255)); //rood
          setButtonDefaultBackground(knop1);
          setButtonDefaultBackground(knop2);
          setButtonDefaultBackground(knop4);
          knop3.requestFocus();
      }
      
      if(e.getActionCommand().equals("knop4_actioned"))
      {
          System.out.println(e.getActionCommand());
          knop4.setBackground(new DVBColor(255, 0, 0, 255)); //rood
          setButtonDefaultBackground(knop1);
          setButtonDefaultBackground(knop2);
          setButtonDefaultBackground(knop3);
          knop4.requestFocus(); 
      }
      
      if(e.getActionCommand().equals("knopHulplijn_actioned"))
      {
          System.out.println(e.getActionCommand());
          setButtonDefaultBackground(knop1);
          setButtonDefaultBackground(knop2);
          setButtonDefaultBackground(knop3);
          setButtonDefaultBackground(knop4);
          reveilTwoAnswers();
          knopHulplijn.requestFocus(); 
      }
      scene.validate();
      scene.setVisible(true);
    }
    
    public void setButtonDefaultBackground(HTextButton button)
    {
        button.requestFocus();  //eerst focus op de knop zetten, anders werkt het veranderen naar defaultbackground niet goed
        button.setBackground(new DVBColor(0, 0, 0, 179));
    }
    
    public void reveilTwoAnswers()
    {
        knop2.setBackground(new DVBColor(255, 0, 0, 255));
        knop4.setBackground(new DVBColor(255, 0, 0, 255));
    }
}
