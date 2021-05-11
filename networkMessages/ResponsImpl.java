package networkMessages;

import java.io.Serializable;
import java.util.Objects;

public class ResponsImpl implements Responses, Serializable{
    private static final long serialVersionUID = -1158790976101708016L;
    private final ResponsType type;
    private final String content;

    public ResponsImpl(ResponsType type, String content){
        this.type = type;
        this.content = content;
    }

    @Override
    public ResponsType getType(){
        return type;
    }

    @Override
    public String getContent(){
        return content;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return  true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponsImpl respons = (ResponsImpl) o;
        return type == respons.type && content.equals(respons.content);
     }

     @Override
    public String toString(){
        return String.format("ResponseImpl(type=%s,content=%s)",type,content);
     }

     @Override
    public int hashCode(){
        return Objects.hash(type,content);
     }
}
