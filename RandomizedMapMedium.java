

import java.util.*;



public class RandomizedMapMedium implements Beatmap{
    private ArrayList<ArrayList<Note>> allNotes;
    public RandomizedMapMedium(SongInfo song){
        allNotes = new ArrayList<>();
    
        
        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }
        
        int bpm = song.getBpm();
        for(int i = 0; i < 5; i++){
            int distance = bpm; // TODO: fix distance
            int randomLane = (int)(Math.random()*4);
            int randomLane2 = (int)(Math.random()*4);
            while(randomLane==randomLane2){
                randomLane2 = (int)(Math.random()*4);
            }
        
            allNotes.get(randomLane).add(new Note(randomLane*100+50,-i*distance,randomLane));
            boolean hasTwoNotes = Math.random()<=0.5;
            if(hasTwoNotes) allNotes.get(randomLane2).add(new Note(randomLane2*100+50,-i*distance,randomLane2));
        }
        
    }

    @Override
    public ArrayList<ArrayList<Note>> getAllNotes(){
        return allNotes;
    }

}
