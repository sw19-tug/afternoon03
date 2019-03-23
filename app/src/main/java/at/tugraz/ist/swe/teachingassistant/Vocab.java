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

    public String getGermanTranslation()
    {
       return german_translation;
    }

    public String getEnglishTranslation()
    {
        return english_translation;
    }

    public int getRating() { return -1; }

    public void setRating(int rating){}
    public int changeRating()
    {
        return -1;
    }
}
