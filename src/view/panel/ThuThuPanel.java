package view.panel;

import view.ActionClick;

import javax.swing.*;
import java.awt.*;

public class ThuThuPanel extends BasePanel{
    @Override
    public void initUI() {
        setLayout(new GridLayout(1, 1));
    }

    @Override
    public void addEvent() {

    }

    @Override
    public void addComp() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Quan Ly Doc Gia", null, new QuanLyDocGia(), null);
        tabbedPane.addTab("Quan Ly Sach", null, new QuanLySach(), null);
        tabbedPane.addTab("Quan Ly Muon Tra", null, new QuanLyMuonTra(), null);
        add(tabbedPane);
    }

    private ActionClick acc;

    public ActionClick getAcc() {
        return acc;
    }

    public void setAcc(ActionClick acc) {
        this.acc = acc;
    }
}
