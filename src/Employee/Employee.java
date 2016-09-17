/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee;

/**
 *
 * @author duclt
 */
public class Employee {

    private String code, name, address;
    private boolean sex;
    private int salary;

    public Employee() {

    }

    public Employee(String code, String name, String address, boolean sex, int salary) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        String pattern = "%s;%s;%s;%s;%s";
        String sex = "";
        if (this.sex) {
            sex = "Male";
        } else {
            sex = "Female";
        }
        return String.format(pattern, this.code, this.name, this.address, sex, this.salary);
    }

    public boolean equals(Employee tmp) {
        return this.address.equals(tmp.address)
                && this.code.equals(tmp.code)
                && this.name.equals(tmp.name)
                && (this.salary == tmp.salary)
                && (this.sex == tmp.sex);
    }
}
