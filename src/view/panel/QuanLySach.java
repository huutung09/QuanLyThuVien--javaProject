package view.panel;

import view.ActionClick;

import java.awt.*;

public class QuanLySach extends BasePanel{
    @Override
    public void initUI() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.BLACK);
    }

    @Override
    public void addEvent() {

    }

    @Override
    public void addComp() {

    }

    @Override
    protected void handleClick(String name) {
        super.handleClick(name);
    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }
}
