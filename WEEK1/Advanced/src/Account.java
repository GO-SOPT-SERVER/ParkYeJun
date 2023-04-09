import java.util.Random;

public class Account {

    private String accountNumber;
    private String name;
    private String pw;
    private int balance;

    public Account(String name, String pw) {
        this.accountNumber = makeRandomAccountNo();  // 계좌번호는 난수로 생성
        this.name = name;
        this.pw = pw;
        this.balance = 0;  // balance 가 따로 초기값을 가지지 않으면 default인 0원으로 초기화
    }

    public Account(String name, String pw, int balance) {
        this(name, pw);
        this.balance = balance;
    }

    public String makeRandomAccountNo() {
        Random random = new Random();
        return (random.nextInt(9000) + 1000) + "-" + (random.nextInt(9000) + 1000);
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getPw() {
        return this.pw;
    }


    public int getBalance() {
        return balance;
    }

    public void deposit(int money) {
        balance += money;
    }

    public int withdraw(int money) {
        if (balance - money >= 0) {
            balance -= money;
        } else {
            System.out.println("잔액이 부족합니다.");
        }
        return balance;
    }

    public void printAccountInfo() {
        System.out.println(
                "이름: " + this.name + "\n" +
                        "계좌번호: " + this.accountNumber + "\n" +
                        "잔액: " + this.balance
        );
    }
}