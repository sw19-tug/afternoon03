package at.tugraz.ist.swe.teachingassistant;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.*;

import static android.support.v4.content.FileProvider.getUriForFile;
import static at.tugraz.ist.swe.teachingassistant.Globals.FILE_EXTENSION;
import static at.tugraz.ist.swe.teachingassistant.Globals.FILE_PROVIDER_AUTHORITY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAddListViewButton();
        configureExportDialog();
        configureImportDialog();
        configureLearningButton();
    }

    private void configureAddListViewButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_enterAddList);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                startActivity(intent);
            }
        });

    }

    private void configureExportDialog() {
        Button ExportViewButton = (Button) findViewById(R.id.btn_export);
        ExportViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void configureImportDialog() {
        Button ImportViewButton = (Button) findViewById(R.id.btn_import);
        ImportViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void configureLearningButton() {
        Button AddListViewButton = (Button) findViewById(R.id.btn_learning);
        AddListViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LearningListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void shareVocabulary()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String mimeType = "*/*";
        intent.setType(mimeType);
        JsonHandler jsonHandler = new JsonHandler();

        String string_to_share = jsonHandler.vocabularToJsonString();
        String file_name = "__sharing" + FILE_EXTENSION;
        File file_path = new File(this.getFilesDir(), "share");
        try
        {
            if(!file_path.exists())
            {
                file_path.mkdir();
            }
            File file = new File(file_path, file_name);
            if(!file.exists())
            {
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            os.flush();
            os.write(string_to_share.getBytes());
            os.close();
            Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), FILE_PROVIDER_AUTHORITY, file);

            Log.d("SHARE", "TMP file created ... " + contentUri.getPath());

            intent.putExtra(Intent.EXTRA_STREAM, contentUri);

            startActivity(Intent.createChooser(intent, "Share File"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        VocabularManager manager= VocabularManager.getInstance();
        //vocabulary.printVocab();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share_button:
                shareVocabulary();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
