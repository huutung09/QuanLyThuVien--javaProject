package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuon {
    private List<String> dsIdSachMuon;
    private String ngayMuon, phieuId, ngayTra;
    private long late;
    private boolean daTra;

    public PhieuMuon(String phieuId, List<String> dsIdSachMuon, String ngayMuon, String ngayTra) {
        this.dsIdSachMuon = dsIdSachMuon;
        this.phieuId = phieuId;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.late = caculateDay(ngayTra);
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

    public long getLate() {
        return late;
    }

    public void setLate(long late) {
        this.late = late;
    }

    public List<String> getDsIdSachMuon() {
        return dsIdSachMuon;
    }

    public void setDsIdSachMuon(List<String> dsIdSachMuon) {
        this.dsIdSachMuon = dsIdSachMuon;
    }

    public long caculateDay(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate dateCal = LocalDate.parse(date, dtf);
        long daysBetween = Duration.between(dateCal.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();
        if (daysBetween <= 0) {
            return 0;
        }
        return daysBetween;
    }


}
