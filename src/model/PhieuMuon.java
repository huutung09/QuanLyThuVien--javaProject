package model;

import java.util.ArrayList;
import java.util.List;

public class PhieuMuon {
    private List<String> dsIdSachMuon;
    private String ngayMuon, phieuId, ngayTra;
    private int late;
    private boolean daTra;

    public PhieuMuon(String phieuId, String ngayMuon, String ngayTra) {
        this.dsIdSachMuon = new ArrayList<>();
        this.phieuId = phieuId;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        daTra = false;
    }

    public String getPhieuId() {
        return phieuId;
    }

    public void setPhieuId(String phieuId) {
        this.phieuId = phieuId;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public boolean isDaTra() {
        return daTra;
    }

    public void setDaTra(boolean daTra) {
        this.daTra = daTra;
    }

    public List<String> getDsMuon() {
        return dsIdSachMuon;
    }

    public void setDsMuon(List<String> dsMuon) {
        this.dsIdSachMuon = dsMuon;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

}
