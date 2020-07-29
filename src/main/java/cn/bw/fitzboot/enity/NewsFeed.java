/**
 * FileName: NewsFeed
 * Author:   jack.xue
 * Date:     2018/11/30 13:12
 * Description: 新闻
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
@Table(name = "t_bw_news_feed")
@Setter
@Getter
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;// 主键id
    private String ext_id;
    private Integer ext_type;
    private String dt;
    private Date tim;
    private String url;
    private String auth;
    private String title;
    private String con;
    private Integer cat;
    private String state;
    private Date upt;
    private Integer news_hot = 1;
    private Integer imp_source = 0;
    private String imp_site;
}