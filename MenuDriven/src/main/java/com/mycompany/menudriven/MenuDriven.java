package com.mycompany.menudriven;

import java.io.*;
import java.util.*;

class Account implements Serializable {

    String accountNo;
    String name;
    int balance;

    Account() {

    }

    Account(String a, String n, int b) {
        accountNo = a;
        name = n;
        balance = b;

    }

    @Override
    public String toString() {
        return "Account No: " + accountNo + "\nName: " + name + "\nBalance: " + balance + "\n";
    }

}

public class MenuDriven {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
        Account acc = null;
        HashMap<String, Account> hm = new HashMap<>();

        try {
            FileInputStream fis = new FileInputStream("Accounts.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            int count = ois.readInt();

            for (int i = 0; i < count; i++) {
                acc = (Account) ois.readObject();
                System.out.println(acc);
                hm.put(acc.accountNo, acc);
            }
            fis.close();
            ois.close();
        } catch (Exception e) {

        }

        FileOutputStream fos = new FileOutputStream("Accounts.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        System.out.println("Menu");

        int choice;
        String accountNo;
        String name;
        int balance;

        do {
            System.out.println("1.Create Account");
            System.out.println("2.Delete Account");
            System.out.println("3.View Account");
            System.out.println("4.View All Account");
            System.out.println("5.Save Account");
            System.out.println("6.Exit");

            choice = sc.nextInt();

            sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            switch (choice) {
                case 1:
                    System.out.println("Enter Details AccountNo,Name,Balance");
                    accountNo = sc.nextLine();
                    name = sc.nextLine();
                    balance = sc.nextInt();

                    acc = new Account(accountNo, name, balance);
                    hm.put(accountNo, acc);
                    System.out.println("Account Created for" + name);
                    break;

                case 2:
                    System.out.println("Enter Account No: ");
                    accountNo = sc.nextLine();
                    hm.remove(accountNo);
                    break;

                case 3:
                    System.out.println("Enter Account Number For Viewing: ");
                    accountNo = sc.nextLine();
                    acc = hm.get(accountNo);
                    System.out.println(acc);
                    break;

                case 4:
                    System.out.println("These existing all user account:");
                    for (Account a : hm.values()) {
                        System.out.println(a);
                    }
                    break;

                case 5:
                case 6:
                    oos.writeInt(hm.size());

                    for (Account a : hm.values()) {
                        oos.writeObject(a);
                    }

            }
        } while (choice != 6);
        oos.flush();
        oos.close();
        fos.close();
    }
}
