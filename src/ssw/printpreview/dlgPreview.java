

package ssw.printpreview;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import ssw.filehandlers.Media;
import ssw.gui.frmMain;
import ssw.print.Printer;

public class dlgPreview extends javax.swing.JDialog implements ActionListener {
    private final static double DEFAULT_ZOOM_FACTOR_STEP = .5;
    protected Pageable pageable;
    private Printer printer;
    private Preview preview;
    private frmMain Parent;
    private File MechImage = null;
    private File LogoImage = null;

    public dlgPreview(String title, JFrame owner, Printer printer, Pageable pageable, double zoom) {
        super(owner, title);
        initComponents();
        this.Parent = (frmMain) owner;
        this.printer = printer;
        preview = new Preview(pageable, zoom, spnPreview.getSize());
        spnPreview.setViewportView(preview);

        btnZoomIn.setAction(new ZoomAction("Zoom In", "magnifier-zoom.png", preview, DEFAULT_ZOOM_FACTOR_STEP, false));
        btnZoomOut.setAction(new ZoomAction("Zoom Out", "magnifier-zoom-out.png", preview, -DEFAULT_ZOOM_FACTOR_STEP, false));

        btnBack.setAction(new BrowseAction("Prev", "arrow-180.png", preview, -1));
        btnForward.setAction(new BrowseAction("Next", "arrow.png", preview, 1));

        btnPageWidth.setAction(new ZoomAction("Width", "document-resize.png", preview, preview.getWidthZoom(), true));
        btnPageHeight.setAction(new ZoomAction("Page", "document-resize-actual.png", preview, preview.getHeightZoom(), true));

        chkPrintCanon.setSelected(Parent.Prefs.getBoolean("UseCanonDots", false));
        chkPrintCharts.setSelected(Parent.Prefs.getBoolean("UseCharts", false));
        
        chkUseHexConversion.setSelected( Parent.Prefs.getBoolean( "UseMiniConversion", false ) );
        if( chkUseHexConversion.isSelected() ) {
            lblOneHex.setEnabled( true );
            cmbHexConvFactor.setEnabled( true );
            lblInches.setEnabled( true );
            cmbHexConvFactor.setSelectedIndex( Parent.Prefs.getInt( "MiniConversionRate", 0 ) );
        }

        if ( pageable.getNumberOfPages() <= 2 ) {
            btnBack.setVisible(false);
            btnForward.setVisible(false);
            jSeparator1.setVisible(false);
        }
    }

    public dlgPreview(String title, JFrame owner, Printer printer, Pageable pageable) {
        this(title, owner, printer, pageable, 0.0);
    }

    public dlgPreview(String title, JFrame owner, Printer printer, Printable printable, PageFormat format, int pages, double zoom) {
        this(title, owner, printer, new MyPageable(printable, format, pages), zoom);
    }

