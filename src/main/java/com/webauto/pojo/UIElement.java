package com.webauto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UIElement类型，一个UIElement对应一个页面元素
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UIElement {
    /**
     * 元素关键字
     */
    private String keyword;
    /**
     * 选择器类型
     */
    private String by;
    /**
     * 选择器的值
     */
    private String value;
}
