package consoleService;

import java.util.Scanner;

/**
 * Class for parsing input from the console.
 */
public class ConsoleService implements IConsoleService {
    private Scanner scan;
    public ConsoleService(Scanner scan){
        this.scan = scan;
    }
    public String readLine(){
       return scan.nextLine();
    }
    @Override
    public void setScanner(Scanner scan){
        this.scan = scan;
    }
    @Override
    public Scanner getScanner() {
        return scan;
    }

    @Override
    public String[] parseCommand(){
        return this
                .readLine()
                .trim()
                .split(" ", 2);
    }

}
