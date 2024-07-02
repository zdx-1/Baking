package com.baking.bakingapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张德鑫
 * @since 2024-07-02
 */
@Getter
@Setter
@Accessors(chain = true)
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String imgUrl;

    private String videoUrl;

    private String content;

    private Integer type;

    private Integer viewCount;

    private Integer commentCount;

    private String brief;

    private Long categoryId;

    private Long userId;

    private Long updateBy;

    private Date createTime;

    private Date updateTime;


}
