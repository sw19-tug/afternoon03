package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.*;
import java.util.Vector;

import static at.tugraz.ist.swe.teachingassistant.Globals.IMPORT_CODE;

public class ImportActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.import_activity);
        this.setFinishOnTouchOutside(false);


        performFileSearch();
        finish();
        //configureImportButton();

    }

    private void configureImportButton() {
        Button ExportButton = (Button) findViewById(R.id.import_ok_btn);
        Button CancelButton = (Button) findViewById(R.id.import_cancel_btn);

        ExportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
                finish();
            }
        });

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.TOAST_IMPORT_CANCEL, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void importVocabulary(String jsonString)
    {
        JsonHandler jsonHandler = new JsonHandler();
        Vector<Vocab> vocabs = jsonHandler.vocabularyFromJsonString(jsonString);

        if(vocabs != null)
        {
            VocabularManager manager = VocabularManager.getInstance();
            manager.setVocabs(vocabs);
            Toast.makeText(getApplicationContext(), R.string.TOAST_IMPORT_SUCCESS, Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public void performFileSearch() {

      /*  Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType(mimeType);

        startActivityForResult(intent, READ_REQUEST_CODE);*/
        Intent intent = new Intent(this, FileSelectActivity.class);
        startActivityForResult(intent, IMPORT_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        Toast.makeText(getApplicationContext(), "Export Success", Toast.LENGTH_LONG).show();
        if (requestCode == IMPORT_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                try
                {
                    Log.d("IMPORT", "Uri: " + uri.toString());
                    String content = readTextFromUri(resultData.getData());
                    importVocabulary(content);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readTextFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
            inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        inputStream.close();
        reader.close();
        return stringBuilder.toString();
    }



}
