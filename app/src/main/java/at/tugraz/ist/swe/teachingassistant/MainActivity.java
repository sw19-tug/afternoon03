package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAddListViewButton();
        configureExportDialog();
        configureImportDialog();
        configureLearningButton();
        configureTestingButton();
    }

    private void configureAddListViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_enterAddList);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                startActivity(intent);
            }
        });

    }

    private void configureExportDialog() {
        Button ExportViewButton = (Button) findViewById(R.id.btn_export);
        ExportViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void configureImportDialog() {
        Button ImportViewButton = (Button) findViewById(R.id.btn_import);
        ImportViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImportActivity.class);
                startActivity(intent);
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


    private void configureTestingButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_testing);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!VocabularManager.getInstance().emptyVocabCheck()) {
                    Intent intent = new Intent(MainActivity.this, SelectTestingActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No vocabs found for test", Toast.LENGTH_LONG).show();
                }
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
