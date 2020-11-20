import nmrJson.ContentType;
import nmrJson.Dimension;
import nmrJson.NmrJSON;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNew {
    public static void main(String[] args) throws IOException {
        // 1.create a json object
        NmrJSON nmrJSON = new NmrJSON(new Integer[] {1, 1, 1, 2048, 2, 2});


        // 2. store information
        nmrJSON.setFileName("mrs data");
        // use built-in enum
        nmrJSON.setContentType(ContentType.SIGNAL);
        // now just accept string but it will get updated to enum
        nmrJSON.setExperimentType("single voxel spectroscopy");

        nmrJSON.setFieldStrength(9.4f);

        // set number of response:
        // date of experiment
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        nmrJSON.setDateofExperiment(formatter.format(date));

        Dimension dimension1 = new Dimension(1);
        dimension1.getInfo().setDimensionInfo("X");
        nmrJSON.addDimension(dimension1);

        Dimension dimension2 = new Dimension(1);
        dimension2.getInfo().setDimensionInfo("Y");
        nmrJSON.addDimension(dimension2);

        Dimension dimension3 = new Dimension(1);
        dimension3.getInfo().setDimensionInfo("Z");
        nmrJSON.addDimension(dimension3);

        Dimension dimension4 = new Dimension(1);
        dimension4.getInfo().setDimensionInfo("Time");
        nmrJSON.addDimension(dimension4);

        Dimension dimension5 = new Dimension(5);
        dimension5.addParameters("TE", new Double[] {10000d});
        nmrJSON.addDimension(dimension5);
        dimension5.getInfo().setAcquisitionBandwidth(4000);

        Dimension dimension6 = new Dimension(6);
        dimension6.addParameters("Metabolite", new String[] {"Naa", "Cho"});
        nmrJSON.addDimension(dimension6);
        // generate random data
        Double[] real = new Double[2048];
        Double[] imag = new Double[2048];
        Double[] real1 = new Double[2048];
        Double[] imag1 = new Double[2048];
        Double[] real2 = new Double[2048];
        Double[] imag2 = new Double[2048];
        for (int i = 0; i < real.length; i++) {
            real[i] = 1d;
            imag[i] = 1d;
            real1[i] = 2d;
            imag1[i] = 2d;
            real2[i] = 3d;
            imag2[i] = 3d;
        }
        // add Fid
        nmrJSON.addSeries(new int[]{0,0,0},new int[] {0,0}, real, imag);
        nmrJSON.addSeries(new int[]{0,0,0},new int[] {0,1}, real1, imag1);
        nmrJSON.addSeries(new int[]{0,0,0},new int[] {1,0}, real2, imag2);
        nmrJSON.addSeries(new int[]{0,0,0},new int[] {1,1}, real1, imag1);
        nmrJSON.getFlags().setCompressed(false);
        nmrJSON.exportJson("D:\\nmrjsonFile\\", "TestNew");
        nmrJSON.exportBinary();

        NmrJSON nmrJSON2 = NmrJSON.importJson("D:\\nmrjsonFile\\", "TestNew");
        nmrJSON2.importBinary();
        nmrJSON2.importSeries(new int[]{0,0,0},new int[] {0,0});

    }
}
