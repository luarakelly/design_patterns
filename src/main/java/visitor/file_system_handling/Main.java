package visitor.file_system_handling;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("project");
        Directory src = new Directory("src");
        Directory docs = new Directory("docs");

        root.add(new File("README.txt", 1.5));
        root.add(src);
        root.add(docs);

        src.add(new File("Main.java", 0.8));
        src.add(new File("Utils.java", 0.5));

        docs.add(new File("spec.md", 2.2));
        docs.add(new File("notes.txt", 0.4));

        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);
        System.out.printf("Total size of all files: %.2f MB%n", sizeCalculator.getTotalSizeInMb());

        SearchVisitor textFiles = new SearchVisitor(file -> file.getExtension().equals("txt"));
        root.accept(textFiles);

        System.out.println("Matching text files:");
        for (File file : textFiles.getMatchingFiles()) {
            System.out.println("- " + file.getName() + " (" + file.getSizeInMb() + " MB)");
        }
    }
}
