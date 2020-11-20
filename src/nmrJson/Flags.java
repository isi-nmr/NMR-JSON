package nmrJson;

public class Flags {
    Boolean Binary = null;
    Boolean Compressed = null;
    Boolean FrequecyDomain = null;
    Boolean Downsampled = null;
    Boolean Filtered = null;
    Boolean Zeropadded = null;
    Boolean Truncated = null;
    Boolean FrequencyCorrected = null;
    Boolean PhaseCorrected = null;

    public Flags() {
    }

    public Boolean isBinary() {
        return Binary;
    }

    public void setBinary(Boolean binary) {
        Binary = binary;
    }

    public Boolean isCompressed() {
        return Compressed;
    }

    public void setCompressed(Boolean compressed) {
        this.Compressed = compressed;
    }

    public Boolean isTruncated() {
        return Truncated;
    }

    public void setTruncated(Boolean truncated) {
        Truncated = truncated;
    }

    public Boolean isFrequecyDomain() {
        return FrequecyDomain;
    }

    public void setFrequecyDomain(Boolean frequecyDomain) {
        FrequecyDomain = frequecyDomain;
    }

    public Boolean isDownsampled() {
        return Downsampled;
    }

    public void setDownsampled(Boolean downsampled) {
        Downsampled = downsampled;
    }

    public Boolean isFiltered() {
        return Filtered;
    }

    public void setFiltered(Boolean filtered) {
        Filtered = filtered;
    }

    public Boolean isZeropadded() {
        return Zeropadded;
    }

    public void setZeropadded(Boolean zeropadded) {
        Zeropadded = zeropadded;
    }

    public Boolean isFrequencyCorrected() {
        return FrequencyCorrected;
    }

    public void setFrequencyCorrected(Boolean frequencyCorrected) {
        FrequencyCorrected = frequencyCorrected;
    }

    public Boolean isPhaseCorrected() {
        return PhaseCorrected;
    }

    public void setPhaseCorrected(Boolean phaseCorrected) {
        PhaseCorrected = phaseCorrected;
    }
}
