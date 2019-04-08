package at.tugraz.ist.swe.teachingassistant;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExportDialog extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_export);

        configureExportButton();
    }



    private void configureExportButton() {
        Button ExportButton = (Button) findViewById(R.id.export_ok_btn);
        Button CancelButton = (Button) findViewById(R.id.export_cancel_btn);
        ExportButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                EditText ExportFilename = (EditText) findViewById(R.id.export_filename);
                FileHandler fileHandler = new FileHandler(getApplicationContext());

                VocabularManager manager = VocabularManager.getInstance();
                Word w1 = new Word("Hallo", "DE");
                Word w2 = new Word("hello", "EN");
                manager.addVocab(w1, w2);
                manager.addVocab(w1, w2);

                fileHandler.exportToFile(ExportFilename.getText().toString());
                Toast.makeText(getApplicationContext(), "Export to " + ExportFilename.getText() + " - " + fileHandler.getStatusMessage(), Toast.LENGTH_LONG).show();
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

