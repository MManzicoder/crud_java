import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static  Connection conn;
     static PreparedStatement psmt;
     static Statement stmt;
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learnjavajdbc?characterEncoding=latin1&useConfigs=maxPerformance", "root","bestcoder123@!");
            if(conn !=null) System.out.println("Successfully connected!");
            Statement stmt = conn.createStatement();
            while(true){
              ask();
            }
//             PreparedStatement pstmt = conn.prepareStatement("insert into student(names, email) values(?,?)");

            //create database
//            pstmt.execute(query);
//            System.out.println(result);


//            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
private static void ask() throws SQLException {
        int choice;
        System.out.println("----------------------- CRUD OPERATION --------------------------\n\n");
        System.out.println("1. Display all records");
        System.out.println("2. Add  a student");
        System.out.println("3. Get a student");
        System.out.println("4. Update a student");
        System.out.println("5. Delete a student");
        System.out.println("7. Quit");
        System.out.println("##################################################################");
        System.out.println("Enter choice");
        choice =  scanner.nextInt();
        switch (choice){
            case 1:
                display();
                break;
            case 2:
                addStudent();
                break;
            case 3:
                getStudent();
                break;
            case 4:
                updateStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 7:
                 System.exit(-1);
                 break;
            default:
                System.out.println("Enter a valid choice \n");
        }
    }

    private static void addStudent() throws SQLException {
        System.out.print("\nEnter firstname: ");
        String firstName = scanner.next();
        System.out.print("\nEnter lastname: ");
        String lastName = scanner.next();
        System.out.print("\nEnter email: ");
        String email = scanner.next();
//        if(checkIfEmailExists(email)) {
//            System.out.println("Student with given email already exists!");
//        }
        psmt = conn.prepareStatement("insert into student (firstname, lastname, email) values(?,?,?)");
        psmt.setString(1, firstName);
        psmt.setString(2, lastName);
        psmt.setString(3, email);
        psmt.execute();
        System.out.println("Added new student\n");
        display();

    }

    private static void display() throws SQLException {
        stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from student");
           boolean found = false;
            System.out.println("###### All records ######\n");
            System.out.println(" ID        FirstName         LastName               Email ");

            while(res.next()){
                found = true;
                System.out.println(" "+res.getInt("id")+"          "+res.getString("firstname")+"               "+res.getString("lastname")+"           "+res.getString("email"));
            }
            if(!found){
                System.out.println("No records found!");
            }
    }
    private static void getStudent() throws SQLException {
        System.out.print("studentID: ");
        int id = scanner.nextInt();
        stmt= conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from student where id="+id);
        if(res.next()) {
            System.out.println("###### Searched record ######\n");
            System.out.println(" ID        FirstName         LastName               Email ");
            System.out.println(" "+res.getInt("id")+"          "+res.getString("firstname")+"               "+res.getString("lastname")+"           "+res.getString("email"));

        }else{
            System.out.println("No student with the given ID!");
        }
    }
    private static boolean checkIfEmailExists(String email) throws SQLException {
        stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from student where email="+email);
        if(res !=null) return true;
        return false;
    }
    private static void updateStudent() throws SQLException {
        System.out.print("studentID: ");
        int id = scanner.nextInt();

        stmt= conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from student where id="+id);
        if(res.next()){
            System.out.println("select field to update");
            System.out.println("1. Firstname");
            System.out.println("2. Lastname");
            System.out.println("3. Email");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter new Firstname");
                    String firstname = scanner.next();
                    if(firstname == res.getString("firstname")){
                        System.out.println("Enter different name");
                        firstname = scanner.nextLine();
                    }
                    psmt = conn.prepareStatement("update student set firstname=? where id=?");
                    psmt.setString(1, firstname);
                    psmt.setInt(2, id);
                    psmt.executeUpdate();
                    System.out.println("Updated successfully!");
                    display();
                    break;
                case 2:
                    System.out.println("Enter new Lastname");
                    String lastname = scanner.next();
                    if(lastname == res.getString("lastname")){
                        System.out.println("Enter different name");
                        lastname = scanner.nextLine();
                    }
                    psmt = conn.prepareStatement("update student set lastname=? where id=?");
                    psmt.setString(1, lastname);
                    psmt.setInt(2, id);
                    psmt.executeUpdate();
                    System.out.println("Updated successfully!");
                    display();
                    break;
                case 3:
                    System.out.print("Enter new email: ");
                    String email = scanner.next();
                    if(email == res.getString("email")){
                        System.out.println("Enter different email");
                        email = scanner.next();
                    };
                    psmt = conn.prepareStatement("update student set email=? where id=?");
                    psmt.setString(1, email);
                    psmt.setInt(2, id);
                    psmt.executeUpdate();
                    System.out.println("Updated successfully!");
                    display();
                    break;
                default:
                    System.out.println("invalid choice!!!!");
            }
        }else{
            System.out.println("No student found with id"+id);
        }
    }
    private static void deleteStudent() throws SQLException {
        System.out.print("studentID: ");
        int id = scanner.nextInt();
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from student where id="+id);
        if(res.next()) {
            stmt.execute("delete from student where id="+id);
            System.out.println("Deleted a record");
            display();
        }else{
            System.out.println("No student found with given ID!");
        }
        }
}
