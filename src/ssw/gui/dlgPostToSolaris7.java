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

import java.awt.Cursor;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import ssw.Constants;
import ssw.components.Mech;
import ssw.filehandlers.HTMLWriter;
import ssw.filehandlers.XMLRPCClient;

public class dlgPostToSolaris7 extends javax.swing.JDialog {
    private frmMain Parent;
    private String Callsign = "",
                   Password = "", // must encrypt this in memory
                   UserImage = "-1",
                   ImageName = "none";
    private int UserID = -1;
    private String[][] Armories = null;
    private Mech CurMech;
    private Cursor Hourglass = new Cursor( Cursor.WAIT_CURSOR );
    private Cursor NormalCursor = new Cursor( Cursor.DEFAULT_CURSOR );

    /** Creates new form dlgPostToSolaris7 */
    public dlgPostToSolaris7(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        Parent = (frmMain) parent;
        CurMech = Parent.CurMech;
        setTitle( "Post to Solaris7.com" );
        initComponents();
        txtCallsign.setText(Parent.Prefs.get("S7Callsign", ""));
        txtPassword.setText(Parent.Prefs.get("S7Password", ""));
        
        if( ! CurMech.GetOptions().S7Callsign.equals( "null" ) ) {
            txtCallsign.setText( CurMech.GetOptions().S7Callsign );
            if( ! CurMech.GetOptions().S7Password.equals( "null" ) ) {
                // decode password
                txtPassword.setText( CurMech.GetOptions().S7Password );
            }
            if( CurMech.GetOptions().S7UserID != -1 ) {
                UserID = CurMech.GetOptions().S7UserID;
            }
            chkSaveInfo.setSelected( true );
        }

        // load the image ID, if any
        if( ! CurMech.GetSolaris7ImageID().equals( "0" ) |! CurMech.GetSolaris7ImageID().equals( "-1" ) ) {
            UserImage = CurMech.GetSolaris7ImageID();
            txtImageName.setText( CurMech.GetSolaris7ImageID() );
        }
    } 

    private String GetHTMLFromFile( String Filename ) throws Exception {
        BufferedReader FR = new BufferedReader( new FileReader( Filename ) );
        String retval = "";
        boolean EOF = false;

        while( EOF == false ) {
            String read = FR.readLine();
            if( read == null ) {
                EOF = true;
            } else {
                retval += read;
            }
        }

        FR.close();
        return retval;
    }

    private String GetTROYear() {
        switch( cmbTROYear.getSelectedIndex() ) {
            case 0:
                return "2750";
            case 1:
                return "3025";
            case 2:
                return "3026";
            case 3:
                return "3039";
            case 4:
                return "3050";
            case 5:
                return "3055";
            case 6:
                return "3057";
            case 7:
                return "3058";
            case 8:
                return "3060";
            case 9:
                return "3067";
            case 10:
                return "3075";
            default:
                return "2750";
        }
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

        jPanel1 = new javax.swing.JPanel();
        lblCallsign = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtCallsign = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chkSaveInfo = new javax.swing.JCheckBox();
        txtPassword = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbArmories = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtImageName = new javax.swing.JTextField();
        btnBrowseImages = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbTROYear = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btnPost = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnGetArmories = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Login Information"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblCallsign.setText("Callsign");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel1.add(lblCallsign, gridBagConstraints);

        lblPassword.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel1.add(lblPassword, gridBagConstraints);

        txtCallsign.setMaximumSize(new java.awt.Dimension(150, 20));
        txtCallsign.setMinimumSize(new java.awt.Dimension(150, 20));
        txtCallsign.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(txtCallsign, gridBagConstraints);

        jLabel2.setText("Enter your Solaris7.com user info:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 2);
        jPanel1.add(jLabel2, gridBagConstraints);

        chkSaveInfo.setText("Save account information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
        jPanel1.add(chkSaveInfo, gridBagConstraints);

        txtPassword.setMaximumSize(new java.awt.Dimension(150, 20));
        txtPassword.setMinimumSize(new java.awt.Dimension(150, 20));
        txtPassword.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel1.add(txtPassword, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Choose Armory"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Post the Mech to this Armory:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 4, 2);
        jPanel2.add(jLabel1, gridBagConstraints);

        cmbArmories.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "none" }));
        cmbArmories.setEnabled(false);
        cmbArmories.setMaximumSize(new java.awt.Dimension(230, 20));
        cmbArmories.setMinimumSize(new java.awt.Dimension(230, 20));
        cmbArmories.setPreferredSize(new java.awt.Dimension(230, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(cmbArmories, gridBagConstraints);

        jLabel3.setText("Use this image:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        txtImageName.setText("none");
        txtImageName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtImageName.setEnabled(false);
        txtImageName.setMaximumSize(new java.awt.Dimension(150, 20));
        txtImageName.setMinimumSize(new java.awt.Dimension(150, 20));
        txtImageName.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(txtImageName, gridBagConstraints);

        btnBrowseImages.setText("Browse");
        btnBrowseImages.setEnabled(false);
        btnBrowseImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseImagesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel2.add(btnBrowseImages, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Select TRO Year:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        jPanel2.add(jLabel4, gridBagConstraints);

        cmbTROYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2750", "3025", "3026", "3039", "3050", "3055", "3057", "3058", "3060", "3067", "3075" }));
        cmbTROYear.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel2.add(cmbTROYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jPanel2, gridBagConstraints);

        btnPost.setText("Post Mech");
        btnPost.setEnabled(false);
        btnPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostActionPerformed(evt);
            }
        });
        jPanel3.add(btnPost);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        getContentPane().add(jPanel3, gridBagConstraints);

        btnGetArmories.setText("Retrieve Armories");
        btnGetArmories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetArmoriesActionPerformed(evt);
            }
        });
        jPanel4.add(btnGetArmories);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
    dispose();
}//GEN-LAST:event_btnCancelActionPerformed

