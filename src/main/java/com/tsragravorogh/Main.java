package com.tsragravorogh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tsragravorogh.Filds.Game;
import com.tsragravorogh.Services.GameService;


public class Main {

    public static void main(String[] args) throws Exception {
        Game g = new Game();
        GameService svc = new GameService();
        svc.play(g, 6);

        SerializeService ss = new SerializeService();
        ss.serialize(g);
        g = ss.deserialize();


//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(g);
//        System.out.println(json);
    }
}
