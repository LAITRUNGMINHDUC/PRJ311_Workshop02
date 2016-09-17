/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author duclt
 */
public class MethodSupport {

    static void rdbGrouping(JRadioButton[] myGroup) {
        ButtonGroup bg = new ButtonGroup();
        for (JRadioButton x : myGroup) {
            bg.add(x);
        }
        myGroup[0].setSelected(true);
    }

    static void loadData(EmployeeModel<Employee> model, HashSet<String> codes) {
        try {
            File f = new File("Employee.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                str = str.trim();
                if (str.length() > 0) {
                    StringTokenizer stk = new StringTokenizer(str, ";");
                    String code = stk.nextToken().trim().toUpperCase();
                    String name = stk.nextToken().trim();
                    String address = stk.nextToken().trim();
                    String sexStr = stk.nextToken().trim();
                    boolean sex = (sexStr.equalsIgnoreCase("MALE") == true);
                    int salary = Integer.parseInt(stk.nextToken());
                    model.getData().add(new Employee(code, name, address, sex, salary));
                    codes.add(code);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    static void clearTxtbox(JTextField[] myText) {
        for (JTextField x : myText) {
            x.setText("");
        }
        myText[0].setEditable(true);
        myText[0].setBackground(Color.WHITE);
    }

    static boolean validateData(JTextField[] myText, HashSet<String> codes, boolean addNew, boolean changed) {
        for (JTextField x : myText) {
            if (!Validator.checkNotBlank(x.getText().trim())) {
                JOptionPane.showMessageDialog(null, x.getName() + " is required");
                x.requestFocus();
                return false;
            }

            if (x.getName().compareTo("Name") == 0) {
                String pattern = "\\D{2,30}";
                if (!Validator.checkText(x.getText().trim(), pattern)) {
                    JOptionPane.showMessageDialog(null, x.getName()
                            + " can't contain numbers and range from 2 to 30 characters");
                    x.requestFocus();
                    return false;
                }
            }
            if (x.getName().compareTo("Address") == 0) {
                String pattern = "[0-9/]{1,5}([,\\s]+[A-Za-z0-9\\s]{2,50}){1,5}";
                if (!Validator.checkText(x.getText().trim(), pattern)) {
                    JOptionPane.showMessageDialog(null, x.getName()
                            + " should contain numbers, words, spacebar or comma and must be longer than 10 chars." 
                            + "\\n" 
                            + "Format should be: [Numbers], [Street], [Town], [District], [City], [Country]");
                    x.requestFocus();
                    return false;
                }

            }

            if (x.getName().compareTo("Code") == 0) {
                String pattern = "E\\d{3}";
                if (!Validator.checkText(x.getText().trim().toUpperCase(), pattern)) {
                    JOptionPane.showMessageDialog(null, x.getName() + " is invalid. Format here: Exxx");
                    x.requestFocus();
                    return false;
                }
                if (addNew && changed && !Validator.checkDuplicate(x.getText().trim().toUpperCase(), codes)) {
                    JOptionPane.showMessageDialog(null, x.getName() + " is duplicate");
                    x.requestFocus();
                    return false;
                }
            }

            if (x.getName().compareTo("Salary") == 0) {
                if (!Validator.checkSalary(x.getText().trim())) {
                    JOptionPane.showMessageDialog(null, x.getName() + " is invalid. Must be integer");
                    x.requestFocus();
                    return false;
                }
            }
        }
        return true;
    }

    static boolean saveData(EmployeeModel<Employee> model) {
        try {
            File f = new File("Employee.txt");
            PrintWriter pw = new PrintWriter(f);
            for (Employee x : model.getData()) {
                pw.println(x);
            }
            pw.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
