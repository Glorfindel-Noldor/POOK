import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Main extends JPanel implements ActionListener, KeyListener {
    private int x = 400; // Player's x position
    private int y = 200; // Player's y position
    private final int speed = 45; // Movement speed

    private int bulletX; // Bullet's x position
    private int bulletY; // Bullet's y position
    private final int bulletWidth = 5;  // Bullet width
    private final int bulletHeight = 10; // Bullet height
    private boolean bulletFired = false; // Tracks if a bullet is currently fired

    public Main() {
        Timer var_timer = new Timer(16, this); // ~60 FPS
        var_timer.start(); // Start the timer

        // Enable key listening
        setFocusable(true);
        addKeyListener(this); // Register this class as the KeyListener
    }

    @Override
    protected void paintComponent(Graphics _brush) {
        super.paintComponent(_brush); // Clear the screen
        Graphics2D playerShape = (Graphics2D) _brush;
        Graphics2D bulletShape = (Graphics2D) _brush;

        // Draw the player rectangle
        playerShape.setColor(Color.BLUE); // Player color
        playerShape.fillRect(x, y, 25, 25);

        // Draw the bullet only if it has been fired
        if (bulletFired) {
            bulletShape.setColor(Color.RED); // Bullet color
            bulletShape.fillRect(bulletX, bulletY, bulletWidth, bulletHeight); // Bullet shape
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update bullet movement if it is fired
        if (bulletFired) {
            bulletY -= 5; // Move bullet upward

            // Reset bullet if it goes off-screen
            if (bulletY + bulletHeight < 0) {
                bulletFired = false; // Stop rendering the bullet
            }
        }

        // Prevent the player rectangle from going out of bounds
        if (x < 0) x = 0;
        if (x + 25 > getWidth()) x = getWidth() - 25;
        if (y < 0) y = 0;
        if (y + 25 > getHeight()) y = getHeight() - 25;

        repaint(); // Request a redraw to reflect changes
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); // Get the key code

        // Move the player rectangle
        if (key == KeyEvent.VK_UP) {
            y -= speed; // Move up
        } else if (key == KeyEvent.VK_DOWN) {
            y += speed; // Move down
        } else if (key == KeyEvent.VK_LEFT) {
            x -= speed; // Move left
        } else if (key == KeyEvent.VK_RIGHT) {
            x += speed; // Move right
        }
        // Fire the bullet when the space bar is pressed
        else if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        repaint(); // Redraw the player rectangle
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used but required to implement KeyListener
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but required to implement KeyListener
    }

    // Method to fire the bullet
    private void fire() {
        if (!bulletFired) { // Only fire a bullet if one isn't already fired
            bulletFired = true;
            bulletX = x + 10; // Center the bullet relative to the player
            bulletY = y;      // Start the bullet at the player's position
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Manually Firing Bullet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new Main());
        frame.setVisible(true);
    }
}

