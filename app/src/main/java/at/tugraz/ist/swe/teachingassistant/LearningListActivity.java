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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class LearningListActivity extends AppCompatActivity {

    private String currentLang = "";
    private String sortingState = "";
    private String sortingStateTags = "";

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
    }
    @Override
    protected void onResume() {
        super.onResume();

        VocabularManager vocabulary = VocabularManager.getInstance();
        ArrayAdapter adapter_language_list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
        ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
        listView_first_language.setAdapter(adapter_language_list);

    }

    private void configureAddListViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_changeLanguage);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLang = currentLang.equals("en") ? "fi" : "en";
                TextView language_title = (TextView) findViewById(R.id.languageTitle);
                language_title.setText(!currentLang.equals("en") ? "Finnish" : "English");

                VocabularManager vocabulary = VocabularManager.getInstance();
                ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, vocabulary.getWordsFromLanguageString(currentLang));
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
        });
    }

    private void changeTagsSortingOrder() {
        Button AlphaSortButton = (Button) findViewById(R.id.btn_tags_sort);
        AlphaSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VocabularManager vocabulary = VocabularManager.getInstance();
                ArrayList<String> tagsList = new ArrayList<>();
                for (Vocab vocab : vocabulary.getVocabs()){
                    String[] tags = vocab.getTags().split(",");
                    for (String tag: tags){
                        if (!tagsList.contains(tag)){
                            tagsList.add(tag);
                        }
                    }
                }

                ArrayList<Vocab> allVocab = (ArrayList) vocabulary.getVocabs().clone();
                ArrayList<Vocab> sortedVocab = new ArrayList<>();
                ArrayList<String> sortedVocabString = new ArrayList<>();
                if (sortingStateTags.isEmpty()){
                    sortingStateTags = "ASC";
                    Collections.sort(tagsList);
                    for (String tag: tagsList){
                        ArrayList<Vocab> locatedVocabs = new ArrayList<>();
                        for (Vocab vocab: allVocab ){
                            String[] vocabTags = vocab.getTags().split(",");
                            if (vocabTags.length > 0 && vocabTags[0].equals(tag)){
                                sortedVocab.add(vocab);
                            }
                        }
                    }
                    sortedVocabString = vocabulary.getWordsFromVocabs(sortedVocab, currentLang);
                }
                else if (sortingStateTags.equals("ASC")){
                    sortingStateTags = "DSC";
                    Collections.reverse(tagsList);
                    for (String tag: tagsList){
                        ArrayList<Vocab> locatedVocabs = new ArrayList<>();
                        for (Vocab vocab: allVocab ){
                            String[] vocabTags = vocab.getTags().split(",");
                            if (vocabTags.length > 0 && vocabTags[0].equals(tag)){
                                sortedVocab.add(vocab);
                            }
                        }
                    }
                    sortedVocabString = vocabulary.getWordsFromVocabs(sortedVocab, currentLang);
                }
                else if (sortingStateTags.equals("DSC")){
                    sortingStateTags = "";
                    sortedVocabString = vocabulary.getWordsFromLanguageString(currentLang);
                }
                TextView tags = (TextView) findViewById(R.id.tv_tags_sort);
                tags.setText(sortingStateTags);
                ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, sortedVocabString);
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
            }
        });
    }

    private void changeAlphabeticalSortingOrder() {
        Button TagsSortButton = (Button) findViewById(R.id.btn_alphabetical);
        TagsSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VocabularManager vocabulary = VocabularManager.getInstance();
                ArrayList<String> sortedVocab = (ArrayList) (vocabulary.getWordsFromLanguageString(currentLang)).clone();
                if (sortingState.isEmpty()){
                    sortingState = "ASC";
                    Collections.sort(sortedVocab);
                }
                else if (sortingState.equals("ASC")){
                    sortingState = "DSC";
                    Collections.reverse(sortedVocab);
                }
                else if (sortingState.equals("DSC")){
                    sortingState = "";
                    sortedVocab = vocabulary.getWordsFromLanguageString(currentLang);
                }
                TextView asc_dsc = (TextView) findViewById(R.id.asc_dsc);
                asc_dsc.setText(sortingState);
                ArrayAdapter adapter_language_list = new ArrayAdapter<String>(LearningListActivity.this, android.R.layout.simple_list_item_1, sortedVocab);
                ListView listView_first_language = (ListView) findViewById(R.id.vocabList);
                listView_first_language.setAdapter(adapter_language_list);
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
                startActivity(intent);
            }
        });
    }



}
