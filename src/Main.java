import java.sql.*;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learnjavajdbc?characterEncoding=latin1&useConfigs=maxPerformance", "root","bestcoder123@!");
            if(conn !=null) System.out.println("Successfully connected!");
            Statement stmt = conn.createStatement();

            while(true){

            }
//             PreparedStatement pstmt = conn.prepareStatement("insert into student(names, email) values(?,?)");

            //create database
//            pstmt.execute(query);
            pstmt.execute("insert into student(names, email) values('Manzi Monn', 'monn@gmail.com')");
            System.out.println("added a record!");
            ResultSet result = pstmt.executeQuery("select * from student");
            int count =0;
            while(result.next()){
                count++;
                System.out.println("Student"+count+ ": "+result.getInt("id")+" "+result.getString(2)+" "+result.getString(3));
            }
//            System.out.println(result);


            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    void ask(){
        int choice;
        System.out.println("----------------------- CRUD OPERATION --------------------------\n\n");
        System.out.println("1. Display all records");
        System.out.println("2. Add  a student");
        System.out.println("3. Get a student");
        System.out.println("4. Update a student");
        System.out.println("5. Delete a student");
        System.out.println("##################################################################");
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

    private void addStudent() {
    }

    private void display() {
    }
    private void getStudent(){

    }
    private void updateStudent(){

    }
    private void deleteStudent(){

    }
}
