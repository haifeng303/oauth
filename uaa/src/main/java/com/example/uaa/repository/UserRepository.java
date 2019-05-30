package com.example.uaa.repository;

import com.example.uaa.POJO.EO.UserEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/4/914:37
 * @Description: 用户
 */
@Repository
public interface UserRepository extends JpaRepository<UserEO, Long> {
    /**
     * @author rhf30
     * @descript 根据用户名查询用户信息
     * @params [username]
     * @return com.example.uaa.POJO.EO.UserEO
     */
    @Query(value = "SELECT * FROM t_employee e WHERE e.username = ?1", nativeQuery = true)
    UserEO findByUsername(String username);
}
