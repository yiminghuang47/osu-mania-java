

import java.util.*;



public class RandomizedMapHard implements Beatmap{
    private ArrayList<ArrayList<Note>> allNotes;
    public RandomizedMapHard(SongInfo song){
        allNotes = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }
        int bpm = song.getBpm(); 
        for(int i = 0; i < 50; i++){
            int distance = bpm; // TODO: fix distance
            int randomLane = (int)(Math.random()*4);
            int randomLane2 = (int)(Math.random()*4);
            int randomLane3 = (int)(Math.random()*4);
            while(randomLane==randomLane2){
                randomLane2 = (int)(Math.random()*4);
            }
            while(randomLane==randomLane3||randomLane2==randomLane3){
                randomLane3 = (int)(Math.random()*4);
            }
        
            allNotes.get(randomLane).add(new Note(randomLane*100+50,-i*distance,randomLane));
            boolean hasTwoNotes = Math.random()<=0.75;
            if(hasTwoNotes) allNotes.get(randomLane2).add(new Note(randomLane2*100+50,-i*distance,randomLane2));
            boolean hasThreeNotes = Math.random()<=0.5;
            if(hasThreeNotes) allNotes.get(randomLane3).add(new Note(randomLane3*100+50,-i*distance,randomLane3));
            
        }
        
    }

    @Override
    public ArrayList<ArrayList<Note>> getAllNotes(){
        return allNotes;
    }

}
