package view.panel;

import view.ICommon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BasePanel extends JPanel implements ICommon {

    public BasePanel() {
        initUI();
        addEvent();
        addComp();
    }

    protected JLabel creatLabel(String text, int x, int y, Font f, Color c, Color bg) {
        JLabel lb = new JLabel();
        lb.setText(text);
        lb.setLocation(x, y);
        lb.setBackground(bg);
        lb.setOpaque(true);
        lb.setForeground(c);
        lb.setFont(f);
        FontMetrics fontMetric = getFontMetrics(lb.getFont());
        int wTitle = fontMetric.stringWidth(lb.getText());
        int hTitle = fontMetric.getHeight();
        lb.setSize(wTitle, hTitle);
        return lb;
    }

    protected JTextField createTextField(int x, int y, int w, Font f, Color c) {
        JTextField tf = new JTextField();
        tf.setFont(f);
        tf.setLocation(x, y);
        FontMetrics fm = getFontMetrics(tf.getFont());
        int hTfA = fm.getHeight();
        tf.setSize(w, hTfA);
        tf.setForeground(c);
        return tf;
    }

    protected JButton createButton(String text, int x, int y, Font f, Color c, String name) {
        JButton bt = new JButton();
        bt.setText(text);
        bt.setFont(f);
        bt.setName(name);
        bt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JComponent comp = (JComponent) e.getSource();
                String name = comp.getName();
                handleClick(name);
            }
        });
        bt.setBackground(Color.LIGHT_GRAY);
        bt.setForeground(c);
        bt.setOpaque(true);
        bt.setLocation(x, y);
        FontMetrics fm = getFontMetrics(bt.getFont());
        int w = fm.stringWidth(bt.getText()) + bt.getInsets().left * 2;
        int h = fm.getHeight() + getInsets().top * 4;
        bt.setSize(w, h);

        return bt;
    }

    protected void handleClick(String name) {

    }
}
