package com.me.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.lang.Thread.State;
import java.util.List;
import com.me.code.TankWindow;
import com.me.entity.Bullet;
import com.me.entity.Tank;



/**
  * 父类
 * @author False
 *
 */

public class BaseFrame extends Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 定义一个重画窗口的线程类，是个内不类，
	 * @author False
	 *
	 */
	class PaintThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
//			super.run();
			while (true) {
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	class BulletThread extends Thread{
		private boolean flag=false;
		private MouseEvent e;
		private Tank tank;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
//			super.run();
			while (flag) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				int tankX = (int)tank.getX();
				int tankY = (int)tank.getY();
				double lenght = Math.sqrt(Math.abs((e.getX() - tank.getX())* (e.getX() - tank.getX())+(e.getY() - tank.getY())* (e.getY() - tank.getY())));
				double speedY = (mouseY-tankY)/lenght;
				double speedX = (mouseX-tankX)/lenght;
				try {
					TankWindow.bulletList.add(new Bullet(tankX, tankY , speedX,speedY, 20));
					Thread.sleep(300);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	
	public void launchFrame(Tank tank,List<Tank> list,List<Bullet> bulletList) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = Constant.GAME_WIDTH;
		int windowHeight = Constant.GAME_HEIGTH;
		this.setSize(windowWidth, windowHeight);
		setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		this.setTitle("Tank");
//		setResizable(false);
		setUndecorated(false);
		Cursor c = kit.createCustomCursor(GameUtils.getImage("images/sccscroll5.png"),new Point(), "");
		setCursor(c);
		setVisible(true);
		new PaintThread().start();
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				if(e.getOldState() != e.getNewState()){
					int newWidth = e.getWindow().getWidth();
					int newHeight = e.getWindow().getHeight();
					Constant.state=true;
		            switch (e.getNewState()) {
		            case Frame.MAXIMIZED_BOTH:
		                // 最大化
		            	offScreenImage=null;
		            	Constant.xChange = newWidth/Constant.GAME_WIDTH;
		            	Constant.yChange = newHeight/Constant.GAME_HEIGTH;
		            	Constant.GAME_WIDTH=newWidth;
		            	Constant.GAME_HEIGTH=newHeight;
		            	setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGTH);
		                break;
		            case Frame.ICONIFIED:
		                // 最小化
		
		                break;
		            case Frame.NORMAL:
		                // 恢复
		            	offScreenImage=null;
		            	Constant.xChange = (double)newWidth/Constant.GAME_WIDTH;
		            	Constant.yChange = (double)newHeight/Constant.GAME_HEIGTH;
		            	Constant.GAME_WIDTH=newWidth;
		            	Constant.GAME_HEIGTH=newHeight;
		            	setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGTH);
		                break;
		            default:
		                break;
		            }
		        }
			}
		});
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					tank.setUp(false);
					break;
				case KeyEvent.VK_D:
					tank.setRight(false);
					break;
				case KeyEvent.VK_A:
					tank.setLeft(false);
					break;
				case KeyEvent.VK_S:
					tank.setDown(false);
					break;
				default:
					break;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					tank.setUp(true);
					break;
				case KeyEvent.VK_D:
					tank.setRight(true);
					break;
				case KeyEvent.VK_A:
					tank.setLeft(true);
					break;
				case KeyEvent.VK_S:
					tank.setDown(true);
					break;
				case KeyEvent.VK_J:
					Tank newtank = new Tank(TankWindow.tankImg, tank.getX(), tank.getY());
//					newtank.setUp(true);
					list.add(newtank);
					break;
				default:
					break;
				}
				
			}
			
		});
		addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int key = e.getButton();
				System.out.println(e);
				switch (key) {
				case MouseEvent.BUTTON1:
					bulletThread.flag = false;
					break;

				default:
					break;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int key = e.getButton();
				switch (key) {
				case MouseEvent.BUTTON1:
					
					
//						Bullet bullet = new Bullet(tankX, tankY, speedX, speedY, 50);
//						bulletList.add(bullet);
					System.out.println(bulletThread.getState());
					if(bulletThread.getState()==State.NEW) {
						bulletThread.flag=true;
						bulletThread.e=e;
						bulletThread.tank=tank;
						bulletThread.start();
					}
					if(bulletThread.getState()==State.TERMINATED) {
						bulletThread = new BulletThread();
						bulletThread.flag=true;
						bulletThread.e=e;
						bulletThread.tank=tank;
						bulletThread.start();
					
					}
					break;

				default:
					break;
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
//			BulletThread bulletThread = new BulletThread();
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				bulletThread.e=e;
				bulletThread.tank=tank;
			}
		});
	}
	BulletThread bulletThread = new BulletThread();
	private Image offScreenImage;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGTH);
		}
			Graphics gOffScreen = offScreenImage.getGraphics();
			Color c = gOffScreen.getColor();
			gOffScreen.setColor(Color.white);
			gOffScreen.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGTH);
			gOffScreen.setColor(c);
			paint(gOffScreen);
			g.drawImage(offScreenImage, 0, 0, null);
		
	}
}
