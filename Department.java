import java.util.HashMap;

public class Department{
    private Double departmentTotalSalary = 0.00;
    private HashMap<String,Employee> employeeMap;
    private String deparmentCode;
    private String departmentName;

    public void setDepCode(String deparmentCode) {
        this.deparmentCode = deparmentCode;
    }

    public void setDepName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setEmployeeMap(HashMap<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    public void setDeptTotalSalary(Double departmentTotalSalary) {
        this.departmentTotalSalary += departmentTotalSalary;
    }

    public String getDepCode() {
        return deparmentCode;
    }

    public String getDepName() {
        return departmentName;
    }

    public Double getDeptTotalSalary() {
        return departmentTotalSalary;
    }

    public HashMap<String, Employee> getEmployeeMap() {
        return employeeMap;
    }
}
