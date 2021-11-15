package model;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class DocGiaManage {
    public static final String path = System.getProperty("user.dir") + "\\listDocGia.txt";
    private List<DocGia> list;
    private List<DocGia> listTimKiem;

    public DocGiaManage(){
        list = new ArrayList<>();
        listTimKiem = new ArrayList<>();
    }

    public List<DocGia> getList() {
        return list;
    }

    public List<DocGia> getListTimKiem() {
        return listTimKiem;
    }

    public void getData() {
        File file = new File(path);
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            String data = "";
            while(raf.getFilePointer() < raf.length()) {
                data += raf.readLine() + "\n";
            }
            raf.close();
            if(data.equals("")) {
                return;
            }
            String[] arrDocGia = data.split("\n");
            for(String docGia: arrDocGia) {
                String[] info = docGia.split("-");
                list.add(new DocGia(info[0], info[1], info[2], info[3], info[4], info[5], info[6]));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void searchData(String id, String ten, String sdt, String taiKhoan, String maPhieuMuon){
        listTimKiem.clear();
        for(DocGia docGia : list){
            if(docGia.getDocGiaId().contains(id) && !id.equals("")){
                listTimKiem.add(docGia);
                continue;
            }
            if(docGia.getHoTen().contains(ten) && !ten.equals("")){
                listTimKiem.add(docGia);
                continue;
            }
            if(docGia.getSdt().contains(sdt) && !sdt.equals("")){
                listTimKiem.add(docGia);
                continue;
            }
            if(docGia.getTaiKhoan().contains(taiKhoan) && !taiKhoan.equals("")){
                listTimKiem.add(docGia);
                continue;
            }
            if(docGia.getPhieuMuonId().contains(maPhieuMuon) && !maPhieuMuon.equals("")){
                listTimKiem.add(docGia);
            }
        }
    }
}
