import java.util.ArrayList;

public class Director extends Manager {
    int stockShares;
    public Director(double salary, int bonus, int stockShares, String name, String department, String title, ArrayList<Employee> reports, int tier) {
        super(salary, bonus, name, department, title, reports, tier);
        this.stockShares = stockShares;
    }

    private boolean containsTrue(Employee employee) {
        for (Employee report : reports) {
            if (employee == report) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void adjustSalary(double change, Employee employee) throws Exception {
        if (employee.getTier() == 2 && !containsTrue(employee)) {
            throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
        }
        else if (employee.getTier() == 1) {
            boolean somewhere = false;
            for (int i = 0; i < reports.size(); i++) {
                Manager manager = (Manager) reports.get(i);
                for (int j = 0; j < manager.reports.size(); j++) {
                    Employee actualEmployee = manager.reports.get(j);
                    if (actualEmployee.equals(employee)) {
                        somewhere = true;
                        break;
                    }
                }
            }
            if (!somewhere) {
                throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
            }
            else {
                employee.adjustSalary(change, employee);
            }
        }
        employee.adjustSalary(change, employee);
    }


    @Override
    public void hire(Employee employee) throws Exception {
        if (employee.getTier() == 3) {
            throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
        }
        else {
            this.reports.add(employee);
            System.out.println("LOG: new Employee hired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
        }
    }

    @Override
    public void fire(Employee employee) throws Exception {
        if(!containsTrue(employee)) {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        else {
            System.out.println("LOG: existing Employee fired (" + employee.getName() + ", " + employee.getDepartment() + ", " + employee.getTitle() + ")");
          if (employee.getTier() == 2 && ((Manager) employee).reports.size() > 0) {
              String reassignMessage = "";
              reassignMessage += "LOG: reports re-assigned [";
              for(Employee oldEmployee : reports) {
                  if (reassignMessage.equals("LOG: reports re-assigned [")) {
                      reassignMessage += oldEmployee.getName() + ", " + oldEmployee.getDepartment() + ", " + oldEmployee.getTitle();
                  }
                  else {
                      reassignMessage += ", " + oldEmployee.getName() + ", " + oldEmployee.getDepartment() + ", " + oldEmployee.getTitle();
                  }
              }
              reassignMessage += "]";
              System.out.println(reassignMessage);
              ArrayList<Employee> newReports = new ArrayList<Employee>();
              for (int f = 0; f < ((Manager) employee).reports.size(); f++) {
                  double newSalary = ((Manager) employee).reports.get(f).getSalary();
                  String newName = ((Manager) employee).reports.get(f).getName();
                  String newDepartment = ((Manager) employee).reports.get(f).getDepartment();
                  String newTitle = ((Manager) employee).reports.get(f).getTitle();
                  Manager manager = new Manager(newSalary, ((Manager) employee).getBonus(), newName, newDepartment, newTitle, new ArrayList<>(), Company.MANAGER);
                  newReports.add(manager);
              }
              reports.addAll(newReports);
            }
            reports.remove(employee);
      }
    }


}
