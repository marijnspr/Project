/**
 * Created by Michelle on 28-3-2017.
 */
public class ORF {

    private String startpositie, stoppositie, readingframe, sequentieORF;

    public ORF(String start, String stop, String rf, String seqORF){
        startpositie = start;
        stoppositie = stop;
        readingframe= rf;
        sequentieORF = seqORF;
    }

    public int getStartPos() {return Integer.getInteger(startpositie);}
    public int getStopPos() {return Integer.getInteger(stoppositie);}
    public String getReadingFrame() {return readingframe;}
    public String getORFseq() {return sequentieORF;}
}

