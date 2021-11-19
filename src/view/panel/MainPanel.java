package view.panel;

import model.DocGia;
import model.ListDocGia;
import view.ActionClick;
import java.io.IOException;
import java.awt.*;
import java.util.List;

import model.Sach;

public class MainPanel extends BasePanel implements ActionClick {
    private DangKyPanel dangKy;
    private DangNhapPanel dangNhap;
    private DocGiaPanel docGia;
    private ThuThuPanel thuThu;
    private TimMuonPanel timMuon;
    private TraPanel tra;
    private DsMuonPanel dsMuon;
    private QuanLyDocGia quanLyDocGia;
    private QuanLySach quanLySach;
    private QuanLyMuonTra quanLyMuonTra;
    private ListDocGia listDocGia;

    public MainPanel() {
        listDocGia = new ListDocGia();
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
        dsMuon = new DsMuonPanel();
        dsMuon.setAcc(this);
        add(dsMuon);

        thuThu = new ThuThuPanel();
        thuThu.setAcc(this);
        add(thuThu);
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
        dangKy.setVisible(false);
        dangNhap.setVisible(true);
    }

    @Override
    public void dangNhapToDangKy() {
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

    public void docGiaTimMuon() {
        docGia.setVisible(false);
        timMuon.setVisible(true);
    }

    @Override
    public void timMuonDocGia() {
        timMuon.setVisible(false);
        docGia.setVisible(true);

    }

    @Override
    public void hienDsMuon(List<Sach> gMuon) {
        timMuon.setVisible(false);
        dsMuon.setVisible(true);
        dsMuon.setGioMuon(gMuon);

    }

    @Override
    public void dsMuonComeBack() {
        dsMuon.setVisible(false);
        timMuon.setVisible(true);

    }

    @Override
    public void docGiaTra() {
        docGia.setVisible(false);
        tra.setVisible(true);
    }

    @Override
    public void traDocGia() {
        tra.setVisible(false);
        docGia.setVisible(true);
    }

    @Override
    public void thuThuQuanLySach() {
        thuThu.setVisible(false);
        quanLySach.setVisible(true);
    }

    @Override
    public void thuThuQuanLyDocGia() {
        thuThu.setVisible(false);
        quanLyDocGia.setVisible(true);
    }

    @Override
    public void thuThuQuanLyMuonTra(String id) {
        quanLyMuonTra.setIdPhieuMuon(id);
        quanLyDocGia.setVisible(false);
        quanLyMuonTra.setVisible(true);

    }

    @Override
    public void backToThuThuMenu() {
        quanLyDocGia.setVisible(false);
        quanLySach.setVisible(false);
        thuThu.setVisible(true);
    }

    @Override
    public void backToQuanLyDocGia() {
        quanLyMuonTra.setVisible(false);
        quanLyDocGia.setVisible(true);
    }

    @Override
    public void addListDocGia(DocGia docGia) {
        this.listDocGia.addDocGia(docGia);
        try {
            listDocGia.ghiDuLieuTuFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DocGia> getListDocGia() {
        return this.listDocGia.getDocgia();
    }

    @Override
    public void dangNhapDocGia() {
        dangNhap.setVisible(false);
        docGia.setVisible(true);
    }

    @Override
    public void dangnhapThuThu() {
        dangNhap.setVisible(false);
        thuThu.setVisible(true);

    }

}
