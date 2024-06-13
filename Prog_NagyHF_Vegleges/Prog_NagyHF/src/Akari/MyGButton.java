package Akari;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class MyGButton extends JButton {
	private static final long serialVersionUID = 1L;

	public MyGButton() {
        this.setBackground(Color.WHITE);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setFocusPainted(false);
        this.setEnabled(false);
        this.setPreferredSize(new Dimension(100,100));
    }
}
