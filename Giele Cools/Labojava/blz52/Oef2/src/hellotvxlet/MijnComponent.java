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
    private String content;
    
    public MijnComponent(int xPos, int yPos, int initWidth, int initHeight, String initText)
    {
        content = initText;
        this.setBounds(xPos, yPos, initWidth, initHeight);
    }
    
    public void setContent(String initContent)
    {
        this.content = initContent;
    }
    
    public void paint (Graphics g)
    {
        g.setColor(new DVBColor(0, 127, 255, 70));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(new DVBColor(255, 255, 255, 255));
        g.drawString(this.content, 15, 18);
    }
}
