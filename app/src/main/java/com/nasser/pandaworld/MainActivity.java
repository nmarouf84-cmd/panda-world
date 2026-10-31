package com.nasser.pandaworld;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nasser.pandaworld.bridge.BridgeNetworkManager;
import com.nasser.pandaworld.engine.UniversalCommandEngine;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements UniversalCommandEngine.LogCallback {

    private EditText etCommandInput;
    private Button btnSendCommand;
    private ListView lvActionLogs;
    private TextView tvNodesStatus;
    private ArrayList<String> logList;
    private ArrayAdapter<String> adapter;
    private UniversalCommandEngine commandEngine;
    private BridgeNetworkManager bridgeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCommandInput = findViewById(R.id.etCommandInput);
        btnSendCommand = findViewById(R.id.btnSendCommand);
        lvActionLogs = findViewById(R.id.lvActionLogs);
        tvNodesStatus = findViewById(R.id.tvNodesStatus);

        logList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, logList);
        lvActionLogs.setAdapter(adapter);

        commandEngine = new UniversalCommandEngine(this);
        bridgeManager = new BridgeNetworkManager(this);

        onLogUpdated("Panda Mobile Node Online. Universal Command Engine Loaded.");
        onLogUpdated("Bridge Network Status: Connected (12 Active Nodes)");

        btnSendCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command = etCommandInput.getText().toString().trim();
                if (!command.isEmpty()) {
                    onLogUpdated("You: " + command);
                    etCommandInput.setText("");
                    commandEngine.executeCommand(command);
                }
            }
        });
    }

    @Override
    public void onLogUpdated(String message) {
        runOnUiThread(() -> {
            String timeStamp = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            logList.add(0, "[" + timeStamp + "] " + message);
            adapter.notifyDataSetChanged();
        });
    }
}