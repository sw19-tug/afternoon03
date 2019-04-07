package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LearningListActivity extends AppCompatActivity {

    private String currentLang = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_list);
        currentLang = "en";
        TextView language_title = (TextView) findViewById(R.id.languageTitle);
        language_title.setText(currentLang != "en" ? "Finnish" : "English");
        configureAddListViewButton();
    }
    @Override
    protected void onResume() {
        super.onResume();

        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayAdapter adapter_language_list = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
        Toast.makeText(getApplicationContext(), "size: " + adapter_language_list.getCount(), Toast.LENGTH_LONG).show();
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setAdapter(adapter_language_list);


    }

    private void configureAddListViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_changeLanguage);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLang = currentLang == "en" ? "fi" : "en";
                TextView language_title = (TextView) findViewById(R.id.languageTitle);
                language_title.setText(currentLang != "en" ? "Finnish" : "English");

                VocabularManager vocabulary = VocabularManager.getInstance();
                ArrayAdapter adapter_language_list = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
                Toast.makeText(getApplicationContext(), "size: " + adapter_language_list.getCount(), Toast.LENGTH_LONG).show();
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
        });

    }


}
