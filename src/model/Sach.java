package model;

public class Sach {
    private String sachId, tenSach, tacGia;
    int soLuong;

    public Sach(String sachId, String tenSach, String tacGia, int soLuong) {
        this.sachId = sachId;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.soLuong = soLuong;
    }

    public String getSachId() {
        return sachId;
    }

    public void setSachId(String sachId) {
        this.sachId = sachId;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

}
