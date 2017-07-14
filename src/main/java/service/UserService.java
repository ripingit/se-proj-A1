package service;

import model.BookRelease;
import model.User;
import model.Picture;

import java.io.File;
import java.util.List;

/*log in ��log out��register��updateUserInformation*/
public interface UserService {
    public Integer save(User user);

	public void delete(User user);

	public void update(User user);

	public User getUserById(int id);
	
	public User getUserByEmail(String email);
	
	public Picture getPictureById(int id);

	public List<User> getAllUsers();
    
    public boolean isAdmin(User user);
    
    public boolean login(String email,String password);
    
    public boolean register(User user);
   
    public boolean dupEmail(String email,User user);
  
    public boolean uploadImage(int id, File image, String fileName, String contentType);
    
    public void manageUser(int id, int status);							//0:ban, 1:unban
    
    public void checkBookRelease(BookRelease bookRelease, short status);	//0:reject, 1:pass
}

