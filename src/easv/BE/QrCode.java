package easv.BE;

public class QrCode {
    private int id;
    private String number;

    public QrCode(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "QrCode{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
