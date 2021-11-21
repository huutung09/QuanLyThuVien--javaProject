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

    void hienDsMuon(List<Sach> gMuon, String PMid);

    void dsMuonComeBack();

    void docGiaTimMuon(String PMid);

    void timMuonDocGia();

    void docGiaTra(String PMid);

    void traDocGia();

    void thuThuQuanLySach();

    void thuThuQuanLyDocGia();

    void thuThuQuanLyMuonTra(String id);

    void backToThuThuMenu();

    void backToQuanLyDocGia();
}
