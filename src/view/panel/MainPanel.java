package view.panel;

import view.ActionClick;

import java.awt.*;

public class MainPanel extends BasePanel implements ActionClick {
    private DangKyPanel dangKy;
    private DangNhapPanel dangNhap;
    private DocGiaPanel docGia;
    private ThuThuPanel thuThu;
    private TimMuonPanel timMuon;
    private TraPanel tra;
    private QuanLyDocGia quanLyDocGia;
    private QuanLySach quanLySach;
    private QuanLyMuonTra quanLyMuonTra;

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
        thuThu = new ThuThuPanel();
        thuThu.setAcc(this);
        add(thuThu);
        dangNhap = new DangNhapPanel();
        dangNhap.setAcc(this);
        add(dangNhap);
        dangKy = new DangKyPanel();
        dangKy.setAcc(this);
        add(dangKy);
        docGia = new DocGiaPanel();
        docGia.setAcc(this);
        add(docGia);
        timMuon = new TimMuonPanel();
        timMuon.setAcc(this);
        add(timMuon);
        tra = new TraPanel();
        tra.setAcc(this);
        add(tra);
        quanLySach = new QuanLySach();
        quanLySach.setAcc(this);
        add(quanLySach);
        quanLyDocGia = new QuanLyDocGia();
        quanLyDocGia.setAcc(this);
        add(quanLyDocGia);
        quanLyMuonTra = new QuanLyMuonTra();
        quanLyMuonTra.setAcc(this);
        add(quanLyMuonTra);

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

    @Override
    public void docGiaTimMuon() {
        // TODO Auto-generated method stub
        docGia.setVisible(false);
        timMuon.setVisible(true);
    }

    @Override
    public void docGiaTra() {
        // TODO Auto-generated method stub
        docGia.setVisible(false);
        tra.setVisible(true);
    }

    @Override
    public void thuThuQuanLySach() {
        // TODO Auto-generated method stub
        thuThu.setVisible(false);
        quanLySach.setVisible(true);
    }

    @Override
    public void thuThuQuanLyDocGia() {
        // TODO Auto-generated method stub
        thuThu.setVisible(false);
        quanLyDocGia.setVisible(true);
    }

    @Override
    public void thuThuQuanLyMuonTra() {
        // TODO Auto-generated method stub
        thuThu.setVisible(false);
        quanLyMuonTra.setVisible(true);
    }

    @Override
    public void backToThuThuMenu() {
        quanLyDocGia.setVisible(false);
        quanLySach.setVisible(false);
        thuThu.setVisible(true);
    }
}
