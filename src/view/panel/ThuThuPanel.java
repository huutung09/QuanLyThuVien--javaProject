package view.panel;

import view.ActionClick;

import javax.swing.*;
import java.awt.*;

public class ThuThuPanel extends BasePanel {

    public static final String BT_QUAN_LY_SACH = "BT_QUAN_LY_SACH";
    public static final String BT_QUAN_LY_DOC_GIA = "BT_QUAN_LY_DOC_GIA";
    public static final String BT_DANG_XUAT = "BT_DANG_XUAT";
    private JButton btQuanLySach, btQuanLyDocGia, btDangXuat;
    private JLabel lbChaoMung;

    @Override
    public void initUI() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.PINK);
    }

    @Override
    public void addEvent() {

    }

    @Override
    public void addComp() {
        Font f1 = new Font("Tahoma", Font.BOLD, 40);
        Font f2 = new Font("Tahoma", Font.PLAIN, 25);

        lbChaoMung = createLabel("Quản lý thư viện", 280, 80, f1, Color.BLACK, null);
        add(lbChaoMung);

        btQuanLySach = createButton("Quản lý sách", 297, lbChaoMung.getY() + lbChaoMung.getHeight() + 130, f2,
                Color.BLACK, BT_QUAN_LY_SACH);
        btQuanLySach.setSize(250, 50);
        setImageFromAssertToButton("books.png", btQuanLySach, 40, 40);
        add(btQuanLySach);

        btQuanLyDocGia = createButton("Quản lý độc giả", 310, btQuanLySach.getY() + btQuanLySach.getHeight() + 50, f2,
                Color.BLACK, BT_QUAN_LY_DOC_GIA);
        btQuanLyDocGia.setSize(250, 50);
        setImageFromAssertToButton("users.png", btQuanLyDocGia, 40, 40);
        add(btQuanLyDocGia);

        btDangXuat = createButton("Đăng xuất", 275, btQuanLyDocGia.getY() + btQuanLyDocGia.getHeight() + 50, f2,
                Color.BLACK, BT_DANG_XUAT);
        btDangXuat.setSize(260, 50);
        setImageFromAssertToButton("logout.png", btDangXuat, 40, 40);
        add(btDangXuat);
    }

    @Override
    protected void handleClick(String name) {
        // TODO Auto-generated method stub
        switch (name) {
        case BT_QUAN_LY_SACH:
            acc.thuThuQuanLySach();
            break;
        case BT_QUAN_LY_DOC_GIA:
            acc.thuThuQuanLyDocGia();
            break;
        case BT_DANG_XUAT:
            acc.dangXuatThuThu();
            break;
        }
    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }
}