    public dlgPreview(String title, JFrame owner, Printer printer, Printable printable, PageFormat format, int pages) {
        this(title, owner, printer, printable, format, pages, 0.0);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    private static class MyPageable implements Pageable {
        public MyPageable(Printable printable, PageFormat format, int pages) {
            this.printable = printable;
            this.format = format;
            this.pages = pages;
        }

        public int getNumberOfPages() {
            return pages;
        }

        public Printable getPrintable(int index) {
            if (index >= pages) throw new IndexOutOfBoundsException();
            return printable;
        }

        public PageFormat getPageFormat(int index) {
            if (index >= pages) throw new IndexOutOfBoundsException();
            return format;
        }

        private Printable printable;
        private PageFormat format;
        private int pages;
    }

    private void refresh() {
        preview.setPageable(printer.Preview());
        preview.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spnPreview = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlPrintOptions = new javax.swing.JPanel();
        chkPrintCharts = new javax.swing.JCheckBox();
        chkUseHexConversion = new javax.swing.JCheckBox();
        lblOneHex = new javax.swing.JLabel();
        cmbHexConvFactor = new javax.swing.JComboBox();
        lblInches = new javax.swing.JLabel();
        chkPrintCanon = new javax.swing.JCheckBox();
        pnlImageOptions = new javax.swing.JPanel();
        chkPrintImage = new javax.swing.JCheckBox();
        btnChooseImage = new javax.swing.JButton();
        chkLogo = new javax.swing.JCheckBox();
        btnChooseLogo = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnBack = new javax.swing.JButton();
        btnForward = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnPageWidth = new javax.swing.JButton();
        btnPageHeight = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnCloseDialog = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setModal(true);

        pnlPrintOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Print Options"));

        chkPrintCharts.setText("Print Tables and Movement Grid");
        chkPrintCharts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPrintChartsActionPerformed(evt);
            }
        });

        chkUseHexConversion.setText("Use Miniatures Scale for Movement");
        chkUseHexConversion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUseHexConversionActionPerformed(evt);
            }
        });

        lblOneHex.setText("One Hex equals");
        lblOneHex.setEnabled(false);

        cmbHexConvFactor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cmbHexConvFactor.setEnabled(false);
        cmbHexConvFactor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHexConvFactorActionPerformed(evt);
            }
        });

        lblInches.setText("Inches");
        lblInches.setEnabled(false);

        chkPrintCanon.setText("Print Canon Dot Patterns");
        chkPrintCanon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPrintCanonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPrintOptionsLayout = new javax.swing.GroupLayout(pnlPrintOptions);
        pnlPrintOptions.setLayout(pnlPrintOptionsLayout);
        pnlPrintOptionsLayout.setHorizontalGroup(
            pnlPrintOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrintOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrintOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrintOptionsLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblOneHex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbHexConvFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInches))
                    .addComponent(chkUseHexConversion)
                    .addComponent(chkPrintCanon)
                    .addComponent(chkPrintCharts))
                .addContainerGap())
        );
        pnlPrintOptionsLayout.setVerticalGroup(
            pnlPrintOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrintOptionsLayout.createSequentialGroup()
                .addComponent(chkPrintCharts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkPrintCanon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkUseHexConversion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrintOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrintOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbHexConvFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOneHex))
                    .addGroup(pnlPrintOptionsLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblInches)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlImageOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Image Options"));

        chkPrintImage.setText("Include TRO Pic");
        chkPrintImage.setToolTipText("From Mech file");
        chkPrintImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPrintImageActionPerformed(evt);
            }
        });

        btnChooseImage.setText("Choose TRO Pic");
        btnChooseImage.setEnabled(false);
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        chkLogo.setText("Include Logo");
        chkLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLogoActionPerformed(evt);
            }
        });

        btnChooseLogo.setText("Choose Logo");
        btnChooseLogo.setEnabled(false);
        btnChooseLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseLogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlImageOptionsLayout = new javax.swing.GroupLayout(pnlImageOptions);
        pnlImageOptions.setLayout(pnlImageOptionsLayout);
        pnlImageOptionsLayout.setHorizontalGroup(
            pnlImageOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImageOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImageOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkPrintImage, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(btnChooseImage, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImageOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChooseLogo)
                    .addComponent(chkLogo))
                .addGap(16, 16, 16))
        );
        pnlImageOptionsLayout.setVerticalGroup(
            pnlImageOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImageOptionsLayout.createSequentialGroup()
                .addGroup(pnlImageOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkLogo)
                    .addComponent(chkPrintImage))
                .addGap(6, 6, 6)
                .addGroup(pnlImageOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChooseImage)
                    .addComponent(btnChooseLogo))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ssw/Images/arrow-180.png"))); // NOI18N
        btnBack.setText("Prev");
        btnBack.setFocusable(false);
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnBack);

        btnForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ssw/Images/arrow.png"))); // NOI18N
        btnForward.setText("Next");
        btnForward.setFocusable(false);
        btnForward.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnForward.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnForward);
        jToolBar1.add(jSeparator1);

        btnPageWidth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ssw/Images/document-resize.png"))); // NOI18N
        btnPageWidth.setText("Width");
        btnPageWidth.setFocusable(false);
        btnPageWidth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPageWidth.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPageWidth);

        btnPageHeight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ssw/Images/document-resize-actual.png"))); // NOI18N
        btnPageHeight.setText("Page");
        btnPageHeight.setFocusable(false);
        btnPageHeight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPageHeight.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnPageHeight);
        jToolBar1.add(jSeparator3);

        btnZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ssw/Images/magnifier-zoom.png"))); // NOI18N
        btnZoomIn.setText("Zoom In");
        btnZoomIn.setFocusable(false);
        btnZoomIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnZoomIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnZoomIn);

        btnZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ssw/Images/magnifier-zoom-out.png"))); // NOI18N
        btnZoomOut.setText("Zoom Out");
        btnZoomOut.setFocusable(false);
        btnZoomOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnZoomOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnZoomOut);
        jToolBar1.add(jSeparator2);

        btnCloseDialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ssw/Images/home.png"))); // NOI18N
        btnCloseDialog.setText("Close");
        btnCloseDialog.setFocusable(false);
        btnCloseDialog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCloseDialog.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCloseDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseDialogActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCloseDialog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPrintOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlImageOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(pnlPrintOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlImageOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(spnPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spnPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkUseHexConversionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUseHexConversionActionPerformed
        if( chkUseHexConversion.isSelected() ) {
            lblOneHex.setEnabled( true );
            cmbHexConvFactor.setEnabled( true );
            lblInches.setEnabled( true );

            printer.setHexConversion( cmbHexConvFactor.getSelectedIndex() + 1 );
        } else {
            lblOneHex.setEnabled( false );
            cmbHexConvFactor.setEnabled( false );
            lblInches.setEnabled( false );
            printer.setHexConversion( 1 );
        }
        refresh();
}//GEN-LAST:event_chkUseHexConversionActionPerformed

    private void chkPrintImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPrintImageActionPerformed
        btnChooseImage.setEnabled(chkPrintImage.isSelected());
        if ( chkPrintImage.isSelected() ) {
            if ( MechImage != null ) {
                try {
                    printer.setMechImagePath(MechImage.getCanonicalPath());
                } catch ( IOException ie ) {

                }
            }
        } else {
            printer.setMechImagePath("");
        }
        refresh();
}//GEN-LAST:event_chkPrintImageActionPerformed

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        String defaultDir = "";
        if ( Parent != null ) {defaultDir = Parent.Prefs.get("LastImagePath", "");}
        Media media = new Media();
        MechImage = media.SelectImage(defaultDir, "Select Image");

        try {
            if ( Parent != null ) {
                Parent.Prefs.put("LastImage", MechImage.getCanonicalPath());
                Parent.Prefs.put("LastImagePath", MechImage.getCanonicalPath().replace(MechImage.getName(), ""));
                Parent.Prefs.put("LastImageFile", MechImage.getName());
            }

            //setImage(MechImage);
            printer.setMechImagePath(MechImage.getCanonicalPath());
            refresh();

        } catch ( Exception e ) {
            //do nothing
        }
}//GEN-LAST:event_btnChooseImageActionPerformed

    private void chkLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLogoActionPerformed
        btnChooseLogo.setEnabled(chkLogo.isSelected());
        if ( chkLogo.isSelected() ) {
            if ( LogoImage != null ) {
                try {
                    printer.setLogoPath(LogoImage.getCanonicalPath());
                } catch ( IOException ie ) {

                }
            }
        } else {
            printer.setLogoPath("");
        }
        refresh();
}//GEN-LAST:event_chkLogoActionPerformed

    private void btnChooseLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseLogoActionPerformed
        String defaultDir = "";
        if ( Parent != null ) {defaultDir = Parent.Prefs.get("LastLogo", "");}
        Media media = new Media();
        LogoImage = media.SelectImage(defaultDir, "Select Logo");

        try {
            if ( Parent != null ) {
                Parent.Prefs.put("LastLogo", LogoImage.getCanonicalPath());
                Parent.Prefs.put("LastLogoPath", LogoImage.getCanonicalPath().replace(LogoImage.getName(), ""));
                Parent.Prefs.put("LastLogoFile", LogoImage.getName());
            }

            //setLogo(LogoImage);
            printer.setLogoPath(LogoImage.getCanonicalPath());
            refresh();

        } catch ( Exception e ) {
            //do nothing
        }
}//GEN-LAST:event_btnChooseLogoActionPerformed

    private void chkPrintCanonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPrintCanonActionPerformed
        printer.setCanon(chkPrintCanon.isSelected());
        refresh();
    }//GEN-LAST:event_chkPrintCanonActionPerformed

    private void chkPrintChartsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPrintChartsActionPerformed
        printer.setCharts(chkPrintCharts.isSelected());
        refresh();
    }//GEN-LAST:event_chkPrintChartsActionPerformed

    private void cmbHexConvFactorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHexConvFactorActionPerformed
        printer.setHexConversion( cmbHexConvFactor.getSelectedIndex() + 1 );
        refresh();
    }//GEN-LAST:event_cmbHexConvFactorActionPerformed

    private void btnCloseDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseDialogActionPerformed
        dispose();
}//GEN-LAST:event_btnCloseDialogActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnChooseLogo;
    private javax.swing.JButton btnCloseDialog;
    private javax.swing.JButton btnForward;
    private javax.swing.JButton btnPageHeight;
    private javax.swing.JButton btnPageWidth;
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JCheckBox chkLogo;
    private javax.swing.JCheckBox chkPrintCanon;
    private javax.swing.JCheckBox chkPrintCharts;
    private javax.swing.JCheckBox chkPrintImage;
    private javax.swing.JCheckBox chkUseHexConversion;
    private javax.swing.JComboBox cmbHexConvFactor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblInches;
    private javax.swing.JLabel lblOneHex;
    private javax.swing.JPanel pnlImageOptions;
    private javax.swing.JPanel pnlPrintOptions;
    private javax.swing.JScrollPane spnPreview;
    // End of variables declaration//GEN-END:variables

}
