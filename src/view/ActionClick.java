package view;

import java.util.List;

import model.Sach;

import model.DocGia;

public interface ActionClick {

    void dangKyToDangNhap();

    void dangNhapToDangKy();

    void dangNhapDocGia(String PMid);

    void dangnhapThuThu();

    void dangXuatDocGia();

    void dangXuatThuThu();

    void addListDocGia(DocGia docGia);

    List<DocGia> getListDocGia();

    void hienDsMuon(List<Sach> gMuon);

    void dsMuonComeBack();

    void docGiaTimMuon();

    void timMuonDocGia();

    void docGiaTra();

    void traDocGia();

    void thuThuQuanLySach();

    void thuThuQuanLyDocGia();

    void thuThuQuanLyMuonTra(String id);

    void backToThuThuMenu();

    void backToQuanLyDocGia();
}
