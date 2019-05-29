package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class LearningListActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener
{

    private String currentLang = "";
    private int position_ = 0;
    private ArrayAdapter adapter_language_list;
    private Spinner spinner_filter;
    private Spinner spinner_sort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_list);
        currentLang = "en";
        TextView language_title = (TextView) findViewById(R.id.languageTitle);
        language_title.setText(!currentLang.equals("en") ? "Finnish" : "English");
        configureAddListViewButton();
        configureListViewItems();
        spinner_filter = (Spinner) findViewById(R.id.spinner);
        spinner_filter.setOnItemSelectedListener(this);
        spinner_sort = (Spinner) findViewById(R.id.spinnerSort);
        spinner_sort.setOnItemSelectedListener(this);

        VocabularManager vocabulary = VocabularManager.getInstance();

        adapter_language_list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setAdapter(adapter_language_list);

    }
    @Override
    protected void onResume() {
        super.onResume();
        spinner_filter.setSelection(0);
        spinner_sort.setSelection(0);
        /*VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayAdapter adapter_language_list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setAdapter(adapter_language_list);*/

    }

    private void configureAddListViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_changeLanguage);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLang = currentLang.equals("en") ? "fi" : "en";
                TextView language_title = (TextView) findViewById(R.id.languageTitle);
                language_title.setText(!currentLang.equals("en") ? "Finnish" : "English");
                if(position_ == 0)
                {
                    VocabularManager vocabulary = VocabularManager.getInstance();
                    ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
                    ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                    listView_first_language.setAdapter(adapter_language_list);
                }
                else
                {
                    VocabularManager vocabulary = VocabularManager.getInstance();
                    ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageRatingString(currentLang, position_ -1));
                    ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                    listView_first_language.setAdapter(adapter_language_list);
                }
            }
        });
    }
    private void configureListViewItems() {
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LearningListActivity.this, LearningInterfaceActivity.class);
                Bundle b = new Bundle();
                b.putInt("position", position);
                b.putString("current_lang", currentLang);
                intent.putExtras(b);
                VocabularManager vocabulary = VocabularManager.getInstance();
                startActivity(intent);
            }
        });
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if(parent.getId() == R.id.spinnerSort)
        {
            if(parent.getItemAtPosition(position).toString().equals("Sort"))
            {


                adapter_language_list.clear();
                VocabularManager vocabulary = VocabularManager.getInstance();
                adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);

            }

            else
            {
                spinner_filter.setSelection(0);

                adapter_language_list.clear();
                VocabularManager vocabulary = VocabularManager.getInstance();
                adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getSortedWords(currentLang, position));
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }

        }

        else
        {

            if(parent.getItemAtPosition(position).toString().equals("Filter"))
            {

                adapter_language_list.clear();
                VocabularManager vocabulary = VocabularManager.getInstance();
                adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
            else
            {
                spinner_sort.setSelection(0);
                adapter_language_list.clear();
                VocabularManager vocabulary = VocabularManager.getInstance();
                adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageRatingString(currentLang, position -1));
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
            position_= position;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    }




