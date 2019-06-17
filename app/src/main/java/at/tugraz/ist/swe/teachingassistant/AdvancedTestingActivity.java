package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AdvancedTestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_test_selection);
        VocabularManager vocabManager = VocabularManager.getInstance();
        TestingManager testingManager = TestingManager.getInstance();
        testingManager.clearRootVocab();
        configureListViewItems();
        ArrayAdapter adapter_language_list;
        adapter_language_list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vocabManager.getWordsFromLanguageString("en"));
        ListView listView_first_language = (ListView) findViewById(R.id.lv_available_vocabs);
        listView_first_language.setAdapter(adapter_language_list);
        configureTestingButton();
    }

    private void configureListViewItems() {
        ListView listView_first_language = (ListView) findViewById(R.id.lv_available_vocabs);
        listView_first_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VocabularManager vocabManager = VocabularManager.getInstance();
                TestingManager testingManager = TestingManager.getInstance();
                Vocab vocabToAdd = vocabManager.getVocabs().elementAt(position);
                if (testingManager.addVocabToRoot(vocabToAdd)) {
                    Toast.makeText(getApplicationContext(), vocabToAdd.getTranslationByLanguage("en") + " has been added", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), vocabToAdd.getTranslationByLanguage("en") + " is already added!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void configureTestingButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_start_test);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestingManager testingManager = TestingManager.getInstance();
                if(!(testingManager.getRootVocabs().size()== 0)) {
                    testingManager.setTestingVocabs(testingManager.getRootVocabs());
                    Intent intent = new Intent(AdvancedTestingActivity.this, TestingActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No vocabs found for test", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
