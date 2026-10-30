package proxy.protected_documents;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        User alice = new User("alice");
        User bob = new User("bob");
        Library library = new Library();

        library.addUnprotectedDocument(
                "public-1", LocalDate.of(2026, 10, 1), "This document is public.");
        library.addProtectedDocument(
                "private-1", LocalDate.of(2026, 10, 15), "This document is confidential.");

        AccessControlService accessControl = AccessControlService.getInstance();
        accessControl.grantAccess("private-1", alice.getUsername());

        DocumentAccess publicDocument = library.getDocument("public-1");
        DocumentAccess protectedDocument = library.getDocument("private-1");

        System.out.println("Available documents: " + library.getDocumentIds());
        System.out.println("Public document created: " + publicDocument.getCreationDate());
        System.out.println("Bob reads public document: " + publicDocument.getContent(bob));
        System.out.println("Alice reads protected document: "
                + protectedDocument.getContent(alice));

        System.out.println("Protected document created: "
                + protectedDocument.getCreationDate());
        try {
            System.out.println("Bob reads protected document: "
                    + protectedDocument.getContent(bob));
        } catch (AccessDeniedException exception) {
            System.out.println("Access denied: " + exception.getMessage());
        }
    }
}
