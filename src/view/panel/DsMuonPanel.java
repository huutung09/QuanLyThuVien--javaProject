package view.panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.ModelTable;
import model.Sach;
import model.ModelTable.Listener;
import view.ActionClick;

public class DsMuonPanel extends BasePanel implements Listener<Sach> {
    private static final String BT_XAC_NHAN_MUON = "BT_XAC_NHAN_MUON";
    private static final String BT_GO_TO_TIM_MUON = "BT_GO_TO_TIM_MUON";
    private static final String BT_XOA = "BT_XOA";
    private static final String[] COLUMN_NAME = { "Mã sách", "Tên sách", "Tác giả", "Số lượng" };
    private JLabel lbDsMuon, lbNgayMuon, lbNgayTra;
    private JTextField tfNgayMuon, tfNgayTra;
    private JButton btXacNhanMuon, btGoToTimMuon, btXoa;
    private JTable muonTable;
    private List<Sach> gioMuon;
    private ModelTable<Sach> muonTableModel;

    @Override
    public void initUI() {
        setLayout(null);
        setVisible(true);
        setBackground(Color.cyan);

    }

    @Override
    public void addEvent() {

    }

    @Override
    public void addComp() {
        gioMuon = new ArrayList<>();
        muonTableModel = new ModelTable<Sach>(gioMuon, COLUMN_NAME);
        muonTableModel.setListener(this);

        Font f1 = new Font("Tahoma", Font.BOLD, 25);
        Font f2 = new Font("Tahoma", Font.PLAIN, 18);
        Font f3 = new Font("Tahoma", Font.PLAIN, 14);

        lbDsMuon = createLabel("Danh sách sách đã chọn để mượn", 200, 50, f1, Color.BLACK, null);
        add(lbDsMuon);

        btGoToTimMuon = createButton("", 0, 0, f1, Color.black, BT_GO_TO_TIM_MUON);
        btGoToTimMuon.setSize(50, 50);
        setImageFromAssertToButton("return.png", btGoToTimMuon, 30, 30);
        add(btGoToTimMuon);

        lbNgayMuon = createLabel("Ngày mượn: ", 100, lbDsMuon.getY() + lbDsMuon.getHeight() + 35, f2, Color.black,
                null);
        add(lbNgayMuon);
        tfNgayMuon = createTextField(230, lbNgayMuon.getY(), 450, f2, Color.BLACK);
        add(tfNgayMuon);
        lbNgayTra = createLabel("Ngày trả: ", 100, lbNgayMuon.getY() + lbNgayMuon.getHeight() + 20, f2, Color.black,
                null);
        add(lbNgayTra);
        tfNgayTra = createTextField(230, lbNgayTra.getY(), 450, f2, Color.BLACK);
        add(tfNgayTra);

        muonTable = new JTable(muonTableModel);
        muonTable.setFont(f2);
        muonTable.setRowHeight(50);
        muonTable.getTableHeader().setFont(f3);
        JScrollPane scr = new JScrollPane(muonTable);
        scr.setLocation(0, lbNgayTra.getY() + lbNgayTra.getHeight() + 30);
        scr.setSize(785, 300);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("DS sách muốn mượn");
        tborder.setTitleFont(f2);
        tborder.setTitleColor(Color.BLACK);
        scr.setBorder(tborder);
        add(scr);

        btXoa = createButton("Xóa sách khỏi danh sách", 300, scr.getHeight() + scr.getY() + 40, f2, Color.BLACK,
                BT_XOA);
        add(btXoa);

        btXacNhanMuon = createButton("Xác nhận mượn", btXoa.getX() + btXoa.getWidth() + 70, btXoa.getY(), f2,
                Color.BLACK, BT_XAC_NHAN_MUON);
        add(btXacNhanMuon);
    }

    @Override
    protected void handleClick(String name) {
        switch (name) {
        case BT_GO_TO_TIM_MUON:
            acc.dsMuonComeBack();
            break;
        case BT_XOA:
            xoaDsMuon();
            break;
        case BT_XAC_NHAN_MUON:
            xacNhanMuon();
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

    public void setGioMuon(List<Sach> gMuon) {
        gioMuon = gMuon;
        muonTableModel = new ModelTable<Sach>(gMuon, COLUMN_NAME);
        muonTableModel.setListener(this);
        muonTable.setModel(muonTableModel);
    }

    @Override
    public Object getTableValue(int rowIndex, int columnIndex, List<Sach> data) {
        Sach s = data.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return s.getSachId();
        case 1:
            return s.getTenSach();
        case 2:
            return s.getTacGia();
        case 3:
            return s.getSoLuong();
        default:
            return null;
        }
    }

    private void xoaDsMuon() {
        int row = muonTable.getSelectedRow();
        if (row != -1) {
            Sach sach = gioMuon.get(row);
            gioMuon.remove(sach);
            ((AbstractTableModel) muonTable.getModel()).fireTableDataChanged();
        }
    }

    private void xacNhanMuon() {
        String strNgayMuon = tfNgayMuon.getText();
        String strNgayTra = tfNgayTra.getText();
        if (strNgayMuon.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui long nhập ngày mượn!");
            return;
        }
        if (strNgayTra.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui long nhập ngày trả!");
            return;
        }
        tfNgayMuon.setText("");
        tfNgayTra.setText("");
        // get phieu id from user
        muonManage.addData("PM1", gioMuon, strNgayMuon, strNgayTra);
        giamSoLuong();
        gioMuon.clear();
        ((AbstractTableModel) muonTable.getModel()).fireTableDataChanged();
    }

    private void giamSoLuong() {
        for (Sach s : gioMuon) {
            s.setSoLuong(s.getSoLuong() - 1);
            for (Sach sach : sachManage.getListSach()) {
                if (s.getSachId().equals(sach.getSachId())) {
                    sach.setSoLuong(sach.getSoLuong() - 1);
                }
            }
        }
        sachManage.updateData();

    }

}
