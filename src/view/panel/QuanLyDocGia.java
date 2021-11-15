package view.panel;

import model.DocGia;
import model.DocGiaManage;
import model.Sach;
import view.ActionClick;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLyDocGia extends BasePanel{


    private static final String BT_SUA = "BT_SUA";
    private static final String BT_BAN = "BT_BAN";
    private static final String BT_TIM_KIEM = "BT_TIM_KIEM";
    private static final String BT_LAM_MOI = "BT_LAM_MOI";
    private static final String BT_BACK = "BT_BACK";
    private static final String[] COLUMN_NAME = {"Mã đọc giả", "Tên đọc giả", "Số điện thoại", "Tài khoản", "Gioi tinh", "Mã phiếu mượn"};
    private static final String path = System.getProperty("user.dir") + "\\listDocGia.txt";

    private DocGiaManage docGiaMg;

    private JTextField tf_id, tf_ten, tf_sdt, tf_tai_khoan, tf_gioi_tinh, tf_ma_phieu_muon ;
    private JLabel lb_id, lb_ten, lb_sdt, lb_tai_khoan, lb_gioi_tinh, lb_ma_phieu_muon;
    private JButton bt_sua, bt_tim_kiem, bt_lam_moi, bt_ban, bt_back;
    private JTable tb_doc_gia;


    public QuanLyDocGia(){
        super();
        docGiaMg = new DocGiaManage();
        docGiaMg.getData();
        initModelDocGia(tb_doc_gia, docGiaMg.getList());

    }

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

        Font f1 = new Font("Tahoma",Font.BOLD,20);
        Font f2 = new Font("Tahoma",Font.PLAIN,25);
        Font f3 = new Font("table_text", Font.PLAIN, 15);
        Font f4 = new Font("table_text", Font.BOLD, 18);

        lb_id = creatLabel("Mã đọc giả:", 50, 80, f2, Color.BLACK, null);
        add(lb_id);
        tf_id = createTextField(190, lb_id.getY(), 200, f2, Color.BLACK);
        add(tf_id);

        lb_ten = creatLabel("Tên đọc giả:", 50, 150, f2, Color.BLACK, null);
        add(lb_ten);
        tf_ten = createTextField(190, lb_ten.getY(), 200, f2, Color.BLACK);
        add(tf_ten);

        lb_sdt = creatLabel("Số điện thoại:", 450, 80, f2, Color.BLACK, null);
        add(lb_sdt);
        tf_sdt = createTextField(630, lb_sdt.getY(), 200, f2, Color.BLACK);
        add(tf_sdt);

        lb_tai_khoan = creatLabel("Tài khoản:", 450, 150, f2, Color.BLACK, null);
        add(lb_tai_khoan);
        tf_tai_khoan = createTextField(630, lb_tai_khoan.getY(), 200, f2, Color.BLACK);
        add(tf_tai_khoan);

        lb_gioi_tinh = creatLabel("Giới tính:", 50, 220, f2, Color.BLACK, null);
        add(lb_gioi_tinh);
        tf_gioi_tinh = createTextField(190, lb_gioi_tinh.getY(), 200, f2, Color.BLACK);
        add(tf_gioi_tinh);

        lb_ma_phieu_muon = creatLabel("Mã phiếu mượn:", 450, 220, f2, Color.BLACK, null);
        add(lb_ma_phieu_muon);
        tf_ma_phieu_muon = createTextField(630, lb_ma_phieu_muon.getY(), 200, f2, Color.BLACK);
        add(tf_ma_phieu_muon);

        bt_back = createButton("Back", 700, 20, f2, Color.BLACK, BT_BACK);
        add(bt_back);

        bt_tim_kiem = createButton("Tìm kiếm", 45, 290, f2, Color.BLACK, BT_TIM_KIEM);
        add(bt_tim_kiem);
        bt_sua = createButton("Sửa", 300, 290, f2, Color.BLACK, BT_SUA);
        add(bt_sua);
        bt_ban = createButton("Ban", 530, 290, f2, Color.BLACK, BT_BAN);
        add(bt_ban);
        bt_lam_moi = createButton("Làm mới", 705, 290, f2, Color.BLACK, BT_LAM_MOI);
        add(bt_lam_moi);

        tb_doc_gia = new JTable();
        tb_doc_gia.setFont(f3);
        tb_doc_gia.setRowHeight(30);
        tb_doc_gia.getTableHeader().setFont(f4);
        JScrollPane scr = new JScrollPane(tb_doc_gia);
        scr.setLocation(25, bt_lam_moi.getY()+bt_lam_moi.getHeight()+50);
        scr.setSize(835, 350);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("Danh sách đọc giả");
        tborder.setTitleFont(f1);
        tborder.setTitleColor(Color.RED);
        scr.setBorder(tborder);
        add(scr);

    }

    private void initModelDocGia(JTable tb_doc_gia, List<DocGia> list){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public int getColumnCount() {
                return COLUMN_NAME.length;
            }

            @Override
            public String getColumnName(int column) {
                return COLUMN_NAME[column];
            }

            @Override
            public int getRowCount() {
                return list.size();
            }

            @Override
            public Object getValueAt(int row, int column) {
                DocGia docGia = list.get(row);
                if(column == 0){
                    return docGia.getDocGiaId();
                }
                else if(column == 1){
                    return docGia.getHoTen();
                }
                else if(column == 2 ){
                    return docGia.getSdt();
                }
                else if(column == 3){
                    return docGia.getTaiKhoan();
                }
                else if(column == 4){
                    return docGia.getGioiTinh();
                }
                else{
                    return docGia.getPhieuMuonId();
                }
            }
        };
        tb_doc_gia.setModel(model);

    }

    @Override
    protected void handleClick(String name) {
        switch (name){
            case BT_TIM_KIEM:
                docGiaMg.searchData(tf_id.getText(), tf_ten.getText(), tf_sdt.getText(), tf_tai_khoan.getText(), tf_ma_phieu_muon.getText());
                if(docGiaMg.getListTimKiem().size() != 0){
                    initModelDocGia(tb_doc_gia, docGiaMg.getListTimKiem());
                }
                else{
                    JOptionPane.showMessageDialog(this, "Không thấy đọc giả cần tìm :)");
                }
                break;
            case BT_SUA:
                break;
            case BT_BAN:
                break;
            case BT_LAM_MOI:
                lamMoi();
                break;

        }

    }

    private void lamMoi(){
        tf_id.setText("");
        tf_ten.setText("");
        tf_sdt.setText("");
        tf_tai_khoan.setText("");
        tf_ma_phieu_muon.setText("");
        initModelDocGia(tb_doc_gia, docGiaMg.getList());
    }


    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }
}
