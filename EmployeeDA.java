import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDA {
    private Employee employee;

    public EmployeeDA(String empNo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("/workspaces/LabAss5/emp.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeLineData = line.split(",", 4);
                if (empNo.equals(employeeLineData[0].trim())) {
                    employee = new Employee();
                    employee.setEmpNo(empNo);
                    employee.setLastName(employeeLineData[1].trim());
                    employee.setFirstName(employeeLineData[2].trim());
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getEmployee() {
        return employee;
    }
}
