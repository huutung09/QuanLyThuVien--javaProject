package model;

public class DocGia {
    private String docGiaId, hoTen, sdt, taiKhoan, matKhau, gioiTinh, phieuMuonId;

    public DocGia(String docGiaId, String hoTen, String sdt, String taiKhoan, String matKhau, String gioiTinh,
            String phieuMuonId) {
        this.docGiaId = docGiaId;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.phieuMuonId = phieuMuonId;
    }



    public String getDocGiaId() {
        return docGiaId;
    }

    public void setDocGiaId(String docGiaId) {
        this.docGiaId = docGiaId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getPhieuMuonId() {
        return phieuMuonId;
    }

    // public void setPhieuMuonId(String phieuMuonId) {
    //     this.phieuMuonId = phieuMuonId;
    // }
        // nam viet them :

    public String getInfos(){
            return this.getDocGiaId()+"-"+this.getHoTen()+"-"+this.getSdt()+"-"+this.getTaiKhoan()+"-"+this.getMatKhau()+"-" +this.getGioiTinh()+"-"+this.getPhieuMuonId()+"\n";
    }
    public String getInfos(){
        return this.getDocGiaId()+"-"+this.getHoTen()+"-"+this.getSdt()+"-"+this.getTaiKhoan()+"-"+this.getMatKhau()+"-" +this.getGioiTinh()+"-"+this.getPhieuMuonId()+"\n";
}
    public boolean kiemTraTKorSDT(String TK,String SDT){
        if(this.getTaiKhoan().equals(TK)|| this.getSdt().equals(SDT)){
            return true ;
        }
        return false ;
    }
    public boolean kiemTraTKorSffDT(String TK,String SDT){
        if(this.getTaiKhoan().equals(TK)|| this.getSdt().equals(SDT)){
            return true ;
        }
        return false ;
    }

}
