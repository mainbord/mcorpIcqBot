package mcorp.domain.rzhunemogu;

public enum RzhunemoguRandomRequestType {

    JOKE (1),
    TALE (2),
    RHYME (3),
    APHORISM (4),
    QUOTATION (5),
    TOAST (6),
    STATUS (8),
    JOKE18 (11),
    TALE18 (12),
    RHYME18 (13),
    APHORISM18 (14),
    QUOTATION18 (15),
    TOAST18 (16),
    STATUS18 (18);

    private Integer cType;

    RzhunemoguRandomRequestType(Integer cType) {
        this.cType = cType;
    }

    public int getcType() {
        return cType;
    }
}
