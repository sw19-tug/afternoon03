package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class TestingActivity extends Activity {

    public Vocab currentVocab = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        int position = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_activity);

        VocabularManager vocabManager = VocabularManager.getInstance();
        TestingManager testingManager = TestingManager.getInstance();

        testingManager.setTestingVocabs(vocabManager.getVocabs());

        testingManager.buildRandomTest();

        Vocab currentVocab = testingManager.getVocabByPosition(position);

        TextView providedWord = (TextView) findViewById(R.id.tv_providedWord);
        providedWord.setText(currentVocab.getTranslationByLanguage("en"));

        TextView progressCounter = (TextView) findViewById(R.id.tv_fraction_progress);
        progressCounter.setText(Integer.toString(position + 1) + "/" + testingManager.getActiveSize());


        final Button button = (Button) findViewById(R.id.btn_testing_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TestingManager testingManager = TestingManager.getInstance();
                EditText request_word = (EditText) findViewById(R.id.et_requestedWord);
                if(testingManager.checkResult(request_word.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Succeed",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Dumbass",Toast.LENGTH_LONG).show();
            }
        });

    }
}

