package clientCommandService;


import consoleService.ConsoleService;
import models.Address;
import models.Coordinates;
import models.Organization;
import models.OrganizationType;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class for checking user input.
 * <p>Used for adding custom organizations.<p>
 */
public class CollectionInput {
    private final ConsoleService consoleService;

    /**
     *
     * @param consoleService - read and parse user input.
     */
    public CollectionInput(ConsoleService consoleService){
        this.consoleService = consoleService;
    }


    public Organization get(){
        return new Organization(getName(),
                getCoordinates(),
                java.sql.Timestamp.valueOf(LocalDateTime.now()),
                getAnnualTurnover(),
                getEmployeeCount(),
                getType(),
                getAddress());
    }

    public String getName(){
        return getString("name", false);
    }
    public Coordinates getCoordinates(){
        return new Coordinates(getDouble("x"), getInteger("y"));
    }
    public Address getAddress(){
        String zip = getString("zip code", true);
        while(zip.length()>18){
            System.err.println("String length cannot be more than 18!\n>");
            zip = getString("zip code", true);
        }
        return new Address(zip);
    }
    public Long getAnnualTurnover(){
        long annualTurnover = getLong("annual turnover");
        while(annualTurnover<=0){
            System.err.println("Annual turnover cannot be less than 0!\n>");
            annualTurnover = getLong("annual turnover");
        }
        return annualTurnover;
    }
    public Long getEmployeeCount(){
        long employeecount = getLong("employee count");
        while(employeecount<=0){
            System.err.println("Employee count cannot be less than 0!\n>");
            employeecount = getLong("employee count");
        }
        return employeecount;
    }
    public OrganizationType getType(){
        String fieldName = "organization type";
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        String line = consoleService.readLine().trim();
        while(!OrganizationType.names().contains(line)){
            System.out.print("Invalid type!\n");
            System.out.print(OrganizationType.names() + "\n");
            System.out.printf("Enter %s:%s", fieldName, "\n>");
            line = consoleService.readLine().trim();
        }
        System.out.printf("Got value: %s\n>", line);
        return OrganizationType.valueOf(line);

    }

    private Integer getInteger(String fieldName) {
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        String line = consoleService.readLine().trim();
        int number = 1;
        boolean e = true;
        while(e) {
            try {
                number = Integer.parseInt(line);
                e = false;
            } catch (NumberFormatException ex) {
                System.out.printf("Required type: Integer. Enter %s:%s", fieldName,"\n>");
                line = consoleService.readLine().trim();
            }
        }
        System.out.printf("Got value: %s\n>", number);
        return number;
    }
    private Long getLong(String fieldName) {
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        String line = consoleService.readLine().trim();
        long number = 1;
        boolean e = true;
        while(e) {
            try {
                number = Long.parseLong(line);
                e = false;
            } catch (NumberFormatException ex) {
                System.out.printf("Required type: Long. Enter %s:%s", fieldName,"\n>");
                line = consoleService.readLine().trim();
            }
        }
        System.out.printf("Got value: %s\n>", number);
        return number;
    }
    private Double getDouble(String fieldName) {
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        String line = consoleService.readLine().trim();
        double number = 1;
        boolean e = true;
        while(e) {
            try {
                number = Double.parseDouble(line);
                e = false;
            } catch (NumberFormatException ex) {
                System.out.printf("Required type: Double. Enter %s:%s", fieldName,"\n>");
                line = consoleService.readLine().trim();
            }
        }
        System.out.printf("Got value: %s\n>", number);
        return number;
    }
    private String getString(String fieldName, boolean canBeNull) {
        System.out.printf("Enter %s:%s", fieldName, "\n>");
        String line = consoleService.readLine();
        if(!canBeNull)
            while(line.isEmpty()){
                System.out.println("Can't be null!");
                System.out.printf("Enter %s:%s", fieldName, "\n>");
                line = consoleService.readLine();
            }
        System.out.printf("Got value: %s \n", line);
        return line;
    }


}
