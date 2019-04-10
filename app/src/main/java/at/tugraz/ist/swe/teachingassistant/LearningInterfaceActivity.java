package at.tugraz.ist.swe.teachingassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;



public class LearningInterfaceActivity extends AppCompatActivity {
    private String currentLang;
    private Integer current_position;
    private VocabularManager vocabulary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_interface);
        Bundle b = getIntent().getExtras();
        currentLang = b.getString("current_lang");
        current_position = b.getInt("position");
        TextView current_language = (TextView) findViewById(R.id.currentLanguage);
        vocabulary = VocabularManager.getInstance();
        current_language.setText(!currentLang.equals(vocabulary.getFirstLanguage()) ? vocabulary.getSecondLanguage() : vocabulary.getFirstLanguage());
        changeCurrentWord();
        configureChangeLanguageButton();
        configureNextButton();
        configurePreviousButton();
    }

    private void configureChangeLanguageButton() {
        Button buttonChangeLanguage = (Button) findViewById(R.id.btn_changeLanguageInterface);
        buttonChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLang = currentLang.equals(vocabulary.getFirstLanguage()) ? vocabulary.getSecondLanguage() : vocabulary.getFirstLanguage();
                TextView language_title = (TextView) findViewById(R.id.currentLanguage);
                language_title.setText(!currentLang.equals(vocabulary.getFirstLanguage()) ? vocabulary.getSecondLanguage() : vocabulary.getFirstLanguage());
                changeCurrentWord();
            }
        });
    }
    private void configureNextButton() {
        Button buttonChangeLanguage = (Button) findViewById(R.id.btn_next);
        buttonChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> words =  vocabulary.getWordsFromLanguageString(currentLang);
                if(current_position != (words.size() - 1))
                current_position += 1;
                changeCurrentWord();
            }
        });
    }

    private void configurePreviousButton() {
        Button buttonChangeLanguage = (Button) findViewById(R.id.btn_prev);
        buttonChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_position != 0)
                current_position -= 1;
                changeCurrentWord();
            }
        });
    }

    private void changeCurrentWord(){
        TextView currentWord = (TextView) findViewById(R.id.currentWord);
        ArrayList<String> words =  vocabulary.getWordsFromLanguageString(currentLang);
        currentWord.setText(words.get(current_position));
    }
}
