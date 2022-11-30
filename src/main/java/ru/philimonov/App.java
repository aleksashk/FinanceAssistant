package ru.philimonov;

import ru.philimonov.service.AccountService;
import ru.philimonov.service.AuthService;
import ru.philimonov.service.CreateAccountService;
import ru.philimonov.service.CreateTypeTransactionService;
import ru.philimonov.service.DeleteAccountService;
import ru.philimonov.service.DeleteTypeTransactionService;
import ru.philimonov.service.EditTypeTransactionService;
import ru.philimonov.service.RegService;
import ru.philimonov.service.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Aloha!!!");
        System.out.println("Press 1 for authorization, 2 for registration");
        Scanner scanner = new Scanner(System.in);
        int request = scanner.nextInt();

        switch (request) {
            case 1:
                AuthService authService = new AuthService();
                String email = request("email: ");
                String password = request("password: ");
                UserDto userDto = authService.auth(email, password);
                System.out.println(userDto);
                System.out.println("press 1 - get list of accounts, 2 - create an account, 3 - delete an account");
                long id = userDto.getId();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        AccountService accountService = new AccountService();
                        System.out.println(accountService.accountDtoList(id));
                        break;
                    case 2:
                        CreateAccountService createAccountService = new CreateAccountService();
                        String accountName = request("account title: ");
                        System.out.println("Input amount: ");
                        double amount = scanner.nextDouble();
                        createAccountService.createAccount(accountName, amount, id);
                        break;
                    case 3:
                        DeleteAccountService deleteAccountService = new DeleteAccountService();
                        deleteAccountService.deleteAccount(id);
                        break;
                    case 4:
                        String typeName = request("Account name: ");
                        CreateTypeTransactionService createTypeTransactionService = new CreateTypeTransactionService();
                        createTypeTransactionService.createType(typeName);
                        break;
                    case 5:
                        System.out.println("Input account id for update: ");
                        long typeId = scanner.nextLong();
                        String typeNameNew = request("new account name: ");
                        EditTypeTransactionService editTypeTransactionService = new EditTypeTransactionService();
                        editTypeTransactionService.editTransactionType(typeNameNew, typeId);
                        break;
                    case 6:
                        String deletedTypeName = request("Input account name for delete: ");
                        DeleteTypeTransactionService deleteTypeTransactionService = new DeleteTypeTransactionService();
                        deleteTypeTransactionService.deleteTransactionType(deletedTypeName);
                        break;
                }
            case 2:
                RegService regService = new RegService();
                email = request("email: ");
                password = request("password: ");
                userDto = regService.registration(email, password);
                System.out.println(userDto);
                break;
        }
    }

    private static String request(String str) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter " + str);
        String result = null;

        try {
            result = reader.readLine();
        } catch (IOException e) {
            System.out.println("Invalid data.");
        }
        return result;
    }
}
