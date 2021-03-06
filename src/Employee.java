public class Employee implements Comparable {

    private double salary;
    private final String name;
    private final String department;
    private final String title;
    private int tier = 1;

    public Employee(double salary, String name, String department, String title) {
        this.salary = salary;
        this.name = name;
        this.department = department;
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void adjustSalary(double change, Employee employee) throws Exception {
        double currentSalary = employee.getSalary();
        double newSalary = currentSalary + change;
        employee.setSalary(newSalary);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getTitle() {
        return title;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
