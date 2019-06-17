package at.tugraz.ist.swe.teachingassistant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private final int SHARE_RETURN_CODE = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_interface);
        DatabaseHelper db = new DatabaseHelper(this);
        List<StoredTest> tests = db.getAllNotes();
        ArrayList<String> testStrings = new ArrayList<>();
        for (StoredTest test : tests) {
            testStrings.add(test.getDate() + " - " + test.getCorrect() + "/" + test.getSize() + " - " + test.getTime() + "s");
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testStrings);
        ListView listView = (ListView) findViewById(R.id.leaderboardList);
        listView.setAdapter(adapter);
    }


}