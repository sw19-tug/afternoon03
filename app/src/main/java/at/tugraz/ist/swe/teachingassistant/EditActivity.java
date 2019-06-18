package at.tugraz.ist.swe.teachingassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private String first_lang;
    private String second_lang;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        first_lang = getIntent().getExtras().getString("first_lang");
        second_lang = getIntent().getExtras().getString("second_lang");

        addVocabViewButton();

        EditText first_language = (EditText) findViewById(R.id.et_first_lang);
        first_language.setText(first_lang);
        EditText second_language = (EditText) findViewById(R.id.et_second_lang);
        second_language.setText(second_lang);
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
                    vocabulary.editVocabByWord(first_lang, second_lang, word1, word2, tags.getText().toString());
                    db.deleteVocabs();
                    for (Vocab vocab : vocabulary.getVocabs()) {
                        db.insertVocab(vocab);
                    }
                    Toast.makeText(getApplicationContext(), "edit: " + first_language.getText() + " " + second_language.getText(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "please enter both languages", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}

