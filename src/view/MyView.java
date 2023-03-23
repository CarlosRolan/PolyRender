package view;

import java.awt.Dimension;
import javax.swing.JFrame;
import myInputs.MyKeyBoard;
import myInputs.MyMouse;
import motorGraphic.Parameters;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Carlos Rolán Díaz
 */
public class MyView extends JFrame implements Runnable {

	// CONST
	private static final long serialVersionUID = 1L;

	// INPUTS
	private MyMouse myMouse;
	private MyKeyBoard myKeyboord;
	// CONTAINER
	private double fps;
	private String framesPerSecnd;
	private Thread thread;
	public MyScene scene;

	// Static
	private static boolean running = false;

	// Getters
	public double getFps() {
		return this.fps;
	}
	public String getFramesPerSecond() {
		return framesPerSecnd;
	}
	public boolean isRunning() {
		return (running);
	}
	// Setters
	public void setFps(double fps) {
		this.fps = fps;
	}
	public void setFramesPerSecond(String framesPerSecond) {
		this.framesPerSecnd = framesPerSecond;
	}

	// Constructos
	public MyView() {
		running = false;
		this.fps = Parameters.DEFAULT_FPS;
		this.framesPerSecnd = String.valueOf(this.fps);
		this.thread = new Thread(this);
		
		JPanel ControlsPanel = new JPanel();
		getContentPane().add(ControlsPanel, BorderLayout.NORTH);
		
		JLabel lb_x = new JLabel("X");
		ControlsPanel.add(lb_x);
		
		JLabel lb_y = new JLabel("Y");
		ControlsPanel.add(lb_y);
		
		JLabel lb_z = new JLabel("Z");
		ControlsPanel.add(lb_z);
		
		JButton btnRotate = new JButton("Start");
		btnRotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (running) {
					try {
						thread.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btnRotate.setText("START");
				} else {
					thread.resume();
					btnRotate.setText("STOP");
				}
			}
		});
		ControlsPanel.add(btnRotate);

		initEvents();
		initWindow(MyViewParams.DEFAULT_WIDTH, MyViewParams.DEFAULT_HEIGHT);
	}


	// INIT METHODS
	private void initWindow(int width, int height) {
		Dimension size = new Dimension(width, height);
		this.setPreferredSize(size);
	}
	private void initEvents() {
		this.myMouse = new MyMouse();
		this.addMouseListener(myMouse);
		this.addMouseMotionListener(myMouse);
		this.addMouseWheelListener(myMouse);
		this.myKeyboord = new MyKeyBoard();
		this.addKeyListener(myKeyboord);
	}

	// PUBLIC METHODS
	public synchronized void start() {
		this.scene = new MyScene();

		setTitle(this.getFramesPerSecond());
		getContentPane().add(this.scene);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		running = true;
		try {
			this.thread.start();
		} catch (IllegalThreadStateException e) {
			e.printStackTrace();
		}
	}
	public synchronized void stop() {
		try {
			this.thread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// START BUFFER
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / this.fps;
		// % of progress to get to the next update
		double delta = 0;
		Integer frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				scene.update();
				// Time between renders
				delta--;
				if (running) {
					scene.render();
				}
				// Counts de nº of frames in a delta time
				frames++;

				// 1 nanosec is 1000 milisec
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					setTitle(frames.toString());
					// setFramesPerSecond(frames.toString());¡
					frames = 0;
				}
			}
		}
		stop();
	}
}
