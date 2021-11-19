package view.panel;

import model.*;
import view.ActionClick;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyMuonTra extends BasePanel implements ModelTable.Listener<Sach> {

    private static final String BT_BACK = "BT_BACK";
    private static final String BT_SUA = "BT_SUA";
    private static final String[] COLUMN_NAME = {"Mã sách", "Tên sách", "Số lương", "Tác giả"};

    private JLabel lb_id, lb_id_value, lb_ngay_tra, lb_ngay_muon, lb_ngay_muon_value, lb_da_tra, lb_late, lb_late_value;
    private JTextField tf_ngay_tra;
    private JRadioButton rb_roi;
    private JRadioButton rb_chua;
    private JButton bt_back, bt_sua;
    private JTable tb_sach_muon;

    private ModelTable<Sach> modelTable;
    private String idPhieuMuon;
    private List<Sach> list;
    private SachManage sachManage;
    private PhieuMuonManage phieuMuonManage;

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

        list = new ArrayList<>();
        sachManage = new SachManage();
        sachManage.getData();
        phieuMuonManage = new PhieuMuonManage();
        phieuMuonManage.getData();

        Font f1 = new Font("Tahoma",Font.BOLD,20);
        Font f2 = new Font("Tahoma",Font.PLAIN,25);
        Font f3 = new Font("table_text", Font.PLAIN, 15);
        Font f5 = new Font("comboBox_text", Font.PLAIN, 18);

        lb_id = createLabel("Mã phiếu mượn:", 50, 80, f2, Color.BLACK, null);
        add(lb_id);
        lb_id_value = new JLabel();
        createLabel(lb_id_value, f2, 240, lb_id.getY(), 150, 30);

        lb_ngay_tra = createLabel("Ngày trả:", 50, 150, f2, Color.BLACK, null);
        add(lb_ngay_tra);
        tf_ngay_tra = createTextField(170, lb_ngay_tra.getY(), 180, f5, Color.BLACK);
        add(tf_ngay_tra);

        lb_ngay_muon = createLabel("Ngày mượn:", 480, 80, f2, Color.BLACK, null);
        add(lb_ngay_muon);
        lb_ngay_muon_value = new JLabel();
        createLabel(lb_ngay_muon_value, f2, 630, lb_ngay_muon.getY(), 150, 30);

        lb_da_tra = createLabel("Đã Trả:", 480, 150, f2, Color.BLACK, null);
        add(lb_da_tra);

        lb_late = createLabel("Late:", 50, 250, f2, Color.BLACK, null);
        add(lb_late);
        lb_late_value = new JLabel();
        createLabel(lb_late_value, f2, 140, 250, 100, 30);

        bt_sua = createButton("Sửa",380, 210, f5, Color.BLACK, BT_SUA);
        add(bt_sua);

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

        bt_back = createButton("", 0, 0, f2, Color.BLACK, BT_BACK);
        bt_back.setSize(70, 70);
        setImageFromAssertToButton("return.png", bt_back, 30, 30);
        add(bt_back);

        tb_sach_muon = new JTable(modelTable);
        tb_sach_muon.setFont(f3);
        tb_sach_muon.setRowHeight(30);
        tb_sach_muon.getTableHeader().setFont(f1);
        JScrollPane scr = new JScrollPane(tb_sach_muon);
        scr.setLocation(25, tf_ngay_tra.getY()+tf_ngay_tra.getHeight()+200);
        scr.setSize(835, 350);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("Danh sách sách mà đọc giả mượn");
        tborder.setTitleFont(f1);
        tborder.setTitleColor(Color.RED);
        scr.setBorder(tborder);
        add(scr);

    }

    @Override
    protected void handleClick(String name) {
        switch (name){
            case BT_BACK:
                acc.backToQuanLyDocGia();
                lb_ngay_muon_value.setText("");
                lb_id_value.setText("");
                tf_ngay_tra.setText("");
                break;
            case BT_SUA:
                sua();
                break;
        }
    }

    private void sua() {
         for(PhieuMuon phieuMuon : phieuMuonManage.getListPhieuMuon()){
             if(phieuMuon.getPhieuId().equals(idPhieuMuon)){
                 phieuMuon.setNgayTra(tf_ngay_tra.getText());
                 phieuMuon.setLate(phieuMuon.caculateDay(tf_ngay_tra.getText()));
                 lb_late_value.setText(String.valueOf(phieuMuon.caculateDay(tf_ngay_tra.getText())));
                 break;
             }
         }
         phieuMuonManage.updateData();
    }

    private void getListSach(){
        list.clear();
        for(PhieuMuon phieuMuon : phieuMuonManage.getListPhieuMuon()){
            if(phieuMuon.getPhieuId().equals(idPhieuMuon)){
                lb_id_value.setText(idPhieuMuon);
                tf_ngay_tra.setText(phieuMuon.getNgayTra());
                lb_ngay_muon_value.setText(phieuMuon.getNgayMuon());
                lb_late_value.setText(String.valueOf(phieuMuon.getLate()));
                if(phieuMuon.getLate() == 0){
                    rb_roi.setSelected(true);
                }
                else{
                    rb_chua.setSelected(true);
                }
                for(String id : phieuMuon.getDsIdSachMuon()){
                    list.add(sachManage.searchSachById(id));
                }
            }
        }
    }

    public void setIdPhieuMuon(String id) {
        this.idPhieuMuon = id;
        getListSach();
        modelTable = new ModelTable<>(list, COLUMN_NAME);
        modelTable.setListener(this);
        tb_sach_muon.setModel(modelTable);

    }

    private void createLabel(JLabel label, Font font, int x, int y, int with, int height){
        label.setBounds(x, y, with, height);
        label.setFont(font);
        add(label);
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
