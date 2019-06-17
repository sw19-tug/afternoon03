package at.tugraz.ist.swe.teachingassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.util.Vector;

import static at.tugraz.ist.swe.teachingassistant.Globals.*;

public class ImportActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.import_activity);
        this.setFinishOnTouchOutside(false);

        performFileSearch();
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
        Intent intent = new Intent(this, FileSelectActivity.class);
        startActivityForResult(intent, IMPORT_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == IMPORT_CODE && resultCode == Activity.RESULT_OK) {
            String file_path = resultData.getStringExtra("file_name");
            File file = new File(file_path);

            if(!file.exists())
            {
                Log.e("IMPORT", "FILE DOES NoT EXISTS, should not happen");
                Toast.makeText(getApplicationContext(), "Import Failed", Toast.LENGTH_LONG).show();
                return;
            }

           try {
               Uri fileUri = FileProvider.getUriForFile(ImportActivity.this, FILE_PROVIDER_AUTHORITY, file);
                try
                {
                    Log.d("IMPORT", "Uri: " + fileUri.toString());
                    String content = readTextFromUri(fileUri);
                    importVocabulary(content);
                    Toast.makeText(getApplicationContext(), "Import Success", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Import Failed", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Import Failed", Toast.LENGTH_LONG).show();
            }
        }
        finish();
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
