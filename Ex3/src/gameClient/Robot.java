package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.edge_data;

import java.util.*;
import java.awt.Color;
import java.awt.color.*;

public class Robot implements RobotInterface
{
	protected static int count_id=0;
	private int id;
	private double speed;
	private Color color;
	private double score;
	private edge_data edge;
	private Color arr[]= {Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.WHITE,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW};
	public Robot()
	{
		id=++count_id;
		speed=1;//default
		int i=(int)(Math.random()*arr.length);
//		System.out.println(i);
		color=arr[i];
		score=0;
		edge=null;
	}
	
	public String toString()
	{
		String res="";
		res+="'id' = "+id+"\n";
		res+="'color' = "+color+"\n";
		res+="'score' = "+"\n";
		res+="'edge' = "+edge.getSrc()+"-->"+edge.getDest()+"\n";
		res+="'speed' = "+speed+"\n\n";
		return res;
		
	}
	int main()
	{
		Robot r=new Robot();
		return 0;
	}
}
