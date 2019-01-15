package com.me.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.me.utils.Constant;

public class Bullet {
	public double getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	public double getSpeedX() {
		return speedX;
	}
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	public double getSpeedY() {
		return speedY;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	private int x;
	private int y;
	private double speedX;
	private double speedY;
	private double speed;
	private double acceleration=10;
	
	public Bullet(int x, int y, double speedX, double speedY, double speed) {
		super();
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.speed = speed;
	}
	public void draw(Graphics g) {
		if(Constant.state) {
			x *=Constant.xChange;
			y *=Constant.yChange;
		}
		speed += acceleration;
		x += speed*speedX;
		y += speed*speedY;
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.drawOval(x, y, 15, 15);
		g.setColor(c);
	}
}
