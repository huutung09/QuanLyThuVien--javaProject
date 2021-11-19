package view.panel;

import view.ActionClick;

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
import utils.CommonFunction;

public class TimMuonPanel extends BasePanel implements Listener<Sach> {

    private static final String BT_TIM_KIEM = "BT_TIM_KIEM";
    private static final String BT_THEM_MUON = "BT_THEM_MUON";
    private static final String BT_DS_MUON = "BT_DS_MUON";
    private static final String BT_GO_TO_DOC_GIA = "BT_GO_TO_DOC_GIA";
    private static final String BT_LAM_MOI = "BT_LAM_MOI";

    private JLabel lbChaoMung, lbTenSach, lbTacGia;
    private JTextField tfTenSach, tfTacGia;
    private JButton btTimKiem, btDsMuon, btThemMuon, btGoToDocGia, btLamMoi;
    private JTable sachTable;
    private ModelTable<Sach> modelTable;
    private List<Sach> gioMuon;

    private static final String[] COLUMN_NAME = { "Mã sách", "Tên sách", "Tác giả", "Số lượng" };

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
        gioMuon = new ArrayList<>();
        modelTable = new ModelTable<Sach>(sachManage.getListSach(), COLUMN_NAME);
        modelTable.setListener(this);

        Font f1 = new Font("Tahoma", Font.BOLD, 25);
        Font f2 = new Font("Tahoma", Font.PLAIN, 18);
        Font f3 = new Font("Tahoma", Font.PLAIN, 14);

        lbChaoMung = createLabel("Chào mừng đến với thư viện", 220, 30, f1, Color.BLACK, null);
        add(lbChaoMung);

        btGoToDocGia = createButton("", 0, 0, f1, Color.black, BT_GO_TO_DOC_GIA);
        btGoToDocGia.setSize(50, 50);
        setImageFromAssertToButton("return.png", btGoToDocGia, 30, 30);
        add(btGoToDocGia);

        lbTenSach = createLabel("Tên sách: ", 100, lbChaoMung.getY() + lbChaoMung.getHeight() + 35, f2, Color.black,
                null);
        add(lbTenSach);
        tfTenSach = createTextField(230, lbTenSach.getY(), 450, f2, Color.BLACK);
        add(tfTenSach);
        lbTacGia = createLabel("Tác giả: ", 100, lbTenSach.getY() + lbTenSach.getHeight() + 20, f2, Color.black, null);
        add(lbTacGia);
        tfTacGia = createTextField(230, lbTacGia.getY(), 450, f2, Color.BLACK);
        add(tfTacGia);
        btTimKiem = createButton("Tìm kiếm", 300, tfTacGia.getY() + tfTacGia.getHeight() + 20, f2, Color.BLACK,
                BT_TIM_KIEM);
        add(btTimKiem);

        btLamMoi = createButton("Làm mới", btTimKiem.getX() + btTimKiem.getWidth() + 30, btTimKiem.getY(), f2,
                Color.black, BT_LAM_MOI);
        add(btLamMoi);

        sachTable = new JTable(modelTable);
        sachTable.setFont(f3);
        sachTable.setRowHeight(50);
        sachTable.getTableHeader().setFont(f2);
        JScrollPane scr = new JScrollPane(sachTable);
        scr.setLocation(0, btTimKiem.getY() + btTimKiem.getHeight() + 20);
        scr.setSize(785, 300);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("DS sách");
        tborder.setTitleFont(f2);
        tborder.setTitleColor(Color.BLACK);
        scr.setBorder(tborder);
        add(scr);

        btThemMuon = createButton("Thêm vào danh sách mượn", 330, scr.getY() + scr.getHeight() + 20, f2, Color.BLACK,
                BT_THEM_MUON);
        add(btThemMuon);
        btDsMuon = createButton("Danh sách Mượn", btThemMuon.getWidth() + btThemMuon.getX() + 20, btThemMuon.getY(), f2,
                Color.BLACK, BT_DS_MUON);
        add(btDsMuon);
    }

    @Override
    protected void handleClick(String name) {
        switch (name) {
        case BT_TIM_KIEM:
            timKiem();
            break;
        case BT_THEM_MUON:
            themMuon();
            break;
        case BT_DS_MUON:
            acc.hienDsMuon(gioMuon);
            break;
        case BT_GO_TO_DOC_GIA:
            acc.timMuonDocGia();
            break;
        case BT_LAM_MOI:
            reload();
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

    private void timKiem() {
        String sachName = tfTenSach.getText();
        String tacGiaName = tfTacGia.getText();
        modelTable = new ModelTable<Sach>(
                CommonFunction.intersection(sachManage.searchSach(sachName), sachManage.searchSach(tacGiaName)),
                COLUMN_NAME);
        modelTable.setListener(this);
        sachTable.setModel(modelTable);
        tfTenSach.setText("");
        tfTacGia.setText("");
    }

    private void themMuon() {
        int row = sachTable.getSelectedRow();
        if (row != -1) {
            String value = sachTable.getModel().getValueAt(row, 0).toString();
            Sach sach = sachManage.searchSachById(value);
            if (sach.getSoLuong() == 0) {
                JOptionPane.showMessageDialog(this, "Sách này trong thư viện đã hết");
                return;
            }
            if (!gioMuon.contains(sach)) {
                gioMuon.add(sach);
                JOptionPane.showMessageDialog(this, sach.getTenSach() + " đã được thêm vào danh sách muốn mượn");
            }
        }

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

    public void reload() {
        sachManage.getData();
        modelTable = new ModelTable<Sach>(sachManage.getListSach(), COLUMN_NAME);
        modelTable.setListener(this);
        sachTable.setModel(modelTable);
        ((AbstractTableModel) sachTable.getModel()).fireTableDataChanged();

    }

}
