
/**
 * Write a description of PhraseFilter here.
 * 
 * @Chia-Hao     
 * @2018.10.10
 */
public class PhraseFilter implements Filter {
    private String type;
    private String phrase;
    public PhraseFilter(String _type, String _phrase){
        type = _type;
        phrase = _phrase;
    }
    public boolean satisfies(QuakeEntry qe)
    {   
        String info = qe.getInfo();
        if( type.equals("start") ){
            if(info.startsWith(phrase)) {
                return true;
            }
        }
        else if( type.equals("end") ){
            if(info.endsWith(phrase)) {
                return true;
            }
        }
        else{ // any
            if(info.contains(phrase)) {
                return true;
            }
        }
        return false;
    }
    public String getName(){
        return "PhraseFilter";
    }
}
