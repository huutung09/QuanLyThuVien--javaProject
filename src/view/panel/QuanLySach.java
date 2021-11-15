package view.panel;

import model.ModelTable;
import model.Sach;
import model.SachManage;
import view.ActionClick;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class QuanLySach extends BasePanel implements MouseListener, ModelTable.Listener<Sach> {

    private static final String BT_THEM = "BT_THEM";
    private static final String BT_SUA = "BT_SUA";
    private static final String BT_XOA = "BT_XOA";
    private static final String BT_TIM_KIEM = "BT_TIM_KIEM";
    private static final String BT_LAM_MOI = "BT_LAM_MOI";
    private static final String BT_BACK = "BT_BACK";
    private static final String[] COLUMN_NAME = {"Mã sách", "Tên sách", "Số lương", "Tác giả"};
    private static final String path = System.getProperty("user.dir") + "\\listSach.txt";

    private List<Sach> listTimKiem;

    private SachManage sachMg;
    private ModelTable<Sach> modelTable;

    private JTextField tf_id, tf_ten, tf_so_luong, tf_tac_gia;
    private JLabel lb_id, lb_ten, lb_so_luong, lb_tac_gia;
    private JButton bt_them, bt_sua, bt_xoa, bt_tim_kiem, bt_lam_moi, bt_back;
    private JTable tb_sach;

    public QuanLySach(){
        super();
        tb_sach.addMouseListener(this);
    }


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

        sachMg = new SachManage();
        sachMg.getData();
        modelTable = new ModelTable<>(sachMg.getListSach(), COLUMN_NAME);
        modelTable.setListener(this);

        Font f1 = new Font("Tahoma",Font.BOLD,20);
        Font f2 = new Font("Tahoma",Font.PLAIN,25);
        Font f3 = new Font("table_text", Font.PLAIN, 15);

        lb_id = createLabel("Mã sách:", 50, 80, f2, Color.BLACK, null);
        add(lb_id);
        tf_id = createTextField(180, lb_id.getY(), 200, f2, Color.BLACK);
        add(tf_id);

        lb_ten = createLabel("Tên sách:", 50, 150, f2, Color.BLACK, null);
        add(lb_ten);
        tf_ten = createTextField(180, lb_ten.getY(), 200, f2, Color.BLACK);
        add(tf_ten);

        lb_so_luong = createLabel("Số lượng:", 450, 80, f2, Color.BLACK, null);
        add(lb_so_luong);
        tf_so_luong = createTextField(630, lb_so_luong.getY(), 200, f2, Color.BLACK);
        add(tf_so_luong);

        lb_tac_gia = createLabel("Tác giả:", 450, 150, f2, Color.BLACK, null);
        add(lb_tac_gia);
        tf_tac_gia = createTextField(630, lb_tac_gia.getY(), 200, f2, Color.BLACK);
        add(tf_tac_gia);

        bt_tim_kiem = createButton("Tìm kiếm", 45, 220, f2, Color.BLACK, BT_TIM_KIEM);
        add(bt_tim_kiem);
        bt_them = createButton("Thêm", 255, 220, f2, Color.BLACK, BT_THEM);
        add(bt_them);
        bt_sua = createButton("Sửa", 415, 220, f2, Color.BLACK, BT_SUA);
        add(bt_sua);
        bt_xoa = createButton("Xóa", 555, 220, f2, Color.BLACK, BT_XOA);
        add(bt_xoa);
        bt_lam_moi = createButton("Làm mới", 705, 220, f2, Color.BLACK, BT_LAM_MOI);
        add(bt_lam_moi);
        bt_back = createButton("back", 40, 20, f2, Color.BLACK, BT_BACK);
        add(bt_back);

        tb_sach = new JTable(modelTable);
        tb_sach.setFont(f3);
        tb_sach.setRowHeight(30);
        tb_sach.getTableHeader().setFont(f1);
        JScrollPane scr = new JScrollPane(tb_sach);
        scr.setLocation(25, bt_lam_moi.getY()+bt_lam_moi.getHeight()+50);
        scr.setSize(835, 420);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("Danh sách sách");
        tborder.setTitleFont(f1);
        tborder.setTitleColor(Color.RED);
        scr.setBorder(tborder);
        add(scr);

    }

    @Override
    protected void handleClick(String name) {
        switch (name){
            case BT_TIM_KIEM:
                listTimKiem = new ArrayList<>();
                timKiem();
                if(listTimKiem.size() != 0){
                    modelTable = new ModelTable<>(listTimKiem, COLUMN_NAME);
                    modelTable.setListener(this);
                    tb_sach.setModel(modelTable);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Không thấy sách cần tìm :)");
                }
                break;
            case BT_THEM:
                them();
                break;
            case BT_SUA:
                sua();
                break;
            case BT_XOA:
                xoa();
                break;
            case BT_LAM_MOI:
                lamMoi();
                break;
        }
    }


    private void deleteData(){

        File file = new File(path);
        try {
            FileOutputStream out = new FileOutputStream(file, false);
            for(Sach sach : sachMg.getListSach()){
                String s = sach.getSachId() + "-" + sach.getTenSach() + "-" + sach.getTacGia() + "-" + sach.getSoLuong() + "\n";
                byte[] buff = s.getBytes();
                out.write(buff);
            }
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private void timKiem(){
       for(Sach sach : sachMg.getListSach()){
           if(sach.getSachId().contains(tf_id.getText()) && !tf_id.getText().equals("")){
               listTimKiem.add(sach);
               continue;
           }
           if(sach.getTenSach().contains(tf_ten.getText()) && !tf_ten.getText().equals("")){
               listTimKiem.add(sach);
               continue;
           }
           if(sach.getTacGia().contains(tf_tac_gia.getText()) && ! tf_tac_gia.getText().equals("")){
               listTimKiem.add(sach);
           }
       }

    }

    private void them() {
        if(!tf_id.getText().equals("") && !tf_ten.getText().equals("")
            && !tf_tac_gia.getText().equals("") && !tf_so_luong.getText().equals("")
        ){
            sachMg.addData(tf_id.getText(), tf_ten.getText(), tf_tac_gia.getText(), Integer.parseInt(tf_so_luong.getText()));
            modelTable.fireTableDataChanged();
        }
        else{
            JOptionPane.showMessageDialog(this, "Nhập đủ thông tin trước khi thêm sách :)");
        }


    }

    private void sua() {
        int sr = tb_sach.getSelectedRow();
        Sach sach = sachMg.getListSach().get(sr);
        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin của sách " + sach.getTenSach() ,"xac nhan",JOptionPane.YES_NO_OPTION);
        if(rs == JOptionPane.YES_OPTION) {

            sach.setSachId(tf_id.getText());
            sach.setTenSach(tf_ten.getText());
            sach.setTacGia(tf_tac_gia.getText());
            sach.setSoLuong(Integer.parseInt(tf_so_luong.getText()));
            modelTable.fireTableDataChanged();
            deleteData();
        }
    }

    private void itemTableClick(){
        int sr = tb_sach.getSelectedRow();
        Sach sach = sachMg.getListSach().get(sr);
        tf_id.setText(sach.getSachId());
        tf_ten.setText(sach.getTenSach());
        tf_tac_gia.setText(sach.getTacGia());
        tf_so_luong.setText(String.valueOf(sach.getSoLuong()));
    }

    private void xoa() {
        int sr = tb_sach.getSelectedRow();
        Sach sach = sachMg.getListSach().get(sr);
        int rs = JOptionPane.showConfirmDialog(this, "Xóa " + sach.getTenSach() +" ra khỏi danh sách" ,"xac nhan",JOptionPane.YES_NO_OPTION);
        if(rs == JOptionPane.YES_OPTION) {

            sachMg.getListSach().remove(sach);
            modelTable.fireTableDataChanged();
            deleteData();
        }
    }

    private void lamMoi(){
        tf_id.setText("");
        tf_ten.setText("");
        tf_so_luong.setText("");
        tf_tac_gia.setText("");
        modelTable = new ModelTable<>(sachMg.getListSach(), COLUMN_NAME);
        modelTable.setListener(this);
        tb_sach.setModel(modelTable);
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
