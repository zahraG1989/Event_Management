package easv.BE;

import java.sql.Timestamp;
import java.util.Date;

public class Ticket {

    private String type;
    private int ticketprice;
    private Timestamp expirationdan;
    private int id;
    private String info;

    public Ticket(int id, String type, int ticketprice, Timestamp expirationdan, String info) {
        this.type = type;
        this.ticketprice = ticketprice;

        this.expirationdan = expirationdan;
        this.id = id;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTicketprice() {
        return ticketprice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "type='" + type + ", ticketprice=" + ticketprice + ", id=" + id + ", info='" + info + '}';
    }
}
