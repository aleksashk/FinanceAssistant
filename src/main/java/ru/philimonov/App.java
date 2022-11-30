package ru.philimonov;

import ru.philimonov.service.AccountDto;
import ru.philimonov.service.AccountFactory;
import ru.philimonov.service.AccountService;
import ru.philimonov.service.AuthService;
import ru.philimonov.service.CreateAccountFactory;
import ru.philimonov.service.CreateAccountService;
import ru.philimonov.service.CreateCategoryTransactionFactory;
import ru.philimonov.service.CreateCategoryTransactionService;
import ru.philimonov.service.DeleteAccountFactory;
import ru.philimonov.service.DeleteAccountService;
import ru.philimonov.service.DeleteCategoryTransactionFactory;
import ru.philimonov.service.DeleteCategoryTransactionService;
import ru.philimonov.service.EditCategoryTransactionFactory;
import ru.philimonov.service.EditCategoryTransactionService;
import ru.philimonov.service.RegFactory;
import ru.philimonov.service.RegService;
import ru.philimonov.service.ServiceFactory;
import ru.philimonov.service.TransactionDto;
import ru.philimonov.service.TransactionShowFactory;
import ru.philimonov.service.TransactionShowService;
import ru.philimonov.service.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Aloha!!!");
        System.out.println("Press 1 for authorization, 2 for registration");
        Scanner scanner = new Scanner(System.in);
        int request = scanner.nextInt();

        switch (request) {
            case 1:
                AuthService authService = ServiceFactory.getAuthService();
                String email = request("email: ");
                String password = request("password: ");
                UserDto userDto = authService.auth(email, password);
                System.out.println(userDto);
                System.out.println("press 1 - get list of accounts, 2 - create an account, 3 - delete an account, 4 - create category, 5 - edit category, 6 - remove category, 7 - show transactions by category in period");
                long id = userDto.getId();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        AccountService accountService = AccountFactory.getAccountService();
                        List<AccountDto> accountList = accountService.accountDtoList(id);
                        for (AccountDto item : accountList) {
                            System.out.println(item);
                        }
                        break;
                    case 2:
                        CreateAccountService createAccountService = CreateAccountFactory.getCreateAccountService();
                        String accountName = request("account title: ");
                        System.out.println("Input amount: ");
                        BigDecimal amount = scanner.nextBigDecimal();
                        createAccountService.createAccount(accountName, amount, id);
                        break;
                    case 3:
                        System.out.print("Input id for deleted account: ");
                        long accountId = scanner.nextLong();
                        DeleteAccountService deleteAccountService = DeleteAccountFactory.getDeleteAccountService();
                        deleteAccountService.deleteAccount(id);
                        break;
                    case 4:
                        String typeName = request("Account name: ");
                        CreateCategoryTransactionService createCategoryTransactionService = CreateCategoryTransactionFactory.getCreateCategoryTransactionService();
                        createCategoryTransactionService.createType(typeName);
                        break;
                    case 5:
                        System.out.println("Input account id for update: ");
                        long typeId = scanner.nextLong();
                        String typeNameNew = request("new account name: ");
                        EditCategoryTransactionService editCategoryTransactionService = EditCategoryTransactionFactory.getEditCategoryTransactionService();
                        editCategoryTransactionService.editTransactionType(typeNameNew, typeId);
                        break;
                    case 6:
                        String deletedTypeName = request("Input account name for delete: ");
                        DeleteCategoryTransactionService deleteCategoryTransactionService = DeleteCategoryTransactionFactory.getDeleteCategoryTransactionService();
                        deleteCategoryTransactionService.deleteTransactionType(deletedTypeName);
                        break;
                    case 7:
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String type = request("category name: ");
                        String startDate = request("startDate (yyyy-MM-dd hh:MM):");
                        String endDate = request("endDate (yyyy-MM-dd hh:MM):");
                        LocalDateTime startTime = LocalDateTime.parse(startDate, formatter);
                        LocalDateTime endTime = LocalDateTime.parse(endDate, formatter);
                        TransactionShowService transactionShowService = TransactionShowFactory.getTransactionShowService();
                        List<TransactionDto> list = transactionShowService.transactionDtoList(type, startTime, endTime);
                        for (TransactionDto transactionDto : list) {
                            System.out.println(transactionDto);
                        }
                        break;
                }
                break;
            case 2:
                RegService regService = RegFactory.getRegService();
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
