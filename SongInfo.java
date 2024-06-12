public class SongInfo {
    private int id;
    private String name;
    private int distance;
    private int offset;
    private int length;
    private String filePath;


    // Constructor
    public SongInfo(int id, String name, int distance, int offset, int length, String filePath) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.offset = offset;
        this.length = length;
        this.filePath = filePath;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getDistance() {
        return distance;
    }

    public int getOffset() {
        return offset;
    }
    public int getLength(){
        return length;
    }


    public String getFilePath() {
        return filePath;
    }

    
}
