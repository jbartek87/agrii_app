package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    List<User> findAll();

    Optional<User> findById(long id);

    @Override
    User save(User user);

    void deleteById(Long id);

    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);


}
