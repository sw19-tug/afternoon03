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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Vector;

public class ImportActivity extends AppCompatActivity
{
    private static final int READ_REQUEST_CODE = 42;
    private String fileExtension;
    private String mimeType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mimeType =  getApplicationContext().getResources().getText(R.string.mimeType).toString();
        fileExtension =  getApplicationContext().getResources().getText(R.string.file_extension).toString();

        setContentView(R.layout.import_activity);
        this.setFinishOnTouchOutside(false);

        configureImportButton();

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
        Gson gson = new Gson();
        Type vocabVector = new TypeToken<Vector<Vocab>>() {
        }.getType();

        Vector<Vocab> vocabs = gson.fromJson(jsonString, vocabVector);
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

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType(mimeType);

        startActivityForResult(intent, READ_REQUEST_CODE);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.d("IMPORT", "Uri: " + uri.toString());
                try
                {
                    String content = readTextFromUri(resultData.getData());
                    importVocabulary(content);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }




}
