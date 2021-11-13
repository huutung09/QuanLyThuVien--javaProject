package view.panel;

import view.ActionClick;

import java.awt.*;

public class MainPanel extends BasePanel implements ActionClick {
    private DangKyPanel dangKy;
    private DangNhapPanel dangNhap;

    public MainPanel() {

    }

    @Override
    public void initUI() {
        setBackground(Color.WHITE);
        setLayout(new CardLayout());
    }

    @Override
    public void addEvent() {

    }

    @Override
    public void addComp() {
        dangKy = new DangKyPanel();
        dangKy.setAcc(this);
        add(dangKy);
        dangNhap = new DangNhapPanel();
        dangNhap.setAcc(this);
        add(dangNhap);

    }

    @Override
    public void dangKyToDangNhap() {
        // TODO Auto-generated method stub
        dangKy.setVisible(false);
        dangNhap.setVisible(true);

    }

    @Override
    public void dangNhapToDangKy() {
        // TODO Auto-generated method stub
        dangNhap.setVisible(false);
        dangKy.setVisible(true);
    }
}
