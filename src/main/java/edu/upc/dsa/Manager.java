package edu.upc.dsa;

import edu.upc.dsa.exceptions.MailNotFoundException;
import edu.upc.dsa.exceptions.ObjectNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.exceptions.WrongPasswordException;
import edu.upc.dsa.models.StoreObject;
import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface Manager {

    //User related
    public User addUser(User t);
    public User addUser(String username, String password, String email);
    public User getUser(String username);
    public User getUser1(String username) throws UserNotFoundException;
    public User getMail (String mail) throws MailNotFoundException;

    //Store related
    public StoreObject addToStore(StoreObject object);
    public StoreObject addToStore(String name, int price);
    public StoreObject getObject(String name) throws ObjectNotFoundException;

    //Register
    public boolean register(String username, String password, String mail);

    //Login through username and through mail
    public int login1(String username, String password)throws UserNotFoundException, WrongPasswordException;
    public int login2(String mail, String password)throws UserNotFoundException, WrongPasswordException;

    //List of Users
    public List<User> findAllUsers();
    public void deleteUser(String username);
    public User updateUser1(User t, String username);
    public User updateUser2(User t, String password);

    //Get the Objects of a User and the Store
    public HashMap<StoreObject,Integer> getUserObjects(String username);
    public List<StoreObject> findAllObjects();

    //Clear and Size of lists used (Users & StoreObjects)
    public void clear();
    public void clearUsers();
    public void clearObjects();
    public int sizeUsers();
    public int sizeObjects();
}
