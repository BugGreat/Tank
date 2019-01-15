package com.me.code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.me.entity.Bullet;
import com.me.entity.Tank;
import com.me.utils.BaseFrame;
import com.me.utils.Constant;
import com.me.utils.GameUtils;

public class TankWindow extends BaseFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		tankList.add(tank);
		new TankWindow().launchFrame(tank,tankList,bulletList);
	}
	public static Image tankImg = GameUtils.getImage("images/one.jpg");
	public static Image backImg = GameUtils.getImage("images/zhuomian.png");
	static Tank tank = new Tank(tankImg, 250, 250);
	static List<Tank> tankList = new ArrayList<>();
	public static List<Bullet> bulletList = new ArrayList<>();

	@Override
	public void paint(Graphics g) {
//		g.drawImage(backImg, 0, 30,getWidth(),getHeight(), null);
		for(int i=0;i<tankList.size();i++) {
			tankList.get(i).draw(g);
			if(tankList.get(i).getX()<0||tankList.get(i).getY()<0||tankList.get(i).getX()>getWidth()||tankList.get(i).getY()>getHeight()) {
				tankList.remove(i);
			}
		}
		for(int i=0;i<bulletList.size();i++) {
			bulletList.get(i).draw(g);
			if(bulletList.get(i).getX()<0||bulletList.get(i).getY()<0||bulletList.get(i).getX()>getWidth()||bulletList.get(i).getY()>getHeight()) {
				bulletList.remove(i);
				continue;
			}
			for(int j=1;j<tankList.size();j++) {
				if(bulletList.get(i).getY()>=tankList.get(j).getY()&&bulletList.get(i).getY()<=(tankList.get(j).getY()+50)&&bulletList.get(i).getX()>=tankList.get(j).getX()&&bulletList.get(i).getX()<=(tankList.get(j).getX()+50)) {
					g.setColor(Color.RED);
					g.fillRect(bulletList.get(i).getX(), bulletList.get(i).getY(), 15, 15);
					bulletList.remove(i);
					tankList.remove(j);
					break;
				}
			}
			
		}
		Constant.state=false;
	}
	
}
