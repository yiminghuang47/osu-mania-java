
import java.util.*;

public class RandomizedMapExtreme implements Beatmap {
    private ArrayList<ArrayList<Note>> allNotes;

    public RandomizedMapExtreme(SongInfo song) {
        allNotes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }

        double distance = song.getDistance() / 4;
        int length = song.getLength() * 4;
        int prevLane1 = -1;
        int prevLane2 = -1;
        for (int i = 0; i < length; i++) {
            boolean noNotes = Math.random() < 0.05;
            if (noNotes)
                continue;
            int randomLane = (int) (Math.random() * 4);
            boolean repeatNotes = Math.random() < 0;
            if (!repeatNotes) {

                while (randomLane == prevLane1 || randomLane == prevLane2) {
                    randomLane = (int) (Math.random() * 4);
                }

            }
            int randomLane2 = (int) (Math.random() * 4);
            while (randomLane2 == randomLane || randomLane2 == prevLane1 || randomLane2 == prevLane2) {
                randomLane2 = (int) (Math.random() * 4);
            }

            allNotes.get(randomLane)
                    .add(new Note(randomLane * 100 + 50, -song.getOffset() + (int) (-i * distance), randomLane));
            prevLane1 = randomLane;
            boolean hasTwoNotes = Math.random() <= 0.0;

            if (hasTwoNotes) {
                allNotes.get(randomLane2)
                        .add(new Note(randomLane2 * 100 + 50, -song.getOffset() + (int) (-i * distance), randomLane2));
                prevLane2 = randomLane2;
            }
        }

    }

    @Override
    public ArrayList<ArrayList<Note>> getAllNotes() {
        return allNotes;
    }

}
