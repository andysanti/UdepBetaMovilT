package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models;

import java.util.Random;

public class ChatMessage {
    //private MemberData data; // data of the user that sent this message
    private String id;
    private String user;
    private String name;
    private String message; // message body
    private String time;
    private String color;
    private boolean belongsToCurrentUser; // is this message sent by us?

    public ChatMessage() {
        this.color = getRandomColor();
    }

    public ChatMessage(String user, String name, String message, String time, boolean belongsToCurrentUser) {
        this.user = user;
        this.name = name;
        this.message = message;
        this.time = time;
        this.color = getRandomColor();
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public ChatMessage(String user, String name, String message, String time) {
        this.user = user;
        this.name = name;
        this.message = message;
        this.time = time;
        this.color = getRandomColor();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }

    public void setBelongsToCurrentUser(boolean belongsToCurrentUser) {
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}