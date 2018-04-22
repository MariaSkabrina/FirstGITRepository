
package com.progress.progressmavenapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class JDBC_test_GIT_hub {
    
     public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");

        System.out.printf("Vavedete 1 za tarsene po parvo ime, "
                + "2 za tarsene po vtoro ime, 3 za tarsene po otdel,\n"
                + "4 za tarsene i po trite kriterii ednovremenno, vavedeni posledovatelno."
                + "5 za sazdavane na nov slujitel - parvo_ime, vtoro_ime, salary\n"
                + "6 za iztrivane na slujitel po vavedeno employee ID\n");

        String choice1 = sc.nextLine();
        boolean result = false;
        int rowsAffected = 0;

        while (!choice1.equals("Exit")) {

            int ch = Integer.parseInt(choice1);

            Connection con = null;
            ResultSet rs = null;
            PreparedStatement stmtPR = null;

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/hr?"
                    + "user=root&password=mgskabrina");

            try {
                if (ch == 1) {
                    String firstName = sc.nextLine();

                    String selectQuery1 = "SELECT e.first_name, e.last_name from employees e where e.first_name = '"
                            + firstName + "'";
                    stmtPR = con.prepareStatement(selectQuery1);

                    rs = stmtPR.executeQuery(selectQuery1);
                    result = stmtPR.execute(selectQuery1);

                    while (rs.next()) {
                        String name1 = rs.getString("e.first_name");
                        String name2 = rs.getString("e.last_name");

                        System.out.println(name1 + "  " + name2);
                    }
                }
                if (ch == 2) {

                    String lastName = sc.nextLine();

                    String selectQuery2 = "SELECT e.first_name, e.last_name from employees e where e.last_name = '"
                            + lastName + "'";
                    stmtPR = con.prepareStatement(selectQuery2);

                    rs = stmtPR.executeQuery(selectQuery2);
                    result = stmtPR.execute(selectQuery2);

                    while (rs.next()) {
                        String name1 = rs.getString("e.first_name");
                        String name2 = rs.getString("e.last_name");

                        System.out.println(name1 + "  " + name2);
                    }
                }

                if (ch == 3) {
                    System.out.println("Molia vavedete nomer Department.");
                    int departmentID = Integer.parseInt(sc.nextLine());
                    String selectQuery3 = "SELECT e.first_name, e.last_name from employees e where e.department_id = "
                            + departmentID;
                    stmtPR = con.prepareStatement(selectQuery3);

                    rs = stmtPR.executeQuery(selectQuery3);
                    result = stmtPR.execute(selectQuery3);

                    while (rs.next()) {
                        String name1 = rs.getString("e.first_name");
                        String name2 = rs.getString("e.last_name");

                        System.out.println(name1 + "  " + name2);
                    }
                }
                if (ch == 4) {
                    System.out.println("Vavedete first_name.");
                    String firstName = sc.nextLine();
                    System.out.println("Vavedete last_name.");
                    String lastName = sc.nextLine();
                    int department_ID = Integer.parseInt(sc.nextLine());
                    System.out.println("Vavedete nomer department.");
                    String selectQuery4 = "SELECT e.first_name, e.last_name from employees e where e.first_name = '"
                            + firstName + "' and e.last_name = '" + lastName + "' and department_ID = " + department_ID;
                    stmtPR = con.prepareStatement(selectQuery4);
                    rs = stmtPR.executeQuery(selectQuery4);
                    result = stmtPR.execute(selectQuery4);

                    while (rs.next()) {
                        String name1 = rs.getString("e.first_name");
                        String name2 = rs.getString("e.last_name");
                        System.out.println(name1 + "  " + name2);
                    }
                }

                if (ch == 5) {
                    System.out.println("Vavedete employee_ID ");
                    int emplID = Integer.parseInt(sc.nextLine());
                    System.out.println("Vavedete last_name");
                    String lastName = sc.nextLine();
                    System.out.println("Vavedete email ");
                    String email = sc.nextLine();
                    System.out.println("vavedete start date: ");
                    String date = sc.nextLine();
                    System.out.println("vavedete job_ID ");
                    String job_ID = sc.nextLine();
                    System.out.println("Vavedete zaplata ");
                    double salary = Double.parseDouble(sc.nextLine());

                    String updateQuery5 = "INSERT INTO employees (employee_id, last_name, email, hire_date, job_id, salary) VALUES ("
                            + emplID + ", '"
                            + lastName + "', '"
                            + email + "', '" + date + "', '"
                            + job_ID + "', " + salary + ")";
                    stmtPR = con.prepareStatement(updateQuery5);
                    rowsAffected = stmtPR.executeUpdate(updateQuery5);
                    result = stmtPR.execute(updateQuery5);

                    while (rs.next()) {
                        Integer empID = null;
                        String newLast_Name = "";
                        String newEmail = "";
                        Date newHire_date = dt.parse(sc.nextLine());
                        String newJob_ID = "";
                        double newSalary = 0;
                        empID = rs.getInt("e.employee_id");
                        newLast_Name = rs.getString("e.last_name");
                        newEmail = rs.getString("e.email");
                        newHire_date = rs.getDate("e.hire_date");
                        newJob_ID = rs.getString("e.job_id");
                        newSalary = rs.getDouble("e.salary");

                        System.out.println(newLast_Name + " " + newEmail + " " + newHire_date
                                + " " + newJob_ID + " " + newSalary);
                    }

                }
                if (ch == 6) {
                    System.out.println("Vavedete nomer slujitel za iztrivane - integer: ");
                    int empID = Integer.parseInt(sc.nextLine());
                    String updateQuery6 = "DELETE from employees WHERE employee_id = " + empID;
                    stmtPR = con.prepareStatement(updateQuery6);
                    rowsAffected = stmtPR.executeUpdate(updateQuery6);
                    result = stmtPR.execute(updateQuery6);
                }
                choice1 = sc.nextLine();
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();

            } finally {
                if (result) {
                    rs.close();
                }

                stmtPR.close();
                con.close();
            }

        }
    }
}
