package at.tugraz.ist.swe.teachingassistant;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;



public class VocabularyListAdapter extends ArrayAdapter<String>
{
    private final ArrayList<String> first_lang;
    private final ArrayList<String> second_lang;
    public VocabularyListAdapter(@NonNull Context context, ArrayList<String> first_lang,  ArrayList<String> second_lang)
    {
        super(context, R.layout.custom_list,first_lang);
        this.first_lang = first_lang;
        this.second_lang = second_lang;

    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = convertView;
        if(convertView == null)
        {
            view = layoutInflater.inflate(R.layout.custom_list,parent,false);
        }

        final TextView textView1 = view.findViewById(R.id.first_lang);
        textView1.setText(first_lang.get(position));

        final TextView textView2 = view.findViewById(R.id.second_lang );
        textView2.setText(second_lang.get(position));

        final Button delete_btn = (Button) view.findViewById(R.id.delete_btn);

        delete_btn.setOnClickListener(new View.OnClickListener()

          {
              @Override
              public void onClick(View v)
              {
                  VocabularManager vocabulary = VocabularManager.getInstance();
                  String first_word_delete = first_lang.get(position);
                  String second_word_delete = second_lang.get(position);
                  vocabulary.deleteVocabByWord(first_word_delete,second_word_delete);
                  remove(first_lang.get(position));
                  second_lang.remove(position);
                  Toast.makeText(getContext(),first_word_delete + " " +  second_word_delete + " deleted", Toast.LENGTH_LONG).show();
              }

          }

        );
        final Button edit_btn = (Button) view.findViewById(R.id.edit_btn);

        edit_btn.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick(View v)
                                          {

                                              Intent intent = new Intent(getContext(), EditActivity.class);
                                              intent.putExtra("first_lang",first_lang.get(position));
                                              intent.putExtra("second_lang",second_lang.get(position));

                                              getContext().startActivity(intent);
                                          }

                                      }

        );


        return view;
    }
}