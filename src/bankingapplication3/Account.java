
package bankingapplication3;
public class Account {
    private int number;
    private String name;
    private double balance;

    public Account(int number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }
    public void deposit(double money){
        if(money > 0) this.balance += money;
    }
    
    public void withdraw(double money){
        if(money > 0 && balance >= money) this.balance -= money;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }   
    
}
