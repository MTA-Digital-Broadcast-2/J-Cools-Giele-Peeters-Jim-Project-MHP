/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import org.havi.ui.*;
import java.awt.*;
import org.dvb.ui.*;



/**
 *
 * @author student
 */
public class Sprite extends HComponent
{
    private Image img;
    //int spaceshipImgX = 10, spaceshipImgY = 10;
    private MediaTracker mtrack; 
    
    public Sprite(String bitmapnaam, int xPos, int yPos)
    {
        img = this.getToolkit().getImage(bitmapnaam);
        mtrack = new MediaTracker(this);
        mtrack.addImage(img, 0);
        
        try
        {
            mtrack.waitForAll(); //wacht tot alle bitmaps geladen zijn
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        this.setBounds(xPos, yPos, img.getWidth(null), img.getHeight(null));
        
    }
    
    public void verplaats(int x, int y)
    {
        this.setBounds(x, y, img.getWidth(this), img.getHeight(this));
    }
    
    public void paint (Graphics g)
    {
        g.drawImage(img, 0, 0, this);
    }
}
