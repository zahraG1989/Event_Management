package easv.BE;

import java.util.Date;

public class Ticket {

    private String type ;
    private int ticketprice ;
    private String barcode ; // for now
    private Date expirationdan ;
    private int id ;
    private String info ;
    public Ticket(int id , String type , int ticketprice , String barcode, Date expirationdan , String info) {
        this.type = type;
        this.ticketprice = ticketprice;
        this.barcode = barcode;
        this.expirationdan = expirationdan;
        this.id = id ;
        this.info = info ;
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

    public void setTicketprice(int ticketprice) {
        this.ticketprice = ticketprice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getExpirationdan() {
        return expirationdan;
    }

    public void setExpirationdan(Date expirationdan) {
        this.expirationdan = expirationdan;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "type='" + type + ", ticketprice=" + ticketprice   + ", id=" + id + ", info='" + info + '}';
    }
}
