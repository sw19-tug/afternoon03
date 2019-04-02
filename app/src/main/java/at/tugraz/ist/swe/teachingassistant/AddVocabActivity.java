package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.System.exit;

public class AddVocabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vocab);

        addVocabViewButton();
    }

    private void addVocabViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.save_translation);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                VocabularManger vocabulary = VocabularManger.getInstance();

                EditText first_language = (EditText) findViewById(R.id.et_first_lang);
                EditText second_language = (EditText) findViewById(R.id.et_second_lang);
                Word word1 = new Word(first_language.getText().toString(), "en");
                Word word2 = new Word(second_language.getText().toString(), "fi");
                vocabulary.addVocab(word1, word2);
                Toast.makeText(getApplicationContext(), "succeed: " + first_language.getText() + " " + second_language.getText(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
