package sample.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIndoRepository extends JpaRepository<UserInfo, Integer> {

}
