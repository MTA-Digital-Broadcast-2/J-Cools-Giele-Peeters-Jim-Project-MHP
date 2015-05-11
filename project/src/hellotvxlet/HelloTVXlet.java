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
   
    //achtergrond
    private HScreen screen;
    private HBackgroundDevice bgDevice;
    private HBackgroundConfigTemplate bgTemplate;
    private HStillImageBackgroundConfiguration bgConfiguration;
    private HBackgroundImage bgImage = new HBackgroundImage("backgroundimage.jpg");
    
    private PlayerBlock playerblock;
    private Sprite groundblock;
    
    private Sprite leftblock;
    private Sprite rightblock;
//    private PlatformBlock platformblock1;
    private Sprite platformblock1;
    private Sprite platformblock2;
    private Sprite platformblock3;
    private Sprite platformblock4;
    
    private CoinBlock coin1;
    private CoinBlock coin2;
    private CoinBlock coin3;
    private CoinBlock coin4;
    private CoinBlock coin5;
    
    private int _score = 0;
    private HStaticText _scoreText;
    
    private Sprite[] arrSprites = new Sprite[20]; 

    
    //---------------------
    

    public void initXlet(XletContext xletContext) throws XletStateChangeException 
    {
        
      if(debug) System.out.println("Xlet Initialiseren");
      this.actueleXletContext = xletContext;

      //achtergrond instellen
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
     
      //grootte en breedte van het scherm
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int screenHeight = screenSize.height;
      int screenWidth = screenSize.width;
      
   
        
      playerblock = new PlayerBlock("cubehead.png", 0, (screenHeight - 75 - 60), 60, 60, 10);
      groundblock = new Sprite("grondbloknature.png", 0, (screenHeight - 75), screenWidth, 75);
      
      //left wall
      leftblock = new Sprite("grondbloknature.png", -200, 0, 200, screenHeight);
      
      //right wall
      rightblock = new Sprite("grondbloknature.png", screenWidth, 0, 200, screenHeight);
      //groundblock = new Sprite("grondbloknature.png", 0, (screenHeight - 75), screenWidth, 75);
  
      
//      platformblock1 = new PlatformBlock("block.png", 100, 100, screenHeight-100, 100, 0);
      platformblock1 = new Sprite("block.png", 150, 350, 70, 70);
      platformblock2 = new Sprite("block.png", 0, 230, 70, 70);
      platformblock3 = new Sprite("block.png", 600, 260, 70, 70);
      platformblock4 = new Sprite("block.png", 400, 400, 70, 70);
      
      coin1 = new CoinBlock("coin_gold.png", 100, 200, 35, 35);
      coin2 = new CoinBlock("coin_gold.png", 200, 200, 35, 35);
      coin3 = new CoinBlock("coin_gold.png", 300, 200, 35, 35);
      coin4 = new CoinBlock("coin_gold.png", 400, 200, 35, 35);
      coin5 = new CoinBlock("coin_gold.png", 500, 200, 35, 35);
      //System.out.println(screenHeight);
      //System.out.println(screenWidth);
      //System.out.println(screenHeight - 100);
      
      _scoreText = new HStaticText("Score: " + _score);
      _scoreText.setSize(170, 70);
      _scoreText.setLocation(screenWidth - _scoreText.getWidth()-8, 5);
//      _scoreText.setSize(200, 100);
      _scoreText.setBackground(new DVBColor(0, 127, 255, 70));
//      _scoreText.setBackgroundMode(HVisible.BACKGROUND_FILL);
      _scoreText.setHorizontalAlignment(HVisible.HALIGN_CENTER);
      _scoreText.setVerticalAlignment(HVisible.VALIGN_CENTER);
      
      //Vraag labo ---------------------------------------------------------------------------------------------------
      System.out.println(playerblock.getHeight());
      System.out.println(playerblock.getWidth());
      System.out.println(groundblock.getHeight());
      System.out.println(groundblock.getWidth());
      
      //sprites aan array toevoegen
      arrSprites[0] = playerblock;
      arrSprites[1] = groundblock;
      arrSprites[2] = platformblock1;
      arrSprites[3] = platformblock2;
      arrSprites[4] = platformblock3;
      arrSprites[5] = platformblock4;
      arrSprites[6] = leftblock;
      arrSprites[7] = rightblock;
      arrSprites[8] = coin1;
      arrSprites[9] = coin2;
      arrSprites[10] = coin3;
      arrSprites[11] = coin4;
      arrSprites[12] = coin5;
      
      scene.add(playerblock);
      scene.add(groundblock);
      scene.add(platformblock1);
      scene.add(platformblock2);
      scene.add(platformblock3);
      scene.add(platformblock4);
      scene.add(leftblock);
      scene.add(rightblock);
      scene.add(coin1);
      scene.add(coin2);
      scene.add(coin3);
      scene.add(coin4);
      scene.add(coin5);
      
      scene.add(_scoreText);
      
      MijnTimerTask objMijnTimerTask = new MijnTimerTask(this);
      Timer timer = new Timer();
      timer.scheduleAtFixedRate(objMijnTimerTask, 0, 50); //start na 0s, tick elke 100ms
      
      scene.validate();
      scene.setVisible(true);
      
    }

    public void startXlet() throws XletStateChangeException{
        
    if(debug) System.out.println("Xlet Starten");
    
    System.out.println("groundlbock: " + Integer.toString(groundblock._posY-groundblock._height));
    
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
           //System.out.println("Pushed Button");
           switch(e.getCode())
           {
               case HRcEvent.VK_LEFT:
//                   System.out.println("Left key pressed");
                   //this.playerblock.move(1);
                   this.playerblock.setIsMovingLeft(true);
                   break;
               case HRcEvent.VK_RIGHT:
//                   System.out.println("Right key pressed");
                   //this.playerblock.move(3);
                   this.playerblock.setIsMovingRight(true);
                   break;
               case HRcEvent.VK_UP:
                  // System.out.println("Up key pressed");
                   //this.playerblock.move(2);
                   this.playerblock.setIsJumping(true);
                   //this.playerblock.setIsStartJumping(true);
                   break;
               case HRcEvent.VK_DOWN:
                   //System.out.println("Down key pressed");
                   //this.playerblock.move(4);
                   break;
                   
           }
       }
       if(e.getType() == KeyEvent.KEY_RELEASED){
           //System.out.println("Released Button");
           switch(e.getCode())
           {
               case HRcEvent.VK_LEFT:
                   //System.out.println("Left key released");
                   this.playerblock.setIsMovingLeft(false);
                   break;
               case HRcEvent.VK_RIGHT:
                   //System.out.println("Right key released");
                   this.playerblock.setIsMovingRight(false);
                   break;
               case HRcEvent.VK_UP:
                   //System.out.println("Up key released");
                   this.playerblock.setIsJumping(false);
                   break;
               case HRcEvent.VK_DOWN:
                   //System.out.println("Down key released");
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
        System.out.println("Image kan niet geladen worden.");
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void timerCallback()
    {
//        System.out.println("ticker");
        this.playerblock.move();
        detectCollision();
    }
    
    public void detectCollision()
    {
        for(int n = 0; n < arrSprites.length; n++)
        {
            if (arrSprites[n] != null && !arrSprites[n].getClass().equals(PlayerBlock.class))
            {
//                System.out.println("arrsprite: " + arrSprites[n].getClass().toString());
                if (arrSprites[n].getClass().equals(Sprite.class))
                {
                  
                   if (playerblock.getIsBottomCollided(arrSprites[n]) && !playerblock.getIsLeftCollided(arrSprites[n]) && !playerblock.getIsRightCollided(arrSprites[n]))   //Als de playerblock zijn top de bottom van een sprite raakt begint hij weer te vallen 
                   {
                        //System.out.println("bottom collided: " + playerblock.getIsBottomCollided(arrSprites[n]));
                         playerblock.setIsJumping(false);
                        playerblock.setIsStartJumping(false);
                        playerblock.setIsFalling(true);
                   }
                    
                   if (playerblock.getIsTopCollided(arrSprites[n]) && !playerblock.getIsLeftCollided(arrSprites[n]) && !playerblock.getIsRightCollided(arrSprites[n])) // Als de playerblock zijn onderkant de top van een Sprite raakt stopt hij met vallen en springen
                   {
                        //System.out.println("top collided: " + playerblock.getIsTopCollided(arrSprites[n]));
                        playerblock._posY = arrSprites[n]._posY - playerblock._height;
                        playerblock.setIsFalling(false);
                        playerblock.setJumpForce(playerblock.getStartJumpforce());   // De jumpforce reset
                        playerblock.setIsStartJumping(false);
//                      System.out.println("playerblock stop: " + Integer.toString(playerblock._posY));
//                      System.out.println("groundblock stop: " + Integer.toString(groundblock._posY));
                   }

                   if (playerblock.getIsLeftCollided(arrSprites[n]))
                   {
                       //System.out.println("left collided: " + playerblock.getIsLeftCollided(arrSprites[n]));
                       //Na zijkant botsen vallen 
//                        playerblock.setIsJumping(false);
//                        playerblock.setIsStartJumping(false);
//                        playerblock.setIsFalling(true);
                        playerblock._posX -= playerblock._velocityX;
                   } 

                   if (playerblock.getIsRightCollided(arrSprites[n]))
                   {
                       //System.out.println("right collided: " + playerblock.getIsRightCollided(arrSprites[n]));
//                        playerblock.setIsJumping(false);
//                       playerblock.setIsStartJumping(false);
//                       playerblock.setIsFalling(true);
                        playerblock._posX += playerblock._velocityX;
                   }
                  
                }
                if (arrSprites[n].getClass().equals(CoinBlock.class))
                
                   if (playerblock.getIsCollided(arrSprites[n]))
                   {
                       CoinBlock coinB = (CoinBlock)arrSprites[n];

                       if(!coinB.getIsCollected())
                       {
                           _score += coinB.getValue();
                           _scoreText.setTextContent("Score: " + _score, HVisible.NORMAL_STATE);
                           scene.remove(arrSprites[n]);
                           scene.repaint();
                       }
                       coinB.setIsCollected(true);

                       System.out.println("score : " + _score);    
                   } 

                }
//                System.out.println(arrSprites[n].getClass());
            }
        }
    }

