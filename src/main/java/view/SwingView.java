package view;

import interface_adapter.Annotation.AnnotationController;
import interface_adapter.Annotation.AnnotationViewModel;
import interface_adapter.Annotation.AnnotationState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public final class SwingView extends JFrame implements PropertyChangeListener {
    private final AnnotationController controller;
    private final AnnotationViewModel viewModel;
    private final JTextArea annotationText = new JTextArea(4, 30);
    private final JLabel status = new JLabel("Ready");

    public SwingView(AnnotationController controller, AnnotationViewModel viewModel) {
        super("PDF Annotation Workspace");
        this.controller = controller;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        buildUi();
    }

    private void buildUi() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        PdfPanel pdfPanel = new PdfPanel();
        add(pdfPanel, BorderLayout.CENTER);

        JPanel editor = new JPanel(new BorderLayout(4, 4));
        editor.add(new JLabel("LaTeX annotation:"), BorderLayout.NORTH);
        editor.add(new JScrollPane(annotationText), BorderLayout.CENTER);
        JButton save = new JButton("Save annotation");
        save.addActionListener(event -> {
            controller.addAnnotation("unsaved-document", pdfPanel.getPage(),
                    null, null, null, null, annotationText.getText(), "manual", null);
        });
        editor.add(save, BorderLayout.SOUTH);
        add(editor, BorderLayout.SOUTH);
        add(status, BorderLayout.NORTH);
        setSize(900, 650);
        setLocationByPlatform(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        AnnotationState state = (AnnotationState) event.getNewValue();
        status.setText(state.getMessage());
        if (state.isSuccess()) {
            annotationText.setText("");
        }
    }
}