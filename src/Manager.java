import java.text.spi.BreakIteratorProvider;
import java.util.ArrayList;

public class Manager extends Employee {
    private final int bonus;
    public ArrayList<Employee> reports;

    public Manager(double salary, int bonus, String name, String department, String title, ArrayList<Employee> reports, int tier) throws Exception {
        super(salary, name, department, title);
        this.bonus = bonus;

        for (Employee report : reports) {
            if (this.getTier() - report.getTier() != 1) {
                throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
            }
        }

        this.reports = reports;
        super.setTier(tier);
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public void adjustSalary(double change, Employee employee) throws Exception {
        if (employee.getTier() == 1 && !isIn(employee)) {
            throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
        }
        super.adjustSalary(change, employee);
    }

    public void hire(Employee employee) throws Exception {
        if (employee.getTier() >= 2) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        }
        else if (employee.getTier() != 1) {
            throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
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
        if (employee.getTier() >= 2) {
            throw new Exception("ERROR: cannot fire an Employee of an equal or greater tier.");
        }
        else if(!isIn(employee)) {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        else {
            System.out.println("LOG: existing Employee fired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
            reports.remove(employee);
        }
    }
}

