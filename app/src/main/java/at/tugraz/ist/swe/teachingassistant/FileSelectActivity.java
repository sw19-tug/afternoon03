package at.tugraz.ist.swe.teachingassistant;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static at.tugraz.ist.swe.teachingassistant.Globals.*;

public class FileSelectActivity extends AppCompatActivity
{
    // The path to the root of this app's internal storage
    private File privateRootDir;
    // The path to the "share" subdirectory
    private File shareDir;
    // Array of files in the images subdirectory
    File[] shareFiles;
    // Array of filenames corresponding to imageFiles
    ArrayList<String> shareFilenames  = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_select);
        // Set up an Intent to send back to apps that request a file
        Intent resultIntent = new Intent(ACTION_RETURN_FILE);
        // Get the files/ subdirectory of internal storage
        File privateRootDir = getFilesDir();
        // Get the files/images subdirectory;
        shareDir = new File(privateRootDir, "files");
        // Get the files in the images subdirectory
        shareFiles = shareDir.listFiles();
        // Set the Activity's result to null to begin with
        setResult(Activity.RESULT_CANCELED, null);
        /*
         * Display the file names in the ListView fileListView.
         * Back the ListView with the array imageFilenames, which
         * you can create by iterating through imageFiles and
         * calling File.getAbsolutePath() for each File
         */
        shareFilenames.clear();
        if(shareFiles != null)
        {
            for (File file : shareFiles)
            {
                shareFilenames.add(file.getAbsolutePath());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,shareFilenames);
        ListView listView = (ListView) findViewById(R.id.share_list);
        listView.setAdapter(adapter);
        configureListViewItems();

    }

    private void configureListViewItems() {
        ListView listView = (ListView) findViewById(R.id.share_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent data = new Intent();
               // Bundle b = new Bundle();
               // b.putInt("position", position);
               // b.putString("file_name", shareFilenames.get(position));
               // data.putExtras(b);
                data.putExtra("file_name", shareFilenames.get(position));
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }


}
