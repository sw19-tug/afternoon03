package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class VocabularyActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(VocabularyActivity.this, AddVocabActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayAdapter adapter_first_language = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(vocabulary.getFirstLanguage()));
        ListView listView_first_language = (ListView) findViewById(R.id.first_language_list);
        listView_first_language.setAdapter(adapter_first_language);
        ArrayAdapter adapter_second_language = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vocabulary.getWordsFromLanguageString(vocabulary.getSecondLanguage()));
        ListView listView_second_language = (ListView) findViewById(R.id.second_language_list);
        listView_second_language.setAdapter(adapter_second_language);

    }


}
