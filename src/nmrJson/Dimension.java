package nmrJson;

import java.util.LinkedHashMap;

public class Dimension extends LinkedHashMap {
    Integer id = null;

    LinkedHashMap parameter = new LinkedHashMap();
    Info info = new Info();

    public Dimension(Integer dim) {
        this.put("id", dim);
        this.put("info", info);
        this.put("parameters", parameter);
    }
    public void addParameters(String name, Object[] parameters){
        parameter.put(name, parameters);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkedHashMap getParameter() {
        return parameter;
    }

    public void setParameter(LinkedHashMap parameter) {
        this.parameter = parameter;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public class Info {
        Integer NumberOfSamples = null;
        String Nucleus = null;
        Float AcquisitionBandwidth = null;
        Float CarrierFrequency = null;
        Float ObservationFrequency = null;
        Float CentralChemicalShift = null;
        Float ZeroOrderPhase = null;
        String DimensionInfo = null;
        String DimensionUnit = null;
        String Encoding = null;
        int[] EncodingSteps = null;

        public Info() {
        }

        public String getDimensionInfo() {
            return DimensionInfo;
        }

        public void setDimensionInfo(String dimensionInfo) {
            DimensionInfo = dimensionInfo;
        }

        public String getDimensionUnit() {
            return DimensionUnit;
        }

        public void setDimensionUnit(String dimensionUnit) {
            DimensionUnit = dimensionUnit;
        }

        public float getCentralChemicalShift() {
            return CentralChemicalShift;
        }

        public void setCentralChemicalShift(float centralChemicalShift) {
            CentralChemicalShift = centralChemicalShift;
        }

        public float getZeroOrderPhase() {
            return ZeroOrderPhase;
        }

        public void setZeroOrderPhase(float zeroOrderPhase) {
            ZeroOrderPhase = zeroOrderPhase;
        }

        public Integer getNumberOfSamples() {
            return NumberOfSamples;
        }

        public void setNumberOfSamples(Integer numberOfSamples) {
            NumberOfSamples = numberOfSamples;
        }

        public String getNucleus() {
            return Nucleus;
        }

        public void setNucleus(String nucleus) {
            Nucleus = nucleus;
        }

        public float getAcquisitionBandwidth() {
            return AcquisitionBandwidth;
        }

        public void setAcquisitionBandwidth(float acquisitionBandwidth) {
            AcquisitionBandwidth = acquisitionBandwidth;
        }

        public float getCarrierFrequency() {
            return CarrierFrequency;
        }

        public void setCarrierFrequency(float carrierFrequency) {
            CarrierFrequency = carrierFrequency;
        }

        public float getObservationFrequency() {
            return ObservationFrequency;
        }

        public void setObservationFrequency(float observationFrequency) {
            ObservationFrequency = observationFrequency;
        }

        public String getEncoding() {
            return Encoding;
        }

        public void setEncoding(String encoding) {
            Encoding = encoding;
        }

        public int[] getEncodingSteps() {
            return EncodingSteps;
        }

        public void setEncodingSteps(int[] encodingSteps) {
            EncodingSteps = encodingSteps;
        }
    }
}
