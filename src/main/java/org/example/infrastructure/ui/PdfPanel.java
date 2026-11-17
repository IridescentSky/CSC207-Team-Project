package org.example.infrastructure.ui;

import javax.swing.*;
import java.awt.*;

/** Replace this panel's placeholder with a PDF renderer (for example Apache PDFBox). */
public final class PdfPanel extends JPanel {
    private int page = 1;

    public PdfPanel() {
        setBackground(Color.LIGHT_GRAY);
        add(new JLabel("Import a PDF to begin (PDF renderer boundary)"));
    }

    public int getPage() { return page; }
    public void setPage(int page) {
        if (page < 1) throw new IllegalArgumentException("page must be positive");
        this.page = page;
    }
}
