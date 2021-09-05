/*Author: Dennis Shaba */
package io.dennis.app.OntracTechonologiesApp.service;
import io.dennis.app.OntracTechonologiesApp.model.User;
import io.dennis.app.OntracTechonologiesApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*This service class helps me to implement all abstract methods
* from UserRepository.*/

@Service
public class UserService  {

    @Autowired //registering userRepository
    UserRepository userRepository;

    //this method will check if email already exist
    public boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
    //this method will check if phone number already exist
    public boolean phoneExist(String email) {
        return userRepository.findByPhone(email) != null;
    }

    //this method will add data if all checks are valid
    public void addUser(User user)throws UsernameNotFoundException {
        if(emailExist(user.getEmail()) || phoneExist(user.getPhone())) {
            throw new UsernameNotFoundException("");
        }
        userRepository.save(user);
    }
}
