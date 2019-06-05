package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;

public class TestingActivity extends Activity {




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
        Log.e("Should be", currentVocab.getTranslationByLanguage("en"));

        TextView progressCounter = (TextView) findViewById(R.id.tv_progress);
        progressCounter.setText(Integer.toString(position + 1) + "/" + testingManager.getActiveSize());


       /* final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });*/

    }
}

