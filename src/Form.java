import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Form extends JFrame {
    public static void main(String[] args) {
        Form form = new Form();
        form.setVisible(true);
    }

    private final int IMAGE_WIDTH = 500;
    private final int IMAGE_HEIGHT = 600;

    private final JPanel mainPanel = new JPanel();
    private final BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final Graphics2D graphics = image.createGraphics();

    private Form() {
        prepare();
    }

    private void prepare() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        final DrawPanel drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        drawPanel.setBackground(Color.CYAN);
        mainPanel.add(drawPanel);

        JButton b1 = new JButton("Draw");
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                draw();
                drawPanel.updateUI();
            }
        });
        mainPanel.add(b1);
        setContentPane(mainPanel);
        this.pack();
    }

    private void draw() {
        graphics.setColor(Color.BLUE);
        graphics.drawLine(0, 0, 40, 40);

        graphics.drawString("Hello", 40, 50);

        BufferedImage tux = null;
        try {
            tux = ImageIO.read(new File("tux.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.drawImage(tux, 0, 0, null);
    }

    private class DrawPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
    }
}
