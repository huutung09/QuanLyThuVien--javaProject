package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhieuMuonManage {
    private static final String PATH = System.getProperty("user.dir") + "\\listPhieuMuon.txt";
    private List<PhieuMuon> listPhieuMuon;

    public PhieuMuonManage() {
        listPhieuMuon = new ArrayList<>();
    }

    public void getData() {
        listPhieuMuon.clear();
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
                List<String> phieuId = Arrays.asList(info[1].split(","));
                PhieuMuon phieu = new PhieuMuon(info[0], phieuId, info[2], info[3]);
                listPhieuMuon.add(phieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(String id, List<Sach> dsSachMuon, String ngayMuon, String ngayTra) {
        File file = new File(PATH);
        List<String> dsIdSachMuon = new ArrayList<>();
        for (Sach s : dsSachMuon) {
            dsIdSachMuon.add(s.getSachId());
        }
        PhieuMuon phieu = new PhieuMuon(id, dsIdSachMuon, ngayMuon, ngayTra);
        listPhieuMuon.add(phieu);
        try {
            FileOutputStream out = new FileOutputStream(file, true);
            String s = id + "-" + String.join(",", dsIdSachMuon) + "-" + ngayMuon + "-" + ngayTra + "-"
                    + Long.toString(phieu.getLate()) + "\n";
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
            for (PhieuMuon phieuMuon : listPhieuMuon) {
                String s = phieuMuon.getPhieuId() + "-" + String.join(",", phieuMuon.getDsIdSachMuon()) + "-"
                        + phieuMuon.getNgayMuon() + "-" + phieuMuon.getNgayTra() + "-" + phieuMuon.getLate() + "\n";
                byte[] buff = s.getBytes();
                out.write(buff);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PhieuMuon> getListPhieuMuon() {
        return listPhieuMuon;
    }

}
