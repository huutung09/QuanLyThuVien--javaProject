package model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ListDocGia {
    private final String PATH = System.getProperty("user.dir") + "\\listDocGia.txt";
    private List<DocGia> docgia;

    public ListDocGia() {
        docgia = new ArrayList<>();

        try {
            docDuLieuTuFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<DocGia> getDocgia() {
        return docgia;
    }

    public void setDocgia(List<DocGia> docgia) {
        this.docgia = docgia;
    }

    public void docDuLieuTuFile() throws IOException {
        File file = new File(PATH);
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        String data = "";
        while ((data = raf.readLine()) != null) {
            String[] infor = data.split("-");
            DocGia docGia = new DocGia(infor[0], infor[1], infor[2], infor[3], infor[4], infor[5], infor[6]);
            docgia.add(docGia);
            break;
        }
        raf.close();
    }

    public void ghiDuLieuTuFile() throws IOException {
        File file = new File(PATH);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        for (int i = 0; i < getDocgia().size(); i++) {
            raf.writeBytes(getDocgia().get(i).getInfos());
        }
        raf.close();
    }

    public void addDocGia(DocGia docGia) {
        this.docgia.add(docGia);
    }

}
