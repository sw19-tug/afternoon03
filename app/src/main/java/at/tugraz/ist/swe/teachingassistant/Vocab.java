package at.tugraz.ist.swe.teachingassistant;

public class Vocab
{
    private String german_translation;
    private String english_translation;

    public Vocab(String german, String english)
    {
      german_translation = german;
      english_translation = english;
    }

    public String germanTranslation()
    {
       return german_translation;
    }

    public String getEnglishTranslation()
    {
        return english_translation;
    }
}
