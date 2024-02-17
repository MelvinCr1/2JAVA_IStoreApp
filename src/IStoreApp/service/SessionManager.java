// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    private static final Map<String, String> sessions = new HashMap<>();

    // Méthode pour démarrer une nouvelle session pour un utilisateur donné
    public static String startSession(String userEmail) {
        String sessionId = generateSessionId();
        sessions.put(sessionId, userEmail);
        return sessionId;
    }

    // Méthode pour récupérer l'email de l'utilisateur à partir de l'identifiant de session
    public static String getUserEmail(String sessionId) {
        return sessions.get(sessionId);
    }

    // Méthode pour mettre fin à une session
    public static void endSession(String sessionId) {
        sessions.remove(sessionId);
    }

    // Méthode pour générer un identifiant de session unique
    private static String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}

