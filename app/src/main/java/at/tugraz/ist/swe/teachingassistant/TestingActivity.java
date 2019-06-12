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


        nextButton();

    }

    private void nextButton(){
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
                            setContentView(R.layout.test_feedback_interface);
                            exitTestingButton();
                            continueTestingButton();
                            TextView progressCounter = (TextView) findViewById(R.id.tv_test_result);
                            progressCounter.setText(Integer.toString(testingManager.getScore()) + "/" + testingManager.getActiveSize());

                            Toast.makeText(getApplicationContext(), "Its over!", Toast.LENGTH_LONG).show();
                            break;
                    }
                }

            }
        });
    }

    private void exitTestingButton(){
        Button exitButton = (Button) findViewById(R.id.btn_exit_testing);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void continueTestingButton(){
        Button continueButton = (Button) findViewById(R.id.btn_continue_testing);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestingManager testingManager = TestingManager.getInstance();
                testingManager.setContinueTesting();

                setContentView(R.layout.testing_activity);
                updateInterface();
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

//    private void goToFeedbackInterface(){
//        setContentView(R.layout.test_feedback_interface);
//        TextView progressCounter = (TextView) findViewById(R.id.tv_test_result);
//        progressCounter.setText(Integer.toString(testingManager.getScore() + 1) + "/" + testingManager.getActiveSize());
//    }
}

