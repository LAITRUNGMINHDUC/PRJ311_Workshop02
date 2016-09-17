/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

import java.util.HashSet;

/**
 *
 * @author duclt
 */
public class Validator {

    static boolean checkNotBlank(String S) {
        return !S.isEmpty();
    }

    static boolean checkText(String S, String pattern) {
        return S.matches(pattern);
    }

    static boolean checkSalary(String S) {
        try {
            Integer.parseInt(S);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static boolean checkDuplicate(String S, HashSet<String> codes) {
        return codes.add(S);
    }
}
