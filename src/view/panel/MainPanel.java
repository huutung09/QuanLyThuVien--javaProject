package view.panel;

import model.DocGia;
import model.ListDocGia;
import view.ActionClick;
import java.io.IOException ;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends BasePanel implements ActionClick {
    private DangKyPanel dangKy;
    private DangNhapPanel dangNhap;
    private DocGiaPanel docGia;
    private ThuThuPanel thuThu;
    private ListDocGia listDocGia ;

    public MainPanel()  {
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
        thuThu = new ThuThuPanel();
        thuThu.setAcc(this);
        add(thuThu);
    }

    @Override
    public void dangKyToDangNhap() {
        // TODO Auto-generated method stub
        dangKy.setVisible(false);
        dangNhap.setVisible(true);
    }

    @Override
    public void dangNhapToDangKy() {
        // TODO Auto-generated method stub
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



    @Override
    public void addListDocGia(DocGia docGia) {
        this.listDocGia.addDocGia(docGia);
        try{
            listDocGia. ghiDuLieuTuFile();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<DocGia> getListDocGia() {
        return this.listDocGia.getDocgia();
    }


}
