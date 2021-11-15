package view.panel;

import java.awt.Color;

import view.ActionClick;

public class DsMuonPanel extends BasePanel {

    @Override
    public void initUI() {
        // TODO Auto-generated method stub
        setLayout(null);
        setVisible(true);
        setBackground(Color.CYAN);

    }

    @Override
    public void addEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addComp() {
        // TODO Auto-generated method stub

    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }

}
