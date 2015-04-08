package hellotvxlet;

import java.io.IOException;
import javax.tv.xlet.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.Properties;
import org.dvb.event.UserEvent;
import org.dvb.ui.*;
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
    private HScene scene;
    
    private HBackgroundDevice bgDevice;
    private HBackgroundConfigTemplate bgTemplate;
    private HStillImageBackgroundConfiguration bgConfiguration;
    private HBackgroundImage bgImg1 = new HBackgroundImage("pizza1.m2v");
    private HBackgroundImage bgImg2 = new HBackgroundImage("pizza2.m2v");
    private HBackgroundImage bgImg3 = new HBackgroundImage("pizza3.m2v");
    private HBackgroundImage bgImg4 = new HBackgroundImage("pizza4.m2v");
    
    private HBackgroundImage bgImg;
    
    private MijnComponent tekstvak;
    private MijnComponent tekstPrijs;
    private HTextButton btnOK;
    
    private int teller = 0;
    private int bestellingTeller = 0;
    private String[][] arrPizzas = {{"Meat Lover's", "9.49"}, {"Pepperoni Lover's", "11.00"}, {"Cheese Lover's", "9.95"}, {"Vegi Lover's", "12.45"}};
    //private double[] arrPrice = {9.49, 11.00, 9.95, 12.45};
    private String[][] arrBestelling;
    private double totalPrice = 0;
    
    
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
        
        HSceneTemplate sceneTemplate = new HSceneTemplate();
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, 
                                    new HScreenDimension(1.0f, 1.0f), 
                                    HSceneTemplate.REQUIRED);
      
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, 
                                    new HScreenPoint(0.0f, 0.0f), 
                                    HSceneTemplate.REQUIRED);
     
        scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        
        EventManager manager = EventManager.getInstance();
        UserEventRepository repository = new UserEventRepository("knoppen");
        
        repository.addKey(HRcEvent.VK_UP);
        repository.addKey(HRcEvent.VK_DOWN);
        repository.addKey(HRcEvent.VK_LEFT);
        repository.addKey(HRcEvent.VK_RIGHT);
        repository.addKey(HRcEvent.VK_ENTER);//OK button
        
        manager.addUserEventListener(this, repository);
        
        bgImg = bgImg1;
        
        tekstvak = new MijnComponent(400, 65, 200, 250, "");
        tekstPrijs = new MijnComponent(400, tekstvak.getY() + tekstvak.getHeight()+10, 200, 40, "Totaal: €" + Math.round(totalPrice*100.0)/100.0);
        scene.add(tekstvak);
        scene.add(tekstPrijs);
        
        arrBestelling = new String[10][2];
          
        //btnOK = new HTextButton("Toevoegen");
        //btnOK.setLocation(150, 150);
        //btnOK.setSize(100, 80);
        //btnOK.setBackground(new DVBColor(0, 0, 0, 179));
        //btnOK.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        scene.validate();
        scene.setVisible(true);
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
                    teller = 0; //teller terug op 0 zetten om te kunnen gebruiken voor switchen tss images
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
                    
                case HRcEvent.VK_ENTER:
                    System.out.println("VK_ENTER is PRESSED");
                    //System.out.println("Pizza: " + arrPizzas[teller] );
                    addPizza(teller);
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
    
    public void addPizza(int teller)
    {
        System.out.println("Teller: " + teller );
        
        //niet meer dan upperbound items in de array
        if (bestellingTeller == arrBestelling.length-1)
        {
            bestellingTeller = 0;
            arrBestelling = new String[10][2];
        }
        arrBestelling[bestellingTeller] = arrPizzas[teller];
        bestellingTeller++;
        System.out.println("Pizza: " + arrPizzas[teller] );
       
        
        String strBestelling = ""; //bestelling terug leegmaken, en daarna opvullen met for-loop
        totalPrice = 0; //totaal terug leegmaken, en daarna opvullen met for-loop
        
        String eol = System.getProperty("line.separator"); //get end of line character
        
        System.out.println("EOL: " + eol );
        
        int i;
        for (i=0; i<arrBestelling.length-1; i++)
        {
            if ( arrBestelling[i][0] != null ) //pizza name
            {
                strBestelling = strBestelling + arrBestelling[i][0] + eol;
                //String formatted = String.format("%s %n", arrBestelling[i]);            
            }
            
            if (arrBestelling[i][1] != null)
            {
                //calculate totalPrice
                double price = Double.parseDouble(arrBestelling[i][1]);
                System.out.println("Prijs: " + price );
                totalPrice = totalPrice + price;
            }
        }
        
        System.out.println("strBestelling: " + strBestelling );
        
        
        tekstvak.setContent(strBestelling);
        tekstPrijs.setContent("Totaal: €" + Math.round(totalPrice*100.0)/100.0);
        tekstvak.repaint();
        tekstPrijs.repaint();
    }
}
