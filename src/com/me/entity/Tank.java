package com.me.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.me.utils.Constant;

public class Tank {
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean rigrt) {
		this.right = rigrt;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	private Image tank;
	private double x;
	private double y;
	private boolean up,down,left,right;
	public Tank(Image tank, double x, double y) {
		super();
		this.tank = tank;
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g) {
		if(Constant.state) {
			x *=Constant.xChange;
			y *=Constant.yChange;
		}
		if(up) {
			y -= 10;
		}
		if(down) {
			y += 10;
		}
		if(right) {
			x += 10;
		}
		if(left) {
			x -= 10;
		}
		g.drawImage(tank, (int)x, (int)y, 50, 50, null);
	}
}
