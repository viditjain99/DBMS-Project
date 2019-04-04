import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BookStore
{
    public static void main(String[] args) throws SQLException
    {
        Scanner scanner=new Scanner(System.in);
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","password123");
        System.out.println("1. Books Database");
        System.out.println("2. Employees Database");
        System.out.println("3. Customer Database");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");
        int choice=scanner.nextInt();
        while(choice!=4)
        {
            if(choice==1)
            {
                int booksDatabaseChoice;
                System.out.println("---------------Books Database---------------");
                System.out.println();
                System.out.println("1. Add Book");
                System.out.println("2. Sell Book");
                System.out.println("3. Search Book");
                System.out.println("4. Search Book in other Branches");
                System.out.println("5. Modify Book Details");
                System.out.print("Enter Choice: ");
            }
            else if(choice==2)
            {
                int employeesDatabaseChoice;
                System.out.println("---------------Employees Database---------------");
                System.out.println();
                System.out.println("1. Search Employee");
                System.out.println("2. Search for Employees in a Branch");
                System.out.println("3. Add Employee");
                System.out.println("4. Remove Employee");
                System.out.println("5. Modify Employee Details");
                System.out.print("Enter Choice: ");
            }
            else if(choice==3)
            {
                int customersDatabaseChoice;
                System.out.println("---------------Customers Database---------------");
                System.out.println();
                System.out.println("1. Search Customer");
                System.out.println("2. Add New Customer");
                System.out.println("3. Delete Customer");
                System.out.println("4. Modify Customer Details");
                System.out.println("5. Return Book");
                System.out.print("Enter Choice: ");
            }
        }
    }
}
