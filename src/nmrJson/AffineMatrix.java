package nmrJson;

public class AffineMatrix {

    Short	qform_code;
    Short	sform_code;
    Float	quatern_b;
    Float	quatern_c;
    Float	quatern_d;
    Float	qoffset_x;
    Float	qoffset_y;
    Float	qoffset_z;
    Float   qfac;
    Float[]	srow_x;
    Float[]	srow_y;
    Float[]	srow_z;

    public AffineMatrix() {
    }


    public Short getQform_code() {
        return qform_code;
    }

    public void setQform_code(Short qform_code) {
        this.qform_code = qform_code;
    }

    public Short getSform_code() {
        return sform_code;
    }

    public void setSform_code(Short sform_code) {
        this.sform_code = sform_code;
    }

    public Float getQuatern_b() {
        return quatern_b;
    }

    public void setQuatern_b(Float quatern_b) {
        this.quatern_b = quatern_b;
    }

    public Float getQuatern_c() {
        return quatern_c;
    }

    public void setQuatern_c(Float quatern_c) {
        this.quatern_c = quatern_c;
    }

    public Float getQuatern_d() {
        return quatern_d;
    }

    public void setQuatern_d(Float quatern_d) {
        this.quatern_d = quatern_d;
    }

    public Float getQoffset_x() {
        return qoffset_x;
    }

    public void setQoffset_x(Float qoffset_x) {
        this.qoffset_x = qoffset_x;
    }

    public Float getQoffset_y() {
        return qoffset_y;
    }

    public void setQoffset_y(Float qoffset_y) {
        this.qoffset_y = qoffset_y;
    }

    public Float getQoffset_z() {
        return qoffset_z;
    }

    public void setQoffset_z(Float qoffset_z) {
        this.qoffset_z = qoffset_z;
    }

    public Float getQfac() {
        return qfac;
    }

    public void setQfac(Float qfac) {
        this.qfac = qfac;
    }

    public Float[] getSrow_x() {
        return srow_x;
    }

    public void setSrow_x(Float[] srow_x) {
        this.srow_x = srow_x;
    }

    public Float[] getSrow_y() {
        return srow_y;
    }

    public void setSrow_y(Float[] srow_y) {
        this.srow_y = srow_y;
    }

    public Float[] getSrow_z() {
        return srow_z;
    }

    public void setSrow_z(Float[] srow_z) {
        this.srow_z = srow_z;
    }
}
