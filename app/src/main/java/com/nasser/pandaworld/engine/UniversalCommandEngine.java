package com.nasser.pandaworld.engine;

public class UniversalCommandEngine {
    public interface LogCallback {
        void onLogUpdated(String message);
    }
    private LogCallback callback;

    public UniversalCommandEngine(LogCallback callback) {
        this.callback = callback;
    }

    public void executeCommand(String command) {
        String lower = command.toLowerCase().trim();
        String response = "";

        if (lower.contains("linkedin") || lower.contains("open")) {
            response = "[Web Node] Opening LinkedIn & Activating Read Aloud Extension.";
        } else if (lower.contains("install") || lower.contains("ثبت") || lower.contains("winget")) {
            response = "[Software Store Node] Authorizing Package Installer.";
        } else if (lower.contains("light") || lower.contains("إضاءة") || lower.contains("alexa")) {
            response = "[Smart Home Node] Toggling IoT Smart Device.";
        } else if (lower.contains("fairuz") || lower.contains("فيروز")) {
            response = "[Media Node] Playing Fairuz - Kifak Enta.";
        } else {
            response = "[Multi-AI Stream] Routed -> " + command;
        }

        if (callback != null) {
            callback.onLogUpdated("Panda Engine: " + response);
        }
    }
}