package at.tugraz.ist.swe.teachingassistant;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportActivity extends AppCompatActivity
{
    static private String mimeType;
    static private String fileExtension;
    private static final int WRITE_REQUEST_CODE = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.export_activity);

            this.setFinishOnTouchOutside(false);
        mimeType =  getApplicationContext().getResources().getText(R.string.mimeType).toString();
        fileExtension =  getApplicationContext().getResources().getText(R.string.file_extension).toString();

        configureExportButton();
    }

    // Here are some examples of how you might call this method.
    // The first parameter is the MIME type, and the second parameter is the name
    // of the file you are creating:
    //
    // createFile("text/plain", "foobar.txt");
    // createFile("image/png", "mypicture.png");

    // Unique request code.

    private void createFile(String fileName) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        // Filter to only show results that can be "opened", such as
        // a file (as opposed to a list of contacts or timezones).
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Create a file with the requested MIME type.
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TITLE, fileName + fileExtension);
        startActivityForResult(intent, WRITE_REQUEST_CODE);
    }



    public String vocabularToJsonString()
    {
        Gson gson = new Gson();
        VocabularManager manager = VocabularManager.getInstance();
        String jsonString = gson.toJson(manager.getVocabs());
        Log.e("JSON", jsonString);
        return jsonString;
    }

    private void alterDocument(Uri uri, String content_string) {
        try {
            ParcelFileDescriptor pfd = getApplicationContext().getContentResolver().
                openFileDescriptor(uri, "w");
            FileOutputStream fileOutputStream =
                new FileOutputStream(pfd.getFileDescriptor());
            fileOutputStream.write(content_string.getBytes());
            // Let the document provider know you're done by closing the stream.
            fileOutputStream.close();
            pfd.close();
            Toast.makeText(getApplicationContext(), "Export Success", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == WRITE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.d("EXPORT", "Uri: " + uri.toString());
                String content = vocabularToJsonString();
                alterDocument(uri, content);

                finish();
            } else
            {
                Log.e("EXPORT", "Write REQUEST FAILED");
            }
            return;
        }
        Log.e("EXPORT", "end result");
    }
    private void configureExportButton() {
        Button ExportButton = (Button) findViewById(R.id.export_ok_btn);
        Button CancelButton = (Button) findViewById(R.id.export_cancel_btn);
        ExportButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                EditText ExportFilename = (EditText) findViewById(R.id.export_filename);

              /*  VocabularManager manager = VocabularManager.getInstance();
                Word w1 = new Word("Hallo", "DE");
                Word w2 = new Word("hello", "EN");
                manager.addVocab(w1, w2);
                manager.addVocab(w1, w2);*/
                createFile(ExportFilename.getText().toString());
                //fileHandler.exportToFile(ExportFilename.getText().toString());
              //  Toast.makeText(getApplicationContext(), "Export to " + ExportFilename.getText() + " - " + fileHandler.getStatusMessage(), Toast.LENGTH_LONG).show();
              //  finish();
            }
        });

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Export canceled!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}

