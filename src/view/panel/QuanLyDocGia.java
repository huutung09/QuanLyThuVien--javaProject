package view.panel;

import model.*;
import view.ActionClick;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class QuanLyDocGia extends BasePanel implements ModelTable.Listener<DocGia>, MouseListener {


    private static final String BT_SUA = "BT_SUA";
    private static final String BT_XEM_PHIEU_MUON = "BT_XEM_PHIEU_MUON";
    private static final String BT_TIM_KIEM = "BT_TIM_KIEM";
    private static final String BT_LAM_MOI = "BT_LAM_MOI";
    private static final String BT_BACK = "BT_BACK";
    private static final String[] COLUMN_NAME = {"Mã đọc giả", "Tên đọc giả", "Số điện thoại", "Tài khoản", "Gioi tinh", "Mã phiếu mượn"};
    private DocGiaManage docGiaMg;
    private ModelTable<DocGia> modelTable;

    private JTextField tf_id, tf_ten, tf_sdt, tf_tai_khoan, tf_ma_phieu_muon ;
    private JLabel lb_id, lb_ten, lb_sdt, lb_tai_khoan, lb_gioi_tinh, lb_ma_phieu_muon;
    private JButton bt_sua, bt_tim_kiem, bt_lam_moi, bt_xem_phieu_muon, bt_back;
    private JTable tb_doc_gia;
    private JRadioButton rb_nam;
    private JRadioButton rb_nu;
    public QuanLyDocGia(){
        super();
        tb_doc_gia.addMouseListener(this);
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

        docGiaMg = new DocGiaManage();
        docGiaMg.getData();
        modelTable = new ModelTable<>(docGiaMg.getList(), COLUMN_NAME);
        modelTable.setListener(this);

        Font f1 = new Font("Tahoma",Font.BOLD,20);
        Font f2 = new Font("Tahoma",Font.PLAIN,25);
        Font f3 = new Font("table_text", Font.PLAIN, 15);
        Font f4 = new Font("table_text", Font.BOLD, 16);
        Font f5 = new Font("comboBox_text", Font.PLAIN, 18);

        lb_id = createLabel("Mã đọc giả:", 50, 80, f2, Color.BLACK, null);
        add(lb_id);
        tf_id = createTextField(190, lb_id.getY(), 200, f5, Color.BLACK);
        add(tf_id);

        lb_ten = createLabel("Tên đọc giả:", 50, 150, f2, Color.BLACK, null);
        add(lb_ten);
        tf_ten = createTextField(190, lb_ten.getY(), 200, f5, Color.BLACK);
        add(tf_ten);

        lb_sdt = createLabel("Số điện thoại:", 450, 80, f2, Color.BLACK, null);
        add(lb_sdt);
        tf_sdt = createTextField(630, lb_sdt.getY(), 200, f5, Color.BLACK);
        add(tf_sdt);

        lb_tai_khoan = createLabel("Tài khoản:", 450, 150, f2, Color.BLACK, null);
        add(lb_tai_khoan);
        tf_tai_khoan = createTextField(630, lb_tai_khoan.getY(), 200, f5, Color.BLACK);
        add(tf_tai_khoan);

        lb_gioi_tinh = createLabel("Giới tính:", 50, 220, f2, Color.BLACK, null);
        add(lb_gioi_tinh);

        rb_nam  =new JRadioButton("Nam");
        rb_nam.setBounds(200,lb_gioi_tinh.getY(), 105,30);
        rb_nam.setFont(f5);
        rb_nam.setBackground(null);
        rb_nu = new JRadioButton("Nữ");
        rb_nu.setBounds(325,lb_gioi_tinh.getY()-4, 105,40);
        rb_nu.setFont(f5);
        rb_nu.setBackground(null);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb_nam);
        bg.add(rb_nu);
        add(rb_nam);
        add(rb_nu);

        lb_ma_phieu_muon = createLabel("Mã phiếu mượn:", 450, 220, f2, Color.BLACK, null);
        add(lb_ma_phieu_muon);
        tf_ma_phieu_muon = createTextField(630, lb_ma_phieu_muon.getY(), 200, f5, Color.BLACK);
        add(tf_ma_phieu_muon);

        bt_back = createButton("", 0, 0, f2, Color.BLACK, BT_BACK);
        bt_back.setSize(70, 70);
        setImageFromAssertToButton("return.png", bt_back, 30, 30);
        add(bt_back);

        bt_tim_kiem = createButton("Tìm kiếm", 45, 290, f1, Color.BLACK, BT_TIM_KIEM);
        bt_tim_kiem.setSize(140, 40);
        setImageFromAssertToButton("searchuser.png", bt_tim_kiem, 30, 30);
        add(bt_tim_kiem);

        bt_sua = createButton("Sửa", 255, 290, f1, Color.BLACK, BT_SUA);
        bt_sua.setSize(100, 40);
        setImageFromAssertToButton("edit.png", bt_sua, 22, 22);
        add(bt_sua);

        bt_xem_phieu_muon = createButton("Xem Phiếu Mượn", 425, 290, f1, Color.BLACK, BT_XEM_PHIEU_MUON);
        bt_xem_phieu_muon.setSize(220, 40);
        setImageFromAssertToButton("icondetails.jpg", bt_xem_phieu_muon, 30, 30);
        add(bt_xem_phieu_muon);

        bt_lam_moi = createButton("Làm mới", 705, 290, f1, Color.BLACK, BT_LAM_MOI);
        bt_lam_moi.setSize(140, 40);
        setImageFromAssertToButton("refresh.png", bt_lam_moi, 25, 25);
        add(bt_lam_moi);

        tb_doc_gia = new JTable(modelTable);
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

    @Override
    protected void handleClick(String name) {
        switch (name){
            case BT_TIM_KIEM:
                docGiaMg.searchData(tf_id.getText(), tf_ten.getText(), tf_sdt.getText(), tf_tai_khoan.getText(), tf_ma_phieu_muon.getText());
                if(docGiaMg.getListTimKiem().size() != 0){
                    modelTable = new ModelTable<>(docGiaMg.getListTimKiem(), COLUMN_NAME);
                    modelTable.setListener(this);
                    tb_doc_gia.setModel(modelTable);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Không thấy đọc giả cần tìm :)");
                }
                break;
            case BT_SUA:
                sua();
                break;
            case BT_XEM_PHIEU_MUON:
                DocGia docGia;
                int sr = tb_doc_gia.getSelectedRow();
                if(docGiaMg.getList().size() != tb_doc_gia.getRowCount()){
                    docGia = docGiaMg.getListTimKiem().get(sr);
                }
                else{
                    docGia = docGiaMg.getList().get(sr);

                }
                acc.thuThuQuanLyMuonTra(docGia.getPhieuMuonId());

                break;
            case BT_LAM_MOI:
                lamMoi();
                break;
            case BT_BACK:
                acc.backToThuThuMenu();
                break;

        }

    }

    private void sua() {
        DocGia docGia;
        int sr = tb_doc_gia.getSelectedRow();
        if(docGiaMg.getList().size() != tb_doc_gia.getRowCount()){
            docGia = docGiaMg.getListTimKiem().get(sr);
        }
        else{
            docGia = docGiaMg.getList().get(sr);

        }

        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin của sách " + docGia.getHoTen() ,"xac nhan",JOptionPane.YES_NO_OPTION);
        if(rs == JOptionPane.YES_OPTION) {

            docGia.setDocGiaId(tf_id.getText());
            docGia.setHoTen(tf_ten.getText());
            docGia.setSdt(tf_sdt.getText());
            docGia.setTaiKhoan(tf_tai_khoan.getText());
            if(rb_nam.isSelected()){
                docGia.setGioiTinh("Nam");
            }
            else{
                docGia.setGioiTinh("Nu");
            }
            modelTable.fireTableDataChanged();
            docGiaMg.updateData();
        }

    }

    private void lamMoi(){
        tf_id.setText("");
        tf_ten.setText("");
        tf_sdt.setText("");
        tf_tai_khoan.setText("");
        tf_ma_phieu_muon.setText("");

        modelTable = new ModelTable<>(docGiaMg.getList(), COLUMN_NAME);
        modelTable.setListener(this);
        tb_doc_gia.setModel(modelTable);
    }

    private void itemTableClick(){

        DocGia docGia;
        int sr = tb_doc_gia.getSelectedRow();
        if(docGiaMg.getList().size() != tb_doc_gia.getRowCount()){
            docGia = docGiaMg.getListTimKiem().get(sr);
        }
        else{
            docGia = docGiaMg.getList().get(sr);
        }
        tf_id.setText(docGia.getDocGiaId());
        tf_ten.setText(docGia.getHoTen());
        tf_sdt.setText(docGia.getSdt());
        tf_tai_khoan.setText(docGia.getTaiKhoan());
        tf_ma_phieu_muon.setText(docGia.getPhieuMuonId());
        if(docGia.getGioiTinh().equals("Nam")){
            rb_nam.setSelected(true);
        }
        else{
            rb_nu.setSelected(true);
        }

    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }

    @Override
    public Object getTableValue(int rowIndex, int columnIndex, List<DocGia> data) {
        DocGia docGia = data.get(rowIndex);
        if(columnIndex == 0){
            return docGia.getDocGiaId();
        }
        else if(columnIndex == 1){
            return docGia.getHoTen();
        }
        else if(columnIndex == 2 ){
            return docGia.getSdt();
        }
        else if(columnIndex == 3){
            return docGia.getTaiKhoan();
        }
        else if(columnIndex == 4){
            return docGia.getGioiTinh();
        }
        else{
            return docGia.getPhieuMuonId();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        itemTableClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
