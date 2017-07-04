package puzzleleaf.tistory.com.miniproject1.objects;


public class BoardObject {


    private String contents;

    public BoardObject(String contents) {
        this.contents = contents;
    }

    public BoardObject()
    {
        contents = " ";
    }


    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}