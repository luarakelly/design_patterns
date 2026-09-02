package composite.organization_structure;

import java.util.ArrayList;
import java.util.List;

public class Department implements Organization {

    private String name;
    private List<Organization> children;

    public Department(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(Organization organization) {
        children.add(organization);
    }

    public void remove(Organization organization) {
        children.remove(organization);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        double totalSalary = 0;
        for (Organization child : children) {
            totalSalary += child.getSalary();
        }
        return totalSalary;
    }

    @Override
    public void printXml() {
        System.out.println("<department>");
        System.out.println("  <name>" + name + "</name>");
        System.out.println("  <salary>" + getSalary() + "</salary>");
        System.out.println("  <children>");
        for (Organization child : children) {
            child.printXml();
        }
        System.out.println("  </children>");
        System.out.println("</department>");
    }
}
