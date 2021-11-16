package view.panel;

import model.ModelTable;
import model.Sach;
import view.ActionClick;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyMuonTra extends BasePanel implements ModelTable.Listener<Sach> {

    private static final String BT_BACK = "BT_BACK";
    private static final String BT_TRA = "BT_TRA";
    private static final String[] COLUMN_NAME = {"Mã sách", "Tên sách", "Số lương", "Tác giả"};
    private static final String path = System.getProperty("user.dir") + "\\listSach.txt";

    private JTextField tf_id, tf_ngay_tra, tf_ngay_muon;
    private JLabel lb_id, lb_ngay_tra, lb_ngay_muon, lb_da_tra;
    private JRadioButton rb_roi;
    private JRadioButton rb_chua;
    private JButton bt_back, bt_tra;
    private JTable tb_sach_muon;

    private ModelTable<Sach> modelTable;

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

        modelTable = new ModelTable<>(new ArrayList<>(), COLUMN_NAME);
        modelTable.setListener(this);

        Font f1 = new Font("Tahoma",Font.BOLD,20);
        Font f2 = new Font("Tahoma",Font.PLAIN,25);
        Font f3 = new Font("table_text", Font.PLAIN, 15);
        Font f4 = new Font("table_text", Font.BOLD, 18);
        Font f5 = new Font("comboBox_text", Font.PLAIN, 18);

        lb_id = createLabel("Mã phiếu mượn:", 50, 80, f2, Color.BLACK, null);
        add(lb_id);
        tf_id = createTextField(240, lb_id.getY(), 180, f5, Color.BLACK);
        add(tf_id);

        lb_ngay_tra = createLabel("Ngày trả:", 50, 150, f2, Color.BLACK, null);
        add(lb_ngay_tra);
        tf_ngay_tra = createTextField(240, lb_ngay_tra.getY(), 180, f5, Color.BLACK);
        add(tf_ngay_tra);

        lb_ngay_muon = createLabel("Ngày mượn:", 480, 80, f2, Color.BLACK, null);
        add(lb_ngay_muon);
        tf_ngay_muon = createTextField(630, lb_ngay_muon.getY(), 180, f5, Color.BLACK);
        add(tf_ngay_muon);

        lb_da_tra = createLabel("Đã Trả:", 480, 150, f2, Color.BLACK, null);
        add(lb_da_tra);

        rb_roi  =new JRadioButton("Rồi");
        rb_roi.setBounds(600,lb_da_tra.getY(), 105,32);
        rb_roi.setFont(f5);
        rb_roi.setBackground(null);
        rb_chua = new JRadioButton("Chưa");
        rb_chua.setBounds(700,lb_da_tra.getY()-4, 105,40);
        rb_chua.setFont(f5);
        rb_chua.setBackground(null);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb_roi);
        bg.add(rb_chua);
        add(rb_roi);
        add(rb_chua);

        bt_back = createButton("Back", 40, 20, f2, Color.BLACK, BT_BACK);
        add(bt_back);
        bt_tra = createButton("Tra sach", 734, 680, f2, Color.BLACK, BT_TRA);
        add(bt_tra);

        tb_sach_muon = new JTable(modelTable);
        tb_sach_muon.setFont(f3);
        tb_sach_muon.setRowHeight(30);
        tb_sach_muon.getTableHeader().setFont(f1);
        JScrollPane scr = new JScrollPane(tb_sach_muon);
        scr.setLocation(25, tf_ngay_tra.getY()+tf_ngay_tra.getHeight()+50);
        scr.setSize(835, 420);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("Danh sách sách mà đọc giả mượn");
        tborder.setTitleFont(f1);
        tborder.setTitleColor(Color.RED);
        scr.setBorder(tborder);
        add(scr);

    }

    @Override
    protected void handleClick(String name) {
        super.handleClick(name);
    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
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
                return s.getSoLuong();
            case 3:
                return s.getTacGia();
            default:
                return null;
        }
    }
}
