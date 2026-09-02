package composite.organization_structure;

public class Employee implements Organization {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void printXml() {
        System.out.println("<employee>");
        System.out.println("  <name>" + name + "</name>");
        System.out.println("  <salary>" + salary + "</salary>");
        System.out.println("</employee>");
    }
}
