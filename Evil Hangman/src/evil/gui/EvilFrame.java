package evil.gui;

import javax.swing.JFrame;

public class EvilFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public EvilFrame()
	{
		super();
		this.setTitle("EvilHangman");
		this.setAlwaysOnTop(false);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EvilFrame();

	}

}
