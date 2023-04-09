import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * main 함수가 실행되는 클래스
 * -> 반드시 static 으로 선언되어야 하는 Main 메서드가 포함된 클래스에서는
 * 나머지 멤버도 모두 static 으로 선언해야만 접근이 가능하다.
 * 이는 static 멤버인 main 에서 non-static 멤버를 접근할 수 없기 때문이다.
 *
 * ** 실제 프로그램에서는 static 사용을 최소화하여 오직 main 메서드만 static 으로 처리하는 것을 선호한다.
 */
public class Bank {

    static Scanner sc;

    public static void main(String[] args) throws NullPointerException {

        sc = new Scanner(System.in);

        // 회원생성 (-> 이 회원이 은행에서 할 일을 처리한다고 가정)
        User user = new User("박예준", 22);

        int num;
        while (true) {
            printCommand();
            System.out.print("선택 >>> ");
            num = sc.nextInt();
            System.out.println();

            if (num < 0 || num > 6) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                continue;
            }
            if (num == 0) {
                break;
            }

            String findAccountNum;
            int money;
            Account account;

            switch (num) {
                case 1:
                    System.out.print("계좌 개설 시 입금할 금액을 입력하세요.> ");
                    money = sc.nextInt();
                    String newAccountNum;
                    if (money > 0) {
                        newAccountNum = createAccount(user, money);
                    } else {
                        newAccountNum = createAccount(user);
                    }
                    System.out.println("\n새로 개설된 계좌의 계좌번호는 " + newAccountNum + " 입니다.\n");
                    break;
                case 2:
                    System.out.print("입금할 계좌 번호와 금액을 입력하세요.> ");
                    findAccountNum = sc.next();
                    money = sc.nextInt();
                    account = findAccount(findAccountNum, user);
                    account.deposit(money);
                    break;
                case 3:
                    System.out.print("출금할 계좌 번호화 금액을 입력하세요.> ");
                    findAccountNum = sc.next();
                    money = sc.nextInt();
                    account = findAccount(findAccountNum, user);
                    account.withdraw(money);
                    break;
                case 4:
                    System.out.print("잔액을 조회할 계좌 번호를 입력하세요.> ");
                    findAccountNum = sc.next();
                    account = findAccount(findAccountNum, user);
                    int balance = account.getBalance();
                    System.out.println("잔액이 " + balance +"원 남았습니다.");
                    break;
                case 5:
                    user.getAccountList();
                    break;
                case 6:
                    System.out.print("조회할 계좌의 계좌 번호를 입력하세요.> ");
                    findAccountNum = sc.next();
                    account = findAccount(findAccountNum, user);
                    account.printAccountInfo();
                    break;
                default:
                    break;
            }
        }
    }

    private static String createAccount(User user)  {
        System.out.print("개설하는 계좌의 비밀번호를 입력하세요.> ");
        String pw = sc.next();
        Account newAccount = new Account(
                user.getName(),
                pw
        );
        user.addAccount(newAccount);
        return newAccount.getAccountNumber();
    }

    private static String createAccount(User user, int balance) {
        System.out.print("개설하는 계좌의 비밀번호를 입력하세요.> ");
        String pw = sc.next();
        Account newAccount = new Account(
                user.getName(),
                pw,
                balance
        );
        user.addAccount(newAccount);
        return newAccount.getAccountNumber();
    }

    private static Account findAccount(String accountNum, User user) {
        Account account = user.findAccount(accountNum);


        System.out.print("비밀번호를 입력하세요.> ");
        String pw = sc.next();
        if (account.getPw().equals(pw)) {
            return account;
        }
        System.out.println("비밀번호가 일치하지 않아 조회에 실패했습니다.");
        return null;
    }

    private static void printCommand() {
        System.out.println(" ============ 필요한 작업을 선택하세요.(ex. 1) ===========");
        System.out.println("1. 계좌개설\n2. 입금\n3. 출금\n4. 잔액조회\n5. 전체 계좌 조회\n6. 계좌 정보 조회\n0. 종료");
        System.out.println(" ===================================================");
    }
}