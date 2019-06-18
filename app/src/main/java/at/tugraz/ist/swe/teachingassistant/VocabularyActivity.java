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
        VocabularyListAdapter mainAdapter = new VocabularyListAdapter(this,vocabulary.getWordsFromLanguageString("en"),vocabulary.getWordsFromLanguageString("fi"));
        ListView listView = (ListView) findViewById(R.id.first_language_list);
        listView.setAdapter(mainAdapter);
    }


}
