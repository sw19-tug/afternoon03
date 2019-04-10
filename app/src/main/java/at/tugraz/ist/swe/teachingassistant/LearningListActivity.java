package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LearningListActivity extends AppCompatActivity {

    private String currentLang = "";
    private VocabularManager vocabulary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_list);
        vocabulary = VocabularManager.getInstance();
        currentLang = vocabulary.getFirstLanguage();
        TextView language_title = (TextView) findViewById(R.id.languageTitle);
        language_title.setText(currentLang);
        configureAddListViewButton();
        configureListViewItems();
    }
    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter adapter_language_list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setAdapter(adapter_language_list);

    }

    private void configureAddListViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_changeLanguage);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLang = currentLang.equals(vocabulary.getFirstLanguage()) ? vocabulary.getSecondLanguage() : vocabulary.getFirstLanguage();
                TextView language_title = (TextView) findViewById(R.id.languageTitle);
                language_title.setText(currentLang);
                ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
        });
    }
    private void configureListViewItems() {
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LearningListActivity.this, LearningInterfaceActivity.class);
                Bundle b = new Bundle();
                b.putInt("position", position);
                b.putString("current_lang", currentLang);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }



}
