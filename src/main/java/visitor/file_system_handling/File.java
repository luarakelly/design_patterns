package visitor.file_system_handling;

public class File implements FileSystemElement {
    private final String name;
    private final double sizeInMb;

    public File(String name, double sizeInMb) {
        this.name = name;
        this.sizeInMb = sizeInMb;
    }

    public String getName() {
        return name;
    }

    public double getSizeInMb() {
        return sizeInMb;
    }

    public String getExtension() {
        int lastDotIndex = name.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < name.length() - 1) {
            return name.substring(lastDotIndex + 1).toLowerCase();
        }
        return "";
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
}
