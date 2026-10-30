package proxy.protected_documents;

import java.time.LocalDate;

public interface DocumentAccess {
    String getId();

    LocalDate getCreationDate();

    String getContent(User user);
}
