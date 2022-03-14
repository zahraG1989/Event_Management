package easv.BE;

import java.util.Date;

public class Ticket {
    private String ticketholder ;
    private String type ;
    private String eventname ;
    private int ticketprice ;
    private String eventlocation;
    private String barcode ; // for now
    private Date expirationdan ;

    public Ticket(String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan) {
        this.ticketholder = ticketholder;
        this.type = type;
        this.eventname = eventname;
        this.ticketprice = ticketprice;
        this.eventlocation = eventlocation;
        this.barcode = barcode;
        this.expirationdan = expirationdan;
    }

    public String getTicketholder() {
        return ticketholder;
    }

    public void setTicketholder(String ticketholder) {
        this.ticketholder = ticketholder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public int getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(int ticketprice) {
        this.ticketprice = ticketprice;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) {
        this.eventlocation = eventlocation;
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
                "ticketholder='" + ticketholder + '\'' +
                ", type='" + type + '\'' +
                ", eventname='" + eventname + '\'' +
                ", ticketprice=" + ticketprice +
                ", eventlocation='" + eventlocation + '\'' +
                ", barcode='" + barcode + '\'' +
                ", expirationdan=" + expirationdan +
                '}';
    }
}
