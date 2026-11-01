package visitor.file_system_handling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor implements FileSystemVisitor {
    private final Predicate<File> matcher;
    private final List<File> matchingFiles = new ArrayList<>();

    public SearchVisitor(Predicate<File> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void visit(File file) {
        if (matcher.test(file)) {
            matchingFiles.add(file);
        }
    }

    @Override
    public void visit(Directory directory) {
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public List<File> getMatchingFiles() {
        return Collections.unmodifiableList(matchingFiles);
    }
}
