package view.panel;

import view.ActionClick;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.ModelTable;
import model.PhieuMuon;
import model.Sach;
import model.ModelTable.Listener;

public class TraPanel extends BasePanel implements Listener<Sach> {
    private static final String BT_TRA = "BT_TRA";
    private static final String BT_BACK_TO_DOC_GIA = "BT_BACK_TO_DOC_GIA";
    private static final String[] COLUMN_NAME = { "Mã sách", "Tên sách", "Tác giả" };

    private JLabel lbTra;
    private JButton btTraSach, btBackToDocGia;
    private JTable traTable;
    private ModelTable<Sach> traModelTable;
    private List<Sach> sachMuon;
    private String PMid;

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

        Font f1 = new Font("Tahoma", Font.BOLD, 30);
        Font f2 = new Font("Tahoma", Font.PLAIN, 18);
        Font f3 = new Font("Tahoma", Font.PLAIN, 14);

        lbTra = createLabel("Trả sách", 350, 40, f1, Color.BLACK, null);
        add(lbTra);

        btBackToDocGia = createButton("", 0, 0, f1, Color.black, BT_BACK_TO_DOC_GIA);
        btBackToDocGia.setSize(50, 50);
        setImageFromAssertToButton("return.png", btBackToDocGia, 30, 30);
        add(btBackToDocGia);

        traTable = new JTable(traModelTable);
        traTable.setFont(f3);
        traTable.setRowHeight(50);
        traTable.getTableHeader().setFont(f2);
        JScrollPane scr = new JScrollPane(traTable);
        scr.setLocation(0, lbTra.getY() + lbTra.getHeight() + 80);
        scr.setSize(900, 300);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("DS sách");
        tborder.setTitleFont(f2);
        tborder.setTitleColor(Color.BLACK);
        scr.setBorder(tborder);
        add(scr);

        btTraSach = createButton("Trả sách", 500, scr.getY() + scr.getHeight() + 100, f2, Color.BLACK, BT_TRA);
        add(btTraSach);
    }

    @Override
    protected void handleClick(String name) {
        if (name.equals(BT_TRA)) {
            traSachAndXoaPhieu();
        } else if (name.equals(BT_BACK_TO_DOC_GIA)) {
            acc.traDocGia();
        }
    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }

    public void setTableModel(String pMid) {
        PMid = pMid;
        sachMuon = getSachFromPhieu(pMid);
        setModel();
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
        default:
            return null;
        }
    }

    private List<Sach> getSachFromPhieu(String PMid) {
        List<Sach> matching = new ArrayList<>();
        for (PhieuMuon phieu : muonManage.getListPhieuMuon()) {
            if (phieu.getPhieuId().equals(PMid)) {
                for (String id : phieu.getDsIdSachMuon()) {
                    matching.add(sachManage.searchSachById(id));
                }
            }
        }
        return matching;
    }

    private void setModel() {
        traModelTable = new ModelTable<Sach>(sachMuon, COLUMN_NAME);
        traModelTable.setListener(this);
        traTable.setModel(traModelTable);
    }

    private void traSachAndXoaPhieu() {
        for (Sach s : sachMuon) {
            for (Sach sach : sachManage.getListSach()) {
                if (s.getSachId().equals(sach.getSachId())) {
                    sach.setSoLuong(sach.getSoLuong() + 1);
                }
            }
        }
        for (PhieuMuon phieuMuon : muonManage.getListPhieuMuon()) {
            if (phieuMuon.getPhieuId().equals(PMid)) {
                muonManage.getListPhieuMuon().remove(phieuMuon);
                break;
            }
        }
        muonManage.updateData();
        sachManage.updateData();
        sachMuon.clear();
        ((AbstractTableModel) traTable.getModel()).fireTableDataChanged();
    }

}
