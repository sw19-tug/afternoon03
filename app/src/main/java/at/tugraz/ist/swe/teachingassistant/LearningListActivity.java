package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class LearningListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String currentLang = "";
    private String sortingState = "";
    private String sortingStateTags = "";
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
        changeTagsSortingOrder();
        changeAlphabeticalSortingOrder();
        filterTags();
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

    private void changeTagsSortingOrder() {
        Button AlphaSortButton = (Button) findViewById(R.id.btn_tags_sort);
        AlphaSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_filter.setSelection(0);
                spinner_sort.setSelection(0);
                ArrayList<String> sortedVocabString = sortByTags();
                Button tags = (Button) findViewById(R.id.btn_tags_sort);
                tags.setText("Tag Sort " + sortingStateTags);
                ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, sortedVocabString);
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
        });
    }

    public ArrayList<String> sortByTags()
    {
        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayList<String> tagsList = new ArrayList<>();
        for (Vocab vocab : vocabulary.getVocabs()) {
            String[] tags = vocab.getTags().split(",");
            for (String tag : tags) {
                if (!tagsList.contains(tag)) {
                    tagsList.add(tag);
                }
            }
        }

        Vector<Vocab> allVocabVector= vocabulary.getVocabs();
        ArrayList<Vocab> allVocab = new ArrayList<Vocab>(allVocabVector);
        ArrayList<Vocab> sortedVocab = new ArrayList<>();
        ArrayList<String> sortedVocabString = new ArrayList<>();
        if (sortingStateTags.isEmpty()) {
            sortingStateTags = "ASC";
            Collections.sort(tagsList);
            for (String tag : tagsList) {
                ArrayList<Vocab> locatedVocabs = new ArrayList<>();
                for (Vocab vocab : allVocab) {
                    String[] vocabTags = vocab.getTags().split(",");
                    if (vocabTags.length > 0 && vocabTags[0].equals(tag)) {
                        sortedVocab.add(vocab);
                    }
                }
            }
            sortedVocabString = vocabulary.getWordsFromVocabs(sortedVocab, currentLang);
        } else if (sortingStateTags.equals("ASC")) {
            sortingStateTags = "DSC";
            Collections.sort(tagsList);
            Collections.reverse(tagsList);
            for (String tag : tagsList) {
                ArrayList<Vocab> locatedVocabs = new ArrayList<>();
                for (Vocab vocab : allVocab) {
                    String[] vocabTags = vocab.getTags().split(",");
                    if (vocabTags.length > 0 && vocabTags[0].equals(tag)) {
                        sortedVocab.add(vocab);
                    }
                }
            }
            sortedVocabString = vocabulary.getWordsFromVocabs(sortedVocab, currentLang);
        } else if (sortingStateTags.equals("DSC")) {
            sortingStateTags = "";
            sortedVocabString = vocabulary.getWordsFromLanguageString(currentLang);
        }
        return sortedVocabString;
    }

    private void changeAlphabeticalSortingOrder() {
        Button TagsSortButton = (Button) findViewById(R.id.btn_alphabetical);
        TagsSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_filter.setSelection(0);
                spinner_sort.setSelection(0);
                ArrayList<String> sortedVocab = sortByAlphabetical();
                Button asc_dsc = (Button) findViewById(R.id.btn_alphabetical);
                asc_dsc.setText("Alphabetical Sort " + sortingState);
                ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, sortedVocab);
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
        });
    }

    public ArrayList<String> sortByAlphabetical(){
        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayList<String> sortedVocab = (ArrayList) (vocabulary.getWordsFromLanguageString(currentLang)).clone();
        if (sortingState.isEmpty()) {
            sortingState = "ASC";
            Collections.sort(sortedVocab);
        } else if (sortingState.equals("ASC")) {
            sortingState = "DSC";
            Collections.sort(sortedVocab);
            Collections.reverse(sortedVocab);
        } else if (sortingState.equals("DSC")) {
            sortingState = "";
            sortedVocab = vocabulary.getWordsFromLanguageString(currentLang);
        }
        return sortedVocab;
    }

    private void filterTags() {
        final EditText filterTagsInput = (EditText) findViewById(R.id.input_tags);
        filterTagsInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                spinner_filter.setSelection(0);
                spinner_sort.setSelection(0);
                //Log.e("Action Event ", event.getCharacters());
                Log.e("Action ID ", Integer.toString(actionId));
                if (actionId == KeyEvent.KEYCODE_ENDCALL) {
                    Log.e("Button hit", filterTagsInput.toString());
                    ArrayList<String> filteredVocabs = filterByTags(filterTagsInput.getText().toString());
                    filterTagsInput.clearFocus();
                    ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, filteredVocabs);
                    ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                    listView_first_language.setAdapter(adapter_language_list);

                }
            return false;
            }
        });
    }

    public ArrayList<String> filterByTags(String filterTagsInput)  {
        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayList<String> tagsList = new ArrayList<>();
        for (Vocab vocab : vocabulary.getVocabs()) {
            String[] tags = vocab.getTags().split(",");
            for (String tag : tags) {
                if (!tagsList.contains(tag)) {
                    tagsList.add(tag);
                }
            }
        }
        ArrayList<String> filteredTagsList = new ArrayList<>();
        String filterInput = filterTagsInput;
        ArrayList<Vocab> allVocabs = new ArrayList<Vocab>(vocabulary.getVocabs());
        ArrayList<String> filteredVocabs = new ArrayList<>();
        if (filterInput.isEmpty()) {
            filteredVocabs = vocabulary.getWordsFromLanguageString(currentLang);
        } else {
            for (String tag : tagsList) {
                if (tag.contains(filterInput)) {
                    filteredTagsList.add(tag);
                }
            }
            Log.e("Tags List: ", filteredTagsList.toString());
            for (Vocab vocab : allVocabs) {
                if (vocab.getTags().length() > 0) {
                    for (String tag : filteredTagsList) {
                        if (vocab.getTags().contains(tag)) {
                            String selectedWord = vocab.getTranslationByLanguage(currentLang);
                            filteredVocabs.add(selectedWord);
                        }
                    }
                }
            }
        }
        return filteredVocabs;
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




