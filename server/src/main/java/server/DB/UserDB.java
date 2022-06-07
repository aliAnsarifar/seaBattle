package server.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import shared.model.User;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class UserDB {

    private static final String folderPath = "src\\main\\resources\\users";
    private static final String mapPath = "src\\main\\resources\\usernameToID.json";
    private static Object UsernameToId = new Object();

    public int usernameToId(String username){
        synchronized (UsernameToId) {
            int Id = -1;
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(mapPath);
                Type type = new TypeToken<HashMap<String, Integer>>() {
                }.getType();
                HashMap<String, Integer> usernameToID = gson.fromJson(fileReader, type);
                fileReader.close();
                if (usernameToID.containsKey(username)) {
                    Id = usernameToID.get(username);
                }
            } catch (IOException e) {
            }
            return Id;
        }
    }

    public ArrayList<User> get(){
        ArrayList<User> users = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader fileReader ;
        for (File file: Objects.requireNonNull(new File(folderPath).listFiles())) {
                try {
                    fileReader = new FileReader(file);
                    User user = gson.fromJson(fileReader , User.class);
                    fileReader.close();
                    users.add(user);

                } catch (IOException e) {

            }
        }
        return users;
    }

    public synchronized User get(String username){
        int Id = usernameToId(username);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader fileReader ;
        for (File file: Objects.requireNonNull(new File(folderPath).listFiles())) {
            if (file.getName().equals(String.valueOf(Id) + ".json")){
                try {
                    fileReader = new FileReader(file);
                    User user = gson.fromJson(fileReader , User.class);
                    fileReader.close();
                    return user;
                } catch (IOException e) {

                }
            }
        }
        return null;
    }

    public synchronized void add(String username , String password) {
        int id;
        Random random = new Random();
        while(true){
            id = random.nextInt();
            if (!new File(folderPath + "\\" + id + ".json").exists()){
                break;
            }
        }
        User user = new User(username , password , id);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(folderPath + "\\" + user.getId()+ ".json");
            gson.toJson(user , fileWriter);
            fileWriter.flush();
            fileWriter.close();
            ////update to usernameToID
            FileReader  fileReader = new FileReader(mapPath);
            Type type = new TypeToken<HashMap<String , Integer>>(){}.getType();
            HashMap<String  , Integer > userToID = gson.fromJson(fileReader , type);
            fileReader.close();
            userToID.put(user.getUsername() , user.getId());
            fileWriter = new FileWriter(mapPath);
            gson.toJson(userToID , fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
        }

    }

    public synchronized void update(User user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(folderPath + "\\" + user.getId()+ ".json");
            gson.toJson(user , fileWriter);
            fileWriter.flush();
            fileWriter.close();
            FileReader  fileReader = new FileReader(mapPath);
            Type type = new TypeToken<HashMap<String , Integer>>(){}.getType();
            HashMap<String  , Integer > userToID = gson.fromJson(fileReader , type);
            fileReader.close();
            for (String string:userToID.keySet()) {
                if (userToID.get(string) == user.getId()){
                    userToID.remove(string , user.getId());
                    break;
                }
            }
            userToID.put(user.getUsername() , user.getId());
            fileWriter = new FileWriter(mapPath);
            gson.toJson(userToID , fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
        }
    }


    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(mapPath);
        gson.toJson(new HashMap<String , Integer>() , fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

}
