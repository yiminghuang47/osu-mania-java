

import java.util.*;



public class RandomizedMapHard implements Beatmap{
    private ArrayList<ArrayList<Note>> allNotes;
    public RandomizedMapHard(SongInfo song){
        allNotes = new ArrayList<>();
    
        
        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }
        /*
         * Note distance (time) = (60/bpm) seconds
         * Note moves Constants.NOTE_VELOCITY pixels every (Constants.REFRESH_RATE/1000) seconds. 
         * Note distance (pixels) = Note distance (time) * NOTE_VELOCITY / (REFRESH_RATE/1000)
         * = 60/bpm * NOTE_VELOCITY / REFRESH_RATE * 1000
         * 
         */
        //double bpm = song.getBpm();
        //double distance = 60.0/bpm * Constants.NOTE_VELOCITY / Constants.REFRESH_RATE * 1000; // TODO: fix distance
        double distance = song.getDistance()/2;
        int length = song.getLength() * 2;
        //System.out.println(bpm);
        //System.out.println(distance);
        for(int i = 0; i < length; i++){
            int randomLane = (int)(Math.random()*4);
            int randomLane2 = (int)(Math.random()*4);
            while(randomLane==randomLane2){
                randomLane2 = (int)(Math.random()*4);
            }
        
            allNotes.get(randomLane).add(new Note(randomLane*100+50,-song.getOffset()+(int)(-i*distance),randomLane));
            boolean hasTwoNotes = Math.random()<=0.5;
            if(hasTwoNotes) allNotes.get(randomLane2).add(new Note(randomLane2*100+50,-song.getOffset()+(int)(-i*distance),randomLane2));
        }
        
    }

    @Override
    public ArrayList<ArrayList<Note>> getAllNotes(){
        return allNotes;
    }

}
