package composite.organization_structure;

public class Main {

    public static void main(String[] args) {

        Department company = new Department("Company");

        Department it = new Department("IT");
        Department hr = new Department("HR");

        Employee luara = new Employee("Luara", 3000);
        Employee silva = new Employee("Silva", 4000);
        Employee joonas = new Employee("Joonas", 3500);

        it.add(luara);
        it.add(silva);
        hr.add(joonas);

        company.add(it);
        company.add(hr);

        System.out.println("Total salary: " + company.getSalary());

        it.remove(silva);

        System.out.println("Total salary after removing Silva: " + company.getSalary());

        company.printXml();
    }
}