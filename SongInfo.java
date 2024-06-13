/**
 * The `SongInfo` class represents information about a song including its ID, name, distance, offset,
 * length, and file path.
 */
public class SongInfo {
    private int id; // unique identifier for each song
    private String name; // artist and title of the song
    private int distance; // distance (in pixels) between each notes. The smaller the distance, the denser the notes are.
    private int offset; // used to line up the first note with the beginning of the song. The smaller the offset, the quicker the first note will come.
    private int length; // length of the song. Note that it's not measured in time units like minutes or seconds. See RandomizedMapEasy.java for its exact usage.
    private String filePath; // file path of the song


    // Constructor
    public SongInfo(int id, String name, int distance, int offset, int length, String filePath) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.offset = offset;
        this.length = length;
        this.filePath = filePath;
    }

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
