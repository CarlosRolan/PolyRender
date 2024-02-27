package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import shapes.MyCube;
import shapes.MyPolyhedro;
import shapes.Point3D;
import shapes.Poly3D;

public class MyScene extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int startX;
	private int startY;

	// OBJECTS TO RENDER
	protected MyCube cube;

	// XYZ AXIS
	private final Poly3D xAxis = new Poly3D(Color.RED,
			new Point3D(MyViewParams.DEFAULT_WIDTH, 0, 0), new Point3D(0, 0, 0),
			new Point3D(-MyViewParams.DEFAULT_WIDTH, 0, 0));
	private final Poly3D yAxis = new Poly3D(Color.GREEN,
			new Point3D(0, MyViewParams.DEFAULT_WIDTH, 0), new Point3D(0, 0, 0),
			new Point3D(0, -MyViewParams.DEFAULT_WIDTH, 0));
	private final Poly3D zAxis = new Poly3D(Color.BLUE,
			new Point3D(0, 0, MyViewParams.DEFAULT_HEIGHT),
			new Point3D(0, 0, 0),
			new Point3D(0, 0, -MyViewParams.DEFAULT_WIDTH));

	private final MyPolyhedro axis = new MyPolyhedro(xAxis, yAxis, zAxis);

	public MyScene() {
		this.startX = MyViewParams.DEFAULT_START_X;
		this.startY = MyViewParams.DEFAULT_START_Y;
		initSceneComponents();
	}

	// GETTERS
	public int getStartX() {
		return this.startX;
	}
	public int getStartY() {
		return this.startY;
	}

	// SETTERS
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}

	private void initSceneComponents() {
		// this.axis.rotate(0, 45, 45);
		// Cube lenght = width = depth
		// REMEBER TO RENDER THE OBJECTS
		this.cube = new MyCube(100);
	}

	// RENNDER OUR CANVAS-ALL THINGS U WANNA SEE HAS TO BE RENDER HERE
	void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		// CANVAS PROPERTIES
		Graphics g = bs.getDrawGraphics();

		// BACKGROUND=====
		g.fillRect(this.startX, this.startY, MyViewParams.DEFAULT_WIDTH,
				MyViewParams.DEFAULT_HEIGHT);
		// ===============

		// TODO SHIT HAPPENING
		//this.axis.renderLines(g);

		//this.cube.renderFaces(g);

		g.dispose();
		bs.show();
	}
	
	// EACH FRAME
	void update() {
		this.cube.rotate(1, 1, 1);
	}

}
