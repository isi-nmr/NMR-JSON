package nmrJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class NmrJSON {
    String Version;
    String Name;
    ContentType ContentType;
    Integer[] Dimensions;
    Integer StorageSize;
    String ExperimentType;
    Float[] VoxelDimension;
    Float DataSlope;
    Float DataOffset;
    Float FieldStrength;
    String DateofExperiment;
    String PulseSequence;

    Flags flags = new Flags();


    private transient Double[] real;
    private transient Double[] imag;
    private transient String path2json;
    private transient String fN;

    LinkedList<Dimension> dimensions = new LinkedList<>();
    LinkedHashMap<String, AffineMatrix> AffineMatrices = new LinkedHashMap<>();

    public NmrJSON(Integer[] dims) {
         Dimensions = dims;
         StorageSize=1;
         Arrays.stream(dims).forEach(i -> StorageSize*=i);
         real = new Double[StorageSize];
         imag = new Double[StorageSize];
    }
    public NmrJSON() {

    }

    public Integer[] getDimensions() {
        return Dimensions;
    }

    public void setDimensions(Integer[] dimensions) {
        Dimensions = dimensions;
    }

    public String getPulseSequence() {
        return PulseSequence;
    }

    public void setPulseSequence(String pulseSequence) {
        PulseSequence = pulseSequence;
    }

    public LinkedHashMap<String, AffineMatrix> getAffineMatrix() {
        return AffineMatrices;
    }

    public void setMainAffineMatrix(AffineMatrix affineMatrix) {
        AffineMatrices.put("Main", affineMatrix);
    }

    public void setVOIAffineMatrix(AffineMatrix affineMatrix) {
        AffineMatrices.put("VOI", affineMatrix);
    }

    public void setUserAffineMatrix(String name, AffineMatrix affineMatrix) {
        AffineMatrices.put(name, affineMatrix);
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }


    public String getDateofExperiment() {
        return DateofExperiment;
    }

    public void setDateofExperiment(String dateofExperiment) {
        DateofExperiment = dateofExperiment;
    }

    public ContentType getContentType() {
        return ContentType;
    }

    public void setContentType(ContentType contentType) {
        this.ContentType = contentType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getExperimentType() {
        return ExperimentType;
    }

    public void setExperimentType(String experimentType) {
        ExperimentType = experimentType;

    }

    public float getFieldStrength() {
        return FieldStrength;
    }

    public void setFieldStrength(Float fieldStrength) {
        FieldStrength = fieldStrength;

    }

    public Integer getStorageSize() {
        return StorageSize;
    }

    public void setStorageSize(Integer storageSize) {
        StorageSize = storageSize;
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }

    public String getPath2json() {
        return path2json;
    }

    public void setPath2json(String path2json) {
        this.path2json = path2json;
    }

    public String getfN() {
        return fN;
    }

    public void setfN(String fN) {
        this.fN = fN;
    }

    public void addSeries(int[] spatialIndices, int[] UserIndices, Double[] real, Double[] imag){

        int[] indices = new int[4 + UserIndices.length];

        System.arraycopy(spatialIndices, 0, indices, 0, 3);
        System.arraycopy(UserIndices, 0, indices, 4, UserIndices.length);

//        int[] indices = ArrayUtils.add(spatialIndices, 0);
//        indices = ArrayUtils.addAll(indices, UserIndices);
        int startIndex = indices[0];
        for (int idx = 1; idx < indices.length; idx++) {
            startIndex = ((Dimensions[idx] * startIndex) + indices[idx]);
        }
        int stepSize = 1;
        for (int i = 4; i < Dimensions.length ; i++) {
            stepSize *= Dimensions[i];
        }
        for (int i = 0; i < Dimensions[3]; i++) {
            this.real[startIndex+i*stepSize] = real[i];
            this.imag[startIndex+i*stepSize] = imag[i];
        }
    }


    public static NmrJSON importJson(String path2json, String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        NmrJSON nmrJSON = gson.fromJson(new FileReader(path2json.concat(fileName).concat(".json")), NmrJSON.class);
        nmrJSON.setfN(fileName);
        nmrJSON.setPath2json(path2json);
        return nmrJSON;
    }

    public void importBinary() throws IOException {
        if(this.flags.isCompressed()) {
            FileInputStream fos = new FileInputStream(path2json.concat(this.getfN()).concat(".zip"));
            ZipInputStream zos = new ZipInputStream(fos);
            zos.getNextEntry();
            byte[] readbuffer = new byte[8];
            double[] rArr = new double[StorageSize];
            double[] iArr = new double[StorageSize];
            for (int i = 0; i < StorageSize; i++) {
                zos.read(readbuffer, 0, 8);
                rArr[i] = Double.longBitsToDouble(zipRead(readbuffer));
            }
            zos.getNextEntry();
            for (int i = 0; i < StorageSize; i++) {
                zos.read(readbuffer, 0, 8);
                iArr[i] = Double.longBitsToDouble(zipRead(readbuffer));
            }
            zos.closeEntry();
            zos.close();
        } else {
            FileInputStream rstream = new FileInputStream(path2json + this.getfN() +  "_RD.nmrJson");
            DataInputStream rinputFile = new DataInputStream(rstream);
            FileInputStream istream = new FileInputStream(path2json + this.getfN() +  "_ID.nmrJson");
            DataInputStream iinputFile = new DataInputStream(istream);
            double[] rArr = new double[StorageSize];
            double[] iArr = new double[StorageSize];
            for (int i = 0; i < StorageSize; i++) {
                rArr[i] =  rinputFile.readDouble();
                iArr[i] =  iinputFile.readDouble();
            }
        }
    }

    public double[][] importSeries(int[] spatialIndices, int[] UserIndices) throws IOException {
        int[] indices = new int[4 + UserIndices.length];
        System.arraycopy(spatialIndices, 0, indices, 0, 3);
        System.arraycopy(UserIndices, 0, indices, 4, UserIndices.length);

        RandomAccessFile readRR = new RandomAccessFile(path2json + this.getfN() +  "_RD.nmrJson", "r");
        RandomAccessFile readRI = new RandomAccessFile(path2json + this.getfN() +  "_ID.nmrJson", "r");
        int startIndex = indices[0];
        for (int idx = 1; idx < indices.length; idx++) {
            startIndex = ((Dimensions[idx] * startIndex) + indices[idx]);
        }
        int stepSize = 1;
        for (int i = 4; i < Dimensions.length ; i++) {
            stepSize *= Dimensions[i];
        }
        readRR.seek(startIndex*8);
        readRI.seek(startIndex*8);
        double[][] series = new double[2][Dimensions[3]];
        for (int i = 0; i < Dimensions[3]; i++) {
            series[0][i] = readRR.readDouble();
            series[1][i] = readRI.readDouble();
            readRR.skipBytes(8 * (stepSize - 1));
            readRI.skipBytes(8 * (stepSize - 1));
        }
        return series;
    }

    public void exportJson(String path2json, String fileName) throws IOException {
        this.fN = fileName;
        this.path2json = path2json;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter(path2json.concat(fileName).concat(".json"))) {
            gson.toJson(this, file);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exportBinary() throws IOException {
        if(flags.isCompressed()) {
            FileOutputStream fos = new FileOutputStream(path2json.concat(this.fN).concat(".zip"));
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry("real"));
            for (int i = 0; i < StorageSize; i++) {
                zos.write(zipWrite(real[i]), 0, 8);
            }
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("imag"));
            for (int i = 0; i < StorageSize; i++) {
                zos.write(zipWrite(imag[i]), 0, 8);
            }
            zos.close();
        } else {
            FileOutputStream rstream = new FileOutputStream(path2json + this.fN + "_RD.nmrJson");
            DataOutputStream routputFile = new DataOutputStream(rstream);
            FileOutputStream istream = new FileOutputStream(path2json + this.fN + "_ID.nmrJson");
            DataOutputStream ioutputFile = new DataOutputStream(istream);
            for (int i = 0; i < StorageSize; i++) {
                routputFile.writeDouble(real[i]);
                ioutputFile.writeDouble(imag[i]);
            }
            routputFile.close();
            ioutputFile.close();
        }
    }

    private byte[] zipWrite(double d) {
        long v = Double.doubleToLongBits(d);
        byte writeBuffer[] = new byte[8];
            writeBuffer[0] = (byte)(v >>> 56);
            writeBuffer[1] = (byte)(v >>> 48);
            writeBuffer[2] = (byte)(v >>> 40);
            writeBuffer[3] = (byte)(v >>> 32);
            writeBuffer[4] = (byte)(v >>> 24);
            writeBuffer[5] = (byte)(v >>> 16);
            writeBuffer[6] = (byte)(v >>>  8);
            writeBuffer[7] = (byte)(v >>>  0);
            return writeBuffer;
    }

    private long zipRead(byte[] readBuffer) {
        return (((long)readBuffer[0] << 56) +
                ((long)(readBuffer[1] & 255) << 48) +
                ((long)(readBuffer[2] & 255) << 40) +
                ((long)(readBuffer[3] & 255) << 32) +
                ((long)(readBuffer[4] & 255) << 24) +
                ((readBuffer[5] & 255) << 16) +
                ((readBuffer[6] & 255) <<  8) +
                ((readBuffer[7] & 255) <<  0));
    }




}
