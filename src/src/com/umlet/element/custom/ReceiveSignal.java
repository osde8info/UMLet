// The UMLet source code is distributed under the terms of the GPL; see license.txt
/*
 * Created on 29.07.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.umlet.element.custom;

import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Vector;

import com.umlet.constants.Constants;
import com.umlet.control.diagram.StickingPolygon;
import com.umlet.element.base.Entity;

/**
 * @author Ludwig
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
@SuppressWarnings("serial")
public class ReceiveSignal extends Entity {
	public void paintEntity(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		g2.setFont(this.handler.getFont());
		Composite[] composites = colorize(g2); //enable colors
		g2.setColor(_activeColor);
		this.handler.getFRC(g2);
		
		Polygon poly = new Polygon();
		poly.addPoint(0,0);
		poly.addPoint(this.getWidth()-1,0);
		poly.addPoint(this.getWidth()-1, this.getHeight()-1);
		poly.addPoint(0, this.getHeight()-1);
		poly.addPoint(this.handler.getFontsize()-2,this.getHeight()/2);
		
		g2.setComposite(composites[1]);
	    g2.setColor(_fillColor);
	    g2.fillPolygon(poly);
	    g2.setComposite(composites[0]);
	    if(_selected) g2.setColor(_activeColor); else g2.setColor(_deselectedColor);
		g2.drawPolygon(poly);

		Vector<String> tmp=Constants.decomposeStrings(this.getPanelAttributes(), "\n");
		int yPos=this.getHeight()/2-tmp.size()*(this.handler.getFontsize()+this.handler.getDistTextToText())/2;

		for (int i=0; i<tmp.size(); i++) {
			String s=tmp.elementAt(i);
			yPos+=this.handler.getFontsize();
			this.handler.writeText(g2,s,(int)this.getWidth()/2, yPos, true);
			yPos+=this.handler.getDistTextToText();
		}
	}
	
	  @Override
	  public StickingPolygon generateStickingBorder(int x, int y, int width, int height) {
	    StickingPolygon p = new StickingPolygon();
	    p.addPoint(new Point(x, y)); 
	    p.addPoint(new Point(x + width, y));
	    p.addPoint(new Point(x + width, y + height));
	    p.addPoint(new Point(x, y + height));
	    p.addPoint(new Point(x + this.handler.getFontsize()-2, y + height/2), true);
	    return p;
	  }
}
