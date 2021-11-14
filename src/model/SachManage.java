package model;

import java.io.File;
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
            // TODO Auto-generated catch block
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
            }
            if (s.getTacGia().equals(term)) {
                matching.add(s);
            }
        }

        return matching;
    }
}
