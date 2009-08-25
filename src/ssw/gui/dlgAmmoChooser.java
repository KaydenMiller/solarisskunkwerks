/*
Copyright (c) 2008~2009, Justin R. Bengtson (poopshotgun@yahoo.com)
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
        this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
        this list of conditions and the following disclaimer in the
        documentation and/or other materials provided with the distribution.
    * Neither the name of Justin R. Bengtson nor the names of contributors may
        be used to endorse or promote products derived from this software
        without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package ssw.gui;

import java.util.Vector;
import ssw.components.Ammunition;
import ssw.components.DataFactory;
import ssw.components.LocationIndex;
import ssw.components.Mech;

public class dlgAmmoChooser extends javax.swing.JDialog {

    private Mech CurMech;
    private Vector Ammo = new Vector();
    private DataFactory Data;

    /** Creates new form dlgAmmoChooser */
    public dlgAmmoChooser( java.awt.Frame parent, boolean modal, Mech m, DataFactory d ) {
        super(parent, modal);
        CurMech = m;
        Data = d;
        initComponents();
        SetState();
    }

    private void SetState() {
        lstInstalledAmmo.setCellRenderer( new AmmoChooserRenderer( this ) );
        setTitle( "Configuring Ammo for " + CurMech.GetName() + " " + CurMech.GetModel() );
        cmbRulesLevel.setSelectedIndex( CurMech.GetRulesLevel() );
        txtAmmoYear.setText( "" + CurMech.GetYear() );
        cmbRulesLevel.setEnabled( false );
        txtAmmoYear.setEnabled( false );
        BuildInstalledAmmoList();
        lstInstalledAmmo.setSelectedIndex( 0 );
        lstInstalledAmmoValueChanged( null );
    }

    private void BuildInstalledAmmoList() {
        Vector v = CurMech.GetLoadout().GetNonCore();
        Ammo.clear();
        for( int i = 0; i < v.size(); i++ ) {
            if( v.get( i ) instanceof Ammunition ) {
                Ammo.add( v.get( i ) );
            }
        }
        lstInstalledAmmo.setListData( Ammo );
        lstInstalledAmmo.repaint();
    }

    public boolean HasAmmo() {
        if( Ammo.size() < 1 ) {
            return false;
        } else {
            return true;
        }
    }

    public Mech GetMech() {
        return CurMech;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        grpAmmoChooser = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAvailableAmmo = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstInstalledAmmo = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btnChangeAmmo = new javax.swing.JButton();
        btnDone = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        rdoCanonAmmo = new javax.swing.JRadioButton();
        rdoAllAmmo = new javax.swing.JRadioButton();
        rdoYearAmmo = new javax.swing.JRadioButton();
        txtAmmoYear = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbRulesLevel = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMaximumSize(new java.awt.Dimension(250, 145));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(250, 145));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 145));

        lstAvailableAmmo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstAvailableAmmo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstAvailableAmmo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(250, 145));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(250, 145));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 145));

        lstInstalledAmmo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstInstalledAmmo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstInstalledAmmo.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstInstalledAmmoValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstInstalledAmmo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jLabel1.setText("Installed Ammo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        btnChangeAmmo.setText("Change");
        btnChangeAmmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeAmmoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnChangeAmmo, gridBagConstraints);

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        getContentPane().add(btnDone, gridBagConstraints);

        jLabel2.setText("Ammo Available for this Slot:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        grpAmmoChooser.add(rdoCanonAmmo);
        rdoCanonAmmo.setText("Respect Canonicity of 'Mech (use 'Mech to filter, select Rules Level)");
        rdoCanonAmmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoCanonAmmoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(rdoCanonAmmo, gridBagConstraints);

        grpAmmoChooser.add(rdoAllAmmo);
        rdoAllAmmo.setSelected(true);
        rdoAllAmmo.setText("Allow All Ammunition");
        rdoAllAmmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAllAmmoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(rdoAllAmmo, gridBagConstraints);

        grpAmmoChooser.add(rdoYearAmmo);
        rdoYearAmmo.setText("Set Year for Ammunition Allowed (select Rules Level)");
        rdoYearAmmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoYearAmmoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(rdoYearAmmo, gridBagConstraints);

        txtAmmoYear.setMaximumSize(new java.awt.Dimension(75, 20));
        txtAmmoYear.setMinimumSize(new java.awt.Dimension(75, 20));
        txtAmmoYear.setPreferredSize(new java.awt.Dimension(75, 20));
        txtAmmoYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAmmoYearKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel1.add(txtAmmoYear, gridBagConstraints);

        jLabel3.setText("Select Ammo Rules Level:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanel1.add(jLabel3, gridBagConstraints);

        cmbRulesLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Introductory", "Tournament Legal", "Advanced", "Experimental", "Era Specific" }));
        cmbRulesLevel.setMaximumSize(new java.awt.Dimension(150, 20));
        cmbRulesLevel.setMinimumSize(new java.awt.Dimension(150, 20));
        cmbRulesLevel.setPreferredSize(new java.awt.Dimension(150, 20));
        cmbRulesLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRulesLevelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(cmbRulesLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChangeAmmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeAmmoActionPerformed
        if( lstInstalledAmmo.getSelectedIndex() < 0 || lstAvailableAmmo.getSelectedIndex() < 0 ) { return; }
        int OldAmmoIndex = lstInstalledAmmo.getSelectedIndex();
        Ammunition OldAmmo = (Ammunition) lstInstalledAmmo.getSelectedValue();
        Ammunition NewAmmo = (Ammunition) lstAvailableAmmo.getSelectedValue();
        if( OldAmmo.GetTonnage() != NewAmmo.GetTonnage() ) {
            javax.swing.JOptionPane.showMessageDialog( this, "You cannot exchange ammunition of differing tonnages." );
            return;
        }
        LocationIndex l = CurMech.GetLoadout().FindIndex( OldAmmo );
        CurMech.GetLoadout().Remove( OldAmmo );
        CurMech.GetLoadout().AddToQueue( NewAmmo );
        try {
            CurMech.GetLoadout().AddTo( NewAmmo, l.Location, l.Index );
        } catch( Exception e ) {
            // whoa, didn't expect that.  well, let's handle it somehow later
        }
        BuildInstalledAmmoList();
        lstInstalledAmmo.setSelectedIndex( OldAmmoIndex );
        lstInstalledAmmoValueChanged( null );
    }//GEN-LAST:event_btnChangeAmmoActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        dispose();
    }//GEN-LAST:event_btnDoneActionPerformed

    private void lstInstalledAmmoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstInstalledAmmoValueChanged
        if( lstInstalledAmmo.getSelectedIndex() < 0 ) { return; }
        Ammunition a = (Ammunition) Ammo.get( lstInstalledAmmo.getSelectedIndex() );
        if( rdoAllAmmo.isSelected() ) {
            lstAvailableAmmo.setListData( Data.GetEquipment().GetAllAmmo( a.GetAmmoIndex(), cmbRulesLevel.getSelectedIndex() ) );
        } else if( rdoCanonAmmo.isSelected() ) {
            lstAvailableAmmo.setListData( Data.GetEquipment().GetAmmoByYear( a.GetAmmoIndex(), CurMech.GetYear(), cmbRulesLevel.getSelectedIndex(), CurMech ) );
        } else if( rdoYearAmmo.isSelected() ) {
            int year = 0;
            try {
                year = Integer.parseInt( txtAmmoYear.getText() );
            } catch( Exception e ) {
                javax.swing.JOptionPane.showMessageDialog( this, "The ammo year is not a number, using the default." );
                txtAmmoYear.setText( "" + CurMech.GetYear() );
                lstAvailableAmmo.setListData( Data.GetEquipment().GetAllAmmo( a.GetAmmoIndex(), cmbRulesLevel.getSelectedIndex() ) );
                lstAvailableAmmo.repaint();
                return;
            }
            lstAvailableAmmo.setListData( Data.GetEquipment().GetAmmoByYear( a.GetAmmoIndex(), year, cmbRulesLevel.getSelectedIndex(), CurMech ) );
        } else {
            lstAvailableAmmo.setListData( Data.GetEquipment().GetAllAmmo( a.GetAmmoIndex(), cmbRulesLevel.getSelectedIndex() ) );
        }
        lstAvailableAmmo.repaint();
    }//GEN-LAST:event_lstInstalledAmmoValueChanged

    private void rdoAllAmmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAllAmmoActionPerformed
        cmbRulesLevel.setEnabled( false );
        txtAmmoYear.setEnabled( false );
        lstInstalledAmmoValueChanged( null );
    }//GEN-LAST:event_rdoAllAmmoActionPerformed

    private void rdoCanonAmmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoCanonAmmoActionPerformed
        cmbRulesLevel.setEnabled( true );
        txtAmmoYear.setEnabled( false );
        lstInstalledAmmoValueChanged( null );
    }//GEN-LAST:event_rdoCanonAmmoActionPerformed

    private void rdoYearAmmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoYearAmmoActionPerformed
        cmbRulesLevel.setEnabled( true );
        txtAmmoYear.setEnabled( true );
        lstInstalledAmmoValueChanged( null );
    }//GEN-LAST:event_rdoYearAmmoActionPerformed

    private void txtAmmoYearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmmoYearKeyTyped
        if( txtAmmoYear.getText().equals( "" ) ) { return; }
        int year = 0;
        try {
            year = Integer.parseInt( txtAmmoYear.getText() );
        } catch( Exception e ) {
            javax.swing.JOptionPane.showMessageDialog( this, "The ammo year is not a number, using the default." );
            txtAmmoYear.setText( "" + CurMech.GetYear() );
        }
        lstInstalledAmmoValueChanged( null );
    }//GEN-LAST:event_txtAmmoYearKeyTyped

    private void cmbRulesLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRulesLevelActionPerformed
        if( cmbRulesLevel.getSelectedIndex() < CurMech.GetRulesLevel() ) { 
            javax.swing.JOptionPane.showMessageDialog( this, "You cannot use a Rules Level lower than the 'Mech's." );
            cmbRulesLevel.setSelectedIndex( CurMech.GetRulesLevel() );
        }
        lstInstalledAmmoValueChanged( null );
    }//GEN-LAST:event_cmbRulesLevelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeAmmo;
    private javax.swing.JButton btnDone;
    private javax.swing.JComboBox cmbRulesLevel;
    private javax.swing.ButtonGroup grpAmmoChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstAvailableAmmo;
    private javax.swing.JList lstInstalledAmmo;
    private javax.swing.JRadioButton rdoAllAmmo;
    private javax.swing.JRadioButton rdoCanonAmmo;
    private javax.swing.JRadioButton rdoYearAmmo;
    private javax.swing.JTextField txtAmmoYear;
    // End of variables declaration//GEN-END:variables

}