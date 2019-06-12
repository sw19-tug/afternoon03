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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_activity);

        VocabularManager vocabManager = VocabularManager.getInstance();
        TestingManager testingManager = TestingManager.getInstance();

        testingManager.setTestingVocabs(vocabManager.getVocabs());

        testingManager.buildRandomTest();
        updateInterface();


        final Button button = (Button) findViewById(R.id.btn_testing_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TestingManager testingManager = TestingManager.getInstance();
                EditText request_word = (EditText) findViewById(R.id.et_requestedWord);
                if (!request_word.equals("")) {
                    switch(testingManager.checkResult(request_word.getText().toString())) {
                        case 0:
                            Toast.makeText(getApplicationContext(), "Dumbass", Toast.LENGTH_LONG).show();
                            updateInterface();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Succeed", Toast.LENGTH_LONG).show();
                            updateInterface();
                            break;
                        case 2:
                        //show score
                            Toast.makeText(getApplicationContext(), "Its over!", Toast.LENGTH_LONG).show();
                            finish();
                            break;
                    }
                }

            }
        });

    }

    private void updateInterface(){
        TestingManager testingManager = TestingManager.getInstance();
        Vocab currentVocab = testingManager.getCurrentVocab();

        TextView providedWord = (TextView) findViewById(R.id.tv_providedWord);
        providedWord.setText(currentVocab.getTranslationByLanguage("en"));

        TextView progressCounter = (TextView) findViewById(R.id.tv_fraction_progress);
        progressCounter.setText(Integer.toString(testingManager.getCurrentPosition() + 1) + "/" + testingManager.getActiveSize());

    }
}

