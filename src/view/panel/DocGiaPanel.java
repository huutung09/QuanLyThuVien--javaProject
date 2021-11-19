package view.panel;

import view.ActionClick;

import javax.swing.*;
import java.awt.*;

public class DocGiaPanel extends BasePanel {

    public static final String BT_TO_TIM_MUON = "BT_TO_TIM_MUON";
    public static final String BT_TO_TRA = "BT_TO_TRA";
    public static final String BT_DANG_XUAT = "BT_DANG_XUAT";
    private JButton btToTimMuon, btToTra, btDangXuat;
    private JLabel lbChaoMung;

    @Override
    public void initUI() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.CYAN);
    }

    @Override
    public void addEvent() {

    }

    @Override
    public void addComp() {
        Font f1 = new Font("Tahoma", Font.BOLD, 40);
        Font f2 = new Font("Tahoma", Font.PLAIN, 25);

        lbChaoMung = createLabel("Chào mừng đến với thư viện", 120, 80, f1, Color.BLACK, null);
        add(lbChaoMung);

        btToTimMuon = createButton("Tìm Kiếm sách", 300, lbChaoMung.getY() + lbChaoMung.getHeight() + 100, f2,
                Color.BLACK, BT_TO_TIM_MUON);
        btToTimMuon.setSize(200, 50);
        add(btToTimMuon);

        btToTra = createButton("Trả sách", 300, btToTimMuon.getY() + btToTimMuon.getHeight() + 50, f2, Color.BLACK,
                BT_TO_TRA);
        btToTra.setSize(200, 50);
        add(btToTra);

        btDangXuat = createButton("Đăng xuất", 300, btToTra.getY() + btToTra.getHeight() + 50, f2, Color.BLACK,
                BT_DANG_XUAT);
        btDangXuat.setSize(200, 50);
        add(btDangXuat);

    }

    @Override
    protected void handleClick(String name) {
        switch (name) {
        case BT_TO_TIM_MUON:
            acc.docGiaTimMuon();
            break;
        case BT_TO_TRA:
            acc.docGiaTra();
            break;
        case BT_DANG_XUAT:
            acc.dangXuatDocGia();
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