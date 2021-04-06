import java.util.ArrayList;

public class Director extends Manager {
    int stockShares;
    public Director(double salary, int bonus, int stockShares, String name, String department, String title, ArrayList<Employee> reports, int tier) {
        super(salary, bonus, name, department, title, reports, tier);
        this.stockShares = stockShares;
    }
    @Override
    public void hire(Employee employee) {
            this.reports.add(employee);
            System.out.println("LOG: new Employee hired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
    }


    private boolean containsTrue(Employee employee) {
        for (Employee report : reports) {
            if (employee == report) {
                return true;
            }
        }
        return false;
    }

    //!containsTrue(employee)
    @Override
    public void fire(Employee employee) throws Exception {
        System.out.println(employee.getName());
        if(!employee.getDepartment().equals(getDepartment())) {
            throw new Exception("Exception in thread \"main\" java.lang.Exception: ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        else {
            System.out.println("LOG: existing Employee fired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
          if (employee.getTier() == 2 && ((Manager) employee).reports.size() > 0) {
                reports.addAll(((Manager) employee).reports);
            }
            reports.remove(employee);
      }
    }


}
