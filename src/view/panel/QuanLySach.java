package view.panel;

import model.Sach;
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


public class QuanLySach extends BasePanel implements MouseListener{

    private static final String BT_THEM = "BT_THEM";
    private static final String BT_SUA = "BT_SUA";
    private static final String BT_XOA = "BT_XOA";
    private static final String BT_TIM_KIEM = "BT_TIM_KIEM";
    private static final String BT_LAM_MOI = "BT_LAM_MOI";
    private static final String[] COLUMN_NAME = {"Mã sách", "Tên sách", "Số lương", "Tác giả"};
    private static final String path = System.getProperty("user.dir") + "\\listSach.txt";

    private List<Sach> list;
    private List<Sach> listTimKiem;

    private JTextField tf_id, tf_ten, tf_so_luong, tf_tac_gia;
    private JLabel lb_id, lb_ten, lb_so_luong, lb_tac_gia;
    private JButton bt_them, bt_sua, bt_xoa, bt_tim_kiem, bt_lam_moi;
    private JTable tb_sach;

    public QuanLySach(){
        super();
        list = new ArrayList<>();
        getData();
        initModelSach(tb_sach, list);
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
        Font f1 = new Font("Tahoma",Font.BOLD,20);
        Font f2 = new Font("Tahoma",Font.PLAIN,25);
        Font f3 = new Font("table_text", Font.PLAIN, 15);

        lb_id = creatLabel("Mã sách:", 50, 50, f2, Color.BLACK, null);
        add(lb_id);
        tf_id = createTextField(180, lb_id.getY(), 200, f2, Color.BLACK);
        add(tf_id);

        lb_ten = creatLabel("Tên sách:", 50, 120, f2, Color.BLACK, null);
        add(lb_ten);
        tf_ten = createTextField(180, lb_ten.getY(), 200, f2, Color.BLACK);
        add(tf_ten);

        lb_so_luong = creatLabel("Số lượng:", 450, 50, f2, Color.BLACK, null);
        add(lb_so_luong);
        tf_so_luong = createTextField(630, lb_so_luong.getY(), 200, f2, Color.BLACK);
        add(tf_so_luong);

        lb_tac_gia = creatLabel("Tác giả:", 450, 120, f2, Color.BLACK, null);
        add(lb_tac_gia);
        tf_tac_gia = createTextField(630, lb_tac_gia.getY(), 200, f2, Color.BLACK);
        add(tf_tac_gia);

        bt_tim_kiem = createButton("Tìm kiếm", 45, 190, f2, Color.BLACK, BT_TIM_KIEM);
        add(bt_tim_kiem);
        bt_them = createButton("Thêm", 255, 190, f2, Color.BLACK, BT_THEM);
        add(bt_them);
        bt_sua = createButton("Sửa", 415, 190, f2, Color.BLACK, BT_SUA);
        add(bt_sua);
        bt_xoa = createButton("Xóa", 555, 190, f2, Color.BLACK, BT_XOA);
        add(bt_xoa);
        bt_lam_moi = createButton("Làm mới", 705, 190, f2, Color.BLACK, BT_LAM_MOI);
        add(bt_lam_moi);

        tb_sach = new JTable();
        tb_sach.setFont(f3);
        tb_sach.setRowHeight(30);
        tb_sach.getTableHeader().setFont(f1);
        JScrollPane scr = new JScrollPane(tb_sach);
        scr.setLocation(20, bt_lam_moi.getY()+bt_lam_moi.getHeight()+50);
        scr.setSize(835, 450);
        scr.setBackground(Color.LIGHT_GRAY);
        TitledBorder tborder = new TitledBorder("Danh sách sách");
        tborder.setTitleFont(f1);
        tborder.setTitleColor(Color.RED);
        scr.setBorder(tborder);
        add(scr);

    }

    private void initModelSach(JTable tb_sach, List<Sach> listSach) {
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
                return listSach.size();
            }

            @Override
            public Object getValueAt(int row, int column) {
                Sach sach = listSach.get(row);
                if(column==0){
                    return sach.getSachId();
                }
                else if(column == 1){
                    return sach.getTenSach();
                }
                else if(column ==2 ){
                    return sach.getSoLuong();
                }
                else{
                    return sach.getTacGia();
                }
            }
        };
        tb_sach.setModel(model);
    }

    @Override
    protected void handleClick(String name) {
        switch (name){
            case BT_TIM_KIEM:
                listTimKiem = new ArrayList<>();
                timKiem();
                if(listTimKiem.size() != 0){
                    initModelSach(tb_sach, listTimKiem);
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

    private void getData(){
        File file = new File(path);
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            String data = "";
            while (raf.getFilePointer() < raf.length()){
                data += raf.readLine() + "\n";
            }
            raf.close();
            if(data.equals("")){
                return;
            }
            String[] arrSach = data.split("\n");
            for(String sach : arrSach){
                String[] info = sach.split("-");
                list.add(new Sach(info[0], info[1], info[2], Integer.parseInt(info[3])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteData(){

        File file = new File(path);
        try {
            FileOutputStream out = new FileOutputStream(file, false);
            for(Sach sach : list){
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

    private void addData(String id, String ten, String tacGia, int soLuong){
        File file = new File(path);
        list.add(new Sach(id, ten, tacGia, soLuong));

        try {
            FileOutputStream out = new FileOutputStream(file, true);
            String s = id + "-" + ten + "-" + tacGia + "-" + soLuong + "\n";
            byte[] buff = s.getBytes();
            out.write(buff);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void timKiem(){
       for(Sach sach : list){
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
            addData(tf_id.getText(), tf_ten.getText(), tf_tac_gia.getText(), Integer.parseInt(tf_so_luong.getText()));
            ((DefaultTableModel) tb_sach.getModel()).fireTableDataChanged();
        }
        else{
            JOptionPane.showMessageDialog(this, "Nhập đủ thông tin trước khi thêm sách :)");
        }


    }

    private void sua() {
        int sr = tb_sach.getSelectedRow();
        Sach sach = list.get(sr);
        int rs = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin của sách " + sach.getTenSach() ,"xac nhan",JOptionPane.YES_NO_OPTION);
        if(rs == JOptionPane.YES_OPTION) {

            sach.setSachId(tf_id.getText());
            sach.setTenSach(tf_ten.getText());
            sach.setTacGia(tf_tac_gia.getText());
            sach.setSoLuong(Integer.parseInt(tf_so_luong.getText()));
            ((DefaultTableModel)tb_sach.getModel()).fireTableDataChanged();
            deleteData();
        }
    }

    private void itemTableClick(){
        int sr = tb_sach.getSelectedRow();
        Sach sach = list.get(sr);
        tf_id.setText(sach.getSachId());
        tf_ten.setText(sach.getTenSach());
        tf_tac_gia.setText(sach.getTacGia());
        tf_so_luong.setText(String.valueOf(sach.getSoLuong()));
    }

    private void xoa() {
        int sr = tb_sach.getSelectedRow();
        Sach sach = list.get(sr);
        int rs = JOptionPane.showConfirmDialog(this, "Xóa " + sach.getTenSach() +" ra khỏi danh sách" ,"xac nhan",JOptionPane.YES_NO_OPTION);
        if(rs == JOptionPane.YES_OPTION) {

            list.remove(sach);
            ((DefaultTableModel)tb_sach.getModel()).fireTableDataChanged();
            deleteData();
        }
    }

    private void lamMoi(){
        tf_id.setText("");
        tf_ten.setText("");
        tf_so_luong.setText("");
        tf_tac_gia.setText("");
        initModelSach(tb_sach, list);
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
