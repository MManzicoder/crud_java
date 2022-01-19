import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
     try {
         Scanner scanner = new Scanner(System.in);
         UserService userService = new UserService();
         System.out.print("Enter your names: ");
         String names = scanner.nextLine();
         System.out.println("Enter your balance: ");
         float amount = scanner.nextFloat();
         User user = new User(names, amount);
         userService.insertUser(user.getuId(), user.getNames(), user.getAmount());
     }catch (SQLException e){
         e.printStackTrace();
         e.getMessage();
         return;
     }
    }
}
