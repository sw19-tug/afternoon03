package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAddListViewButton();
        configureExportDialog();
        configureLearningButton();
    }

    private void configureAddListViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_enterAddList);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddVocabActivity.class);
                startActivity(intent);
            }
        });

    }

    private void configureExportDialog() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_export);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExportDialog exportDialog = new ExportDialog();
                exportDialog.show(getSupportFragmentManager(), "Export Dialog");
            }
        });

    }

    private void configureLearningButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_learning);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LearningListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        VocabularManager manager= VocabularManager.getInstance();
        //vocabulary.printVocab();
    }

}
