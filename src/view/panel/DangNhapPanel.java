package view.panel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import model.ListDocGia;
import model.TaiKhoan;
import view.ActionClick;

import javax.swing.*;

public class DangNhapPanel extends BasePanel {
    public static final String BT_DANG_NHAP = "BT_DANG_NHAP";
    public static final String BT_DANG_KY = "BT_DANG_KY";
    private JLabel lbTaiKhoan;
    private JTextField jtTaiKhoan;
    private JLabel lbMatKhau;
    private JPasswordField jpMatKhau;
    private JButton btDangNhap;
    private JButton btDangKy;
    private JLabel lbHienThi;
    private int getNguoiDN;

    private List<TaiKhoan> listThuThu;
    private List<TaiKhoan> listBanDoc;

    @Override
    public void initUI() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.cyan);

    }

    @Override
    public void addComp() {
        // TODO Auto-generated method stub
        Font f2 = new Font("Tahoma", Font.PLAIN, 18);
        Font f3 = new Font("Tahoma", Font.PLAIN, 30);
        // tạo combobox chọn người đăng nhập là ai
        JComboBox comBoBook = new JComboBox();
        comBoBook.setBackground(Color.LIGHT_GRAY);
        comBoBook.setLocation(350, 60);
        comBoBook.setFont(f2);
        comBoBook.setSize(100, 30);
        comBoBook.addItem("Thu Thu");
        comBoBook.addItem("Doc Gia");
        add(comBoBook);

        comBoBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getNguoiDN = comBoBook.getSelectedIndex();
            }
        });

        lbHienThi = createLabel("ĐĂNG NHẬP", 320, 20, f3, Color.RED, null);
        add(lbHienThi);
        //
        lbTaiKhoan = createLabel("Tài khoản :", 250, 100, f2, Color.BLACK, null);
        add(lbTaiKhoan);
        jtTaiKhoan = createTextField(350, 100, 200, f2, Color.BLACK);
        add(jtTaiKhoan);

        //
        lbMatKhau = createLabel("Mật Khẩu :", 250, 150, f2, Color.BLACK, null);
        add(lbMatKhau);
        jpMatKhau = createPasswordField(350, 150, 200, f2, Color.BLACK);
        add(jpMatKhau);
        //
        btDangNhap = createButton("Đăng Nhập", 200, 200, f2, Color.GREEN, BT_DANG_NHAP);
        add(btDangNhap);
        btDangKy = createButton("Đăng Ký", 450, 200, f2, Color.GREEN, BT_DANG_KY);
        add(btDangKy);

    }

    @Override
    protected void handleClick(String name) {
        if (name.equals("BT_DANG_KY")) {
            acc.dangNhapToDangKy();
        } else if (name.equals("BT_DANG_NHAP")) {
            if (jtTaiKhoan.getText().equals("") || jpMatKhau.getText().equals("")) {
                JOptionPane.showConfirmDialog(null, "vui lòng nhâp đủ thông tin ", "Error", JOptionPane.CLOSED_OPTION);
            } else

            { // tạo list thu thư
                listThuThu = new ArrayList<>();
                File fileThuThu = new File(System.getProperty("user.dir") + "\\listTkThuThu.txt");
                try {
                    RandomAccessFile raf = new RandomAccessFile(fileThuThu, "r");
                    String line = "";
                    while ((line = raf.readLine()) != null) {
                        String[] item = line.split("-");
                        listThuThu.add(new TaiKhoan(item[0], item[1]));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // tạo list doc gia
                listBanDoc = new ArrayList<>();
                File file = new File(System.getProperty("user.dir") + "\\listDocGia.txt");
                try {
                    RandomAccessFile raf = new RandomAccessFile(file, "rw");
                    String data = "";
                    while (raf.getFilePointer() < raf.length()) {
                        data += raf.readLine() + "\n";
                    }
                    raf.close();
                    if (data.equals("")) {
                        return;
                    }
                    String[] arrDocGia = data.split("\n");
                    for (String docGia : arrDocGia) {
                        String[] info = docGia.split("-");
                        listBanDoc.add(new TaiKhoan(info[3], info[4]));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (getNguoiDN == 0) {
                    if (checkValideThuThu(jtTaiKhoan.getText(), jpMatKhau.getText())) {
                        JOptionPane.showConfirmDialog(null, "Ban da dang nhap thanh cong", "thanh cong",
                                JOptionPane.CLOSED_OPTION);
                        acc.dangnhapThuThu();
                    } else {
                        JOptionPane.showConfirmDialog(null,
                                "Đăng nhập không thành công\nVui lòng kiểm tra lại tài khoản của bạn", "thanh cong",
                                JOptionPane.CLOSED_OPTION);
                    }
                } else if (getNguoiDN == 1) {

                    if (checkValideBanDoc(jtTaiKhoan.getText(), jpMatKhau.getText())) {
                        JOptionPane.showConfirmDialog(null, "Đăng nhập thành công", "thành công",
                                JOptionPane.CLOSED_OPTION);
                        acc.dangNhapDocGia();
                    } else {
                        JOptionPane.showConfirmDialog(null,
                                "\t\tĐăng nhập bị lỗi\n Vui lòng kiểm tra tài khoản của bạn", "thành công",
                                JOptionPane.CLOSED_OPTION);
                    }

                }
            }

        }

    }

    private boolean checkValideThuThu(String text, String text1) {
        for (int i = 0; i < listThuThu.size(); i++) {
            if (text.equals(listThuThu.get(i).getTK()) && text1.equals(listThuThu.get(i).getMK())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkValideBanDoc(String text, String text1) {
        for (int i = 0; i < listBanDoc.size(); i++) {
            if (text.equals(listBanDoc.get(i).getTK()) && text1.equals(listBanDoc.get(i).getMK())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEvent() {

    }

    ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }

}
