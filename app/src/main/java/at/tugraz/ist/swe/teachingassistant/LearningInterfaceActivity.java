package at.tugraz.ist.swe.teachingassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;


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
        current_language.setText(!currentLang.equals("en") ? "Finnish" : "English");
        changeCurrentWord();
        configureChangeLanguageButton();
        configureNextButton();
        configurePreviousButton();
        configureSeekBar();
    }

    private void configureChangeLanguageButton() {
        Button buttonChangeLanguage = (Button) findViewById(R.id.btn_changeLanguageInterface);
        buttonChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLang = currentLang.equals("en") ? "fi" : "en";
                TextView language_title = (TextView) findViewById(R.id.currentLanguage);
                language_title.setText(!currentLang.equals("en") ? "Finnish" : "English");
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



    private void configureSeekBar()
    {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(vocabulary.getVocabs().get(current_position).getRating());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int rating = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                rating = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                VocabularManager vocabulary = VocabularManager.getInstance();
                Vector<Vocab> vocabs = vocabulary.getVocabs();
                vocabs.get(current_position).setRating(rating);
            }
        });

    }
}
