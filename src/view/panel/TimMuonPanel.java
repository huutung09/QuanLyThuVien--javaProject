package view.panel;

import view.ActionClick;

import java.awt.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.ModelTable;
import model.Sach;
import model.SachManage;
import model.ModelTable.Listener;

public class TimMuonPanel extends BasePanel implements Listener<Sach> {

    private static final String BT_TIM_KIEM = "BT_TIM_KIEM";
    private static final String BT_THEM_MUON = "BT_THEM_MUON";
    private static final String BT_DS_MUON = "BT_DS_MUON";

    private JLabel lbChaoMung, lbTenSach, lbTacGia;
    private JTextField tfTenSach, tfTacGia;
    private JButton btTimKiem, btDsMuon, btThemMuon;
    private JTable sachTable;
    private SachManage manage;
    private ModelTable<Sach> modelTable;

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
        manage = new SachManage();
        manage.getData();
        modelTable = new ModelTable<Sach>(manage.getListSach(), COLUMN_NAME);
        modelTable.setListener(this);

        Font f1 = new Font("Tahoma", Font.BOLD, 25);
        Font f2 = new Font("Tahoma", Font.PLAIN, 18);
        Font f3 = new Font("Tahoma", Font.PLAIN, 14);
        lbChaoMung = creatLabel("Chào mừng đến với thư viện", 230, 10, f1, Color.BLACK, Color.cyan);
        add(lbChaoMung);
        lbTenSach = creatLabel("Tên sách: ", 100, lbChaoMung.getY() + lbChaoMung.getHeight() + 35, f2, Color.black,
                Color.cyan);
        add(lbTenSach);
        tfTenSach = createTextField(230, lbTenSach.getY(), 450, f2, Color.BLACK);
        add(tfTenSach);
        lbTacGia = creatLabel("Tác giả: ", 100, lbTenSach.getY() + lbTenSach.getHeight() + 20, f2, Color.black,
                Color.cyan);
        add(lbTacGia);
        tfTacGia = createTextField(230, lbTacGia.getY(), 450, f2, Color.BLACK);
        add(tfTacGia);
        btTimKiem = createButton("Tìm kiếm", 350, tfTacGia.getY() + tfTacGia.getHeight() + 20, f2, Color.BLACK,
                BT_TIM_KIEM);
        add(btTimKiem);

        sachTable = new JTable(modelTable);
        sachTable.setFont(f3);
        sachTable.setRowHeight(50);
        sachTable.getTableHeader().setFont(f2);
        JScrollPane scr = new JScrollPane(sachTable);
        scr.setLocation(0, btTimKiem.getY() + btTimKiem.getHeight() + 20);
        scr.setSize(800, 300);
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
            break;
        case BT_DS_MUON:
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
        String searchTerm = tfTenSach.getText();
        modelTable = new ModelTable<Sach>(manage.searchSach(searchTerm), COLUMN_NAME);
        modelTable.setListener(this);
        sachTable.setModel(modelTable);

    }

    @Override
    public Object getTableValue(int rowIndex, int columnIndex, List<Sach> data) {
        // TODO Auto-generated method stub
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

}
