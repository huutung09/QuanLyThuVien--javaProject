package view.panel;

import view.ActionClick;

import java.awt.*;

public class MainPanel extends BasePanel implements ActionClick {
    private DangKyPanel dangKy;
    private DangNhapPanel dangNhap;
    private DocGiaPanel docGia;
    private ThuThuPanel thuThu;

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

        docGia = new DocGiaPanel();
        docGia.setAcc(this);
        add(docGia);

        dangNhap = new DangNhapPanel();
        dangNhap.setAcc(this);
        add(dangNhap);
        dangKy = new DangKyPanel();
        dangKy.setAcc(this);
        add(dangKy);
        // docGia = new DocGiaPanel();
        // docGia.setAcc(this);
        // add(docGia);
        thuThu = new ThuThuPanel();
        thuThu.setAcc(this);
        add(thuThu);
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

    @Override
    public void dangXuatDocGia() {
        docGia.setVisible(false);
        dangNhap.setVisible(true);
    }

    @Override
    public void dangXuatThuThu() {
        thuThu.setVisible(false);
        dangNhap.setVisible(true);
    }
}
