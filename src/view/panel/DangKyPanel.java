package view.panel;

import java.awt.Color;

import view.ActionClick;

public class DangKyPanel extends BasePanel {

    @Override
    public void initUI() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.CYAN);

    }

    @Override
    public void addEvent() {

    }

    @Override
    public void addComp() {

    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }

}
