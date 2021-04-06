import java.text.spi.BreakIteratorProvider;
import java.util.ArrayList;

public class Manager extends Employee {
    private int bonus;
    public ArrayList<Employee> reports;

    public Manager(double salary, int bonus, String name, String department, String title, ArrayList<Employee> reports, int tier) {
        super(salary, name, department, title);
        this.bonus = bonus;
        //error exceptions
        this.reports = reports;
        super.setTier(tier);
    }


    public void hire(Employee employee) throws Exception {
        if (employee.getTier() > 2) {
            throw new Exception("Exception in thread \"main\" java.lang.Exception: ERROR: cannot hire an Employee of an equal or greater tier.");
        }
        else {
            reports.add(employee);
            System.out.println("LOG: new Employee hired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
        }
    }

    private boolean isIn(Employee employee) {
        for (Employee report : reports) {
            if (employee == report) {
                return true;
            }
        }
        return false;
    }

    public void fire(Employee employee) throws Exception {
        if (employee.getTier() > 2) {
            throw new Exception("Exception in thread \"main\" java.lang.Exception: ERROR: cannot fire an Employee of an equal or greater tier.");
        }
        else if(!isIn(employee)) {
            throw new Exception("Exception in thread \"main\" java.lang.Exception: ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        else {
            System.out.println("LOG: existing Employee fired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
            reports.remove(employee);
        }
    }
}

