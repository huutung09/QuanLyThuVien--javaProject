package view;

import java.util.List;

import model.Sach;

public interface ActionClick {
    void dangKyToDangNhap();

    void dangNhapToDangKy();

    void dangXuatDocGia();

    void dangXuatThuThu();

    void hienDsMuon(List<Sach> gMuon);

    void dsMuonComeBack();

    void docGiaTimMuon();

    void timMuonDocGia();

    void docGiaTra();

    void traDocGia();

    void thuThuQuanLySach();

    void thuThuQuanLyDocGia();

    void thuThuQuanLyMuonTra();
}
