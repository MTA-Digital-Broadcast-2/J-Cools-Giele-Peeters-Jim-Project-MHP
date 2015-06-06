package hellotvxlet;

import org.havi.ui.*;
import java.awt.*;
import org.dvb.ui.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author student
 */
public class MijnComponent extends HComponent 
{
    private Image imgjpg = this.getToolkit().getImage("");
  
    public MijnComponent(int xPos, int yPos, int initWidth, int initHeight)
    {
        this.setBounds(xPos, yPos, initWidth, initHeight);
    }
    
    public void paint (Graphics g)
    {
        g.setColor(new DVBColor(0, 127, 255, 255));
        g.fillRoundRect(10, 10, 200, 100, 15, 15);
        
        g.setColor(new DVBColor(0, 150, 255, 255));
        g.fillRoundRect(0, 0, 200, 100, 15, 15);
             
        g.setColor(new DVBColor(255, 255, 0, 255));
        g.drawString("Tekst1", 15, 20);
    }
}
