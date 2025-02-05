package edu.upc.dsa.orm;

import edu.upc.dsa.Manager;
import edu.upc.dsa.dao.DAO;
import edu.upc.dsa.dao.LevelsCreatedDao;
import edu.upc.dsa.dao.LevelsCreatedDaoImpl;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.CustomLevel;
import edu.upc.dsa.models.ScoreData;
import edu.upc.dsa.models.StoreObject;
import edu.upc.dsa.models.User;
import edu.upc.dsa.services.ListLevelsCreatedService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DBTest {
    final static Logger logger = Logger.getLogger(DBTest.class);
    Session session;
    Manager manager;

    @Before
    public void setUp() throws Exception {
        session = FactorySession.openSession();
        manager = DAO.getInstance();
        manager.deleteUser("a");    // delete user in case it exists
    }

    @Test
    public void queryTest() throws Exception {
        User user = new User("a","a","a@a.com");
        Assert.assertEquals("a", session.save(user).getUsername());
        logger.debug(session.findAll(User.class, Map.of("username", "a")));
        Assert.assertEquals("a@a.com", session.findAll(User.class, Map.of("username", "a")).get(0).getMail());

        session.update(User.class, Map.of("mail", "a@b.com"), Map.of("username", "a"));
        logger.debug(session.findAll(User.class, Map.of("username", "a")));
        Assert.assertEquals("a@b.com", session.findAll(User.class, Map.of("username", "a")).get(0).getMail());

        session.delete(User.class, Map.of("username", "a"));
        Assert.assertEquals(0, session.findAll(User.class, Map.of("username", "a")).size());

    }

    @Test
    public void shopTest() throws Exception {
        User u = manager.register("a", "a", "a@a.com");
        StoreObject o = manager.addToStore("plàtan", 3, "images/platan.png", "descripció");
        manager.buyObject(u.getId(), o.getId(), 1);
        Assert.assertEquals(47, manager.getUserByID(u.getId()).getMoney(), 0.0001);

        manager.deleteUser("a");
        session.delete(StoreObject.class, Map.of("ID", o.getId()));
    }

    @Test
    public void userTest() throws Exception {
        User u = manager.register("a", "a", "a@a.com");
        manager.addPuntos(u.getId(), 5);
        Assert.assertEquals(5, manager.getUserByID(u.getId()).getPuntos());
        manager.deleteUser("a");

    }
    @Test
    public void testAddScore() throws SQLException, UserNotFoundException {
        // Create a new user
        User user = new User("a","a","a@a.com");


        // Add score to the user
        ScoreData scoreData = new ScoreData(100, 1);
        manager.addScore(user.getId(), scoreData);

        // Retrieve the user and check the score
        User updatedUser = manager.getUserByID(user.getId());
        assertNotNull(updatedUser);
        assertEquals(100, updatedUser.getPuntos());
    }




    @After
    public void after() {
        session.close();
    }
}
