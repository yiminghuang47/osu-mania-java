import java.util.*;

public class Beatmap {
    private ArrayList<ArrayList<Note>> allNotes;
    public Beatmap(){
        allNotes = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }
        
        for(int i = 0; i < 200; i++){
            int randomLane = (int)(Math.random()*4);
            allNotes.get(randomLane).add(new Note(randomLane*100+50,-i*100));

            
        }
        /* 
        allNotes.get(0).add(new Note(50, 0));
        allNotes.get(1).add(new Note(150, -100));
        allNotes.get(2).add(new Note(250, -150));
        allNotes.get(3).add(new Note(350, 0));
        allNotes.get(0).add(new Note(50, -250));
        allNotes.get(0).add(new Note(50, -350));
        */
        
    }
    public ArrayList<ArrayList<Note>> getAllNotes(){
        return allNotes;
    }

}
