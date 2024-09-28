package com.example.demo.repository;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    // Buscar usuarios cuyos nombres comiencen con cierta cadena sin importar mayúsculas o minúsculas
    List<User> findByNameStartingWithIgnoreCase(String prefix);
}
