package view.panel;

import java.awt.*;

import model.DocGia;
import view.ActionClick;

import javax.swing.*;

public class DangKyPanel extends BasePanel {
    private JLabel lbHienThi ;
   private JLabel lbDocGiaID;
    private JLabel lbHoTen;
    private JLabel lbSDT;
    private JLabel lbTaiKhoan;
    private JLabel lbMatKhau;
    private JLabel lbGioiTinh;
    private JLabel lbPhieuMuonID;
    private JTextField  jtNguoiDungID, jtHoTen,jtSDT,jtTaiKHoan,jtPhieuMuonID;
    private JPasswordField jpMatKhau ;
    private JButton btDangKy;

    public static final String BT_DANG_KY = "BT_DANG_KY";
    private String getGioiTinh ;
   @Override
    public void initUI() {
        // TODO Auto-generated method stub
       setLayout(null);
       setVisible(true);
       setBackground(Color.cyan);





    }

    @Override
    public void addEvent() {
        // TODO Auto-generated method stub



    }

    @Override
    public void addComp() {
        // TODO Auto-generated method stub
        Font f2 = new Font("Tahoma", Font.PLAIN, 18);
        Font f3 = new Font("Tahoma", Font.PLAIN, 30);
        Font f4 = new Font("Tahoma", Font.PLAIN, 15);
        lbHienThi= createLabel("Đăng ký",350,20,null,Color.RED,f3);
        add(lbHienThi);
        lbDocGiaID= createLabel("ID_Độc Giả:",250,60,null,Color.BLACK,f2);
        add(lbDocGiaID);
        lbHoTen=createLabel("Họ tên:",250,90,null,Color.BLACK,f2);
        add(lbHoTen);
        lbSDT=createLabel("SĐT:",250,120,null,Color.BLACK,f2);
        add(lbSDT);
        lbTaiKhoan=createLabel("Tài khoản:",250,150,null,Color.BLACK,f2);
        add(lbTaiKhoan);
        lbMatKhau=createLabel("Mật khẩu:",250,180,null,Color.BLACK,f2);
        add(lbMatKhau);
        lbGioiTinh=createLabel("Giới Tính:",250,210,null,Color.BLACK,f2);
        add(lbGioiTinh);
        lbPhieuMuonID=createLabel("ID_phiếu mượn:",250,250,null,Color.BLACK,f2);
        add(lbPhieuMuonID);
        //

        jtNguoiDungID= createTextField(380,60,200,f4,Color.BLACK);
        add(jtNguoiDungID);
        jtHoTen= createTextField(380,90,200,f4,Color.BLACK);
        add(jtHoTen);
        jtSDT= createTextField(380,120,200,f4,Color.BLACK);
        add(jtSDT);
        jtTaiKHoan= createTextField(380,150,200,f4,Color.BLACK);
        add(jtTaiKHoan);
        jpMatKhau= createPasswordField(380,180,200,f4,Color.BLACK );
        add(jpMatKhau);

        //tạo combobook chọn giới tính
        JComboBox comBoBookGT=new JComboBox();
        comBoBookGT.setBackground(Color.LIGHT_GRAY);
        comBoBookGT.setLocation(380,210);
        comBoBookGT.setFont(f4);
        comBoBookGT.setSize(100,30);
        comBoBookGT.addItem("Nu");
        comBoBookGT.addItem("Nam");
        add(comBoBookGT);
        getGioiTinh=(String) comBoBookGT.getSelectedItem();
      //
        jtPhieuMuonID= createTextField(380,250,200,f4,Color.BLACK);
        add(jtPhieuMuonID);
        btDangKy=createButton("ĐĂNG KÝ",350,300,f2,Color.GREEN,BT_DANG_KY );
        add(btDangKy);
        //

   }






    @Override
    protected void handleClick(String name) {
        if (name.equals(BT_DANG_KY)) {
            if (jtNguoiDungID.getText().equals("") || jtHoTen.getText().equals("") || jtSDT.getText().equals("") || jtTaiKHoan.getText().equals("") || jpMatKhau.getPassword().equals("")
                    || jtPhieuMuonID.getText().equals("")) {
                JOptionPane.showConfirmDialog(null, "vui lòng nhâp đủ thông tin ", "Error", JOptionPane.CLOSED_OPTION);
            } else {
                if(checkAcc()){
                    DocGia docGia=new DocGia(jtNguoiDungID.getText(),jtHoTen.getText(),jtSDT.getText(),jtTaiKHoan.getText(),jpMatKhau.getText(),getGioiTinh,jtPhieuMuonID.getText());
                    acc.addListDocGia(docGia);
                    JOptionPane.showConfirmDialog(null,"TK bạn đọc đăng ký thành công","thư viện",JOptionPane.CLOSED_OPTION);
                    clearText();
                }
            }
        }
    }
    public boolean checkAcc(){
       for(int i =0 ; i<acc.getListDocGia().size();i++){
           if(acc.getListDocGia().get(i).kiemTraTKorSDT(jtTaiKHoan.getText(),jtSDT.getText())){
               JOptionPane.showConfirmDialog(null,"tài khoản hoặc SDT đã được đăng ký,\n bạn hãy kiểm tra lại","lỗi",JOptionPane.CLOSED_OPTION);
                return false ;
           }
       }
       return true ;
    }
    public void clearText() {
        jtNguoiDungID.setText("");
        jtHoTen.setText("");
        jtSDT.setText("");
        jtTaiKHoan.setText("");
        jpMatKhau.setText("");
        jtPhieuMuonID.setText("");
    }
      private ActionClick acc ;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }

}
