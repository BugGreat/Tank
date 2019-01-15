package com.me.utils;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 * @author False
 * 常用的工具类（图片的加载）
 */
 
public class GameUtils {
	public static Image getImage(String path) {
		URL u = GameUtils.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public static Cursor getCur(String path) {
		
		return null;
	}
	private GameUtils() {//工具类构造方法通常私有
		// TODO Auto-generated constructor stub
	}
}
