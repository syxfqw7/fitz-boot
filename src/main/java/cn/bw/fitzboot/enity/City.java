/**
 * FileName: City
 * Author:   jack.xue
 * Date:     2018/11/30 11:02
 * Description: city
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2018/11/30           1.0.0              描述
 */
package cn.bw.fitzboot.enity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 〈TODO〉<br>
 *
 * @author jack.xue
 * @create 2018/11/30
 * @since 1.0.0
 */
@Entity
@Table(name = "t_bw_city")
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String name;
    private String address;
}