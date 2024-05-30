package consoleService;

import java.util.Scanner;

public interface IConsoleService {
    void setScanner(Scanner scan);
    String[] parseCommand();
    Scanner getScanner();
    String readLine();
}
