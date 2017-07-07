package puzzleleaf.tistory.com.miniproject1.object;


import java.util.ArrayList;

public class BoardObject {


    private String contents;
    private ArrayList<ReplyObject> replyObjects ;

    public BoardObject(String contents) {
        this.contents = contents;
        replyObjects = new ArrayList<>();
    }

    public BoardObject()
    {
        contents = " ";
        replyObjects = new ArrayList<>();
    }

    public ArrayList<ReplyObject> getReplyObjects()
    {
        return replyObjects;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}