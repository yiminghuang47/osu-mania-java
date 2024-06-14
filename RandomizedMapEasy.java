
import java.util.*;

public class RandomizedMapEasy implements Beatmap {
    private ArrayList<ArrayList<Note>> allNotes;

    public RandomizedMapEasy(SongInfo song) {
        allNotes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }

        double distance = song.getDistance();
        int length = song.getLength();

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
