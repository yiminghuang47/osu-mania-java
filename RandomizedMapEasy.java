
import java.util.*;

public class RandomizedMapEasy implements Beatmap {
    private ArrayList<ArrayList<Note>> allNotes;

    public RandomizedMapEasy(SongInfo song) {
        allNotes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }
        /*
         * Note distance (time) = (60/bpm) seconds
         * Note moves Constants.NOTE_VELOCITY pixels every (Constants.REFRESH_RATE/1000)
         * seconds.
         * Note distance (pixels) = Note distance (time) * NOTE_VELOCITY /
         * (REFRESH_RATE/1000)
         * = 60/bpm * NOTE_VELOCITY / REFRESH_RATE * 1000
         * 
         */
        // double bpm = song.getBpm();
        // double distance = 60.0/bpm * Constants.NOTE_VELOCITY / Constants.REFRESH_RATE
        // * 1000; // TODO: fix distance
        double distance = song.getDistance();
        int length = song.getLength();
        // System.out.println(bpm);
        // System.out.println(distance);
        for (int i = 0; i < length; i++) {
            int randomLane = (int) (Math.random() * 4);

            allNotes.get(randomLane)
                    .add(new Note(randomLane * 100 + 50, -song.getOffset() + (int) (-i * distance), randomLane));

        }

    }

    @Override
    public ArrayList<ArrayList<Note>> getAllNotes() {
        return allNotes;
    }

}