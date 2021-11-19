package model;

public class TaiKhoan {
    private String TK,MK;

    public TaiKhoan(String TK,String MK) {

        this.TK = TK;
        this.MK=MK ;
    }

    public TaiKhoan() {
    }

    public String getTK() {
        return TK;
    }

    public void setTK(String TK) {
        this.TK = TK;
    }

    public String getMK() {
        return MK;
    }

    public void setMK(String MK) {
        this.MK = MK;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "TK='" + TK + '\'' +
                ", MK='" + MK + '\'' +
                '}';
    }
}
