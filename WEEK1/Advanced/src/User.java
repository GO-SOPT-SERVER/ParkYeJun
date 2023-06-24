import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private int age;
    private List<Account> accountList;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        accountList = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public String getName() {
        return this.name;
    }

    public void getAccountList() {
        System.out.println("=====" + name + "님의 전체 계좌 정보 =====");
        for (Account account : accountList) {
            account.printAccountInfo();
        }
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accountList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("입력한 계좌번호의 정보가 존재하지 않습니다.");
        return new Account("없음", "1234");
    }

}