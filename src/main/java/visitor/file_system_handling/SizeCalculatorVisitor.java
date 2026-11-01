package visitor.file_system_handling;

public class SizeCalculatorVisitor implements FileSystemVisitor {
    private double totalSizeInMb = 0;

    @Override
    public void visit(File file) {
        totalSizeInMb += file.getSizeInMb();
    }

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public double getTotalSizeInMb() {
        return totalSizeInMb;
    }
}
