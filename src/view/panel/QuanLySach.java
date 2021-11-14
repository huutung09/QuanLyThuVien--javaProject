package view.panel;

import model.Sach;
import view.ActionClick;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class QuanLySach extends BasePanel{

    private static final String BT_THEM = "BT_THEM";
    private static final String BT_SUA = "BT_SUA";
    private static final String BT_XOA = "BT_XOA";
    private static final String BT_TIM_KIEM = "BT_TIM_KIEM";
    private static final String BT_LAM_MOi = "BT_LAM_MOi";
    private static final String[] COLUMN_NAME = {"Mã sách", "Tên sách", "Số lương", "Tác giả"};
    private static final String path = System.getProperty("user.dir") + "\\listSach.txt";

    private List<Sach> list;

    private JTextField tf_id, tf_ten, tf_so_luong, tf_tac_gia;
    private JLabel lb_id, lb_ten, lb_so_luong, lb_tac_gia;
    private JButton bt_them, bt_sua, bt_xoa, bt_tim_kiem, bt_lam_moi;
    private JTable tb_sach;

    public QuanLySach(){
        super();
        list = new ArrayList<>();
        getData();
        initModelSach(tb_sach);
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
        bt_them = createButton("Thêm", 255, 190, f2, Color.BLACK, BT_TIM_KIEM);
        add(bt_them);
        bt_sua = createButton("Sửa", 415, 190, f2, Color.BLACK, BT_TIM_KIEM);
        add(bt_sua);
        bt_xoa = createButton("Xóa", 555, 190, f2, Color.BLACK, BT_TIM_KIEM);
        add(bt_xoa);
        bt_lam_moi = createButton("Làm mới", 705, 190, f2, Color.BLACK, BT_TIM_KIEM);
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

    private void initModelSach(JTable tb_sach) {
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
                Sach sach = list.get(row);
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
                break;
            case BT_THEM:
                break;
            case BT_SUA:
                break;
            case BT_XOA:
                break;
            case BT_LAM_MOi:
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

    private void addData(String id, String ten, String tacGia, int soLuong){
        File file = new File(path);
        list.add(new Sach(id, ten, tacGia, soLuong));

        try {
            FileOutputStream out = new FileOutputStream(file, true);
            String s = id + "-" + ten + "-" + tacGia + "-" + String.valueOf(soLuong) + "\n";
            byte[] buff = s.getBytes();
            out.write(buff);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
