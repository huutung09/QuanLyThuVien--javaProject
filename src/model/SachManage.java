package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class SachManage {
    private static final String PATH = System.getProperty("user.dir") + "\\listSach.txt";
    private List<Sach> listSach;

    public SachManage() {
        listSach = new ArrayList<>();
    }

    public List<Sach> getListSach() {
        return listSach;
    }

    public void getData() {
        listSach.clear();
        File file = new File(PATH);
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            String data = "";
            while (raf.getFilePointer() < raf.length()) {
                data += raf.readLine() + "\n";
            }
            raf.close();
            if (data.equals("")) {
                return;
            }
            String[] dsSach = data.split("\n");
            for (String s : dsSach) {
                String[] info = s.split("-");
                Sach sach = new Sach(info[0], info[1], info[2], Integer.parseInt(info[3]));
                listSach.add(sach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(String id, String ten, String tacGia, int soLuong) {
        File file = new File(PATH);
        listSach.add(new Sach(id, ten, tacGia, soLuong));

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

    public void updateData() {
        File file = new File(PATH);
        try {
            FileOutputStream out = new FileOutputStream(file, false);
            for (Sach sach : listSach) {
                String s = sach.getSachId() + "-" + sach.getTenSach() + "-" + sach.getTacGia() + "-"
                        + Integer.toString(sach.getSoLuong()) + "\n";
                byte[] buff = s.getBytes();
                out.write(buff);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Sach> searchSach(String term) {
        if (term.equals("")) {
            return listSach;
        }

        List<Sach> matching = new ArrayList<>();
        for (Sach s : listSach) {
            if (s.getTenSach().toLowerCase().contains(term.toLowerCase())) {
                matching.add(s);
            } else if (s.getTacGia().toLowerCase().contains(term.toLowerCase())) {
                matching.add(s);
            }
        }

        return matching;
    }

    public Sach searchSachById(String id) {
        for (Sach s : listSach) {
            if (s.getSachId().equals(id)) {
                return s;
            }
        }
        return null;
    }

}
