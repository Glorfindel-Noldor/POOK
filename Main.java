import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Main extends JPanel implements ActionListener, KeyListener { 
    // override  is needed because we are borrowing from JPanel(abstract?) ActionListener(interface?) KeyListener(interface?)
    private int x = 400; // Rectangle's x position
    private int y = 200; // Rectangle's y position
    private final int speed = 45; // Movement speed

    public Main() {
        Timer var_timer = new Timer(1, this); // ~60 FPS
        var_timer.start(); // Start the timer

        // Enable key listening
        setFocusable(true);
        addKeyListener(this);// we are using 'this' referring to Main's state and Main itself ?
    }

    @Override
    protected void paintComponent(Graphics _brush) {
        super.paintComponent(_brush); // Clear the screen
        Graphics2D theShape = (Graphics2D) _brush;
        // Draw the moving rectangle
        theShape.fillRect(x, y, 10, 10);
        


        
    }



    
    @Override
    public void actionPerformed(ActionEvent e) {
    // boundaries 


        if (x < 0) x = 0; // Prevent going left off-screen
        if (x + 25 > getWidth()) x = getWidth() - 25; // Prevent going right off-screen
        if (y < 0) y = 0; // Prevent going up off-screen
        if (y + 50 > getHeight()) y = getHeight() - 50; // Prevent going down off-screen
        repaint(); // Request a redraw to reflect changes
    }

    
    
    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); // Get the key code

        // Update position based on arrow keys
        if (key == KeyEvent.VK_UP) {
            y -= speed; // Move up
        } else if (key == KeyEvent.VK_DOWN) {
            y += speed; // Move down
        } else if (key == KeyEvent.VK_LEFT) {
            x -= speed; // Move left
        } else if (key == KeyEvent.VK_RIGHT) {
            x += speed; // Move right
        }
        else if (key == KeyEvent.VK_SPACE){
            
        }






        
        repaint(); // Redraw to reflect changes
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used but why do we have to have this here 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but why do we have to have this here 
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Rectangle with Keystrokes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new Main());
        frame.setVisible(true);
        
    }
}

