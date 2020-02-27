package corp.ny.com.tuttifrutti;

import android.support.annotation.NonNull;

import corp.ny.com.rufus.database.Constraint;
import corp.ny.com.rufus.database.Model;
import corp.ny.com.rufus.database.Schema;
import corp.ny.com.rufus.database.annotation.Column;
import corp.ny.com.rufus.database.annotation.Table;


/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 19/04/18.
 */
@Table
public class Message extends Model<Message> {
    @Column(primary = true, increment = true)
    private int id;
    @Column
    private String message;
    @Column
    private boolean isOpen;
    @Column
    @corp.ny.com.rufus.database.annotation.Constraint(references = "id", onTable = "User")
    private int receiverId;
    @Column(defaultInt = 10)
    @corp.ny.com.rufus.database.annotation.Constraint(references = "id", onTable = "User")
    private int senderId;

    public static Message getInstance(int id) {
        Message message = new Message();
        message.setId(id);
        message.setMessage(String.format("%s %s","message",id));
        message.setReceiverId(1);
        message.setSenderId(1);
        message.setOpen(!message.isOpen);
        return message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
