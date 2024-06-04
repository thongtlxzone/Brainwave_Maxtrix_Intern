import java.util.*;

class BankAccount{
    private String fullname;
    private String username;
    private String password;
    private int accountNo;
    private float balance;
    private List<String> transactionsHistory = new ArrayList<>();

    public BankAccount() {

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<String> getTransactionsHistory() {
        return transactionsHistory;
    }
    //Method
    public void register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your Name: ");
        this.fullname = sc.nextLine();
        System.out.println("\nEnter your Username: ");
        this.username = sc.nextLine();
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();
        this.transactionsHistory.add(""); //avoid null pointer exception
        Random rand = new Random();
        this.accountNo = (rand.nextInt(999999)+100000) % 1000000; // random account number;
        System.out.println("\nRegistration Successful. Please Log in to your Bank Account");
    }
    public boolean login(){
        boolean isLoggedin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLoggedin){
            System.out.println("\nEnter Your Username: ");
            String inputUsername = sc.nextLine();
            System.out.println("\nEnter Your Password: ");
            String inputPassword = sc.nextLine();
            if(inputUsername.equals(this.username) && inputPassword.equals(this.password)){
                System.out.println("\nLogin Successful");
                isLoggedin = true;
            }else {
                System.out.println("\nIncorrect Username Or Password");
            }
        }
        return isLoggedin;
    }
    public void withdraw(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Amount To Withdraw: ");
        float amount = sc.nextFloat();
        try{
            if(amount >= 0f && amount <= this.balance){
                this.balance -= amount;
                System.out.println("\nWithdraw Successfully.");
                Date date = new Date();
                this.transactionsHistory.add("Withdrawn " +amount+ "$ from your account at " + date);
                this.displayAccount();
            }else {
                System.out.println("\nInvalid Amount.");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void deposit(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Amount To Transfer: ");
        float depositMoney = sc.nextFloat();
        try{
            if(depositMoney < 10000f){
                this.balance += depositMoney;
                System.out.println("Deposit Successfully.");
                Date date = new Date();
                this.transactionsHistory.add("Deposited " +depositMoney+ "$ to your account at " + date);
                this.displayAccount();
            }else {
                System.out.println("\nInvalid Amount. The Valid Is 10000$");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void transfer(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Account Number To Withdraw: ");
        int transferTo = sc.nextInt(); //accountNumber to transfer money to...
        System.out.println("\nEnter Amount To Withdraw: ");
        float amount = sc.nextFloat();
        try{
            if(amount >= 0f && amount <= this.balance && amount < 10000f){
                this.balance -= amount;
                System.out.println("\nTransferred To "+transferTo+" Successfully.");
                Date date = new Date();
                this.transactionsHistory.add("\nTransferred "+amount+"$ To "+transferTo+" Successfully." + date);
                this.displayAccount();
            }else {
                System.out.println("\nInvalid Amount.");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void transHistory(){
        for (String trans : this.transactionsHistory) {
            System.out.println("\n"+trans);
        }
    }
    public void displayAccount(){
        System.out.println("\nFullname: " + this.fullname);
        System.out.println("\nAccount Number: " + this.accountNo);
        System.out.println("\nBalance: " + this.balance);
        System.out.println("\nTransactions Performed: " + (this.transactionsHistory.size() - 1));
    }
}

public class Main {
    public static int takenIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while(!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if(flag && input>limit || input<1) {
                    System.out.println("Choose the number between 1 to "+limit);
                    flag=false;
                }
            }catch(Exception e) {
                System.out.println("Enter only integer value.");
                flag=false;
            }
        }
        return input;
    }
    public static void main(String[] args) {
        System.out.println("\n********************WELCOME TO THE ATM*******************");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option: ");
        int choose = takenIntegerInput(2);

        if(choose == 1) {
            BankAccount b= new BankAccount();
            b.register();
            while(true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Enter your choice: ");
                int ch = takenIntegerInput(2);
                if(ch==1) {
                    if(b.login()) {
                        System.out.println("\n********************WELCOME BACK "+b.getFullname() +"*******************");
                        b.displayAccount();
                        boolean isFinished = false;
                        while(!isFinished) {
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.println("Enter your choice: ");
                            int c = takenIntegerInput(6);
                            switch(c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.displayAccount();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                }else {
                    System.exit(0);
                }

            }
        }else {
            System.exit(0);
        }
    }
}