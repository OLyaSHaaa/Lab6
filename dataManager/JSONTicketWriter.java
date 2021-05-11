package dataManager;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ticket.Ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class JSONTicketWriter implements DataWriter{
    private String fileName;
    public JSONTicketWriter(String fileName){
    this.fileName = fileName;
    }

    @Override
    public void writeElements(HashSet<Ticket> tickets) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String json = gson.toJson(tickets);
            bufferedWriter.write(json);

            /*for (Ticket ticket : tickets) {
                JsonElement element = gson.toJsonTree(ticket);
                String json = gson.toJson(element);
                //bufferedWriter.write(json + "," + "\n");
                bufferedWriter.write(json);
            }*/

            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Пропишите дополнительные права доступа,Алексей Евгеньевич");
        }
    }
}