package applicatie7a;

/**
 * Created by Michelle on 28-3-2017.
 */
public class ORF {

    private String headers, readingframe, sequentieORF;
    private int startpositie, stoppositie;

    /**
     * fucntie: Het object voor de ORF's maken.
     * known bugs: niet aanwezig
     * @param header de header va het bestand.
     * @param start de startpositie van het ORF.
     * @param stop de stoppositie van het ORF.
     * @param rf het readingframe waar het ORF zich op bevind.
     * @param seqORF de sequentie van de ORF van start tm stop.
     */
    public ORF(String header, int start, int stop, String rf, String seqORF) {
        headers = header;
        startpositie = start;
        stoppositie = stop;
        readingframe = rf;
        sequentieORF = seqORF;
    }


    public int getStartPos() {
        return startpositie;
    }


    public int getStopPos() {
        return stoppositie;
    }


    public String getReadingFrame() {
        return readingframe;
    }


    public String getORFseq() {
        return sequentieORF;
    }
}

