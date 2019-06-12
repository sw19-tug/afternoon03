package at.tugraz.ist.swe.teachingassistant;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static at.tugraz.ist.swe.teachingassistant.Globals.FILE_EXTENSION;
import static at.tugraz.ist.swe.teachingassistant.Globals.FILE_PROVIDER_AUTHORITY;

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

    private void createFile(String fileName) {
        /*Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
     //   String path = Environment.get().getPath()
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TITLE, fileName + fileExtension);
        startActivityForResult(intent, WRITE_REQUEST_CODE);*/
        JsonHandler jsonHandler = new JsonHandler();

        String string_to_export = jsonHandler.vocabularToJsonString();
        String file_name = fileName + FILE_EXTENSION;
        File file_path = new File(this.getFilesDir(), "files");
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
            os.write(string_to_export.getBytes());
            os.close();
            Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), FILE_PROVIDER_AUTHORITY, file);
            Log.d("EXPORT", "File successfully created ... " + contentUri.getPath());

            Toast.makeText(getApplicationContext(), "Export Success", Toast.LENGTH_LONG).show();
        } catch (Exception e)
        {

            Toast.makeText(getApplicationContext(), "Export FAILED", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void configureExportButton() {
        Button ExportButton = (Button) findViewById(R.id.export_ok_btn);
        Button CancelButton = (Button) findViewById(R.id.export_cancel_btn);
        ExportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ExportFilename = (EditText) findViewById(R.id.export_filename);
                createFile(ExportFilename.getText().toString());
                finish();
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

