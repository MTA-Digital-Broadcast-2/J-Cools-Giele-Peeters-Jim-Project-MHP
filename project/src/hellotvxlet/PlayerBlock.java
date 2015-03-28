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
   private Dimension _screenSize;
   
   private MediaTracker mtrack; 
   
   //Moving
   private boolean _isMovingLeft;
   private boolean _isMovingRight;
   private int _velocityX = 12;
   
   //getter
   public boolean getIsMovingLeft()
   {
       return this._isMovingLeft;
   }
   //setter
   public void setIsMovingLeft(boolean initIsMovingLeft)
   {
       this._isMovingLeft = initIsMovingLeft;
       this._isMovingRight = false;
   }
   
   //getter
   public boolean getIsMovingRight()
   {
       return this._isMovingRight;
   }
   //setter
   public void setIsMovingRight(boolean initIsMovingRight)
   {
       this._isMovingRight = initIsMovingRight;
       this._isMovingLeft = false;
   }
   
   
   //Falling 
   //Deze hoeven geen properties te hebben, want zijn enkel nodig voor PlayerBlock
   private boolean _isFalling = true;
   private int _velocityY = 1; //Hoe hard je naar beneden valt
   
   //Jump
   private boolean _isJumping = true;
   private boolean _isStartJumping = false;
   private int _jumpForce = 12;
   private int _isStartJumpForce = 12;
   
   //getter
   public boolean getIsJumping()
   {
       return this._isJumping;
   }
   
   //setter
   public void setIsJumping(boolean initIsJumping)
   {
       this._isJumping = initIsJumping;
   }
   
     //getter
   public boolean getIsFalling()
   {
       return this._isFalling;
   }
   
   //setter
   public void setIsFalling(boolean initIsFalling)
   {
       this._isFalling = initIsFalling;
   }
   
   
   //colliding
   //private Rectangle _bottomRectangle;
   //private Rectangle _playerblockRectangle;
   
   public PlayerBlock(String initBitmapNaam, int initXPos, int initYPos, int initWidth, int initHeight, Dimension initScreenSize)
   {
         _posX = initXPos;
         _posY = initYPos;
         _width = initWidth;
         _height = initHeight;
         _screenSize = initScreenSize;
         
         
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
   /*
    public void move(int direction)
    {
        switch (direction)
        {
            case 1:
                this._posX -= this._velocityX; //Left
                break;
            case 2:
                this._posY -= this._jumpForce; //Up
                break;
            case 3:
                this._posX += this._velocityX; //right
                break;
            case 4:
                this._posY += this._jumpForce; //down
                break;
        }
        this.setBounds(this._posX, this._posY, _image.getWidth(this), _image.getHeight(this));
    }*/
   
   public void move()
    {
       if (this.getIsBottomCollided())
       {
              System.out.println("bottom coll: " + this.getIsBottomCollided());
       }
       if (this.getIsTopCollided())
       {
            System.out.println("top coll: " + this.getIsTopCollided());
       }
    
       if (this.getIsLeftCollided())
       {
           System.out.println("left coll: " + this.getIsLeftCollided());
       } 
      
       if (this.getIsRightCollided())
       {
           System.out.println("right coll: " + this.getIsRightCollided());
       }
     
  
       
        if (this._isMovingLeft == true)
        {
            //System.out.print("Playerblock is moving left.");
            this._posX -= this._velocityX; //Left
        }
        else if (this._isMovingRight == true)
        {
            //System.out.print("Playerblock is moving right.");
            this._posX += this._velocityX; //right
        }
        
        //hij valt altijd
        this._posY += this._velocityY;
        
        //vallen wordt tegengewerkt als hij springt
        if (this._isJumping == true || this._isStartJumping == true) //jumping
        {
            if (this._posY < this._posY + this._jumpForce && this._isFalling == false)
            {
                System.out.println("jumping");
                this._posY -= this._velocityY;
                this._posY -= this._velocityY;
                this._jumpForce--;
                this._isStartJumping = true;
            }
            else
            {
                this._isFalling = true;
            }
        }
        
        
        
        //tijdelijke oplossing voor implementatie van colliden
        //als playerblock beneden komt, moet hij stoppen met vallen
        /*if (this._posY >= this._screenSize.height - 150)
        {
            System.out.print("Playerblock stops falling.");
            this._isFalling = false;
            
        }*/
        
               
           /*
            case 2:
                this._posY -= this._jumpForce; //Up
                break;
            case 3:
              
                break;
            case 4:
                this._posY += this._jumpForce; //down
                break;*/
        
        this.setBounds(this._posX, this._posY, _image.getWidth(this), _image.getHeight(this));
    }
    
    public void paint (Graphics g)
    {
        g.drawImage(_image, 0, 0, this);
    }
    
    //colliden moet op basis van sprites --> zowel platformblocks als randen moeten colliden
    public boolean getIsBottomCollided()
    {
       Rectangle _bottomRectangle = new Rectangle(0, this._screenSize.height - this._velocityY, this._screenSize.width, this._velocityY);
       Rectangle _playerblockRectangle = new Rectangle(this._posX, this._posY, this._width, this._height);
       
       return _bottomRectangle.intersects(_playerblockRectangle);
    }
    
    public boolean getIsTopCollided()
    {
       Rectangle _topRectangle = new Rectangle(0, 0+this._velocityY, this._screenSize.width, this._velocityY);
       Rectangle _playerblockRectangle = new Rectangle(this._posX, this._posY, this._width, this._height);
       
       return _topRectangle.intersects(_playerblockRectangle);
    }
   
    public boolean getIsLeftCollided()
    {
       Rectangle _leftRectangle = new Rectangle(0, 0+this._velocityY, this._velocityX, this._screenSize.height-(2*this._velocityY));
       Rectangle _playerblockRectangle = new Rectangle(this._posX, this._posY, this._width, this._height);
       
       return _leftRectangle.intersects(_playerblockRectangle);
    }
    
     public boolean getIsRightCollided()
    {
       Rectangle _rightRectangle = new Rectangle(this._screenSize.width-this._velocityX, 0+this._velocityY, this._velocityX, this._screenSize.height-(2*this._velocityY));
       Rectangle _playerblockRectangle = new Rectangle(this._posX, this._posY, this._width, this._height);
       
       return _rightRectangle.intersects(_playerblockRectangle);
    }
   

   
   
    
    

}
