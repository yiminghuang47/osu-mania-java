public class Judgements {
    public static final int JUDGEMENT_LINE = 450;
    public static final int PERFECT_DIFF = 25;
    public static final int GOOD_DIFF = 75;
    public static final int BAD_DIFF = 100;
    public static final int MISS_DIFF = 150;
    public static String getJudgement(int yPos){
        if(yPos>Constants.FRAME_HEIGHT) return "MISS";
        int diff = Math.abs(yPos+Constants.NOTE_HEIGHT-JUDGEMENT_LINE);
        if(diff<=PERFECT_DIFF) return "PERFECT";
        else if(diff<=GOOD_DIFF) return "GOOD";
        else if(diff<=BAD_DIFF) return "BAD";
        else if(diff<=MISS_DIFF) return "MISS";
        else return "NONE"; // no judgement

        
    }

}
