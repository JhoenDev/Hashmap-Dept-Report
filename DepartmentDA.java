import java.util.*;
import java.text.*;
import java.io.FileReader;
import java.io.FileNotFoundException;


public class DepartmentDA {
    public DepartmentDA() throws FileNotFoundException {
        System.out.println(generateDepartmentReport());
    }

    private String generateDepartmentReport() throws FileNotFoundException {
        StringBuilder report = new StringBuilder();

        Scanner depFile = new Scanner(new FileReader("/workspaces/LabAss5/dep.csv"));
        depFile.nextLine();

        while (depFile.hasNextLine()) {
            String[] RawFile = depFile.nextLine().split(",");
            Department dep = new Department();

            dep.setDepCode(RawFile[0].trim());
            dep.setDepName(RawFile[1].trim());
            readEmp(dep);
            report.append(printDep(dep));
        }

        return report.toString();
    }

    private void readEmp(Department department) throws FileNotFoundException {
        Scanner depEmpFile = new Scanner(new FileReader("/workspaces/LabAss5/depTemp.csv"));
        HashMap<String, Employee> employeeMap = new HashMap<>();
        double totalSalary = 0;

        try {
            while (depEmpFile.hasNextLine()) {
                String line = depEmpFile.nextLine();
                String[] rawArray = line.split(",", 3);
            
                String DepCode = rawArray[0].trim();
                String employeeNo = rawArray[1].trim();
                double salary = Double.parseDouble(rawArray[2].trim());
            
                if (!department.getDepCode().trim().equalsIgnoreCase(DepCode)) {
                    continue;
                }
                
                EmployeeDA empDa = new EmployeeDA(employeeNo);
                Employee emp = empDa.getEmployee();
                emp.setSalary(salary);
                employeeMap.put(employeeNo, emp);
                totalSalary += salary;
            }
        } finally {
            depEmpFile.close();
        }
        department.setEmployeeMap(employeeMap);
        department.setDeptTotalSalary(totalSalary);
    }

    private String printDep(Department department) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        StringBuilder departmentString = new StringBuilder();
    
        departmentString.append("Department Code: ").append(department.getDepCode()).append("\n");
        departmentString.append("Department Name: ").append(department.getDepName()).append("\n");
        departmentString.append("Department Total Salary: ").append(df.format(department.getDeptTotalSalary())).append("\n");
        departmentString.append("---------------------Details -------------------------").append("\n");
        departmentString.append(String.format("%-10s %-20s %10s\n", "EmpNo", "EmployeeName", "Salary"));
    
        for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
            Employee employee = entryMap.getValue();
            departmentString.append(String.format("%-10s %-20s %10s\n", entryMap.getKey(), employee.getLastName() + ", " + employee.getFirstName(), df.format(employee.getSalary())));
        }
        departmentString.append("\n");
    
        return departmentString.toString();
    }
}
