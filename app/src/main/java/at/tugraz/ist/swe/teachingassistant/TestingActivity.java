package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TestingActivity extends Activity {

    public Vocab currentVocab = null;
    public int hintCounter = 0;
    private Timer timer;
    private boolean running = false;
    public int seconds = 0;
    public DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_activity);
        TestingManager testingManager = TestingManager.getInstance();
        testingManager.buildRandomTest();
        updateInterface();

        hintButton();
        nextButton();
        db = new DatabaseHelper(this);

        startTimer();

    }

    private void updateTimerText() {
        TextView timeView = (TextView) findViewById(R.id.tv_time);
        timeView.setText("Time: " + seconds + "s");
    }

    private Runnable timerTick = new Runnable() {
        @Override
        public void run() {
            seconds++;
            updateTimerText();
        }
    };

    private void startTimer() {
        timer = new Timer();
        running = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runTimer();
            }
        }, 0, 1000);
    }

    private void runTimer() {
        this.runOnUiThread(timerTick);
    }


    private void nextButton() {
        final Button button = (Button) findViewById(R.id.btn_testing_next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TestingManager testingManager = TestingManager.getInstance();
                EditText request_word = (EditText) findViewById(R.id.et_requestedWord);
                if (!request_word.equals("")) {
                    switch (testingManager.checkResult(request_word.getText().toString())) {
                        case 0:
                            Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
                            updateInterface();
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
                            updateInterface();
                            break;
                        case 2:
                            timer.cancel();
                            setContentView(R.layout.test_feedback_interface);
                            Button continueButton = (Button) findViewById(R.id.btn_continue_testing);
                            TextView continueText = (TextView) findViewById(R.id.tv_user_info);
                            if (testingManager.getScore() == testingManager.getActiveSize()) {
                                continueButton.setVisibility(View.INVISIBLE);
                                continueText.setVisibility(View.INVISIBLE);
                            }
                            exitTestingButton();
                            voidSaveButton();
                            continueTestingButton();
                            TextView progressCounter = (TextView) findViewById(R.id.tv_test_result);
                            TextView pointsText = (TextView) findViewById(R.id.tv_test_points);
                            TextView timeText = (TextView) findViewById(R.id.tv_test_result_time);
                            progressCounter.setText("Answers: " + Integer.toString(testingManager.getScore()) + "/" + testingManager.getActiveSize());
                            float score = testingManager.getScore();
                            float testSize = testingManager.getActiveSize();
                            float percentage = score / testSize * 100;
                            double rounded = Math.round(percentage * 100.0) / 100.0;
                            pointsText.setText("Points: " + rounded + "/100");
                            timeText.setText("Time: " + seconds + " s");
                            break;
                    }
                }

            }
        });
    }

    private void hintButton() {
        final Button button = (Button) findViewById(R.id.btn_testing_hint);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText text = (EditText) findViewById(R.id.et_requestedWord);
                if (hintCounter + 1 < currentVocab.getTranslationByLanguage("fi").length()) {
                    text.setText(text.getText().toString() + currentVocab.getTranslationByLanguage("fi").substring(hintCounter, hintCounter + 1));
                }
                hintCounter++;
            }
        });
    }

    private void voidSaveButton() {
        final Button button = (Button) findViewById(R.id.btn_save_test);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TestingManager testingManager = TestingManager.getInstance();
                db.insert(testingManager.getActiveSize(), testingManager.getScore(), seconds);
                button.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void exitTestingButton() {
        Button exitButton = (Button) findViewById(R.id.btn_exit_testing);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void continueTestingButton() {
        Button continueButton = (Button) findViewById(R.id.btn_continue_testing);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestingManager testingManager = TestingManager.getInstance();
                if (testingManager.getSizeOfIncorrectVocabs() != 0) {
                    testingManager.setContinueTesting();
                    setContentView(R.layout.testing_activity);
                    updateInterface();
                    nextButton();
                } else
                    finish();
            }
        });
    }

    public void stopTimer() {
        timer.cancel();
    }

    private void updateInterface() {
        TestingManager testingManager = TestingManager.getInstance();
        Log.e("Testing", "current_position: " + testingManager.getCurrentPosition() + "actual size: " + testingManager.getActiveSize());
        currentVocab = testingManager.getCurrentVocab();

        TextView providedWord = (TextView) findViewById(R.id.tv_providedWord);
        providedWord.setText(currentVocab.getTranslationByLanguage("en"));

        TextView progressCounter = (TextView) findViewById(R.id.tv_fraction_progress);
        progressCounter.setText(Integer.toString(testingManager.getCurrentPosition() + 1) + "/" + testingManager.getActiveSize());

        EditText request_word = (EditText) findViewById(R.id.et_requestedWord);
        request_word.setText("");
        hintCounter = 0;
    }
}

