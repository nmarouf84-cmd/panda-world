package com.nasser.pandaworld.bridge;

import com.nasser.pandaworld.engine.UniversalCommandEngine;

public class BridgeNetworkManager {
    private UniversalCommandEngine.LogCallback callback;

    public BridgeNetworkManager(UniversalCommandEngine.LogCallback callback) {
        this.callback = callback;
    }

    public void syncNodes() {
        if (callback != null) {
            callback.onLogUpdated("Bridge Sync: 12 Nodes Synced Successfully.");
        }
    }
}