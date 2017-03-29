/**
 * Created by Michelle on 28-3-2017.
 */
public class ORF {

    private String headers, startpositie, stoppositie, readingframe, sequentieORF;

    public ORF(String header, String start, String stop, String rf, String seqORF) {
        headers = header;
        startpositie = start;
        stoppositie = stop;
        readingframe = rf;
        sequentieORF = seqORF;
    }

    public int getStartPos() {
        return Integer.getInteger(startpositie);
    }

    public int getStopPos() {
        return Integer.getInteger(stoppositie);
    }

    public String getReadingFrame() {
        return readingframe;
    }

    public String getORFseq() {
        return sequentieORF;
    }
}

