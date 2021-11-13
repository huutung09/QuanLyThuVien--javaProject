package view.panel;

import view.ActionClick;

import javax.swing.*;
import java.awt.*;

public class DocGiaPanel extends BasePanel {

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
        tabbedPane.addTab("Tim Muon Sach", null, new TimMuonPanel(), null);
        tabbedPane.addTab("Tra Sach", null, new TraPanel(), null);
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
