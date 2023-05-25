package infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampAdapter implements Timestamp {

    private LocalDateTime localDateTime;

    public TimestampAdapter(){
        this.localDateTime = LocalDateTime.now();
    }
    
    public String getTime(){
        return("Timestamp da criação do usuário: " + localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }
    

}
