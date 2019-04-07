package at.tugraz.ist.swe.teachingassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class LearningInterfaceActivity extends AppCompatActivity {
    private String current_lang;
    private Integer current_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_interface);
        Bundle b = getIntent().getExtras();
        current_lang = b.getString("current_lang");
        current_position = b.getInt("position");
        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayList<String> words =  vocabulary.getWordsFromLanguageString(current_lang);
        TextView language_title = (TextView) findViewById(R.id.currentWord);
        language_title.setText(words.get(current_position));
        TextView current_language = (TextView) findViewById(R.id.currentLanguage);
        current_language.setText(current_lang);
    }
}
