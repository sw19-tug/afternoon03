package at.tugraz.ist.swe.teachingassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVocabActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vocab);

        addVocabViewButton();
        db = new DatabaseHelper(this);
    }

    private void addVocabViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.save_translation);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                VocabularManager vocabulary = VocabularManager.getInstance();

                EditText first_language = (EditText) findViewById(R.id.et_first_lang);
                EditText second_language = (EditText) findViewById(R.id.et_second_lang);
                EditText tags = (EditText) findViewById(R.id.et_tags);
                if (!(first_language.getText().toString().isEmpty() || second_language.getText().toString().isEmpty())) {
                    Word word1 = new Word(first_language.getText().toString(), "en");
                    Word word2 = new Word(second_language.getText().toString(), "fi");
                    vocabulary.addVocab(word1, word2, tags.getText().toString());
                    db.deleteVocabs();
                    for (Vocab vocab : vocabulary.getVocabs()) {
                        db.insertVocab(vocab);
                    }
                    Toast.makeText(getApplicationContext(), "succeed: " + first_language.getText() + " " + second_language.getText(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "please enter both languages", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
