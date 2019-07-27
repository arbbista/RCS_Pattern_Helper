import models.AttributeModel;
import models.MethodModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
  private JPanel MainJpanel;
  private JButton buildButton;
  private JTextField srcPathTextBox;
  private JTextField attributeNameTextBox1;
  private JTextField attributeNameTextBox2;
  private JTextField attributeNameTextBox3;
  private JTextField attributeNameTextBox4;
  private JTextField attributeNameTextBox5;
  private JTextField attributeNameTextBox6;
  private JTextField attributeNameTextBox7;
  private JCheckBox CreateJooqMapperCheckBox;
  private JCheckBox createBuilderCheckBox;
  private JCheckBox createRCSCheckBox;
  private JTextField modelNameTextbox;
  private JButton chooseFileButton;
  private JComboBox attributeTypecomboBox1;
  private JComboBox attributeTypecomboBox2;
  private JComboBox attributeTypecomboBox3;
  private JComboBox attributeTypecomboBox4;
  private JComboBox attributeTypecomboBox5;
  private JComboBox attributeTypecomboBox6;
  private JComboBox attributePrivacycomboBox1;
  private JComboBox attributePrivacycomboBox2;
  private JComboBox attributePrivacycomboBox3;
  private JComboBox attributePrivacycomboBox4;
  private JComboBox attributePrivacycomboBox5;
  private JComboBox attributePrivacycomboBox6;
  private JComboBox attributePrivacycomboBox7;
  private JButton xButton;
  private JLabel messageLabel;
  private JTextField RCSPrefixTextBox;
  private JTextField attributeNameTextBox8;
  private JTextField attributeTypeTextBox2;
  private JTextField attributeTypeTextBox1;
  private JTabbedPane tabbedPane;
  private JPanel ModelBuilerBodyTabbedPane;
  private JPanel RCSToolsTabbedPane;
  private JPanel RCSToolsJPanel;
  private JTextField fromTextBox;
  private JTextField toTextBox;
  private JButton addMethodButton;
  private JTextField methodNameMethodAdderTextBox;
  private JComboBox themeComboBox;
  private JButton MethodReplacerButton;
  private JTextField prefixToReplaceTextBox;
  private JTextField RCSPrefixTexBox;
  private JPanel RCSCreatorTabbedPane;
  private JTextField prefixMethodAdderTextBox;
  private JLabel prefixToAddMethodLabel;
  private JComboBox methodPrivacyMethodAdderComboBox;
  private JComboBox methodReturnTypeMethodAdderComboBox;
  private JComboBox methodParameterTypeComboBox1;
  private JTextField methodParamterNameTextBox1;
  private JComboBox methodParameterTypeComboBox2;
  private JTextField methodParameterTypeTextBox3;
  private JTextField methodParamterNameTextBox2;
  private JTextField methodParamterNameTextBox3;
  private JComboBox methodParameterAnnotationComboBox1;
  private JComboBox methodParameterAnnotationComboBox2;
  private JComboBox methodParameterAnnotationComboBox3;
  private JButton aboutButton;
  private JPanel RCSCreatorJPanel;
  private JPanel ModelBuilerBodyTabbedPane3;

  public Main() {
    buildButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (isFormValid()) {
          try {
            if (tabbedPane.getSelectedIndex() == 0) {
              //Create model
              if (!modelNameTextbox.getText().trim().isEmpty()) {
                ModelCreator modelCreator = new ModelCreator(srcPathTextBox.getText(),
                        modelNameTextbox.getText(), getAddedModelAttributes());
                modelCreator.create();

                //Create jooq mapper
                if (CreateJooqMapperCheckBox.isSelected()) {
                  JooqMapperCreator jooqMapperCreator = new JooqMapperCreator(srcPathTextBox.getText(),
                          modelNameTextbox.getText(), getAddedModelAttributes());
                  jooqMapperCreator.create();
                }

                //Create model builder
                if (createBuilderCheckBox.isSelected()) {
                  BuilderCreator builderCreator = new BuilderCreator(srcPathTextBox.getText(),
                          modelNameTextbox.getText(), getAddedModelAttributes());
                  builderCreator.create();
                }

                showMessage("Model created successfully!", Color.green);
              } else {
                showMessage("Error creating model: Please specify the model name", Color.red);
              }
            } else if (tabbedPane.getSelectedIndex() == 2) {

              if (!RCSPrefixTexBox.getText().trim().isEmpty()) {
                RCSCreator rcsCreator = new RCSCreator(srcPathTextBox.getText(), RCSPrefixTexBox.getText());
                rcsCreator.create();
                showMessage("RCS Created successfully!", Color.green);
              } else {
                showMessage("Error creating RCS: Please specify the prefix", Color.red);
              }
            }
          } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error creating file: \n" +
                    ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    });

    xButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    srcPathTextBox.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        srcPathTextBox.selectAll();
      }
    });

    modelNameTextbox.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        modelNameTextbox.selectAll();
      }
    });

    chooseFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser j = new JFileChooser("C:/");
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        j.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")));
        j.showSaveDialog(null);

        if (j.getSelectedFile() != null)
          srcPathTextBox.setText(j.getSelectedFile().getPath());
      }
    });

    addMethodButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (isFormValid() && isMethodAdderFormValid()) {

          if (!RCSfilesExists(prefixMethodAdderTextBox.getText())) {
            showMessage("Error RCS prefix: No RCS files found with the specified prefix", Color.red);
          }

          List<AttributeModel> parameters = new ArrayList<>();

          //Get method parameters and add it to the parameters list
          if (!methodParamterNameTextBox1.getText().trim().isEmpty()) {
            parameters.add(new AttributeModel(null, (String) methodParameterAnnotationComboBox1.getSelectedItem(),
                    (String) methodParameterTypeComboBox1.getSelectedItem(),
                    methodParamterNameTextBox1.getText()));
          }

          if (!methodParamterNameTextBox2.getText().trim().isEmpty()) {
            parameters.add(new AttributeModel(null, (String) methodParameterAnnotationComboBox2.getSelectedItem(),
                    (String) methodParameterTypeComboBox2.getSelectedItem(),
                    methodParamterNameTextBox2.getText()));
          }

          if (!methodParamterNameTextBox3.getText().trim().isEmpty()) {
            parameters.add(new AttributeModel(null, (String) methodParameterAnnotationComboBox3.getSelectedItem(),
                    methodParameterTypeTextBox3.getText(),
                    methodParamterNameTextBox3.getText()));
          }

          MethodModel method = new MethodModel((String) methodPrivacyMethodAdderComboBox.getSelectedItem(),
                  (String) methodReturnTypeMethodAdderComboBox.getSelectedItem(),
                  methodNameMethodAdderTextBox.getText(), parameters);

          MethodAdder methodAdder = new MethodAdder(srcPathTextBox.getText(), prefixMethodAdderTextBox.getText(), method);

          try {
            methodAdder.add();
            showMessage("Method added successfully!", Color.green);
          } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error while writing/reading file:\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    });

    //Theme changer
    themeComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (themeComboBox.getSelectedItem().toString().equals("Dark")) {
          MainJpanel.setBackground(new Color(60, 63, 65));
          ModelBuilerBodyTabbedPane.setBackground(new Color(60, 63, 65));
          RCSToolsTabbedPane.setBackground(new Color(60, 63, 65));
          RCSCreatorTabbedPane.setBackground(new Color(60, 63, 65));
          RCSCreatorJPanel.setBackground(new Color(60, 63, 65));
          RCSToolsJPanel.setBackground(new Color(60, 63, 65));
        } else {
          MainJpanel.setBackground(new Color(187, 187, 187));
          ModelBuilerBodyTabbedPane.setBackground(new Color(187, 187, 187));
          RCSToolsTabbedPane.setBackground(new Color(187, 187, 187));
          RCSCreatorTabbedPane.setBackground(new Color(187, 187, 187));
          RCSCreatorJPanel.setBackground(new Color(187, 187, 187));
          RCSToolsJPanel.setBackground(new Color(187, 187, 187));
        }
      }
    });

    MethodReplacerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (isFormValid()) {
          if (!RCSfilesExists(prefixToReplaceTextBox.getText())) {
            showMessage("Error RCS prefix: No RCS files found with the specified prefix", Color.red);
          } else if (fromTextBox.getText().trim().isEmpty()) {
            showMessage("Error from value: Please specify the value you want to replace", Color.red);
          } else if (toTextBox.getText().trim().isEmpty()) {
            showMessage("Error to value: Please specify value you want to be replaced with", Color.red);
          } else {
            MethodReplacer methodReplacer = new MethodReplacer(srcPathTextBox.getText(),
                    prefixToReplaceTextBox.getText(), fromTextBox.getText(), toTextBox.getText());
            try {
              methodReplacer.replace();
              showMessage("Method replaced successfully!", Color.green);
            } catch (IOException ex) {
              JOptionPane.showMessageDialog(null,
                      "Error while writing/reading file:\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
          }
        }
      }
    });

    RCSPrefixTexBox.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        if (!RCSPrefixTexBox.getText().trim().isEmpty()) {
          String prefix = RCSPrefixTexBox.getText();
          RCSPrefixTexBox.setText(prefix.substring(0, 1).toUpperCase() + prefix.substring(1));
        }
      }
    });

    prefixToReplaceTextBox.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        if (!prefixToReplaceTextBox.getText().trim().isEmpty()) {
          String prefix = prefixToReplaceTextBox.getText();
          prefixToReplaceTextBox.setText(prefix.substring(0, 1).toUpperCase() + prefix.substring(1));
        }
      }
    });

    modelNameTextbox.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        if (!modelNameTextbox.getText().trim().isEmpty()) {
          String prefix = modelNameTextbox.getText();
          modelNameTextbox.setText(prefix.substring(0, 1).toUpperCase() + prefix.substring(1));
        }
      }
    });

    aboutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "Author: Arber Pllana\n" + "Contact: arbbista@gmail.com");
      }
    });
  }

  private ArrayList<AttributeModel> getAddedModelAttributes() {
    ArrayList<AttributeModel> attributes = new ArrayList<>();

    //Add first attribute
    if (!attributeNameTextBox1.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox1.getSelectedItem(),
              (String) attributeTypecomboBox1.getSelectedItem(), attributeNameTextBox1.getText()));
    }

    //Add second attribute
    if (!attributeNameTextBox2.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox2.getSelectedItem(),
              (String) attributeTypecomboBox2.getSelectedItem(), attributeNameTextBox2.getText()));
    }

    //Add 3rd attribute
    if (!attributeNameTextBox3.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox3.getSelectedItem(),
              (String) attributeTypecomboBox3.getSelectedItem(), attributeNameTextBox3.getText()));
    }

    //Add 4th attribute
    if (!attributeNameTextBox4.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox4.getSelectedItem(),
              (String) attributeTypecomboBox4.getSelectedItem(), attributeNameTextBox4.getText()));
    }

    //Add 5th attribute
    if (!attributeNameTextBox5.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox5.getSelectedItem(),
              (String) attributeTypecomboBox5.getSelectedItem(), attributeNameTextBox5.getText()));
    }

    //Add 6th attribute
    if (!attributeNameTextBox6.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox6.getSelectedItem(),
              (String) attributeTypecomboBox6.getSelectedItem(), attributeNameTextBox6.getText()));
    }

    //Add 7th attribute
    if (!attributeNameTextBox7.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox7.getSelectedItem(),
              attributeTypeTextBox1.getText(), attributeNameTextBox7.getText()));
    }

    //Add 8th attribute
    if (!attributeNameTextBox8.getText().trim().isEmpty()) {
      attributes.add(new AttributeModel((String) attributePrivacycomboBox7.getSelectedItem(),
              attributeTypeTextBox2.getText(), attributeNameTextBox7.getText()));
    }

    return attributes;
  }

  private boolean isFormValid() {
    if (srcPathTextBox.getText().trim().isEmpty()) {
      showMessage("Error path: Please specify the source path", Color.red);
      return false;
    }
    return true;
  }

  private boolean isMethodAdderFormValid() {
    if (prefixMethodAdderTextBox.getText().trim().isEmpty()) {
      showMessage("Error prefix: Please specify the prefix", Color.red);
      return false;
    } else if (methodNameMethodAdderTextBox.getText().trim().isEmpty()) {
      showMessage("Error method name: Please specify the method name", Color.red);
      return false;
    } else if (methodParamterNameTextBox3.getText().trim().isEmpty() && methodParameterTypeTextBox3.getText().trim().isEmpty()) {
      showMessage("Error method parameter type: Please specify the method parameter type", Color.red);
      return false;
    } else if (methodParamterNameTextBox3.getText().trim().isEmpty() && !methodParameterTypeTextBox3.getText().trim().isEmpty()) {
      showMessage("Error method parameter name: Please specify the method parameter name", Color.red);
      return false;
    }

    return true;
  }

  //If at least one of RCS files exists, it returns true
  private boolean RCSfilesExists(String prefix) {
    File controller = new File(srcPathTextBox.getText() + "\\" + prefix + "Controller.java");
    File service = new File(srcPathTextBox.getText() + "\\" + prefix + "Service.java");
    File serviceImpl = new File(srcPathTextBox.getText() + "\\" + prefix + "ServiceImpl.java");
    File repository = new File(srcPathTextBox.getText() + "\\" + prefix + "Repository.java");
    File repositoryImpl = new File(srcPathTextBox.getText() + "\\" + prefix + "RepositoryImpl.java");

    return controller.exists() || service.exists() || serviceImpl.exists() || repository.exists() || repositoryImpl.exists();
  }

  private static String getCurrentTimeUsingDate() {
    Date date = new Date();
    String strDateFormat = "kk:mm:ss";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

    return dateFormat.format(date);
  }

  private void showMessage(String message, Color color) {
    messageLabel.setForeground(color);
    messageLabel.setText("(" + getCurrentTimeUsingDate() + ") " + message);
  }

  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame("RCS Pattern Helper");

    frame.setUndecorated(true); // Remove title bar

    frame.setContentPane(new Main().MainJpanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);

    //Remove focus from inner components on startup
    frame.requestFocusInWindow();

    frame.setIconImage(ImageIO.read(Main.class.getResourceAsStream("/images/app-icon.png")));
  }

  private void createUIComponents() {
    // TODO: place custom component creation code here
  }
}
