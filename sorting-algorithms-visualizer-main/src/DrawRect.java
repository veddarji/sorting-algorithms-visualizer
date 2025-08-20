import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class DrawRect extends JPanel {
	
	public static final int HEIGHT = 900;
	public static final int WIDTH = 30;
	public static final Color RECT_COLOR = new Color(52, 152, 219);
	public static final Color RECT_BORDER = new Color(41, 128, 185);
	
	ArrayList<Integer> arr;
	
	public DrawRect(ArrayList<Integer> heights) {
		// Shallow Cloning
		arr = heights;
	}
	
	// Changing the instance variable so that paint component is called
	public void setArr(ArrayList<Integer> newArr) {
		arr = newArr;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);
		
		// Enable anti-aliasing for smoother rendering
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		graphics.setColor(RECT_COLOR);
		for (int i = 0, x = 10; x <= 1580; x += WIDTH + 4, i++) {
			int y = arr.get(i);
			// Draw filled rectangle with gradient effect
			graphics.fillRect(x, y, WIDTH, HEIGHT);
			// Draw border for better definition
			graphics.setColor(RECT_BORDER);
			graphics.drawRect(x, y, WIDTH, HEIGHT);
			graphics.setColor(RECT_COLOR);
		}
	}
} 
