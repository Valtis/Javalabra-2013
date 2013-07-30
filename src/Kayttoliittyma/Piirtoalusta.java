

package Kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Piirtoalusta extends JPanel {

    public Piirtoalusta() {
        super.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
        graphics.fillRect(50, 80, 100, 50);
    }
}

