package applicatie7a;

/**
 * Created by Michelle on 28-3-2017.
 */
public class ORF {

    private String headers, readingframe, sequentieORF;
    private int startpositie, stoppositie;

    /**
     *
     * @param header
     * @param start
     * @param stop
     * @param rf
     * @param seqORF
     */
    public ORF(String header, int start, int stop, String rf, String seqORF) {
        headers = header;
        startpositie = start;
        stoppositie = stop;
        readingframe = rf;
        sequentieORF = seqORF;
    }

    /**
     *
     * @return
     */
    public int getStartPos() {
        return startpositie;
    }

    /**
     *
     * @return
     */
    public int getStopPos() {
        return stoppositie;
    }

    /**
     *
     * @return
     */
    public String getReadingFrame() {
        return readingframe;
    }

    /**
     *
     * @return
     */
    public String getORFseq() {
        return sequentieORF;
    }
}

