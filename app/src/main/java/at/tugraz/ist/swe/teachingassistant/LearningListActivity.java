package at.tugraz.ist.swe.teachingassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LearningListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_list);
    }
    @Override
    protected void onResume() {
        super.onResume();

        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayAdapter adapter_language_list = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString("en"));
        Toast.makeText(getApplicationContext(), "size: " + adapter_language_list.getCount(), Toast.LENGTH_LONG).show();
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setAdapter(adapter_language_list);

    }
}
