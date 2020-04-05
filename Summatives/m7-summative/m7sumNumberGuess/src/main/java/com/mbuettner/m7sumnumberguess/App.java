/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m7sumnumberguess;

import com.mbuettner.m7sumnumberguess.Controllers.NumberGuessController;
import com.mbuettner.m7sumnumberguess.DTO.Game;
import com.mbuettner.m7sumnumberguess.Dao.NumberGuessDao;
import com.mbuettner.m7sumnumberguess.Dao.NumberGuessDaoImpl;
import com.mbuettner.m7sumnumberguess.Dao.RoundDao;
import com.mbuettner.m7sumnumberguess.Dao.RoundDaoImpl;
import com.mbuettner.m7sumnumberguess.Service.NumberGuessService;
import com.mbuettner.m7sumnumberguess.UI.NumberGuessView;
import com.mbuettner.m7sumnumberguess.UI.UserIO;
import com.mbuettner.m7sumnumberguess.UI.UserIOConsoleImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author mbuet
 */
public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        NumberGuessView view = new NumberGuessView(io);
        NumberGuessDao dao = new NumberGuessDaoImpl();
        RoundDao roundDao = new RoundDaoImpl();
        NumberGuessService service = new NumberGuessService(dao, roundDao);
        NumberGuessController controller = new NumberGuessController(view, service);

        controller.run();
    }

}
