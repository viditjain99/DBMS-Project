package bookstore;


import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookStore
{
    public static void printMainMenu()
    {
        System.out.println("1. Books Database");
        System.out.println("2. Employees Database");
        System.out.println("3. Customer Database");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");
    }

    public static void printBooksDatabaseMainMenu()
    {
        System.out.println("---------------Books Database---------------");
        System.out.println();
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Sell Book");
        System.out.println("4. Search Book");
        System.out.println("5. Search Book in other Branches");
        System.out.println("6. Modify Book Details");
        System.out.println("7. Back");
        System.out.print("Enter Choice: ");
    }
    
    public static void printEmployeesDatabaseMainMenu() {
    	System.out.println("---------------Employees Database---------------");
        System.out.println();
        System.out.println("1. Search Employee");
        System.out.println("2. Search Employees in a Branch");
        System.out.println("3. Add Employee");
        System.out.println("4. Remove Employee");
        System.out.println("5. Modify Employee Details");
        System.out.println("6. Back");
        System.out.print("Enter Choice: ");
    }
    
    public static void main(String[] args) throws SQLException
    {
        Scanner scanner=new Scanner(System.in);
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStore","root","password123");
        System.out.print("Enter Branch Id: ");
        int branchId=scanner.nextInt();
        printMainMenu();
        ResultSet resultSet;
        Statement statement=connection.createStatement();
        int bookId,yearOfPublishing,quantity;
        String title,author,publisher;
        float price;
        ArrayList<Integer> branchIds;
        ArrayList<Integer> quantities;
        int choice=scanner.nextInt();
        while(choice!=4)
        {
            if(choice==1)
            {
                int booksDatabaseChoice;
                printBooksDatabaseMainMenu();
                booksDatabaseChoice=scanner.nextInt();
                switch(booksDatabaseChoice)
                {
                    case 1:
                        System.out.print("Book Id: ");
                        bookId=scanner.nextInt();
                        System.out.print("Title: ");
                        title=scanner.nextLine();
                        author=scanner.nextLine();
                        price=scanner.nextFloat();
                        publisher=scanner.nextLine();
                        yearOfPublishing=scanner.nextInt();
                        System.out.print("Quantity: ");
                        quantity=scanner.nextInt();
                        statement.executeQuery("insert into Books values("+bookId+","+title+","+author+","+price+","+publisher+","+yearOfPublishing+")");
                        resultSet=statement.executeQuery("select quantity from found_in where branch_id="+branchId);
                        resultSet.next();
                        int q=resultSet.getInt(1);
                        quantity=quantity+q;
                        statement.executeQuery("update found_in set quantity="+quantity+" where branch_id="+branchId+" and book_id="+bookId);
                        System.out.println("Book added");
                        break;

                    case 2:
                        System.out.print("Book Id: ");
                        bookId=scanner.nextInt();
                        statement.executeQuery("delete from found_in where branch_id="+branchId+" and book_id="+bookId);
                        System.out.println("Book Removed");
                        break;

                    case 3:
                        System.out.print("Book Id: ");
                        bookId=scanner.nextInt();
                        System.out.print("Quantity: ");
                        quantity=scanner.nextInt();
                        resultSet=statement.executeQuery("select quantity from found_in where book_id="+bookId+" and branch_id="+branchId);
                        resultSet.next();
                        System.out.println(resultSet.getInt(1));
                        int temp=resultSet.getInt(1);
                        temp=temp-quantity;
                        if(temp<0)
                        {
                            System.out.println("Not enough stock");
                        }
                        else
                        {
                            statement.executeUpdate("update found_in set quantity="+temp+" where branch_id="+branchId+" and book_id="+bookId);
                            System.out.println("Books Sold");
                            if(temp==0)
                            {
                                System.out.println("Stock over");
                            }
                        }
                        break;

                    case 4:
                        System.out.println("1. Search by Title");
                        System.out.println("2. Search by Price");
                        System.out.println("3. Search by Author");
                        System.out.println("4. Search by Publisher");
                        System.out.println("5. Back");
                        System.out.print("Enter choice: ");
                        int ch=scanner.nextInt();
                        scanner.nextLine();
                        if(ch==1)
                        {
                            System.out.print("Enter Title: ");
                            title=scanner.nextLine();
                            resultSet=statement.executeQuery("select * from Books where title=\""+title+"\"");
                            while(resultSet.next())
                            {
                                System.out.print(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+
                                        resultSet.getString(3)+" "+resultSet.getInt(4)+" "+resultSet.getString(5)+" "+
                                        resultSet.getInt(6));
                            }
                            System.out.println();
                        }
                        else if(ch==2)
                        {
                            System.out.print("Enter Price: ");
                            price=scanner.nextFloat();
                            statement=connection.createStatement();
                            resultSet=statement.executeQuery("select * from Books where price="+price);
                            while(resultSet.next())
                            {
                                System.out.print(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+
                                        resultSet.getString(3)+" "+resultSet.getInt(4)+" "+resultSet.getString(5)+" "+
                                        resultSet.getInt(6));
                            }
                            System.out.println();
                        }
                        else if(ch==3)
                        {
                            System.out.print("Enter Author: ");
                            author=scanner.nextLine();
                            statement=connection.createStatement();
                            resultSet=statement.executeQuery("select * from Books where author=\""+author+"\"");
                            while(resultSet.next())
                            {
                                System.out.print(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+
                                        resultSet.getString(3)+" "+resultSet.getInt(4)+" "+resultSet.getString(5)+" "+
                                        resultSet.getInt(6));
                            }
                            System.out.println();
                        }
                        else if(ch==4)
                        {
                            System.out.print("Enter Publisher: ");
                            publisher=scanner.nextLine();
                            statement=connection.createStatement();
                            resultSet=statement.executeQuery("select * from Books where publisher=\""+publisher+"\"");
                            while(resultSet.next())
                            {
                                System.out.print(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+
                                        resultSet.getString(3)+" "+resultSet.getInt(4)+" "+resultSet.getString(5)+" "+
                                        resultSet.getInt(6));
                            }
                            System.out.println();
                        }
                        else if(ch==5)
                        {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println();
                            printBooksDatabaseMainMenu();
                            booksDatabaseChoice=scanner.nextInt();
                        }
                        else
                        {
                            System.out.println("Invalid choice...");
                        }
                        break;

                    case 5:
                        System.out.print("Book Id: ");
                        bookId=scanner.nextInt();
                        resultSet=statement.executeQuery("select * from found_in where book_id="+bookId+" and branch_id!="+branchId+" and quantity>0");
                        branchIds=new ArrayList<>();
                        quantities=new ArrayList<>();
                        while(resultSet.next())
                        {
                            branchIds.add(resultSet.getInt(2));
                            quantities.add(resultSet.getInt(3));
                        }
                        String branches="(";
                        if(branchIds.size()==1)
                        {
                            branches=branches+branchIds.get(0)+")";
                        }
                        else
                        {
                            for(int i=0;i<branchIds.size();i++)
                            {
                                if(i==0)
                                {
                                    branches=branches+branchIds.get(i)+",";
                                }
                                else if(i==branchIds.size()-1)
                                {
                                    branches=branches+branchIds.get(i);
                                }
                                else
                                {
                                    branches=branches+branchIds.get(i)+",";
                                }
                            }
                            branches=branches+")";
                        }
                        resultSet=statement.executeQuery("select address,city from Branch where branch_id in "+branches);
                        int count=0;
                        while(resultSet.next())
                        {
                            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+quantities.get(count));
                            count++;
                        }
                        break;

                    case 6:
                        System.out.println("1. Change Title");
                        System.out.println("2. Change Author");
                        System.out.println("3. Change Price");
                        System.out.println("4. Change Publisher");
                        System.out.println("5. Back");
                        System.out.print("Enter Choice: ");
                        int ch1=scanner.nextInt();
                        if(ch1==1)
                        {
                            System.out.print("Book Id: ");
                            bookId=scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("New Title: ");
                            title=scanner.nextLine();
                            statement.executeUpdate("update Books set title=\""+title+"\" where book_id="+bookId);
                        }
                        else if(ch1==2)
                        {
                            System.out.print("Book Id: ");
                            bookId=scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Author Name: ");
                            author=scanner.nextLine();
                            statement.executeUpdate("update Books set author=\""+author+"\" where book_id="+bookId);
                        }
                        else if(ch1==3)
                        {
                            System.out.print("Book Id: ");
                            bookId=scanner.nextInt();
                            System.out.print("New Price: ");
                            price=scanner.nextFloat();
                            statement.executeUpdate("update Books set price="+price+" where book_id="+bookId);
                        }
                        else if(ch1==4)
                        {
                            System.out.print("Book Id: ");
                            bookId=scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Publisher Name: ");
                            publisher=scanner.nextLine();
                            statement.executeUpdate("update Books set publisher=\""+publisher+"\" where book_id="+bookId);
                        }
                        else if(ch1==5)
                        {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println();
                            printBooksDatabaseMainMenu();
                            booksDatabaseChoice=scanner.nextInt();
                        }
                        else
                        {
                            System.out.println("Invalid choice...");
                        }
                        break;

                    case 7:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        printMainMenu();
                        choice=scanner.nextInt();
                        break;
                }
            }
            
            
            else if(choice==2)
            {
                int employeesDatabaseChoice;
                printEmployeesDatabaseMainMenu();
                int employeeDatabaseChoice=scanner.nextInt();
                switch(employeeDatabaseChoice)
                {
                    case 1:
                    	System.out.println("1. Search by Name");
                    	System.out.println("2. Search by Salary");
                    	System.out.println("3. Search by Address");
                    	System.out.println("4. Back");
                    	System.out.print("Enter choice: ");
                        int ch=scanner.nextInt();
                        scanner.nextLine();
                        if(ch==1)
                        {
                            System.out.print("Enter Name: ");
                            String name=scanner.nextLine();
                            resultSet=statement.executeQuery("select * from Employee where name=\""+name+"\"");
                            while(resultSet.next())
                            {
                                System.out.print(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+
                                        resultSet.getString(3)+" "+resultSet.getInt(4)+" "+resultSet.getString(5)+" "+
                                        resultSet.getInt(6));
                            }
                            System.out.println();
                        }
                        else if(ch==2)
                        {
                            System.out.print("Enter Salary: ");
                            int salary=scanner.nextInt();
                            statement=connection.createStatement();
                            resultSet=statement.executeQuery("select * from Employee where salary="+salary);
                            while(resultSet.next())
                            {
                                System.out.print(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+
                                        resultSet.getString(3)+" "+resultSet.getInt(4)+" "+resultSet.getString(5)+" "+
                                        resultSet.getInt(6));
                            }
                            System.out.println();
                        }
                        else if(ch==3)
                        {
                            System.out.print("Enter Address: ");
                            String address=scanner.nextLine();
                            statement=connection.createStatement();
                            resultSet=statement.executeQuery("select * from Employee where address=\""+address+"\"");
                            while(resultSet.next())
                            {
                                System.out.print(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+
                                        resultSet.getString(3)+" "+resultSet.getInt(4)+" "+resultSet.getString(5)+" "+
                                        resultSet.getInt(6));
                            }
                            System.out.println();
                        }
                        else if(ch==4)
                        {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println();
                            printEmployeesDatabaseMainMenu();
                            employeesDatabaseChoice=scanner.nextInt();
                        }
                        else
                        {
                            System.out.println("Invalid choice...");
                        }
                        break;
                        
                        
                    case 2:
                        

                    case 3:
                    	System.out.print("Employee Id: ");
                        int empId=scanner.nextInt();
                        System.out.print("Name: ");
                        String name=scanner.nextLine();
                        String gender=scanner.nextLine();
                        String birthdate=scanner.nextLine();
                        int salary=scanner.nextInt();
                        String address=scanner.nextLine();
                        System.out.print("Is he a manager?(Y/N):");
                        String cho = scanner.nextLine();
                        if(cho.equals("N")) {
                        	System.out.print("Name of manager: ");
                            String man_name=scanner.nextLine();
                            statement.executeQuery("insert into Employee values("+empId+","+name+","+gender+","+birthdate+","+salary+","+address+")");
                            statement.executeQuery("insert into manages values("+man_name+","+empId+")");
                        }
                        else {
                        	statement.executeQuery("insert into Employee values("+empId+","+name+","+gender+","+birthdate+","+salary+","+address+")");
                        }
                        System.out.println("Employee added");
                        break;

                    case 4:
                    	System.out.print("Employee Id: ");
                        int empid=scanner.nextInt();
                        statement.executeQuery("delete from Employee where emp_id="+empid);
                        statement.executeQuery("delete from manages where emp_id="+empid);
                        System.out.println("Employee Removed");
                        break;
                        
                    case 5:
                    	System.out.println("1. Change Salary");
                        System.out.println("2. Change Address");
                        System.out.println("3. Back");
                        System.out.print("Enter Choice: ");
                        int ch1=scanner.nextInt();
                        if(ch1==1)
                        {
                            System.out.print("Employee id: ");
                            int empid1=scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("New salary: ");
                            int salary1=scanner.nextInt();
                            statement.executeUpdate("update Employee set salary=\""+salary1+"\" where emp_id="+empid1);
                        }
                        else if(ch1==2)
                        {
                            System.out.print("Employee id: ");
                            int empid2=scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("New address: ");
                            String address2=scanner.nextLine();
                            statement.executeUpdate("update Employee set address=\""+address2+"\" where emp_id="+empid2);
                        }
                        else if(ch1==3)
                        {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println();
                            printEmployeesDatabaseMainMenu();
                            employeesDatabaseChoice=scanner.nextInt();
                        }
                        else
                        {
                            System.out.println("Invalid choice...");
                        }
                        break;

                    case 6:
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        printMainMenu();
                        choice=scanner.nextInt();
                        break;
                }
                
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
        System.out.println("Bye");
    }
}
