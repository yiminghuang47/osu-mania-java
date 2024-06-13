

import java.util.*;



public class RandomizedMapMedium implements Beatmap{
    private ArrayList<ArrayList<Note>> allNotes;
    public RandomizedMapMedium(SongInfo song){
        allNotes = new ArrayList<>();
    
        
        for (int i = 0; i < 4; i++) {
            allNotes.add(new ArrayList<Note>());
        }
        double distance = song.getDistance()/2;
        int length = song.getLength()*2;

        int prevLane = -1;
        int prevLane2 = -1;
        for(int i = 0; i < length; i++){
            boolean noNotes = Math.random() < 0.8;
            boolean sameLane = Math.random() < 0.2;
            if(noNotes&&i%2==1) continue;
            int randomLane = (int)(Math.random()*4);
            if(!sameLane){
                while(randomLane==prevLane || randomLane == prevLane2){
                    randomLane = (int)(Math.random()*4);
                }
            }

            int randomLane2 = (int)(Math.random()*4);
            
            while(randomLane==randomLane2 || randomLane2 == prevLane || randomLane2 == prevLane2){
                randomLane2 = (int)(Math.random()*4);
            }
            prevLane = randomLane;
        
            allNotes.get(randomLane).add(new Note(randomLane*100+50,-song.getOffset()+(int)(-i*distance),randomLane));
            boolean hasTwoNotes = Math.random()<=0.5;
            if(hasTwoNotes) {
                allNotes.get(randomLane2).add(new Note(randomLane2*100+50,-song.getOffset()+(int)(-i*distance),randomLane2));
                prevLane2 = randomLane2;
            }
        }
        
    }

    @Override
    public ArrayList<ArrayList<Note>> getAllNotes(){
        return allNotes;
    }

}
