package at.tugraz.ist.swe.teachingassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    private void configureChangeLanguageButton() {
        Button buttonChangeLanguage = (Button) findViewById(R.id.btn_changeLanguageInterface);
        buttonChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_lang = current_lang == "en" ? "fi" : "en";
                TextView language_title = (TextView) findViewById(R.id.currentLanguage);
                language_title.setText(current_lang != "en" ? "Finnish" : "English");
            }
        });
    }
}
