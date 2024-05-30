package models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Basic Organization class with properties <b>id</b>, <b>name</b>, <b>coordinates</b>, <b>creationDate</b>, <b>annualTurnover</b>, <b>employeesCount</b>, <b>type</b>, <b>postalAddress</b>.
 */
public class Organization implements Comparable<Organization>, Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private Long employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле может быть null

    /**
     *
     * @param name - Organization name
     * @param coordinates - Location
     * @param date - Creation date
     * @param annualTurnover - Total annual turnover
     * @param employeesCount - Number of employees
     * @param type - Organization type (Public, Open Joint Stock company, Commercial)
     * @param address - Address
     */
    public Organization(String name, Coordinates coordinates, Date date, Long annualTurnover, Long employeesCount, OrganizationType type, Address address){
        this.annualTurnover = annualTurnover;
        this.coordinates = coordinates;
        this.creationDate = date;
        this.name = name;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = address;
    }
    public Organization(){};
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Long annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public Long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Long employeesCount) {
        this.employeesCount = employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }


    public Address getPostalAddress() {
        return postalAddress;
    }


    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }


    @Override
    public int compareTo(Organization o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(employeesCount, that.employeesCount) && Objects.equals(id, that.id) && Objects.equals(name, that.name) && type == that.type && Objects.equals(postalAddress, that.postalAddress);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeesCount, type, postalAddress);
    }


    @Override
    public String toString() {
        return "Организация \"" + name+ "\"; ID: " + id +
                "; Число сотрудников: " + employeesCount +
                "; Вид: " + type +
                "; Адрес: " + getPostalAddress().getZipCode() + "; Дата основания: " + creationDate +
        "; Годовой оборот: " + annualTurnover + "\n";
    }

    /**
     * Checks Organization parameters
     * @return true if all fields are valid
     */
    public boolean validate(){
        return id > 0 &&
                name != null &&
                coordinates != null &&
                creationDate != null &&
                type != null &&
                annualTurnover != null &&
                annualTurnover > 0 &&
                employeesCount != null &&
                employeesCount > 0;
    }
}
