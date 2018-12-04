/**
 * FileName: UserRepository
 * Author:   jack.xue
 * Date:     2018/11/30 13:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2018/11/30           1.0.0              描述
 */
package cn.bw.fitzboot.repository;
import cn.bw.fitzboot.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2018/11/30
 * @since 1.0.0
 * [GET] api for query all: http://localhost:8080/fitz-boot/api/users
 * [GET] api for query by id: http://localhost:8080/fitz-boot/api/users/1
 * [GET] api for query by password [ux]: http://localhost:8080/fitz-boot/api/users/search/passwordEquals?password=123456
 * [DELETE] api for delete by id: http://localhost:8080/fitz-boot/api/users/7
 * [POST][SAVE] api for save by json: http://localhost:8080/fitz-boot/api/users @@raw:@@ {"username":"robet5","password":"33333335"}
 * [PUT][UPDATE] api for update by id: http://localhost:8080/fitz-boot/api/users/9 @@raw:@@ {"username":"robet9","password":"666666"}
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    @RestResource(exported = false)
    void delete(Long aLong);

    @Override
    @RestResource(exported = false)
    <S extends User> S save(S s);

    @RestResource(path = "passwordEquals", rel = "passwordEquals")
    User findByPasswordEquals(@Param("password") String password);

}