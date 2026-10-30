package proxy.protected_documents;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class AccessControlService {
    private static final AccessControlService INSTANCE = new AccessControlService();

    private final Set<AccessKey> permissions = ConcurrentHashMap.newKeySet();

    private AccessControlService() {
    }

    public static AccessControlService getInstance() {
        return INSTANCE;
    }

    public void grantAccess(String documentId, String username) {
        permissions.add(new AccessKey(documentId, username));
    }

    public void revokeAccess(String documentId, String username) {
        permissions.remove(new AccessKey(documentId, username));
    }

    public boolean isAllowed(String documentId, String username) {
        return permissions.contains(new AccessKey(documentId, username));
    }

    private record AccessKey(String documentId, String username) {
    }
}
