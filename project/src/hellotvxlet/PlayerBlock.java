/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.*;
import org.dvb.ui.*;
import java.awt.*;
import org.dvb.ui.*;
import org.havi.ui.*;
import java.lang.Object;

/**
 *
 * @author student
 */
public class PlayerBlock extends HComponent {
    
   private int _posX;
   private int _posY;
   private int _width;
   private int _height;
   private Image _image;
   
   private MediaTracker mtrack; 
   
   //Moving
   private boolean _isMovingLeft;
   private boolean _isMovingRight;
   private int _velocityX;
   
   //Falling 
   //Deze hoeven geen properties te hebben, want zijn enkel nodig voor PlayerBlock
   private boolean _isFalling = true;
   private int _velocityY = 15; //Hoe hard je naar beneden valt
   
   //Jump
   private boolean _isJumping = true;
   private boolean _isStartJumping = false;
   private int _jumpForce = 12;
   private int _isStartJumpForce = 12;
   
   
   public PlayerBlock(String initBitmapNaam, int initXPos, int initYPos, int initWidth, int initHeight)
   {
         _posX = initXPos;
         _posY = initYPos;
         _width = initWidth;
         _height = initHeight;
         
         _image = this.getToolkit().getImage(initBitmapNaam);
         mtrack = new MediaTracker(this);
         mtrack.addImage(_image, 0);
       
        try
        {
            mtrack.waitForAll(); //wacht tot alle bitmaps geladen zijn
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        this.setBounds(_posX, _posY, _width, _height);
   }
   
    public void move(String direction)
    {
        switch (direction)
        {
            case: "up":
                break;
            case: "down":
                break;
            case
        }
        this.setBounds(initXPos, initYPos, _image.getWidth(this), _image.getHeight(this));
    }
    
   
    public void paint (Graphics g)
    {
        g.drawImage(_image, 0, 0, this);
    }

   
   
    
    

}
