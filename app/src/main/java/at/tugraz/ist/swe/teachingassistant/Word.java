package at.tugraz.ist.swe.teachingassistant;


public class Word
{

    private String text;
    private String lang;

    public Word(String text, String lang)
    {
        this.text = text;
        this.lang = lang.toLowerCase();
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getLang()
    {
        return lang;
    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }
}