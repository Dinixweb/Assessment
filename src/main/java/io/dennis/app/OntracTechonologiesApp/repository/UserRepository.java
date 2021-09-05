/*Author: Dennis Shaba */
package io.dennis.app.OntracTechonologiesApp.repository;

import io.dennis.app.OntracTechonologiesApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByPhone(String phone);

}
