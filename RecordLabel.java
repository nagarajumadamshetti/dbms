public class RecordLabel {
    private String recordLabelID;
    private String name;

    public RecordLabel(String recordLabelID, String name) {
        this.recordLabelID = recordLabelID;
        this.name = name;
    }

    public String getRecordLabelID() {
        return recordLabelID;
    }

    public void setRecordLabelID(String recordLabelID) {
        this.recordLabelID = recordLabelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
