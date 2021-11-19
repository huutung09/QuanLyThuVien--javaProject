package view;

import model.DocGia;
import java.util.List;

public interface ActionClick {

    void dangKyToDangNhap();
    void dangNhapToDangKy();
    void dangXuatDocGia();
    void dangXuatThuThu();
    void addListDocGia(DocGia docGia);
    List<DocGia> getListDocGia() ;

}
