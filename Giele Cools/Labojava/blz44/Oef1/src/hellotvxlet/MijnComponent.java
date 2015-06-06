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
    public MijnComponent(int xPos, int yPos, int initWidth, int initHeight)
    {
        this.setBounds(xPos, yPos, initWidth, initHeight);
    }
    
    public void paint (Graphics g)
    {
        g.setColor(new DVBColor(100, 100, 100, 179));
        g.fillRect(0, 0, 100, 100);
        g.setColor(Color.white);
        g.drawString("Tekst1", 15, 20);
    }
}
