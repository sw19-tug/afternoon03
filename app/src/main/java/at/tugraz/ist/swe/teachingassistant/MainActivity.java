package at.tugraz.ist.swe.teachingassistant;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    private void configureShareButton() {
        Button ShareButton = (Button) findViewById(R.id.share_button);
        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareVocabulary();
            }
        });
    }

    private void shareVocabulary()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String mimeType = getApplicationContext().getResources().getText(R.string.mimeType).toString();
        intent.setType(mimeType);
        JsonHandler jsonHandler = new JsonHandler();

        String string_to_share = jsonHandler.vocabularToJsonString();
        String file_name = ".__sharing" + getApplicationContext().getResources().getText(R.string.file_extension).toString();
        String file_path = getApplication().getFilesDir() + File.separator + file_name;
        try
        {
            File file = new File(file_path);
            file.createNewFile();
            if(file.exists())
            {
                OutputStream os = new FileOutputStream(file);
                os.flush();
                os.write(string_to_share.getBytes());
                os.close();
                Log.e("SHARE", "TMP file ćreated ... " + file_name);
            }
            Log.e("SHARE", "Start Create TMP file - " + file_name);

            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+ file_path));

            intent.putExtra(Intent.EXTRA_SUBJECT,
                "Sharing File...");
            intent.putExtra(Intent.EXTRA_TEXT, "Sharing File...");

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
        menu.getItem(R.id.share_button);
        configureShareButton();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        return super.onOptionsItemSelected(item);
    }
}
