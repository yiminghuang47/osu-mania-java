public class SongInfo {
    private int id;
    private String name;
    private int bpm;
    private String filePath;

    // Constructor
    public SongInfo(int id, String name, int bpm, String filePath) {
        this.id = id;
        this.name = name;
        this.bpm = bpm;
        this.filePath = filePath;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
