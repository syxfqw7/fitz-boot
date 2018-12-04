/**
 * FileName: User
 * Author:   jack.xue
 * Date:     2018/11/30 13:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2018/11/30           1.0.0              描述
 */
package cn.bw.fitzboot.enity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2018/11/30
 * @since 1.0.0
 */
@Entity
@Table(name = "t_bw_user")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Date birthday;
    private Date create_date;
}