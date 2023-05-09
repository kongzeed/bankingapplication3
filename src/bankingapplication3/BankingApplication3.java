package bankingapplication3;

import java.util.Random;
import java.util.Scanner;

public class BankingApplication3 {

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        int option = 0;
        
        // creater account
        Account account ;
        // Account field
        String name;
        int number;
        double balance;
        
        //create bank 
        Bank bank = new Bank("mybank");
        
        
        
        while (option != 6) {
            menu();
            option = scaner.nextInt();
            scaner.nextLine();
            
            
            switch (option) {
                case 1: {
                    bank.listAccount();
                    break;
                }
                case 2: {
                    System.out.println("Input detail about your account");
                    System.out.print("Enter Account name : ");
                    name = scaner.nextLine();
                    //System.out.print("Enter Account number : ");
                    //number = scaner.nextInt();
                    System.out.print("Enter initial balance : ");
                    balance = scaner.nextDouble();
                    account = new Account(gennerateAccountNumber(),name,balance);
                    bank.openAccount(account);
                    break;
                }
                case 3: {
                    System.out.print("Enter Account number you want to closed : ");
                    number = scaner.nextInt();
                    bank.closeAccount(number);
                    System.out.println("Account hava been deleted");
                    break;
                }
                case 4: {
                    System.out.print("Enter Account number : ");
                    number = scaner.nextInt();
                    System.out.print("Enter a balance to deposit : ");
                    balance = scaner.nextDouble();
                    account = bank.getAccount(number) ;
                    bank.depositMoney(account, balance);
                    System.out.println("Sucees your  balance is : " + account.getBalance());
                    break;
                }
                case 5: {
                    System.out.print("Enter Account number : ");
                    number = scaner.nextInt();
                    System.out.print("Enter a balance to withdraw : ");
                    balance = scaner.nextDouble();
                    account = bank.getAccount(number) ;
                    bank.withdrawMoney(account, balance);
                    System.out.println("Sucees your  balance is : " + account.getBalance());
                    break;
                }
                case 6: {
                    break;
                }
                default: {
                    System.out.println("Please input a following number");
                }
            }
        }
    }

    public static void menu() {
        System.out.println("1. Display All Accounts​");
        System.out.println("2. Open New Account​");
        System.out.println("3. Close Existing Account");
        System.out.println("4. Deposit​");
        System.out.println("5. Withdraw​");
        System.out.println("6. Exit");
        System.out.print("Enter your choices :");
    }
    
    public static int gennerateAccountNumber(){
        Random ran = new Random();
        int accNum = 100000 + ran.nextInt(900000);
        return accNum;
    
    }

}
