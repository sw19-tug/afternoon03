package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectTestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_test);
        TestingManager testingManager = TestingManager.getInstance();
        configureListViewItems();
        ArrayAdapter adapter_language_list;
        adapter_language_list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testingManager.getAvailableTests());
        ListView listView_first_language = (ListView) findViewById(R.id.lv_test_type);
        listView_first_language.setAdapter(adapter_language_list);

    }

    private void configureListViewItems() {
        ListView listView_first_language = (ListView) findViewById(R.id.lv_test_type);
        listView_first_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //usual Random test
                if (position == 0) {
                    VocabularManager vocabManager = VocabularManager.getInstance();
                    TestingManager testingManager = TestingManager.getInstance();
                    testingManager.setTestingVocabs(vocabManager.getVocabs());
                    Intent intent = new Intent(SelectTestingActivity.this, TestingActivity.class);
                    startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(SelectTestingActivity.this, AdvancedTestingActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

}
