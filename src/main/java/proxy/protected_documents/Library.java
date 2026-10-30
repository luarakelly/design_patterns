package proxy.protected_documents;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public final class Library {
    private final HashMap<String, DocumentAccess> documents = new HashMap<>();

    public DocumentAccess addUnprotectedDocument(
            String id, LocalDate creationDate, String content) {
        Document document = new Document(id, creationDate, content);
        addDocument(document);
        return document;
    }

    public DocumentProxy addProtectedDocument(
            String id, LocalDate creationDate, String content) {
        Document document = new Document(id, creationDate, content);
        DocumentProxy proxy = new DocumentProxy(document);
        addDocument(proxy);
        return proxy;
    }

    public DocumentAccess getDocument(String id) {
        DocumentAccess document = documents.get(id);
        if (document == null) {
            throw new IllegalArgumentException("No document found with id '" + id + "'.");
        }
        return document;
    }

    public Set<String> getDocumentIds() {
        return Collections.unmodifiableSet(documents.keySet());
    }

    private void addDocument(DocumentAccess document) {
        Objects.requireNonNull(document, "document");
        if (documents.putIfAbsent(document.getId(), document) != null) {
            throw new IllegalArgumentException(
                    "A document with id '" + document.getId() + "' already exists.");
        }
    }
}
