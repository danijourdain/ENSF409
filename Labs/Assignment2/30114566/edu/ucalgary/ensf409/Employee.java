/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.4
@since      1.0
*/

package edu.ucalgary.ensf409;

public class Employee {
    private String name;
    private final String IDNUMBER;
    private String managerID;
    private Employee[] supervisedEmployees = new Employee[10];

    /**
     * Constructor for an employee without a manager assigned.
     * @param name The name of the employee.
     * @param idNumber The id number of the employee.
     */
    public Employee(String name, String idNumber) {
        this.name = name;
        this.IDNUMBER = idNumber;
    }

    /**
     * Cosntructor for an employee with a manager assigned.
     * @param name The name of the employee.
     * @param idNumber The id number of the new employee.
     * @param managerID The id number of the emploee's manager.
     */
    public Employee(String name, String idNumber, String managerID) {
        this.name = name;
        this.IDNUMBER = idNumber;
        this.managerID = managerID;
    }

    /**
     * Getter method for the name of the employee.
     * @return The employee's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method for the name of the employee.
     * @param name The new name of the employee.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the employee's ID number.
     * @return The employee's ID number.
     */
    public String getIDNumber() {
        return this.IDNUMBER;
    }

    /**
     * Getter method for the ID number of an employee's manager.
     * @return The ID number for the manager of an employee.
     */
    public String getManagerID() {
        return this.managerID;
    }

    /**
     * Setter method for the ID number of an employee's manager.
     * @param newManager The new manager ID to be assigned to the employee.
     */
    public void setManagerID(String newManager) {
        this.managerID = newManager;
    }

    /**
     * This method will add another employee to a manager's list of employees
     * they supervise.
     * @param newEmployee The new employee to be added to the list.
     */
    public void addEmployee(Employee newEmployee) {
        for(int i = 0; i < this.supervisedEmployees.length; i++) {
            //iterate through the array of Employees until an empty spot is found
            if(this.supervisedEmployees[i] == null) {
                this.supervisedEmployees[i] = newEmployee;
                break;
                //add the new employee, then break out of the for loop
            }
        }

        //if the Employee array is already full, do nothing
    }

    /**
     * Getter method for the list of employees a manager supervises.
     * @return The arrays of Employees a manager supervises.
     */
    public Employee[] getEmployees() {
        return this.supervisedEmployees;
    }
    
}
