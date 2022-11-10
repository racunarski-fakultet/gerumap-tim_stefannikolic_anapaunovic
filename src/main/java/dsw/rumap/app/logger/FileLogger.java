package dsw.rumap.app.logger;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.core.Logger;
import dsw.rumap.app.msggenerator.Message;
import dsw.rumap.app.msggenerator.MessageType;
import dsw.rumap.app.msggenerator.Problem;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class FileLogger extends AbstractLogger {

    FileWriter fw;
    BufferedWriter bw;

    public FileLogger() {

        AppCore.getInstance().getMsgGenerator().addSubscriber(this);
    }

    @Override
    public void printMessage(Message message) {
        super.printMessage(message);
        try {
            fw = new FileWriter("log.txt", true);
            bw = new BufferedWriter(fw);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            bw.append("["+ type + "] ["+ dtf.format(message.getTimeStamp()) + "] "+ print + "\n");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void closeFileLogger(){
        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}