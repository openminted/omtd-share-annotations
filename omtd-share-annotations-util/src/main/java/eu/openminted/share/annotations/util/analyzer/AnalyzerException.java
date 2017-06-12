package eu.openminted.share.annotations.util.analyzer;

public class AnalyzerException extends Exception
{
    private static final long serialVersionUID = 8209210549389513073L;

    public AnalyzerException()
    {
        super();
    }

    public AnalyzerException(String aMessage, Throwable aCause, boolean aEnableSuppression,
            boolean aWritableStackTrace)
    {
        super(aMessage, aCause, aEnableSuppression, aWritableStackTrace);
    }

    public AnalyzerException(String aMessage, Throwable aCause)
    {
        super(aMessage, aCause);
    }

    public AnalyzerException(String aMessage)
    {
        super(aMessage);
    }

    public AnalyzerException(Throwable aCause)
    {
        super(aCause);
    }
}