private void btnGetArmoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetArmoriesActionPerformed
    setCursor( Hourglass );
    XMLRPCClient serve = new XMLRPCClient( Constants.Solaris7URL );
    Callsign = txtCallsign.getText();
    Password = String.copyValueOf( txtPassword.getPassword() );

    try {
        if( CurMech.GetOptions().S7UserID == -1 ) {
            UserID = serve.GetMemberID( Callsign, Password );
            Parent.Prefs.put("S7Callsign", Callsign);
            Parent.Prefs.put("S7Password", Password);
        }
        if( UserID != -1 ) {
            Armories = serve.GetArmoryList( UserID );
        } else {
            javax.swing.JOptionPane.showMessageDialog( this, "Could not retrieve a MemberID from this Callsign or Password.\nThe Mech cannot be posted." );
            setCursor( NormalCursor );
            return;
        }
    } catch( Exception e ) {
        javax.swing.JOptionPane.showMessageDialog( this, e.getMessage() );
        setCursor( NormalCursor );
        return;
    }

    if( Armories != null ) {
        String[] input = new String[Armories.length];
        for( int i = 0; i < Armories.length; i++ ) {
            input[i] = Armories[i][0];
        }
        cmbArmories.setModel( new DefaultComboBoxModel( input ) );
        cmbArmories.setEnabled( true );
        btnPost.setEnabled( true );
        btnBrowseImages.setEnabled( true );
        cmbTROYear.setEnabled( true );
        setCursor( NormalCursor );
    } else {
        javax.swing.JOptionPane.showMessageDialog( this, "No armories were returned from this Callsign or Password.\nThe Mech cannot be posted." );
        setCursor( NormalCursor );
        return;
    }
}//GEN-LAST:event_btnGetArmoriesActionPerformed

private void btnPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostActionPerformed
    // see if we need to save the username, password, and user ID
    setCursor( Hourglass );
    if( chkSaveInfo.isSelected() ) {
        CurMech.GetOptions().S7Callsign = txtCallsign.getText();
        CurMech.GetOptions().S7UserID = UserID;
        CurMech.GetOptions().S7Password = String.copyValueOf( txtPassword.getPassword() );
    }

    // export the mech to HTML
    String file = "";
    XMLRPCClient serve = new XMLRPCClient( Constants.Solaris7URL );

    if( CurMech.GetModel().isEmpty() ) {
        file = CurMech.GetName() + ".html";
    } else {
        file = CurMech.GetName() + " " + CurMech.GetModel() + ".html";
    }
    if( ! CurMech.GetOptions().HTMLPath.equals( "none" ) ) {
        file = CurMech.GetOptions().HTMLPath + File.separator + file;
    }

    HTMLWriter HTMw = new HTMLWriter( CurMech, CurMech.GetOptions() );
    String HTMLout = "";

    try {
        HTMw.WriteHTML( Constants.HTMLTemplateName, file );
        HTMLout = GetHTMLFromFile( file );
    } catch( IOException e ) {
        javax.swing.JOptionPane.showMessageDialog( this, "There was a problem writing or reading the file:\n" + e.getMessage() );
        setCursor( NormalCursor );
        return;
    } catch( Exception e ) {
        javax.swing.JOptionPane.showMessageDialog( this, e.getMessage() );
        setCursor( NormalCursor );
        return;
    }

    String ArmoryID = Armories[cmbArmories.getSelectedIndex()][1];
    String TROYear = GetTROYear();
    String MechID = "0";
    try {
        MechID = serve.PostToSolaris7( "" + UserID, ArmoryID, HTMLout, UserImage, TROYear, CurMech );
    } catch( Exception e ) {
        javax.swing.JOptionPane.showMessageDialog( this, "There was a problem with the server:\n" + e.getMessage() );
        setCursor( NormalCursor );
        return;
    }

    if( MechID.equals( "0" ) ) {
        javax.swing.JOptionPane.showMessageDialog( this, "Mech was not posted to Solaris7.com.  The reason is unknown." );
    } else {
        javax.swing.JOptionPane.showMessageDialog( this, "Mech successfully posted to Solaris7.com!" );
        CurMech.SetSolaris7ID( MechID );
        if( ! UserImage.equals( "-1" ) |! UserImage.equals( "0" ) ) {
            CurMech.SetSolaris7ImageID( UserImage );
        }
    }
    setCursor( NormalCursor );
    dispose();
}//GEN-LAST:event_btnPostActionPerformed

private void btnBrowseImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseImagesActionPerformed
    setCursor( Hourglass );
    dlgBrowseS7Images ImageViewer = new dlgBrowseS7Images( Parent, true, UserID, CurMech.GetSolaris7ImageID() );
    Point p = Parent.getLocationOnScreen();
    ImageViewer.setLocation( p.x + 100, p.y );

    ImageViewer.setVisible( true );
    if( ! ImageViewer.GetImageID().equals( "-1" ) ) {
        UserImage = ImageViewer.GetImageID();
        ImageName = ImageViewer.GetImageName();
    }
    ImageViewer.dispose();
    txtImageName.setText( ImageName );
    setCursor( NormalCursor );
}//GEN-LAST:event_btnBrowseImagesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseImages;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGetArmories;
    private javax.swing.JButton btnPost;
    private javax.swing.JCheckBox chkSaveInfo;
    private javax.swing.JComboBox cmbArmories;
    private javax.swing.JComboBox cmbTROYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCallsign;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField txtCallsign;
    private javax.swing.JTextField txtImageName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

}
