// This snippet creates a new class that extends JInternalFrame.  Use it
// together with the DesktopFrame snippet to create the structure of an MDI
// (multiple document interface) application.

// Instructions:
// 1. Create a DesktopPane snippet.  Be sure to specify the "Name of Internal
//    Frame" parameter.  The snippet has a main method, so is usually placed
//    in a new, empty project.
// 2. Create an InternalFrame snippet, specifying the same class name.
// 3. You can now compile and run the project. Use the File menu to open new
//    internal frames.

//Title:
//Version:
//Copyright:
//Author:
//Company:
//Description:

package  PAD;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InternalFrame extends JInternalFrame {
  JFileChooser jFileChooser1 = new JFileChooser();
  JButton jButton1 = new JButton();

  public InternalFrame() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  jFileChooser1.setEnabled(false);
  jButton1.setEnabled(true);
  }

  public void jbInit() throws Exception {
    this.setClosable(true);
    this.setIconifiable(true);
    this.setMaximizable(true);
    this.setResizable(true);
    this.getContentPane().setLayout(null);
    jFileChooser1.setBounds(new Rectangle(83, 37, 376, 121));
    jButton1.setText("jButton1");
    jButton1.setBounds(new Rectangle(178, 283, 79, 27));
    jButton1.addMouseListener(new java.awt.event.MouseAdapter() {

      public void mouseClicked(MouseEvent e) {
        jButton1_mouseClicked(e);
      }
    });
    this.getContentPane().add(jFileChooser1, null);
    this.getContentPane().add(jButton1, null);
  }

  void jFileChooser1_actionPerformed(ActionEvent e) {

  }

  void jButton1_mouseClicked(MouseEvent e) {
  jFileChooser1.setEnabled(false);
  }



}
                
